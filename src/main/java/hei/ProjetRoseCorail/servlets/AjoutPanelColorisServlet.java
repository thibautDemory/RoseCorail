package hei.ProjetRoseCorail.servlets;

import hei.ProjetRoseCorail.entities.ListeDesSaisons;
import hei.ProjetRoseCorail.entities.Panelcoloris;
import hei.ProjetRoseCorail.managers.PanelColorisLibrary;
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
import java.util.ArrayList;
import java.util.List;

@MultipartConfig
@WebServlet("/administration/AjoutPanelColoris")
public class AjoutPanelColorisServlet extends GenericServlet{
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

        templateEngine.process("/administration/AjoutPanelColoris", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //On récupère les infos:
        String nomsetnumeros="";
        String image="";
        String saison="";
        File filequicontientlimage=null;

        try{
            nomsetnumeros=req.getParameter("nom-des-coloris");
            saison=req.getParameter("saison-panel-coloris");


            filequicontientlimage = new File("D:\\Informatique\\Projet 100h\\RoseCorail\\src\\main\\webapp\\images\\panelcoloris\\"+nomsetnumeros.trim());
            filequicontientlimage.mkdirs(); // permet de transformer le fichier en répertoire. A noter que grace à cette méthode, on créer les dossiers qui n'existent pas dans le chemin de la ligne d'au dessus
            Part imagePart = req.getPart("image-panel-coloris"); // on récupere  l'image du formulaire
            imagePart.write(filequicontientlimage.getAbsolutePath()+"/image.jpg"); // on écrit l'image que l'on vient de récupérer dans le répertoire précedemment créer


        }catch (IllegalArgumentException e){
            String error=e.getMessage();
            System.out.println(error);
        }
        System.out.println(nomsetnumeros);
        Panelcoloris newpanelcoloris= new Panelcoloris(null,nomsetnumeros,"images\\panelcoloris\\"+nomsetnumeros+"\\image.jpg",saison);
        try{
            Panelcoloris createdpanelColoris= PanelColorisLibrary.getInstance().addPanelColoris(newpanelcoloris);
            System.out.println("Un nouveau panel coloris a été créer");
            resp.sendRedirect(String.format("/RoseCorail/lesColoris"));
        }catch (IllegalArgumentException e){
            String error=e.getMessage();
        }


    }
}