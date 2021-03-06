package hei.ProjetRoseCorail.servlets;

import hei.ProjetRoseCorail.dao.impl.DataSourceProvider;
import hei.ProjetRoseCorail.entities.*;
import hei.ProjetRoseCorail.managers.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

@WebServlet("/lesPlats")
public class LesPlatsServlet extends GenericServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        String statut=(String) req.getSession().getAttribute("statut");
        String modification=(String) req.getParameter("Modification");

        ArticleLibrary articleLibrary=ArticleLibrary.getInstance();
        List<Article> platsacake=articleLibrary.listPlatsPlatACake();
        List<Article> platsAfromage=articleLibrary.listPlatsPlatAFromage();
        List<Article> coupelles=articleLibrary.listPlatsCoupelle();
        List<Couleur> couleurs= CouleurLibrary.getInstance().listCouleursActives();

        List<byte[]> listImagePlatACake = new ArrayList<>();

        for(int i=0; i<platsacake.size(); i++){
            int idPlat = platsacake.get(i).getId_article();
            String query = "SELECT image FROM article WHERE id_article=?";
            try (Connection connection = DataSourceProvider.getDataSource().getConnection();
                 PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, idPlat);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    java.sql.Blob img = resultSet.getBlob("image");
                    byte[] imgData = img.getBytes(1,(int)img.length());
                    //resp.setHeader("expires", "0");
                    resp.setContentType("image/jpg");
                    OutputStream o = resp.getOutputStream();
                    o.write(imgData);
                    o.flush();
                    o.close();
                    listImagePlatACake.add(imgData);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        if (statut==null||"".equals(statut)||statut=="visiteur"){
            statut="visiteur";
        }else{
            String nom=req.getSession().getAttribute("nom").toString();
            String prenom=req.getSession().getAttribute("prenom").toString();
            webContext.setVariable("prenom",prenom);
            webContext.setVariable("nom",nom);

        }
        webContext.setVariable("platsacake",platsacake);
        webContext.setVariable("listImagePlatsACake",listImagePlatACake);
        webContext.setVariable("platsAfromage",platsAfromage);
        webContext.setVariable("coupelles",coupelles);
        webContext.setVariable("couleurs",couleurs);
        webContext.setVariable("modification",modification);

        System.out.println(statut);
        webContext.setVariable("statut",statut);

        // On prépare le filtre de date du mois actuel pour la page "fragment.html"
        LocalDate maintenant=LocalDate.now();
        String anneeMoisActuelle = maintenant.toString().substring(0,7);
        webContext.setVariable("anneeMoisActuelle",anneeMoisActuelle);

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
        List<Couleur> lescouleurs=CouleurLibrary.getInstance().listCouleursActives();
        Devis panier;
        CompteClient client=compteClientLibrary.getCompteClientById(idClient);

        try{
            //récupération des valeurs

            for (int i=0;i<lescouleurs.size();i++) {
                if (req.getParameter(lescouleurs.get(i).getNom_couleur()) != null && !req.getParameter(lescouleurs.get(i).getNom_couleur()).equals("") && Integer.parseInt(req.getParameter(lescouleurs.get(i).getNom_couleur()))!=0) {
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
        if(client.getNumero_panier_actif()==0){
            System.out.println("panier actif est nul");
            Devis panieraconstruire=new Devis(null,idClient,maintenant,"panier",true);
            panier=devisLibrary.creerundevis(panieraconstruire);
            compteClientLibrary.changernumeropanieractif(idClient,panier.getId_devis());
        }else{
            System.out.println(client.getNumero_panier_actif());
            System.out.println("panier actif est pas nul");
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
            resp.sendRedirect("/RoseCorail/lesPlats");
        }catch (IllegalArgumentException e){
            String erreur= e.getMessage();
            System.out.println("2"+erreur);

        }

    }
}
