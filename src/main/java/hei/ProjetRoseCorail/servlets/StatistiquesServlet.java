package hei.ProjetRoseCorail.servlets;

import hei.ProjetRoseCorail.entities.Article;
import hei.ProjetRoseCorail.entities.Couleur;
import hei.ProjetRoseCorail.entities.Devis;
import hei.ProjetRoseCorail.entities.LigneDevis;
import hei.ProjetRoseCorail.managers.ArticleLibrary;
import hei.ProjetRoseCorail.managers.CouleurLibrary;
import hei.ProjetRoseCorail.managers.DevisLibrary;
import hei.ProjetRoseCorail.managers.LigneDevisLibrary;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/administration/statistiques")
public class StatistiquesServlet extends GenericServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        String statut=(String) req.getSession().getAttribute("statut");

        CouleurLibrary couleurLibrary = CouleurLibrary.getInstance();
        ArticleLibrary articleLibrary = ArticleLibrary.getInstance();
        LigneDevisLibrary ligneDevisLibrary = LigneDevisLibrary.getInstance();
        DevisLibrary devisLibrary = DevisLibrary.getInstance();
        List<Devis> listDevis = devisLibrary.listlesdevis();

        // on crée un tableau de int à deux dim, une dim pour les id des articles et une dim pour la quantité commandée
        // Tableau de stat pour les articles
        List<Article> listArticle = articleLibrary.listarticles();
        int tabStatArticles[][] = new int[2][listArticle.size()];
        for(int a=0; a<listArticle.size(); a++){
            Article article = listArticle.get(a);
            tabStatArticles[0][a] = article.getId_article();
            tabStatArticles[1][a] = 0;
        }
        // on crée un tableau de int à deux dim, une dim pour les id des articles et une dim pour la quantité commandée
        // Tableau de stat pour les couleurs
        List<Couleur> listCouleurs = couleurLibrary.listCouleursActives();
        int tabStatCouleurs[][] = new int[2][listCouleurs.size()];
        for(int a=0; a<listCouleurs.size(); a++){
            Couleur couleur = listCouleurs.get(a);
            tabStatCouleurs[0][a] = couleur.getId_couleur();
            tabStatCouleurs[1][a] = 0;
        }

        // On prépare le filtre de date du mois actuel
        LocalDate maintenant=LocalDate.now();
        String maintenantLong = maintenant.toString();
        String maintenantCut = maintenantLong.substring(0,7);

        // ici on remplit la 2ieme dim du tab crée précédemment
        for(int i=0; i<listDevis.size(); i++){
            Devis devis = listDevis.get(i);
            String dateLong = devis.getDate().toString();
            String dateCut = dateLong.substring(0,7);
            if(dateCut.equals(maintenantCut)){
                int idDevis = devis.getId_devis();
                List<LigneDevis> toutesLesLignesDuDevis = ligneDevisLibrary.listLignesDevisPourUnDevis(idDevis);
                for(int j=0; j<toutesLesLignesDuDevis.size(); j++){
                    LigneDevis ligneDevis = toutesLesLignesDuDevis.get(j);
                    int idArticle = ligneDevis.getId_article();
                    int idCouleur = ligneDevis.getId_couleur();
                    for(int z=0; z<listArticle.size(); z++){
                        if(idArticle == tabStatArticles[0][z]){
                            tabStatArticles[1][z] += ligneDevis.getQuantite();
                        }
                    }
                    for(int z=0; z<listCouleurs.size(); z++){
                        if(idCouleur == tabStatCouleurs[0][z]){
                            tabStatCouleurs[1][z] += ligneDevis.getQuantite();
                        }
                    }
                }
            }
        }

        // Affichage TEST des 2 tableaux
        for(int w=0; w<listArticle.size(); w++){
            System.out.print("idArticle n°"+w+" : "+tabStatArticles[0][w]+" : ");
            System.out.println("quantityArticle : "+tabStatArticles[1][w]);
            System.out.print("idColor n°"+w+" : "+tabStatCouleurs[0][w]+" : ");
            System.out.println("quantityColor : "+tabStatCouleurs[1][w]);
        }

        // On trie et on garde les 5 articles les plus vendus
        int tabStatTop5Articles[][] = new int[2][5];
        for(int d=0; d<listArticle.size()-1; d++){
            int aux = d;
            int auxQteArticle = tabStatArticles[1][aux];
            int auxIdArticle = tabStatArticles[0][aux];
            for(int c=d+1; c<listArticle.size(); c++){
                if(tabStatArticles[1][aux]<tabStatArticles[1][c]){
                    aux = c;
                    auxQteArticle = tabStatArticles[1][aux];
                    auxIdArticle = tabStatArticles[0][aux];
                }
            }
            tabStatArticles[0][aux] = tabStatArticles[0][d];
            tabStatArticles[1][aux] = tabStatArticles[1][d];
            tabStatArticles[0][d] = auxIdArticle;
            tabStatArticles[1][d] = auxQteArticle;
        }

        if(listArticle.size()<5){
            for(int e=0; e<listArticle.size(); e++){
                tabStatTop5Articles[0][e] = tabStatArticles[0][e];
                tabStatTop5Articles[1][e] = tabStatArticles[1][e];
            }
        }else{
            for(int e=0; e<5; e++){
                tabStatTop5Articles[0][e] = tabStatArticles[0][e];
                tabStatTop5Articles[1][e] = tabStatArticles[1][e];
            }
        }

        // On trie et on garde les 5 couleurs les plus vendues
        int tabStatTop5Couleurs[][] = new int[2][5];
        for(int d=0; d<listCouleurs.size()-1; d++){
            int aux = d;
            int auxQteCouleur = tabStatCouleurs[1][aux];
            int auxIdCouleur = tabStatCouleurs[0][aux];
            for(int c=d+1; c<listCouleurs.size(); c++){
                if(tabStatCouleurs[1][aux]<tabStatCouleurs[1][c]){
                    aux = c;
                    auxQteCouleur = tabStatCouleurs[1][aux];
                    auxIdCouleur = tabStatCouleurs[0][aux];
                }
            }
            tabStatCouleurs[0][aux] = tabStatCouleurs[0][d];
            tabStatCouleurs[1][aux] = tabStatCouleurs[1][d];
            tabStatCouleurs[0][d] = auxIdCouleur;
            tabStatCouleurs[1][d] = auxQteCouleur;
        }

        if(listCouleurs.size()<5){
            for(int e=0; e<listCouleurs.size(); e++){
                tabStatTop5Couleurs[0][e] = tabStatCouleurs[0][e];
                tabStatTop5Couleurs[1][e] = tabStatCouleurs[1][e];
            }
        }else{
            for(int e=0; e<5; e++){
                tabStatTop5Couleurs[0][e] = tabStatCouleurs[0][e];
                tabStatTop5Couleurs[1][e] = tabStatCouleurs[1][e];
            }
        }


        // Affichage TEST des 2 tableaux
        System.out.println();
        System.out.println("TRIE");
        for(int w=0; w<listArticle.size(); w++){
            System.out.print("idArticle n°"+w+" : "+tabStatTop5Articles[0][w]+" : ");
            System.out.println("quantityArticle : "+tabStatTop5Articles[1][w]);
            System.out.print("idColor n°"+w+" : "+tabStatCouleurs[0][w]+" : ");
            System.out.println("quantityColor : "+tabStatCouleurs[1][w]);
        }


        // On affecte les valeurs au HTML




        webContext.setVariable("statut",statut);

        templateEngine.process("administration/statistiques", webContext, resp.getWriter());
    }
}
