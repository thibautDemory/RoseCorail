package hei.ProjetRoseCorail.servlets;

import hei.ProjetRoseCorail.entities.Article;
import hei.ProjetRoseCorail.entities.Couleur;
import hei.ProjetRoseCorail.managers.ArticleLibrary;
import hei.ProjetRoseCorail.managers.CouleurLibrary;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/lesPlats")
public class LesPlatsServlet extends GenericServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        String statut=(String) req.getSession().getAttribute("statut");

        ArticleLibrary articleLibrary=ArticleLibrary.getInstance();
        List<Article> platsacake=articleLibrary.listPlatsPlatACake();
        List<Article> platsAfromage=articleLibrary.listPlatsPlatAFromage();
        List<Article> coupelles=articleLibrary.listPlatsCoupelle();
        List<Couleur> couleurs= CouleurLibrary.getInstance().listCouleurs();

        if (statut==null||"".equals(statut)){
            statut="visiteur";
        }else{
            String nom=req.getSession().getAttribute("nom").toString();
            String prenom=req.getSession().getAttribute("prenom").toString();
            webContext.setVariable("prenom",prenom);
            webContext.setVariable("nom",nom);
        }
        webContext.setVariable("platsacake",platsacake);
        webContext.setVariable("platsAfromage",platsAfromage);
        webContext.setVariable("coupelles",coupelles);
        webContext.setVariable("couleurs",couleurs);

        System.out.println(statut);
        webContext.setVariable("statut",statut);

        templateEngine.process("lesPlats", webContext, resp.getWriter());
    }
}
