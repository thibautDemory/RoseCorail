package hei.ProjetRoseCorail.servlets;

import hei.ProjetRoseCorail.entities.Article;
import hei.ProjetRoseCorail.entities.Couleur;
import hei.ProjetRoseCorail.entities.LigneDevis;
import hei.ProjetRoseCorail.entities.Posseder;
import hei.ProjetRoseCorail.managers.ArticleLibrary;
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
import java.util.ArrayList;
import java.util.List;
@MultipartConfig
@WebServlet("/administration/ModificationArticle")
public class ModificationArticleServlet extends GenericServlet{
    ArticleLibrary articleLibrary=ArticleLibrary.getInstance();
    PossederLibrary possederLibrary=PossederLibrary.getInstance();
    LigneDevisLibrary ligneDevisLibrary=LigneDevisLibrary.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        String statut=(String) req.getSession().getAttribute("statut");
        ArticleLibrary articleLibrary=ArticleLibrary.getInstance();
        CouleurLibrary couleurLibrary= CouleurLibrary.getInstance();
        PossederLibrary possederLibrary= PossederLibrary.getInstance();
        webContext.setVariable("statut",statut);

        Integer id=Integer.parseInt(req.getParameter("id"));
        Article cetArticle= articleLibrary.getArticleById(id);
        List<Couleur> lescouleursdecetarticle=possederLibrary.listCouleursPourUnArticle(id);
        List<Couleur> lescouleurs=couleurLibrary.listCouleursActives();
        List<String> lescouleursdecetarticleenString=new ArrayList<>();
        List<String> lescouleursenString=new ArrayList<>();
        for (int i=0;i<lescouleursdecetarticle.size();i++){
            lescouleursdecetarticleenString.add(lescouleursdecetarticle.get(i).getNom_couleur());
        }
        for (int i=0;i<lescouleurs.size();i++){
            lescouleursenString.add(lescouleurs.get(i).getNom_couleur());
        }


        List<Integer> unadouze= new ArrayList<Integer>();
        for (int i=1;i<13;i++){
            unadouze.add(i);
        }

        webContext.setVariable("cetarticle",cetArticle);
        webContext.setVariable("unadouze",unadouze);
        webContext.setVariable("sescouleurs",lescouleursdecetarticleenString);
        webContext.setVariable("lescouleurs",lescouleursenString);

        templateEngine.process("/administration/ModificationArticle", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //on récupère les paramètres:
        String nom="";
        String description="";
        String dimension="";
        Integer sous_categorie=0;
        String reference="";
        Double prix=0.0;
        Integer vendupar=null;
        File filequicontientlimage=null;
        String idArticleAModifier=null;
        List<Couleur> lescouleurs=CouleurLibrary.getInstance().listCouleursActives();
        List<Integer> numerocouleurschecked=new ArrayList<>();

        try{
            nom=req.getParameter("nom-article");
            description=req.getParameter("description-article");
            dimension=req.getParameter("dimension-article");
            filequicontientlimage= new File("D:\\Informatique\\Projet 100h\\RoseCorail\\src\\main\\webapp\\images\\article\\"+nom.trim());
            filequicontientlimage.mkdirs();
            Part imagePart = req.getPart("image-article");
            imagePart.write(filequicontientlimage.getAbsolutePath()+"/image.jpg");
            reference=req.getParameter("reference-article");
            prix=Double.parseDouble(req.getParameter("prix-article"));
            vendupar=Integer.parseInt(req.getParameter("lot-article"));
            idArticleAModifier=req.getParameter("idArticle");
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
        Article articlemodif= new Article(null, sous_categorie,nom,reference,description,"images\\article\\"+nom+"\\image.jpg",dimension,prix,vendupar,1);
        List<LigneDevis> leslignesDevisPourcetArticle=ligneDevisLibrary.listLignesDevisPourUnArticle(Integer.parseInt(idArticleAModifier));
        Integer idnouvelarticle=0;
        try{
            ligneDevisLibrary.deleteLigneDevisForOneArticle(Integer.parseInt(idArticleAModifier));
            possederLibrary.deletePossederForArticle(Integer.parseInt(idArticleAModifier));
            articleLibrary.deleteArticle(Integer.parseInt(idArticleAModifier));
            Article createdArticle= articleLibrary.addArticle(articlemodif);
            idnouvelarticle=createdArticle.getId_article();

        }catch (IllegalArgumentException e){
            String errorMessage =e.getMessage();
            System.out.println("error2"+errorMessage);
            req.getSession().setAttribute("errorMessage",errorMessage);

        }

        List<Posseder> listedesposseder=new ArrayList<>();
        for (int i=0;i<numerocouleurschecked.size();i++){
            Posseder newposseder= new Posseder(null,numerocouleurschecked.get(i),idnouvelarticle);
            listedesposseder.add(newposseder);
        }

        try{
            for (int i=0;i<listedesposseder.size();i++){
                Posseder createdPosseder=possederLibrary.addPosseder(listedesposseder.get(i));
            }
            for (int i=0;i<leslignesDevisPourcetArticle.size();i++){
                LigneDevis newligneDevis=new LigneDevis(null,
                        leslignesDevisPourcetArticle.get(i).getId_couleur(),
                        leslignesDevisPourcetArticle.get(i).getId_devis(),
                        idnouvelarticle,
                        leslignesDevisPourcetArticle.get(i).getQuantite());
                LigneDevis createdLigneDevis=ligneDevisLibrary.addLigneDevis(newligneDevis);

            }
            resp.sendRedirect(String.format("/administration/formulaire"));

        }catch (IllegalArgumentException e){
            String errorMessage =e.getMessage();

            System.out.println("erreur1"+errorMessage);
        }


    }
}
