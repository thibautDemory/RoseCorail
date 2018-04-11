package hei.ProjetRoseCorail.servlets;

import hei.ProjetRoseCorail.entities.PhotosPresentation;
import hei.ProjetRoseCorail.managers.PhotoPresentationLibrary;
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

@MultipartConfig
@WebServlet("/administration/AjouterPhoto")
public class AjouterPhotoServlet extends GenericServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        String statut=(String) req.getSession().getAttribute("statut");
        webContext.setVariable("statut",statut);

        // On prépare le filtre de date du mois actuel pour la page "fragment.html"
        LocalDate maintenant=LocalDate.now();
        String anneeMoisActuelle = maintenant.toString().substring(0,7);
        webContext.setVariable("anneeMoisActuelle",anneeMoisActuelle);

        templateEngine.process("administration/AjouterPhoto", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //on récupère les données
        String page=null;

        try{
            page=req.getParameter("carrousel-page");
        }catch (IllegalArgumentException e){

        }
        PhotosPresentation newphoto=new PhotosPresentation(null,page,"bonjour");
        PhotosPresentation createdphoto=new PhotosPresentation(null,null,null);
        try{
            createdphoto= PhotoPresentationLibrary.getInstance().addPhotoPresentation(newphoto);
        }catch (IllegalArgumentException e){

        }
        Integer iddelanouvellephoto=createdphoto.getId_photo();
        try{
            File filequicontientlimage=null;
            filequicontientlimage = new File("D:\\Informatique\\Projet 100h\\RoseCorail\\src\\main\\webapp\\images\\"+page);
            filequicontientlimage.mkdirs(); // permet de transformer le fichier en répertoire. A noter que grace à cette méthode, on créer les dossiers qui n'existent pas dans le chemin de la ligne d'au dessus
            Part imagePart = req.getPart("imagePhoto"); // on récupere  l'image du formulaire
            imagePart.write(filequicontientlimage.getAbsolutePath()+"/image"+iddelanouvellephoto+".jpg"); // on écrit l'image que l'on vient de récupérer dans le répertoire précedemment créer
            PhotoPresentationLibrary.getInstance().modifierphoto("/RoseCorail/images/"+page+"/image"+iddelanouvellephoto+".jpg",iddelanouvellephoto);
            resp.sendRedirect("/RoseCorail/administration/modifierphotosaccueil");
             }
        catch (IllegalArgumentException e){

        }
    }
}
