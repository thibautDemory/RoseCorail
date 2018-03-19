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
import java.sql.ClientInfoStatus;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/panier")
public class PanierServlet extends GenericServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        ArticleLibrary articleLibrary=ArticleLibrary.getInstance();
        CouleurLibrary couleurLibrary=CouleurLibrary.getInstance();
        CompteClientLibrary compteClientLibrary=CompteClientLibrary.getInstance();
        String statut=(String) req.getSession().getAttribute("statut");
        Integer idPanier=0;
        List<LignePanier> lesArticlesCouleurEtQuantites= new ArrayList<>();
        if (statut==null||"".equals(statut)){
            statut="visiteur";
        }else{
            Integer idClient= (Integer) req.getSession().getAttribute("idClient");
            idPanier=compteClientLibrary.getCompteClientById(idClient).getNumero_panier_actif();
            String nom=req.getSession().getAttribute("nom").toString();
            String prenom=req.getSession().getAttribute("prenom").toString();
            webContext.setVariable("prenom",prenom);
            webContext.setVariable("nom",nom);
        }
        System.out.println(idPanier);

        //affichage des produits du panier
        if (idPanier!=null){
            List<LigneDevis> leslignesdevis = LigneDevisLibrary.getInstance().listLignesDevisPourUnDevis(idPanier);


            for (int i =0;i<leslignesdevis.size();i++){
                Article article=articleLibrary.getArticleById(leslignesdevis.get(i).getId_article());
                Couleur couleur=couleurLibrary.getCouleurByID(leslignesdevis.get(i).getId_couleur());
                lesArticlesCouleurEtQuantites.add(new LignePanier(
                        article.getImage(),
                        article.getReference(),
                        article.getNom_article(),
                        article.getDimension(),
                        couleur.getImage_couleur(),
                        leslignesdevis.get(i).getQuantite(),
                        article.getLot_vente(),
                        article.getPrix(),
                        leslignesdevis.get(i).getId_ligne_devis()));
            }

            webContext.setVariable("lesarticlesdupanier",lesArticlesCouleurEtQuantites);
        }else{
            webContext.setVariable("paniervide",true);
        }
        System.out.println(statut);
        webContext.setVariable("statut",statut);

        templateEngine.process("panier", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //envoi d'un email à rose corail avec le devis
        //redirection vers une page qui dit que le devis a bien été envoyer
        //le devis change d'état
        //création d'un nouveau panier actif pr le client
        //changement de son numero panier actif.

        DevisLibrary devisLibrary=DevisLibrary.getInstance();
        CompteClientLibrary compteClientLibrary=CompteClientLibrary.getInstance();
        LocalDate maintenant=LocalDate.now();

        Integer idClient= (Integer) req.getSession().getAttribute("idClient");


        CompteClient client=compteClientLibrary.getCompteClientById(idClient);
        Integer idPanier=client.getNumero_panier_actif();
        Integer idnouveaupanier=0;
        devisLibrary.passerdePanierAPreparation(idPanier);
        Devis devis= new Devis(null,idClient,maintenant,"panier",true);
        try{
            Devis createddevis=devisLibrary.creerundevis(devis);
            idnouveaupanier=createddevis.getId_devis();
            compteClientLibrary.changernumeropanieractif(idClient,idnouveaupanier);
            resp.sendRedirect("/devisenvoyesucces");

        }catch (IllegalArgumentException e){

        }

    }
}
