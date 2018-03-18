package hei.ProjetRoseCorail.servlets;

import hei.ProjetRoseCorail.entities.Article;
import hei.ProjetRoseCorail.entities.Couleur;
import hei.ProjetRoseCorail.managers.ArticleLibrary;
import hei.ProjetRoseCorail.managers.CouleurLibrary;
import hei.ProjetRoseCorail.managers.PossederLibrary;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/administration/ModificationArticle")
public class ModificationArticleServlet extends GenericServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        String statut=(String) req.getSession().getAttribute("statut");
        ArticleLibrary articleLibrary=ArticleLibrary.getInstance();
        CouleurLibrary couleurLibrary= CouleurLibrary.getInstance();
        PossederLibrary possederLibrary= PossederLibrary.getInstance();
        webContext.setVariable("statut",statut);

        Integer id=Integer.parseInt(req.getParameter("id"));
        Article cetArticle= articleLibrary.getArticleById(id);
        List<Couleur> lescouleursdecetarticle=possederLibrary.listCouleursPourUnArticle(id);
        List<Couleur> lescouleurs=couleurLibrary.listCouleursActives();
        List<String> lescouleursdecetarticleenString=new ArrayList<>();
        List<String> lescouleursenString=new ArrayList<>();
        for (int i=0;i<lescouleursdecetarticle.size();i++){
            lescouleursdecetarticleenString.add(lescouleursdecetarticle.get(i).getNom_couleur());
        }
        for (int i=0;i<lescouleurs.size();i++){
            lescouleursenString.add(lescouleurs.get(i).getNom_couleur());
        }


        List<Integer> unadouze= new ArrayList<Integer>();
        for (int i=1;i<13;i++){
            unadouze.add(i);
        }

        webContext.setVariable("cetarticle",cetArticle);
        webContext.setVariable("unadouze",unadouze);
        webContext.setVariable("sescouleurs",lescouleursdecetarticleenString);
        webContext.setVariable("lescouleurs",lescouleursenString);

        templateEngine.process("/administration/ModificationArticle", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //on récupère les paramètres:

    }
}
