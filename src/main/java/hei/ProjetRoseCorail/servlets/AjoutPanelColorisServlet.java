package hei.ProjetRoseCorail.servlets;

import hei.ProjetRoseCorail.entities.Panelcoloris;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;


@WebServlet("/administration/AjoutPanelColoris")
public class AjoutPanelColorisServlet extends GenericServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        String statut=(String) req.getSession().getAttribute("statut");

        webContext.setVariable("statut",statut);

        templateEngine.process("/administration/AjoutPanelColoris", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //On récupère les infos:
        String nomsetnumeros="";
        String image="";
        String saison="";
        File filequicontientlimage;

        try{
            nomsetnumeros=req.getParameter("nom-des-coloris");
            saison=req.getParameter("saison-panel-coloris");

            filequicontientlimage = new File("C:\\workSpaceWEB\\RoseCorailGit\\src\\main\\webapp\\images\\"+nomsetnumeros.trim());

            Part imagePart = req.getPart("imageActualite"); // on récupere  l'image du formulaire
            imagePart.write(filequicontientlimage.getAbsolutePath()+"/image.jpg"); // on écrit l'image que l'on vient de récupérer dans le répertoire précedemment créer


        }catch (IllegalArgumentException e){
            String error=e.getMessage();
            System.out.println(error);
        }
        Panelcoloris newpanelcoloris= new Panelcoloris(null,nomsetnumeros,"images\\"+nomsetnumeros+"\\image.jpg",saison);


    }
}