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
        // Le tableau est construit de la façon suivante :
        // ligne n°0 : idArticle
        // Ligne n°1 : scoreArticle
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
        for(int w=0; w<5; w++){
            System.out.print("idArticle n°"+w+" : "+tabStatTop5Articles[0][w]+" : ");
            System.out.println("quantityArticle : "+tabStatTop5Articles[1][w]);
            System.out.print("idColor n°"+w+" : "+tabStatTop5Couleurs[0][w]+" : ");
            System.out.println("quantityColor : "+tabStatTop5Couleurs[1][w]);
        }

        // On affecte les valeurs du top5 Articles et top5 Couleurs à des variables
        // pour pouvoir les envoyer dans le HTML avec Thymeleaf

        // On crée les variables du TOP5 articles
        String nomArticle1 = "rien";
        String nomArticle2 = "rien";
        String nomArticle3 = "rien";
        String nomArticle4 = "rien";
        String nomArticle5 = "rien";
        int scoreArticle1 = 0;
        int scoreArticle2 = 0;
        int scoreArticle3 = 0;
        int scoreArticle4 = 0;
        int scoreArticle5 = 0;

        //Si on a moins de 5 articles dans les stat
        if(listArticle.size()<5){
            if(listArticle.size() <= 0) {

            }else if(listArticle.size() == 1){
                nomArticle1 = articleLibrary.getArticleById(tabStatTop5Articles[0][0]).getNom_article();
                scoreArticle1 = tabStatTop5Articles[1][0];
            }else if(listArticle.size() == 2){
                nomArticle1 = articleLibrary.getArticleById(tabStatTop5Articles[0][0]).getNom_article();
                nomArticle2 = articleLibrary.getArticleById(tabStatTop5Articles[0][1]).getNom_article();
                scoreArticle1 = tabStatTop5Articles[1][0];
                scoreArticle2 = tabStatTop5Articles[1][1];
            }else if(listArticle.size() == 3){
                nomArticle1 = articleLibrary.getArticleById(tabStatTop5Articles[0][0]).getNom_article();
                nomArticle2 = articleLibrary.getArticleById(tabStatTop5Articles[0][1]).getNom_article();
                nomArticle3 = articleLibrary.getArticleById(tabStatTop5Articles[0][2]).getNom_article();
                scoreArticle1 = tabStatTop5Articles[1][0];
                scoreArticle2 = tabStatTop5Articles[1][1];
                scoreArticle3 = tabStatTop5Articles[1][2];
            }else if(listArticle.size() == 4){
                nomArticle1 = articleLibrary.getArticleById(tabStatTop5Articles[0][0]).getNom_article();
                nomArticle2 = articleLibrary.getArticleById(tabStatTop5Articles[0][1]).getNom_article();
                nomArticle3 = articleLibrary.getArticleById(tabStatTop5Articles[0][2]).getNom_article();
                nomArticle4 = articleLibrary.getArticleById(tabStatTop5Articles[0][3]).getNom_article();
                scoreArticle1 = tabStatTop5Articles[1][0];
                scoreArticle2 = tabStatTop5Articles[1][1];
                scoreArticle3 = tabStatTop5Articles[1][2];
                scoreArticle4 = tabStatTop5Articles[1][3];
            }
        // Si on a 5 (ou plus) articles dans les stat
        }else {
            nomArticle1 = articleLibrary.getArticleById(tabStatTop5Articles[0][0]).getNom_article();
            nomArticle2 = articleLibrary.getArticleById(tabStatTop5Articles[0][1]).getNom_article();
            nomArticle3 = articleLibrary.getArticleById(tabStatTop5Articles[0][2]).getNom_article();
            nomArticle4 = articleLibrary.getArticleById(tabStatTop5Articles[0][3]).getNom_article();
            nomArticle5 = articleLibrary.getArticleById(tabStatTop5Articles[0][4]).getNom_article();
            scoreArticle1 = tabStatTop5Articles[1][0];
            scoreArticle2 = tabStatTop5Articles[1][1];
            scoreArticle3 = tabStatTop5Articles[1][2];
            scoreArticle4 = tabStatTop5Articles[1][3];
            scoreArticle5 = tabStatTop5Articles[1][4];
        }

        // On crée les variables du TOP5 couleur
        String nomCouleur1 = "rien";
        String nomCouleur2 = "rien";
        String nomCouleur3 = "rien";
        String nomCouleur4 = "rien";
        String nomCouleur5 = "rien";

        int scoreCouleur1 = 0;
        int scoreCouleur2 = 0;
        int scoreCouleur3 = 0;
        int scoreCouleur4 = 0;
        int scoreCouleur5 = 0;

        //Si on a moins de 5 couleurs dans les stat
        if(listCouleurs.size()<5){
            if(listArticle.size() <= 0) {

            }else if(listArticle.size() == 1){
                nomCouleur1 = couleurLibrary.getCouleurByID(tabStatCouleurs[0][1]).getNom_couleur();
                scoreCouleur1 = tabStatTop5Couleurs[1][0];
            }else if(listArticle.size() == 2){
                nomCouleur1 = couleurLibrary.getCouleurByID(tabStatTop5Couleurs[0][0]).getNom_couleur();
                nomCouleur2 = couleurLibrary.getCouleurByID(tabStatTop5Couleurs[0][1]).getNom_couleur();
                scoreCouleur1 = tabStatTop5Couleurs[1][0];
                scoreCouleur2 = tabStatTop5Couleurs[1][1];
            }else if(listArticle.size() == 3){
                nomCouleur1 = couleurLibrary.getCouleurByID(tabStatTop5Couleurs[0][0]).getNom_couleur();
                nomCouleur2 = couleurLibrary.getCouleurByID(tabStatTop5Couleurs[0][1]).getNom_couleur();
                nomCouleur3 = couleurLibrary.getCouleurByID(tabStatTop5Couleurs[0][2]).getNom_couleur();
                scoreCouleur1 = tabStatTop5Couleurs[1][0];
                scoreCouleur2 = tabStatTop5Couleurs[1][1];
                scoreCouleur3 = tabStatTop5Couleurs[1][2];
            }else if(listArticle.size() == 4){
                nomCouleur1 = couleurLibrary.getCouleurByID(tabStatTop5Couleurs[0][0]).getNom_couleur();
                nomCouleur2 = couleurLibrary.getCouleurByID(tabStatTop5Couleurs[0][1]).getNom_couleur();
                nomCouleur3 = couleurLibrary.getCouleurByID(tabStatTop5Couleurs[0][2]).getNom_couleur();
                nomCouleur4 = couleurLibrary.getCouleurByID(tabStatTop5Couleurs[0][3]).getNom_couleur();
                scoreCouleur1 = tabStatTop5Couleurs[1][0];
                scoreCouleur2 = tabStatTop5Couleurs[1][1];
                scoreCouleur3 = tabStatTop5Couleurs[1][2];
                scoreCouleur4 = tabStatTop5Couleurs[1][3];
            }
        //Si on a 5 (ou plus) couleurs dans les stat
        }else {
            nomCouleur1 = couleurLibrary.getCouleurByID(tabStatTop5Couleurs[0][0]).getNom_couleur();
            nomCouleur2 = couleurLibrary.getCouleurByID(tabStatTop5Couleurs[0][1]).getNom_couleur();
            nomCouleur3 = couleurLibrary.getCouleurByID(tabStatTop5Couleurs[0][2]).getNom_couleur();
            nomCouleur4 = couleurLibrary.getCouleurByID(tabStatTop5Couleurs[0][3]).getNom_couleur();
            nomCouleur5 = couleurLibrary.getCouleurByID(tabStatTop5Couleurs[0][4]).getNom_couleur();

            scoreCouleur1 = tabStatTop5Articles[1][0];
            scoreCouleur2 = tabStatTop5Articles[1][1];
            scoreCouleur3 = tabStatTop5Articles[1][2];
            scoreCouleur4 = tabStatTop5Articles[1][3];
            scoreCouleur5 = tabStatTop5Articles[1][4];
        }

        webContext.setVariable("nomArticle1",nomArticle1);
        webContext.setVariable("nomArticle2",nomArticle2);
        webContext.setVariable("nomArticle3",nomArticle3);
        webContext.setVariable("nomArticle4",nomArticle4);
        webContext.setVariable("nomArticle5",nomArticle5);
        webContext.setVariable("scoreArticle1",scoreArticle1);
        webContext.setVariable("scoreArticle2",scoreArticle2);
        webContext.setVariable("scoreArticle3",scoreArticle3);
        webContext.setVariable("scoreArticle4",scoreArticle4);
        webContext.setVariable("scoreArticle5",scoreArticle5);

        webContext.setVariable("nomCouleur1",nomCouleur1);
        webContext.setVariable("nomCouleur2",nomCouleur2);
        webContext.setVariable("nomCouleur3",nomCouleur3);
        webContext.setVariable("nomCouleur4",nomCouleur4);
        webContext.setVariable("nomCouleur5",nomCouleur5);
        webContext.setVariable("scoreCouleur1",scoreCouleur1);
        webContext.setVariable("scoreCouleur2",scoreCouleur2);
        webContext.setVariable("scoreCouleur3",scoreCouleur3);
        webContext.setVariable("scoreCouleur4",scoreCouleur4);
        webContext.setVariable("scoreCouleur5",scoreCouleur5);


        webContext.setVariable("statut",statut);

        templateEngine.process("administration/statistiques", webContext, resp.getWriter());
    }
}
