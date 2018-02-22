package hei.ProjetRoseCorail.servlets;

import hei.ProjetRoseCorail.entities.*;
import hei.ProjetRoseCorail.managers.ArticleLibrary;
import hei.ProjetRoseCorail.managers.CouleurLibrary;
import hei.ProjetRoseCorail.managers.DevisLibrary;
import hei.ProjetRoseCorail.managers.PossederLibrary;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/modalClient")
public class ModalClientServlet extends GenericServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        Boolean panierencours = (Boolean) req.getSession().getAttribute("panierencours");

        Integer id=Integer.parseInt(req.getParameter("id"));
        req.getSession().setAttribute("idArticlequejeregarde",id);
        ArticleLibrary articleLibrary=ArticleLibrary.getInstance();
        CouleurLibrary couleurLibrary= CouleurLibrary.getInstance();
        PossederLibrary possederLibrary= PossederLibrary.getInstance();


        Article cetArticle= articleLibrary.getArticleById(id);
        List<Couleur> lescouleursdecetarticle=possederLibrary.listCouleursPourUnArticle(id);

        webContext.setVariable("cetarticle",cetArticle);
        webContext.setVariable("sescouleurs",lescouleursdecetarticle);


        templateEngine.process("modalClient", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        CouleurLibrary couleurLibrary=CouleurLibrary.getInstance();
        DevisLibrary devisLibrary=DevisLibrary.getInstance();
        //création des variables
        String nomarticle=null;
        Integer panierencours =  (Integer) req.getSession().getAttribute("panierencours");
        String idClientString= (String) req.getSession().getAttribute("idClient");
        Integer idClient=Integer.parseInt(idClientString);
        LocalDate maintenant=LocalDate.now();

        List<LigneDevis> listelignesdevis=new ArrayList<>();
        List<Couleur> lescouleurschoisies=new ArrayList<>();
        List<Integer> lesquantiteschoisies =new ArrayList<>();
        List<Couleur> lescouleurs=CouleurLibrary.getInstance().listCouleurs();
        Devis panier;

        try{
            //récupération des valeurs
            nomarticle = req.getParameter("nomarticle");
            for (int i=0;i<lescouleurs.size();i++) {
                if (req.getParameter(lescouleurs.get(i).getNom_couleur()) != null && !req.getParameter(lescouleurs.get(i).getNom_couleur()).equals("")) {
                    lescouleurschoisies.add(couleurLibrary.getCouleurByName(lescouleurs.get(i).getNom_couleur()));
                    lesquantiteschoisies.add(Integer.parseInt(req.getParameter(lescouleurs.get(i).getNom_couleur())));
                }
            }
        }catch (IllegalArgumentException e){
            String erreur= e.getMessage();
            System.out.println("1"+erreur);
        }
        if(panierencours==null|| panierencours==0){
            Devis panieraconstruire=new Devis(null,idClient,maintenant,"panier",true);
            panier=devisLibrary.creerundevis(panieraconstruire);
            req.getSession().setAttribute("panierencours",panier.getId_devis());
        }else{
            panier=devisLibrary.getPanierClient(idClient);
        }
        Article article=ArticleLibrary.getInstance().getArticleByName(nomarticle);

        try {
            for (int i = 0; i < lescouleurschoisies.size(); i++) {
                LigneDevis nouvelleligne = new LigneDevis(null,lescouleurschoisies.get(i).getId_couleur(),panier.getId_devis(),article.getId_article(),lesquantiteschoisies.get(i));
                listelignesdevis.add(nouvelleligne);
            }
            resp.sendRedirect("/lesPlats");


        }catch (IllegalArgumentException e){
           String erreur= e.getMessage();
           System.out.println("2"+erreur);

        }
        System.out.println(listelignesdevis.get(0).getId_ligne_devis());
    }
}
