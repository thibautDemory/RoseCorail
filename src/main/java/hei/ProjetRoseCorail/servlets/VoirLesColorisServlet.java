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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/administration/voirlescoloris")
public class VoirLesColorisServlet extends GenericServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        String statut=(String) req.getSession().getAttribute("statut");
        webContext.setVariable("statut",statut);
        List<Couleur> lescouleurs;
        CouleurLibrary couleurLibrary=CouleurLibrary.getInstance();
        lescouleurs=couleurLibrary.listCouleursActives();
        webContext.setVariable("lescouleurs",lescouleurs);

        // On prépare le filtre de date du mois actuel pour la page "fragment.html"
        LocalDate maintenant=LocalDate.now();
        String anneeMoisActuelle = maintenant.toString().substring(0,7);
        webContext.setVariable("anneeMoisActuelle",anneeMoisActuelle);

        templateEngine.process("/administration/voirlescoloris", webContext, resp.getWriter());
    }
}
