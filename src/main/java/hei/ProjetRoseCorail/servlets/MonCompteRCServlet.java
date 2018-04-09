package hei.ProjetRoseCorail.servlets;

import hei.ProjetRoseCorail.entities.CompteClient;
import hei.ProjetRoseCorail.entities.CompteRoseCorail;
import hei.ProjetRoseCorail.managers.CompteClientLibrary;
import hei.ProjetRoseCorail.managers.CompteRoseCorailLibrary;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Integer.parseInt;


@WebServlet("/administration/monCompteRC")
public class MonCompteRCServlet extends GenericServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        String statut=(String) req.getSession().getAttribute("statut");

        webContext.setVariable("statut",statut);

        CompteRoseCorail compteRoseCorail = CompteRoseCorailLibrary.getInstance().getCompteRoseCorailById(1);

        String email = compteRoseCorail.getEmail();
        String telephone = compteRoseCorail.getNumero_tel();

        webContext.setVariable("email", email);
        webContext.setVariable("telephone", telephone);

        templateEngine.process("/administration/monCompteRC", webContext, resp.getWriter());
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // GET PARAMETERS
        int idCompteRC = 1;
        String telephone = null;
        String email = null;

        try{
            telephone = req.getParameter("telephone");
            email = req.getParameter("email");

        }catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();
            req.getSession().setAttribute("errorMessage", errorMessage);
            resp.sendRedirect("/RoseCorail/administration/monCompteRC");
        }


        System.out.println("id = "+idCompteRC+" ; telephone = "+telephone+" ; email = "+email);

        // UPDATE CompteClient
        CompteRoseCorail compteRoseCorail = new CompteRoseCorail(idCompteRC, email, telephone);
        try {
            CompteRoseCorail updateCompteRoseCorail = CompteRoseCorailLibrary.getInstance().updateCompteRoseCorailWithoutPassword(compteRoseCorail);

            // REDIRECT TO ACCUEIL
            resp.sendRedirect(String.format("/RoseCorail/accueil"));
        } catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();

            req.getSession().setAttribute("errorMessage", errorMessage);

            resp.sendRedirect("/RoseCorail/administration/monCompteRC");
        }
    }
}
