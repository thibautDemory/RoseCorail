package hei.ProjetRoseCorail.servlets;

import hei.ProjetRoseCorail.entities.CompteClient;
import hei.ProjetRoseCorail.managers.CompteClientLibrary;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/administration/voirlesclients")
public class VoirLesClientsServlet extends GenericServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        String statut=(String) req.getSession().getAttribute("statut");
        webContext.setVariable("statut",statut);
        // On prépare le filtre de date du mois actuel pour la page "fragment.html"
        LocalDate maintenant=LocalDate.now();
        String anneeMoisActuelle = maintenant.toString().substring(0,7);
        webContext.setVariable("anneeMoisActuelle",anneeMoisActuelle);

        List<CompteClient> lesclients= CompteClientLibrary.getInstance().listComptesClients();
        webContext.setVariable("lesclients",lesclients);

        templateEngine.process("/administration/voirlesclients", webContext, resp.getWriter());

    }
}
