package hei.ProjetRoseCorail.servlets;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/prive")
public class PriveServlet extends GenericServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        String identifiantUtilisateurConnecte = (String) req.getSession().getAttribute("utilisateurConnecte");
        String motdepasse= (String) req.getSession().getAttribute("motdepasse");
        webContext.setVariable("nom",identifiantUtilisateurConnecte);
        webContext.setVariable("mdp",motdepasse);


        templateEngine.process("prive", webContext, resp.getWriter());

    }
}
