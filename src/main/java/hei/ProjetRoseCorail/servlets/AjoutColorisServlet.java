package hei.ProjetRoseCorail.servlets;

import hei.ProjetRoseCorail.entities.Actualite;
import hei.ProjetRoseCorail.entities.Couleur;
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

import static java.lang.Integer.parseInt;

@MultipartConfig
@WebServlet("/administration/ajoutColoris")
public class AjoutColorisServlet extends GenericServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        String statut=(String) req.getSession().getAttribute("statut");

        webContext.setVariable("statut",statut);

        templateEngine.process("administration/ajoutcoloris", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // GET PARAMETERS
        String nom_couleur = null;
        String numero_couleur;
        String image_couleur = null;
        String saison = null;

        nom_couleur = req.getParameter("nomColoris");
        numero_couleur = req.getParameter("numeroColoris");
        saison = req.getParameter("saisonColoris");

        File filequicontientlimage=null;
        filequicontientlimage = new File("C:\\workSpaceWEB\\RoseCorailGit\\src\\main\\webapp\\images\\"+nom_couleur.trim());
        filequicontientlimage.mkdirs(); // permet de transformer le fichier en répertoire. A noter que grace à cette méthode, on créer les dossiers qui n'existent pas dans le chemin de la ligne d'au dessus
        Part imagePart = req.getPart("imageColoris"); // on récupere  l'image du formulaire
        imagePart.write(filequicontientlimage.getAbsolutePath()+"/image.jpg"); // on écrit l'image que l'on vient de récupérer dans le répertoire précedemment créer


        // CREATE couleur
        Couleur newCouleur = new Couleur(null, nom_couleur, numero_couleur, "img\\"+nom_couleur.trim()+"\\image.jpg", saison);
        try {
            Couleur createdCouleur = CouleurLibrary.getInstance().addCouleur(newCouleur);

            // REDIRECT TO Accueil
            resp.sendRedirect(String.format("/accueil"));
        } catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();

            req.getSession().setAttribute("errorMessage", errorMessage);

            resp.sendRedirect("ajoutColoris");
        }
    }
}
