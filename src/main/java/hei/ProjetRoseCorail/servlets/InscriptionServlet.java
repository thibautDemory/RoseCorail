package hei.ProjetRoseCorail.servlets;

import hei.ProjetRoseCorail.entities.Actualite;
import hei.ProjetRoseCorail.entities.CompteClient;
import hei.ProjetRoseCorail.managers.ActualiteLibrary;
import hei.ProjetRoseCorail.managers.CompteClientLibrary;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@WebServlet("/inscription")
public class InscriptionServlet extends GenericServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        String statut=(String) req.getSession().getAttribute("statut");
        if (statut==null||"".equals(statut)){
            statut="visiteur";
        }
        webContext.setVariable("statut",statut);

        templateEngine.process("inscription", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // GET PARAMETERS
        String email = null;
        String nom_boutique = null;
        String nom_gerant = null;
        String prenom_gerant = null;
        String adresse = null;
        String ville = null;
        String code_postal = null;
        String mdp = null;
        String numero_tel = null;
        String num_tva = null;
        String site_internet = null;
        String description_activite = null;

        email = req.getParameter("email");
        nom_boutique = req.getParameter("nom_boutique");
        nom_gerant = req.getParameter("nom_gerant");
        prenom_gerant = req.getParameter("prenom_gerant");
        adresse = req.getParameter("adresse");
        ville = req.getParameter("ville");
        code_postal = req.getParameter("code_postal");
        mdp = req.getParameter("mdp");
        numero_tel = req.getParameter("numero_tel");
        num_tva = req.getParameter("num_tva");
        site_internet = req.getParameter("site_internet");
        description_activite = req.getParameter("description_activite");

        // CREATE CompteClient
        CompteClient newCompteClient = new CompteClient(null, email, nom_boutique, nom_gerant, prenom_gerant,
                adresse, ville, code_postal, mdp, numero_tel, num_tva, site_internet, description_activite,123456789);
        try {
            CompteClient createdCompteClient = CompteClientLibrary.getInstance().addCompteClient(newCompteClient);

            // REDIRECT TO Compteclient
            resp.sendRedirect("monCompteClient");
        } catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();

            req.getSession().setAttribute("errorMessage", errorMessage);

            resp.sendRedirect("inscription");
        }
    }
}
