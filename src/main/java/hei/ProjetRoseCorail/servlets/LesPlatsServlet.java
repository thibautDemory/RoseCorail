package hei.ProjetRoseCorail.servlets;

import hei.ProjetRoseCorail.entities.*;
import hei.ProjetRoseCorail.managers.*;
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

@WebServlet("/lesPlats")
public class LesPlatsServlet extends GenericServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        String statut=(String) req.getSession().getAttribute("statut");


        ArticleLibrary articleLibrary=ArticleLibrary.getInstance();
        List<Article> platsacake=articleLibrary.listPlatsPlatACake();
        List<Article> platsAfromage=articleLibrary.listPlatsPlatAFromage();
        List<Article> coupelles=articleLibrary.listPlatsCoupelle();
        List<Couleur> couleurs= CouleurLibrary.getInstance().listCouleurs();

        if (statut==null||"".equals(statut)){
            statut="visiteur";
        }else{
            String nom=req.getSession().getAttribute("nom").toString();
            String prenom=req.getSession().getAttribute("prenom").toString();
            webContext.setVariable("prenom",prenom);
            webContext.setVariable("nom",nom);

        }
        webContext.setVariable("platsacake",platsacake);
        webContext.setVariable("platsAfromage",platsAfromage);
        webContext.setVariable("coupelles",coupelles);
        webContext.setVariable("couleurs",couleurs);

        System.out.println(statut);
        webContext.setVariable("statut",statut);


        templateEngine.process("lesPlats", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer idArticle=(Integer) req.getSession().getAttribute("idArticlequejeregarde");


        CouleurLibrary couleurLibrary=CouleurLibrary.getInstance();
        DevisLibrary devisLibrary=DevisLibrary.getInstance();
        LigneDevisLibrary ligneDevisLibrary=LigneDevisLibrary.getInstance();
        CompteClientLibrary compteClientLibrary= CompteClientLibrary.getInstance();
        //création des variables

        Integer idClient= (Integer) req.getSession().getAttribute("idClient");

        LocalDate maintenant=LocalDate.now();

        List<LigneDevis> listelignesdevis=new ArrayList<>();
        List<Couleur> lescouleurschoisies=new ArrayList<>();
        List<Integer> lesquantiteschoisies =new ArrayList<>();
        List<Couleur> lescouleurs=CouleurLibrary.getInstance().listCouleurs();
        Devis panier=devisLibrary.getDevisByiD(compteClientLibrary.getCompteClientById(idClient).getNumero_panier_actif());
        CompteClient client=compteClientLibrary.getCompteClientById(idClient);

        try{
            //récupération des valeurs

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
        System.out.println("nomarticle"+idArticle);
        for (int i=0;i<lescouleurschoisies.size();i++){
            System.out.println(lescouleurschoisies.get(i));
            System.out.println(lesquantiteschoisies.get(i));
        }
        if(client.getNumero_panier_actif()==null){
            Devis panieraconstruire=new Devis(null,idClient,maintenant,"panier",true);
            panier=devisLibrary.creerundevis(panieraconstruire);

        }else{
            panier=devisLibrary.getPanierClient(idClient);
        }
        Article article=ArticleLibrary.getInstance().getArticleById(idArticle);

        for (int i = 0; i < lescouleurschoisies.size(); i++) {
            LigneDevis nouvelleligne = new LigneDevis(null,lescouleurschoisies.get(i).getId_couleur(),panier.getId_devis(),article.getId_article(),lesquantiteschoisies.get(i));
            listelignesdevis.add(nouvelleligne);
        }

        try {
            for (int i = 0; i < lescouleurschoisies.size(); i++) {
                LigneDevis createdLigne = ligneDevisLibrary.addLigneDevis(listelignesdevis.get(i));
            }


            resp.sendRedirect("/lesPlats");


        }catch (IllegalArgumentException e){
            String erreur= e.getMessage();
            System.out.println("2"+erreur);

        }
        System.out.println(listelignesdevis.get(0).getId_ligne_devis());
    }
}
