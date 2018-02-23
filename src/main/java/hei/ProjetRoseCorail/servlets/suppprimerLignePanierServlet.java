package hei.ProjetRoseCorail.servlets;

import hei.ProjetRoseCorail.managers.LigneDevisLibrary;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/supprimerLignePanier")
public class suppprimerLignePanierServlet extends GenericServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        String idasupprimer= req.getParameter("id");
        LigneDevisLibrary.getInstance().deleteLigneDevis(Integer.parseInt(idasupprimer));
        resp.sendRedirect("/panier");
    }
}
