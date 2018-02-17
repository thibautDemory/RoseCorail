package hei.ProjetRoseCorail.servlets;


import hei.ProjetRoseCorail.entities.Couleur;
import hei.ProjetRoseCorail.managers.CouleurLibrary;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Integer.parseInt;

@WebServlet("/administration/modifierColoris")
public class ModifierColorisServlet extends GenericServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        String statut=(String) req.getSession().getAttribute("statut");

        int idColoris = parseInt(req.getParameter("id"));
        Couleur couleur = CouleurLibrary.getInstance().getCouleurByID(idColoris);

        String nomColoris = couleur.getNom_couleur();
        String numeroColoris = couleur.getNumero_couleur();
        String imageColoris = couleur.getImage_couleur();
        String saisonColoris = couleur.getSaison();

        webContext.setVariable("nomColoris", nomColoris);
        webContext.setVariable("numeroColoris", numeroColoris);
        webContext.setVariable("imageColoris", imageColoris);
        webContext.setVariable("saisonColoris", saisonColoris);

        System.out.println("cette couleur va être modifié, nom = "+nomColoris+"; numeroColoris = "+numeroColoris+"; imageColoris = "+imageColoris+"; saison = "+saisonColoris);

        webContext.setVariable("statut",statut);

        templateEngine.process("administration/ajoutcoloris", webContext, resp.getWriter());
    }
}
