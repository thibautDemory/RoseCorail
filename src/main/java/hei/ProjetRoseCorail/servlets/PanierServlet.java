package hei.ProjetRoseCorail.servlets;

import hei.ProjetRoseCorail.entities.Article;
import hei.ProjetRoseCorail.entities.Couleur;
import hei.ProjetRoseCorail.entities.LigneDevis;
import hei.ProjetRoseCorail.entities.LignePanier;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/panier")
public class PanierServlet extends GenericServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        ArticleLibrary articleLibrary=ArticleLibrary.getInstance();
        CouleurLibrary couleurLibrary=CouleurLibrary.getInstance();
        String statut=(String) req.getSession().getAttribute("statut");
        Integer idPanier=0;
        List<LignePanier> lesArticlesCouleurEtQuantites= new ArrayList<>();
        if (statut==null||"".equals(statut)){
            statut="visiteur";
        }else{
            Integer idClient= (Integer) req.getSession().getAttribute("idClient");
            idPanier=DevisLibrary.getInstance().getPanierClient(idClient).getId_devis();
            String nom=req.getSession().getAttribute("nom").toString();
            String prenom=req.getSession().getAttribute("prenom").toString();
            webContext.setVariable("prenom",prenom);
            webContext.setVariable("nom",nom);
        }
        System.out.println(idPanier);

        //affichage des produits du panier
        if (idPanier!=0){
            List<LigneDevis> leslignesdevis = LigneDevisLibrary.getInstance().listLignesDevisPourUnDevis(idPanier);
            System.out.println(leslignesdevis.get(0));

            for (int i =0;i<leslignesdevis.size();i++){
                Article article=articleLibrary.getArticleById(leslignesdevis.get(i).getId_article());
                Couleur couleur=couleurLibrary.getCouleurByID(leslignesdevis.get(i).getId_couleur());
                lesArticlesCouleurEtQuantites.add(new LignePanier(
                        article.getImage(),
                        article.getReference(),
                        article.getNom_article(),
                        article.getDimension(),
                        couleur.getImage_couleur(),
                        leslignesdevis.get(i).getQuantite()));
            }
            webContext.setVariable("lesarticlesdupanier",lesArticlesCouleurEtQuantites);
        }
        System.out.println(statut);
        webContext.setVariable("statut",statut);

        templateEngine.process("panier", webContext, resp.getWriter());
    }
}
