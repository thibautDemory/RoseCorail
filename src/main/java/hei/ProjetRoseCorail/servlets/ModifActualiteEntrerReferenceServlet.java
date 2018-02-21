package hei.ProjetRoseCorail.servlets;

import hei.ProjetRoseCorail.entities.Actualite;
import hei.ProjetRoseCorail.entities.Couleur;
import hei.ProjetRoseCorail.managers.ActualiteLibrary;
import hei.ProjetRoseCorail.managers.CouleurLibrary;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/administration/modifActualiteEntrerReference")
public class ModifActualiteEntrerReferenceServlet extends GenericServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        List<Actualite> listeActualite= ActualiteLibrary.getInstance().listActualites();
        String statut=(String) req.getSession().getAttribute("statut");

        webContext.setVariable("statut",statut);
        webContext.setVariable("listeActualite",listeActualite);

        templateEngine.process("administration/modifActualiteEntrerReference", webContext, resp.getWriter());
    }

}