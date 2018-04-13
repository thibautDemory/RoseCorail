package hei.ProjetRoseCorail.servlets;

import hei.ProjetRoseCorail.managers.CouleurLibrary;
import hei.ProjetRoseCorail.managers.PanelColorisLibrary;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;

import static java.lang.Integer.parseInt;
@WebServlet("/administration/SupprimerPanelColoris")
public class SupprimerPanelColorisServlet extends GenericServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PanelColorisLibrary panelColorisLibrary=PanelColorisLibrary.getInstance();
        int idPanelColoris = parseInt(req.getParameter("id"));
        String saisondupanel=panelColorisLibrary.getPanelColorisById(idPanelColoris).getSaison();
        panelColorisLibrary.deletePanelColoris(idPanelColoris);

        System.out.println("ce panel colrois  a bien été supprimer");
        resp.sendRedirect("/RoseCorail/lesColoris?Modification=active&saison="+saisondupanel);
    }
}
