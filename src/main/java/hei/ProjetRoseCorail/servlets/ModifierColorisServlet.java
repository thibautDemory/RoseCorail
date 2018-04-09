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

        String idColorisString = req.getParameter("id");
        System.out.println("l'id coloris string est :"+idColorisString);


        Couleur couleur = CouleurLibrary.getInstance().getCouleurByID(Integer.parseInt(idColorisString));

        String nomColoris = couleur.getNom_couleur();
        String numeroColoris = couleur.getNumero_couleur();
        String imageColoris = couleur.getImage_couleur();
        String saisonColoris = couleur.getSaison();

        webContext.setVariable("idColoris", idColorisString);
        webContext.setVariable("nomColoris", nomColoris);
        webContext.setVariable("numeroColoris", numeroColoris);
        webContext.setVariable("imageColoris", imageColoris);
        webContext.setVariable("saisonColoris", saisonColoris);



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
        String idColorisString= null;
        File filequicontientlimage=null;
        Integer idColorisInt=0;

        try {
            nom_couleur = req.getParameter("nomColoris");
            numero_couleur = req.getParameter("numeroColoris");
            saison = req.getParameter("saisonColoris");
            idColorisString=req.getParameter("idColoris");
            idColorisInt = Integer.parseInt(idColorisString);
            filequicontientlimage = new File("D:\\Informatique\\Projet 100h\\RoseCorail\\src\\main\\webapp\\image\\couleur\\"+nom_couleur.trim());
            filequicontientlimage.mkdirs(); // permet de transformer le fichier en répertoire. A noter que grace à cette méthode, on créer les dossiers qui n'existent pas dans le chemin de la ligne d'au dessus
            Part imagePart = req.getPart("imageColoris"); // on récupere  l'image du formulaire
            imagePart.write(filequicontientlimage.getAbsolutePath()+"/image.jpg"); // on écrit l'image que l'on vient de récupérer dans le répertoire précedemment créer

        }catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();

            System.out.println("error1"+errorMessage);

        }

        System.out.println("coloris string" +idColorisInt);
        System.out.println("num " +numero_couleur);
        System.out.println(idColorisInt);


        // CREATE couleur
        Couleur newCouleur = new Couleur(null, nom_couleur, numero_couleur, "image\\couleur\\"+nom_couleur.trim()+"\\image.jpg", saison,1);
        List<Article> lesarticlesdecettecouleur=possederLibrary.listArticlesPourUneCouleur(idColorisInt);
        List<LigneDevis> leslignesDevisPourcetteCouleur=ligneDevisLibrary.listLignesDevisPourUneCouleur(idColorisInt);
        Integer idcouleur=0;
        try {
            ligneDevisLibrary.deleteLigneDevisForOneCouleur(idColorisInt);
            possederLibrary.deletePossederForCouleur(idColorisInt);
            CouleurLibrary.getInstance().deleteCouleur(idColorisInt);
            Couleur createdCouleur = CouleurLibrary.getInstance().addCouleur(newCouleur);
            idcouleur=createdCouleur.getId_couleur();

        } catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();

            req.getSession().setAttribute("errorMessage", errorMessage);
            System.out.println("error2"+errorMessage);
        }

        System.out.println("idcouleur"+idcouleur);
        try{
            for (int i =0; i<lesarticlesdecettecouleur.size();i++){
                Posseder newposseder= new Posseder(null,idcouleur,lesarticlesdecettecouleur.get(i).getId_article());
                Posseder createdPosseder=possederLibrary.addPosseder(newposseder);
            }
            for (int i=0;i<leslignesDevisPourcetteCouleur.size();i++){
                LigneDevis newligneDevis=new LigneDevis(null,
                        idcouleur,
                        leslignesDevisPourcetteCouleur.get(i).getId_devis(),
                        leslignesDevisPourcetteCouleur.get(i).getId_article(),
                        leslignesDevisPourcetteCouleur.get(i).getQuantite());
                LigneDevis createdLigneDevis=ligneDevisLibrary.addLigneDevis(newligneDevis);

            }

            resp.sendRedirect(String.format("/RoseCorail/administration/formulaire"));
        }catch (IllegalArgumentException e){
            String errorMessage = e.getMessage();
            req.getSession().setAttribute("errorMessage", errorMessage);
            System.out.println("error3"+errorMessage);

        }
    }
}
