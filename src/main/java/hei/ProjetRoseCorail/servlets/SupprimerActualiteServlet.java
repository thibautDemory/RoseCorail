package hei.ProjetRoseCorail.servlets;

import hei.ProjetRoseCorail.managers.ActualiteLibrary;
import hei.ProjetRoseCorail.managers.CouleurLibrary;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Integer.parseInt;

@WebServlet("/administration/supprimerActualite")
public class SupprimerActualiteServlet extends GenericServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idActu = parseInt(req.getParameter("id"));
        ActualiteLibrary.getInstance().deleteActualite(idActu);
        System.out.println("cette actu a bien été supprimer");
        resp.sendRedirect("/administration/formulaire");
    }
}
