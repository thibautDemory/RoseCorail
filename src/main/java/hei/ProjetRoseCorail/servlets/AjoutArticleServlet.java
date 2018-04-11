package hei.ProjetRoseCorail.servlets;

import hei.ProjetRoseCorail.entities.Article;
import hei.ProjetRoseCorail.entities.Couleur;
import hei.ProjetRoseCorail.entities.Posseder;
import hei.ProjetRoseCorail.managers.ArticleLibrary;
import hei.ProjetRoseCorail.managers.CouleurLibrary;
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
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@MultipartConfig
@WebServlet("/administration/ajoutArticle")
public class AjoutArticleServlet extends GenericServlet{
    private static final String UPLOAD_DIR = "uploads";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        String statut=(String) req.getSession().getAttribute("statut");
        List<Couleur> listedescouleurs= CouleurLibrary.getInstance().listCouleursActives();
        List<Integer> unadouze= new ArrayList<Integer>();
        for (int i=1;i<13;i++){
            unadouze.add(i);
        }

        webContext.setVariable("statut",statut);
        webContext.setVariable("unadouze",unadouze);
        webContext.setVariable("listedescouleurs",listedescouleurs);

        // On prépare le filtre de date du mois actuel pour la page "fragment.html"
        LocalDate maintenant=LocalDate.now();
        String anneeMoisActuelle = maintenant.toString().substring(0,7);
        webContext.setVariable("anneeMoisActuelle",anneeMoisActuelle);


        templateEngine.process("administration/ajout-article", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PossederLibrary possederLibrary=PossederLibrary.getInstance();

        //Get parameters:
        String nom="";
        String description="";
        String dimension="";
        Integer sous_categorie=0;
        String reference="";
        Double prix=0.0;
        Integer vendupar=null;
        File filequicontientlimage=null;
        List<Couleur> lescouleurs=CouleurLibrary.getInstance().listCouleursActives();
        List<Integer> numerocouleurschecked=new ArrayList<>();
        String resultat="bonjour";
        String pathaafficher="";

        // gets absolute path of the web application
        String applicationPath = req.getServletContext().getRealPath("");
        // constructs path of the directory to save uploaded file
        String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;

        // creates the save directory if it does not exists
        File fileSaveDir = null;






        try{
            nom=req.getParameter("nom-article");
            description=req.getParameter("description-article");
            dimension=req.getParameter("dimension-article");



            //pathaafficher=filequicontientlimage.getAbsolutePath();
            fileSaveDir=new File(uploadFilePath+"/article/"+nom.trim());
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdirs();
            }
            System.out.println("Upload File Directory="+fileSaveDir.getAbsolutePath());


            Part imagePart = req.getPart("image-article");
            imagePart.write(uploadFilePath+"/article/"+nom.trim()+"/image.jpg");
            System.out.println(uploadFilePath+"/article/"+nom.trim()+"/image.jpg");
            reference=req.getParameter("reference-article");
            prix=Double.parseDouble(req.getParameter("prix-article"));
            vendupar=Integer.parseInt(req.getParameter("lot-article"));
            sous_categorie=Integer.parseInt(req.getParameter("sous-categorie-article"));
            for (int i=0;i<lescouleurs.size();i++) {
                if (req.getParameter(lescouleurs.get(i).getNom_couleur()) != null && !req.getParameter(lescouleurs.get(i).getNom_couleur()).equals("")) {
                        numerocouleurschecked.add(lescouleurs.get(i).getId_couleur());
                }
            }

        }catch (IllegalArgumentException e){
            String error=e.getMessage();

            System.out.println("erreur1"+error);

        }
        //on créer l'article
        for (int i=0;i<numerocouleurschecked.size();i++){
            System.out.println(numerocouleurschecked.get(i));
        }


        Article newarticle = new Article(null, sous_categorie,nom,reference,description,"/image/article/"+nom+"/image.jpg",dimension,prix,vendupar,1);
        System.out.println("l'article est créer");

        try{
            Article createdArticle= ArticleLibrary.getInstance().addArticle(newarticle);
            System.out.println("larticlelibrary");
        }catch (IllegalArgumentException e){
            String errorMessage =e.getMessage();
            System.out.println("error2"+errorMessage);
            req.getSession().setAttribute("errorMessage",errorMessage);
            resp.sendRedirect("/RoseCorail/administration/ajoutArticle");
        }
        Integer idarticle=newarticle.getId_article();
        List<Posseder> listedesposseder=new ArrayList<>();
        for (int i=0;i<numerocouleurschecked.size();i++){
            Posseder newposseder= new Posseder(null,numerocouleurschecked.get(i),idarticle);
            listedesposseder.add(newposseder);
        }

        try{
            for (int i=0;i<listedesposseder.size();i++){
                Posseder createdPosseder=possederLibrary.addPosseder(listedesposseder.get(i));
            }

            resp.sendRedirect(String.format("/RoseCorail/administration/formulaire"));

        }catch (IllegalArgumentException e){
            String errorMessage =e.getMessage();

            System.out.println("erreur1"+errorMessage);
        }





    }
}
