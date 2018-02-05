package hei.ProjetRoseCorail.servlets;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/connexion")
public class ConnexionServlet extends GenericServlet{

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
        req.getSession().setAttribute("utilisateurConnecte", req.getParameter("email"));
        req.getSession().setAttribute("motdepasse",req.getParameter("pwd"));



        String utilisateur= (String) req.getSession().getAttribute("utilisateurConnecte");
        req.getSession().setAttribute("statut","visiteur");

        if (utilisateur.equals("beatrice@hotmail.fr")){
            System.out.println("beatrice");
            req.getSession().setAttribute("statut","admin");
        }
        if(utilisateur.equals("thibaut@hotmail.fr")){
            req.getSession().setAttribute("statut","client");
        }

        resp.sendRedirect("accueil");
    }
}
