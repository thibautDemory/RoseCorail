package hei.ProjetRoseCorail.servlets;

import hei.ProjetRoseCorail.entities.Article;
import hei.ProjetRoseCorail.entities.Couleur;
import hei.ProjetRoseCorail.entities.LigneDevis;
import hei.ProjetRoseCorail.entities.LignePanier;
import hei.ProjetRoseCorail.managers.ArticleLibrary;
import hei.ProjetRoseCorail.managers.CouleurLibrary;
import hei.ProjetRoseCorail.managers.LigneDevisLibrary;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/AffichageDevis")
public class AffichageDevisServlet extends GenericServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArticleLibrary articleLibrary=ArticleLibrary.getInstance();
        CouleurLibrary couleurLibrary=CouleurLibrary.getInstance();
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        String statut=(String) req.getSession().getAttribute("statut");

        if (statut==null||"".equals(statut)){
            statut="visiteur";
        }else{
            String nom=req.getSession().getAttribute("nom").toString();
            String prenom=req.getSession().getAttribute("prenom").toString();
            webContext.setVariable("prenom",prenom);
            webContext.setVariable("nom",nom);
        }
//On récupère l'id du devis que l'on veut afficher
        Integer idDevis= Integer.parseInt(req.getParameter("id"));
        //On récupère les lignes devis correspondantes:
        List<LigneDevis> leslignesdevis = LigneDevisLibrary.getInstance().listLignesDevisPourUnDevis(idDevis);
        //On créer une liste qui contiendra les lignes affichables
        List<LignePanier> leslignesdudevisaafficher=new ArrayList<>();
        //On récupère toutes les caractéristiques que l'on souhaite
        for (int i =0;i<leslignesdevis.size();i++){
            Article article=articleLibrary.getArticleById(leslignesdevis.get(i).getId_article());
            Couleur couleur=couleurLibrary.getCouleurByID(leslignesdevis.get(i).getId_couleur());
            leslignesdudevisaafficher.add(new LignePanier(
                    article.getImage(),
                    article.getReference(),
                    article.getNom_article(),
                    article.getDimension(),
                    couleur.getImage_couleur(),
                    leslignesdevis.get(i).getQuantite(),
                    article.getLot_vente(),
                    article.getPrix(),
                    leslignesdevis.get(i).getId_ligne_devis()));
        }
        if(leslignesdevis.size()==0){
            webContext.setVariable("paniervide",true);
        }
        System.out.println(statut);
        webContext.setVariable("leslignesaafficher",leslignesdudevisaafficher);
        webContext.setVariable("statut",statut);

        templateEngine.process("AffichageDevis", webContext, resp.getWriter());
    }
}
