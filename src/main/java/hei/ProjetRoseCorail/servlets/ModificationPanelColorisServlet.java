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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@MultipartConfig
@WebServlet("/administration/ModificationPanelColoris")
public class ModificationPanelColorisServlet extends GenericServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        String statut=(String) req.getSession().getAttribute("statut");
        Integer id=Integer.parseInt(req.getParameter("id"));
        PanelColorisLibrary panelColorisLibrary=PanelColorisLibrary.getInstance();
        Panelcoloris panelcolorisaModifier=panelColorisLibrary.getPanelColorisById(id);
        ListeDesSaisons listeDesSaisons= new  ListeDesSaisons();
        List<String> lessaisonsenString = new ArrayList<>();
        for (int i =0; i<listeDesSaisons.lessaisons.size();i++){
            lessaisonsenString.add(listeDesSaisons.lessaisons.get(i));
            System.out.println(lessaisonsenString.get(i));
        }
        webContext.setVariable("lessaisons",listeDesSaisons.lessaisons);


        webContext.setVariable("panelColoris",panelcolorisaModifier);

        webContext.setVariable("statut",statut);

        // On prépare le filtre de date du mois actuel pour la page "fragment.html"
        LocalDate maintenant=LocalDate.now();
        String anneeMoisActuelle = maintenant.toString().substring(0,7);
        webContext.setVariable("anneeMoisActuelle",anneeMoisActuelle);

        templateEngine.process("/administration/ModificationPanelColoris", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //On récupère les infos:
        Integer idpanelasuppr=0;
        String nomsetnumeros="";
        String image="";
        String saison="";
        File filequicontientlimage;

        try{
            idpanelasuppr=Integer.parseInt(req.getParameter("idPanel"));
            nomsetnumeros=req.getParameter("nom-des-coloris");
            saison=req.getParameter("saison-panel-coloris");

            filequicontientlimage = new File("D:\\Informatique\\Projet 100h\\RoseCorail\\src\\main\\webapp\\images\\panelcoloris\\"+nomsetnumeros.trim());
            filequicontientlimage.mkdirs();
            Part imagePart = req.getPart("image-panel-coloris"); // on récupere  l'image du formulaire
            imagePart.write(filequicontientlimage.getAbsolutePath()+"/image.jpg"); // on écrit l'image que l'on vient de récupérer dans le répertoire précedemment créer


        }catch (IllegalArgumentException e){
            String error=e.getMessage();
            System.out.println(error);
        }
        PanelColorisLibrary.getInstance().deletePanelColoris(idpanelasuppr);
        Panelcoloris newpanelcoloris= new Panelcoloris(null,nomsetnumeros,"\\RoseCorail\\images\\panelcoloris\\"+nomsetnumeros+"\\image.jpg",saison);

        try{
            Panelcoloris createdpanelColoris= PanelColorisLibrary.getInstance().addPanelColoris(newpanelcoloris);
            System.out.println("saison="+saison);
            resp.sendRedirect(("/RoseCorail/lesColoris?saison=Autome-Hiver 2016"));
        }catch (IllegalArgumentException e){
            String error = e.getMessage();
        }


    }
}