package hei.ProjetRoseCorail.servlets;

import hei.ProjetRoseCorail.entities.*;
import hei.ProjetRoseCorail.managers.ArticleLibrary;
import hei.ProjetRoseCorail.managers.CompteClientLibrary;
import hei.ProjetRoseCorail.managers.DevisLibrary;
import hei.ProjetRoseCorail.managers.LigneDevisLibrary;
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

@WebServlet("/devisClient")
public class DevisClientServlet extends GenericServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        String statut=(String) req.getSession().getAttribute("statut");

        if (statut==null||"".equals(statut)||statut=="visiteur"){
            statut="visiteur";
        }else{
            String nom=req.getSession().getAttribute("nom").toString();
            String prenom=req.getSession().getAttribute("prenom").toString();
            webContext.setVariable("prenom",prenom);
            webContext.setVariable("nom",nom);
        }
        System.out.println(statut);


        DevisLibrary devisLibrary=DevisLibrary.getInstance();
        Integer idClient=(Integer) req.getSession().getAttribute("idClient");
        List<Devis> lesdevisdececlient=devisLibrary.listdevispouruncompteclient(idClient);
        List<DevisVuParRC> affichagedesdevis = new ArrayList<>();

        for (int i =0; i<lesdevisdececlient.size();i++){
            Integer nbarticles=0;
            Double prixtotal=0.0;
            Integer idDevis=lesdevisdececlient.get(i).getId_devis();
            Devis devis=DevisLibrary.getInstance().getDevisByiD(idDevis);

            List<LigneDevis> leslignesdecedevis= LigneDevisLibrary.getInstance().listLignesDevisPourUnDevis(idDevis);
            for (int j=0;j<leslignesdecedevis.size();j++){
                Article article= ArticleLibrary.getInstance().getArticleById(leslignesdecedevis.get(j).getId_article());
                nbarticles=nbarticles+leslignesdecedevis.get(j).quantite;
                prixtotal=prixtotal+leslignesdecedevis.get(j).quantite*article.getPrix();
            }

            affichagedesdevis.add(new DevisVuParRC(
                    idDevis,
                    CompteClientLibrary.getInstance().getCompteClientById(idClient).getNom_boutique(),
                    lesdevisdececlient.get(i).getDate(),
                    nbarticles,
                    prixtotal,
                    lesdevisdececlient.get(i).getEtat()
            ));
        }
        webContext.setVariable("affichagedesdevis",affichagedesdevis);

        webContext.setVariable("statut",statut);

        // On prépare le filtre de date du mois actuel pour la page "fragment.html"
        LocalDate maintenant=LocalDate.now();
        String anneeMoisActuelle = maintenant.toString().substring(0,7);
        webContext.setVariable("anneeMoisActuelle",anneeMoisActuelle);

        templateEngine.process("devisClient", webContext, resp.getWriter());
    }
}
