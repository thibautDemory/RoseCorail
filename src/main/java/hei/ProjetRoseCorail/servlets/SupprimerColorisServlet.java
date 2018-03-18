package hei.ProjetRoseCorail.servlets;

import hei.ProjetRoseCorail.managers.CouleurLibrary;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Integer.parseInt;

@WebServlet("/administration/supprimerColoris")
public class SupprimerColorisServlet extends GenericServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idColoris = parseInt(req.getParameter("id"));
        CouleurLibrary.getInstance().rendreinactivecouleur(idColoris);
        System.out.println("cette couleur a bien été supprimer");
        resp.sendRedirect("/administration/formulaire");
    }
}
