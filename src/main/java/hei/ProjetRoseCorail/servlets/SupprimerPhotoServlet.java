package hei.ProjetRoseCorail.servlets;

import hei.ProjetRoseCorail.managers.PhotoPresentationLibrary;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Integer.parseInt;

@WebServlet("/administration/SupprimerPhoto")
public class SupprimerPhotoServlet  extends GenericServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idPhotoaSuppr = parseInt(req.getParameter("id"));
        PhotoPresentationLibrary.getInstance().deletePhotoPresentation(idPhotoaSuppr);
        System.out.println("cette photo de présentation a bien été supprimer");
        resp.sendRedirect("/RoseCorail/administration/ModifierPhotosDePresentation");
    }
}
