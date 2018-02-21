package hei.ProjetRoseCorail.servlets;


import hei.ProjetRoseCorail.entities.Article;
import hei.ProjetRoseCorail.entities.Couleur;
import hei.ProjetRoseCorail.entities.LigneDevis;
import hei.ProjetRoseCorail.entities.Posseder;
import hei.ProjetRoseCorail.managers.CouleurLibrary;
import hei.ProjetRoseCorail.managers.LigneDevisLibrary;
import hei.ProjetRoseCorail.managers.PossederLibrary;
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
import java.util.List;

import static java.lang.Integer.parseInt;

@MultipartConfig
@WebServlet("/administration/modifierColoris")
public class ModifierColorisServlet extends GenericServlet{
    PossederLibrary possederLibrary=PossederLibrary.getInstance();
    LigneDevisLibrary ligneDevisLibrary=LigneDevisLibrary.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        String statut=(String) req.getSession().getAttribute("statut");

        int idColoris = parseInt(req.getParameter("id"));
        String idColorisString = String.valueOf(idColoris);

        Couleur couleur = CouleurLibrary.getInstance().getCouleurByID(idColoris);

        String nomColoris = couleur.getNom_couleur();
        String numeroColoris = couleur.getNumero_couleur();
        String imageColoris = couleur.getImage_couleur();
        String saisonColoris = couleur.getSaison();

        webContext.setVariable("nomColoris", nomColoris);
        webContext.setVariable("numeroColoris", numeroColoris);
        webContext.setVariable("imageColoris", imageColoris);
        webContext.setVariable("saisonColoris", saisonColoris);
        webContext.setVariable("idColorisString", idColorisString);


        System.out.println("cette couleur va être modifié, nom = "+nomColoris+"; numeroColoris = "+numeroColoris+"; imageColoris = "+imageColoris+"; saison = "+saisonColoris);

        webContext.setVariable("statut",statut);

        templateEngine.process("administration/modifcoloris", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // GET PARAMETERS
        String nom_couleur = null;
        String numero_couleur = null;
        String image_couleur = null;
        String saison = null;
        String idColorisString = "bonjour";
        File filequicontientlimage=null;
        int idColorisInt = 0;
        try {
            nom_couleur = req.getParameter("nomColoris");
            numero_couleur = req.getParameter("numeroColoris");
            saison = req.getParameter("saisonColoris");
            idColorisString = req.getParameter("idColorisString");
            idColorisInt = Integer.parseInt(idColorisString);
            filequicontientlimage = new File("D:\\RoseCorail\\Images\\Couleurs\\"+nom_couleur.trim());
            filequicontientlimage.mkdirs(); // permet de transformer le fichier en répertoire. A noter que grace à cette méthode, on créer les dossiers qui n'existent pas dans le chemin de la ligne d'au dessus
            Part imagePart = req.getPart("imageColoris"); // on récupere  l'image du formulaire
            imagePart.write(filequicontientlimage.getAbsolutePath()+"/image.jpg"); // on écrit l'image que l'on vient de récupérer dans le répertoire précedemment créer

        }catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();
            req.getSession().setAttribute("errorMessage", errorMessage);
            System.out.println("error1"+errorMessage);
            resp.sendRedirect("/administration/ajoutColoris");
        }

        System.out.println("coloris string" +idColorisString);
        System.out.println("num " +numero_couleur);
        System.out.println(idColorisInt);




        // CREATE couleur
        Couleur newCouleur = new Couleur(null, nom_couleur, numero_couleur, "img\\"+nom_couleur.trim()+"\\image.jpg", saison);
        List<Article> lesarticlesdecettecouleur=possederLibrary.listArticlesPourUneCouleur(idColorisInt);
        List<LigneDevis> leslignesDevisPourcetteCouleur=ligneDevisLibrary.listLignesDevisPourUneCouleur(idColorisInt);
        Integer idcouleur=0;
        try {
            ligneDevisLibrary.deleteLigneDevisForOneCouleur(idColorisInt);
            possederLibrary.deletePossederForCouleur(idColorisInt);
            CouleurLibrary.getInstance().deleteCouleur(idColorisInt);
            Couleur createdCouleur = CouleurLibrary.getInstance().addCouleur(newCouleur);
            idcouleur=createdCouleur.getId_couleur();

            // REDIRECT TO Accueil

        } catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();

            req.getSession().setAttribute("errorMessage", errorMessage);
            System.out.println("error2"+errorMessage);

            resp.sendRedirect(String.format("/administration/modifColorisEntrerReference"));
        }

        System.out.println("idcouleur"+idcouleur);
        try{
            for (int i =0; i<lesarticlesdecettecouleur.size();i++){
                Posseder newposseder= new Posseder(null,idcouleur,lesarticlesdecettecouleur.get(i).getId_article());
                Posseder createdPosseder=possederLibrary.addPosseder(newposseder);
            }
            for (int i=0;i<leslignesDevisPourcetteCouleur.size();i++){
                LigneDevis newligneDevis=new LigneDevis(null,idcouleur,leslignesDevisPourcetteCouleur.get(i).getId_devis(),leslignesDevisPourcetteCouleur.get(i).getId_article());
                LigneDevis createdLigneDevis=ligneDevisLibrary.addLigneDevis(newligneDevis);
            }

            resp.sendRedirect(String.format("/accueil"));
        }catch (IllegalArgumentException e){
            String errorMessage = e.getMessage();

            req.getSession().setAttribute("errorMessage", errorMessage);
            System.out.println("error3"+errorMessage);
            resp.sendRedirect(String.format("/administration/modifColorisEntrerReference"));
        }
    }
}
