package hei.ProjetRoseCorail.servlets;

import hei.ProjetRoseCorail.entities.Actualite;
import hei.ProjetRoseCorail.entities.Couleur;
import hei.ProjetRoseCorail.entities.ListeDesSaisons;
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
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

@MultipartConfig
@WebServlet("/administration/ajoutColoris")
public class AjoutColorisServlet extends GenericServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        String statut=(String) req.getSession().getAttribute("statut");
        ListeDesSaisons listeDesSaisons = new ListeDesSaisons();
        List<String> lessaisonsenString = new ArrayList<>();
        for (int i =0; i<listeDesSaisons.lessaisons.size();i++){
            lessaisonsenString.add(listeDesSaisons.lessaisons.get(i));
            System.out.println(lessaisonsenString.get(i));
        }
        webContext.setVariable("lessaisons",lessaisonsenString);

        webContext.setVariable("statut",statut);

        // On prépare le filtre de date du mois actuel pour la page "fragment.html"
        LocalDate maintenant=LocalDate.now();
        String anneeMoisActuelle = maintenant.toString().substring(0,7);
        webContext.setVariable("anneeMoisActuelle",anneeMoisActuelle);

        templateEngine.process("administration/ajoutcoloris", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // GET PARAMETERS
        String nom_couleur = null;
        String numero_couleur;

        String saison = null;

        nom_couleur = req.getParameter("nomColoris");
        numero_couleur = req.getParameter("numeroColoris");
        saison = req.getParameter("saisonColoris");

        File filequicontientlimage=null;
        filequicontientlimage = new File("D:\\Informatique\\Projet 100h\\RoseCorail\\src\\main\\webapp\\images\\couleurs\\"+nom_couleur.trim());
        filequicontientlimage.mkdirs(); // permet de transformer le fichier en répertoire. A noter que grace à cette méthode, on créer les dossiers qui n'existent pas dans le chemin de la ligne d'au dessus
        Part imagePart = req.getPart("imageColoris"); // on récupere  l'image du formulaire
        imagePart.write(filequicontientlimage.getAbsolutePath()+"/image.jpg"); // on écrit l'image que l'on vient de récupérer dans le répertoire précedemment créer


        // CREATE couleur
        Couleur newCouleur = new Couleur(null, nom_couleur, numero_couleur, "/RoseCorail/images/couleurs/"+nom_couleur.trim()+"/image.jpg", saison,1);
        try {
            Couleur createdCouleur = CouleurLibrary.getInstance().addCouleur(newCouleur);

            // REDIRECT TO Accueil
            resp.sendRedirect(String.format("/RoseCorail/administration/formulaire"));
        } catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();

            req.getSession().setAttribute("errorMessage", errorMessage);

            resp.sendRedirect("/RoseCorail/ajoutColoris");
        }
    }
}
