package hei.ProjetRoseCorail.servlets;

import hei.ProjetRoseCorail.entities.Actualite;
import hei.ProjetRoseCorail.managers.ActualiteLibrary;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@MultipartConfig
@WebServlet("/administration/ajoutActualite")

public class AjoutActualiteServlet extends GenericServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        String statut=(String) req.getSession().getAttribute("statut");

        webContext.setVariable("statut",statut);

        templateEngine.process("ajoutActualite", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // GET PARAMETERS
        String titre = null;
        String contenu = null;
        String image_actualite = null;

        titre = req.getParameter("titreActualite");
        contenu = req.getParameter("contenuActualite");

        File filequicontientlimage=null;
        filequicontientlimage = new File("C:\\workSpaceWEB\\RoseCorailGit\\src\\main\\webapp\\images\\"+titre.trim());
        filequicontientlimage.mkdirs(); // permet de transformer le fichier en répertoire. A noter que grace à cette méthode, on créer les dossiers qui n'existent pas dans le chemin de la ligne d'au dessus
        Part imagePart = req.getPart("imageActualite"); // on récupere  l'image du formulaire
        imagePart.write(filequicontientlimage.getAbsolutePath()+"/image.jpg"); // on écrit l'image que l'on vient de récupérer dans le répertoire précedemment créer


        // CREATE Actualité
        Actualite newActualite = new Actualite(null, titre, contenu, "img\\"+titre.trim()+"\\image.jpg");
        try {
            Actualite createdActualite = ActualiteLibrary.getInstance().addActualite(newActualite);

            // REDIRECT TO DETAIL Actualité
            resp.sendRedirect(String.format("accueil"));
        } catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();

            req.getSession().setAttribute("errorMessage", errorMessage);

            resp.sendRedirect("ajoutActualite");
        }
    }
}
