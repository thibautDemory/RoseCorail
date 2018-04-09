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

        for(int w=0; w<listArticle.size(); w++){
            System.out.print("idArticle n°"+w+" : "+tabStatArticles[0][w]+" : ");
            System.out.println("quantityArticle : "+tabStatArticles[1][w]);
            System.out.print("idColor n°"+w+" : "+tabStatCouleurs[0][w]+" : ");
            System.out.println("quantityColor : "+tabStatCouleurs[1][w]);
        }




        webContext.setVariable("statut",statut);

        templateEngine.process("administration/statistiques", webContext, resp.getWriter());
    }
}
