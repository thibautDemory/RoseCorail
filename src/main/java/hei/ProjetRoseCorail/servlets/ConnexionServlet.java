package hei.ProjetRoseCorail.servlets;

import hei.ProjetRoseCorail.dao.impl.CompteClientDaoImpl;
import hei.ProjetRoseCorail.entities.CompteClient;
import hei.ProjetRoseCorail.managers.CompteClientLibrary;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/connexion")
public class ConnexionServlet extends GenericServlet{
 CompteClientLibrary compteClientLibrary = CompteClientLibrary.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        String statut=(String) req.getSession().getAttribute("statut");
        if (statut==null||"".equals(statut)){
            statut="visiteur";
        }
        webContext.setVariable("statut",statut);

        templateEngine.process("connexion", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String adresseEmailRentree=req.getParameter("email");
        String motDePasseRentree=req.getParameter("pwd");
        CompteClient client= compteClientLibrary.getCompteClientByMail(adresseEmailRentree);
        if (client.getMdp().equals(motDePasseRentree)){
            req.getSession().setAttribute("idClient",client.getId_compte_client());
            req.getSession().setAttribute("nomClient",client.getNom_gerant());
            req.getSession().setAttribute("prenomClient",client.getPrenom_gerant());
            req.getSession().setAttribute("statut","client");
            resp.sendRedirect("accueil");
        }else{
                resp.sendRedirect("connexion");
        }

    }
}
