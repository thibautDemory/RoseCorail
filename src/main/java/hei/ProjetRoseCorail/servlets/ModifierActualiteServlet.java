package hei.ProjetRoseCorail.servlets;

import hei.ProjetRoseCorail.entities.*;
import hei.ProjetRoseCorail.managers.ActualiteLibrary;
import hei.ProjetRoseCorail.managers.CouleurLibrary;
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
import java.time.LocalDate;
import java.util.List;

import static java.lang.Integer.parseInt;

@MultipartConfig
@WebServlet("/administration/modifierActualite")
public class ModifierActualiteServlet extends GenericServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        String statut=(String) req.getSession().getAttribute("statut");

        int idActualite = parseInt(req.getParameter("id"));
        String idActualiteString = String.valueOf(idActualite);

        Actualite actualite = ActualiteLibrary.getInstance().getActualiteByID(idActualite);

        String titreActualite = actualite.getTitreActualite();
        String contenuActualite = actualite.getContenu();
        String imageActualite = actualite.getImageActualite();

        webContext.setVariable("titreActualite", titreActualite);
        webContext.setVariable("idActualite", idActualiteString);
        webContext.setVariable("contenuActualite", contenuActualite);
        webContext.setVariable("imageActualite", imageActualite);



        System.out.println("cette actualité va être modifié, titreActualite = "+titreActualite+"; contenuActualite = "
                +contenuActualite+"; imageActualite = "+imageActualite+"; idActualiteString = "+idActualiteString);

        webContext.setVariable("statut",statut);

        // On prépare le filtre de date du mois actuel pour la page "fragment.html"
        LocalDate maintenant=LocalDate.now();
        String anneeMoisActuelle = maintenant.toString().substring(0,7);
        webContext.setVariable("anneeMoisActuelle",anneeMoisActuelle);

        templateEngine.process("administration/modifActualite", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // GET PARAMETERS
        String titreActualite = null;
        String contenuActualite = null;
        String imageActualite = null;
        String idActualiteString=req.getParameter("idActualite");
        File filequicontientlimage=null;
        int idActualiteInt = 0;
        try {
            titreActualite = req.getParameter("titreActualite");
            contenuActualite = req.getParameter("contenuActualite");

            idActualiteInt = parseInt(idActualiteString);
            filequicontientlimage = new File("D:\\Informatique\\Projet 100h\\RoseCorail\\src\\main\\webapp\\images\\actualites\\"+titreActualite.trim());
            filequicontientlimage.mkdirs(); // permet de transformer le fichier en répertoire. A noter que grace à cette méthode, on créer les dossiers qui n'existent pas dans le chemin de la ligne d'au dessus
            Part imagePart = req.getPart("imageActualite"); // on récupere  l'image du formulaire
            imagePart.write(filequicontientlimage.getAbsolutePath()+"/image.jpg"); // on écrit l'image que l'on vient de récupérer dans le répertoire précedemment créer

        }catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();
            req.getSession().setAttribute("errorMessage", errorMessage);
            System.out.println("error1"+errorMessage);
            resp.sendRedirect("/RoseCorail/administration/modifierActualite");
        }

        System.out.println("actu id string " +idActualiteString);
        System.out.println("actu id int " +idActualiteInt);

        // CREATE Actualite
        Actualite newActualite = new Actualite(null, titreActualite, contenuActualite, "/RoseCorail/images/actualites/"+titreActualite.trim()+"/image.jpg");

        try {
            ActualiteLibrary.getInstance().deleteActualite(idActualiteInt);
            ActualiteLibrary.getInstance().addActualite(newActualite);

            // REDIRECT TO Accueil
            resp.sendRedirect(String.format("/RoseCorail/administration/formulaire"));

        } catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();
            req.getSession().setAttribute("errorMessage", errorMessage);
            resp.sendRedirect(String.format("/RoseCorail/administration/modifierActualite"));
        }
    }

}
