package hei.ProjetRoseCorail.servlets;

import hei.ProjetRoseCorail.entities.Article;
import hei.ProjetRoseCorail.entities.Couleur;
import hei.ProjetRoseCorail.managers.ArticleLibrary;
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
import java.util.ArrayList;
import java.util.List;
@MultipartConfig
@WebServlet("/administration/ajoutArticle")
public class AjoutArticleServlet extends GenericServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        String statut=(String) req.getSession().getAttribute("statut");
        List<Couleur> listedescouleurs= CouleurLibrary.getInstance().listCouleurs();
        List<Integer> unadouze= new ArrayList<Integer>();
        for (int i=1;i<13;i++){
            unadouze.add(i);
        }

        webContext.setVariable("statut",statut);
        webContext.setVariable("unadouze",unadouze);
        webContext.setVariable("listedescouleurs",listedescouleurs);


        templateEngine.process("administration/ajout-article", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Get parameters:
        String nom="";
        String description="";
        String dimension="";
        Integer sous_categorie=2;
        String reference="";
        Double prix=0.0;
        Integer vendupar=null;
        File filequicontientlimage=null;

        try{
            nom=req.getParameter("nom-article");
            description=req.getParameter("description-article");
            dimension=req.getParameter("dimension-article");
            filequicontientlimage= new File("D:\\Informatique\\Projet 100h\\RoseCorail\\src\\main\\webapp\\image\\"+nom.trim());
            filequicontientlimage.mkdirs();
            Part imagePart = req.getPart("image-article");
            imagePart.write(filequicontientlimage.getAbsolutePath()+"/image.jpg");
            reference=req.getParameter("reference-article");
            prix=Double.parseDouble(req.getParameter("prix-article"));
            vendupar=Integer.parseInt(req.getParameter("lot-article"));
            sous_categorie=Integer.parseInt(req.getParameter("sous-categorie-article"));


        }catch (IllegalArgumentException e){
            String error=e.getMessage();

            System.out.println("erreur1"+error);

        }
        //on créer l'article
        Article newarticle = new Article(null, sous_categorie,nom,reference,description,"images\\article\\"+nom+"\\image.jpg",dimension,prix,vendupar);
        System.out.println("l'article est créer");
        try{
            Article createdArticle= ArticleLibrary.getInstance().addArticle(newarticle);
            System.out.println("larticlelibrary");
            resp.sendRedirect(String.format("/lesPlats"));

        }catch (IllegalArgumentException e){
            String errorMessage =e.getMessage();
            System.out.println("error2"+errorMessage);
            req.getSession().setAttribute("errorMessage",errorMessage);
            resp.sendRedirect("/administration/ajoutArticle");
        }





    }
}
