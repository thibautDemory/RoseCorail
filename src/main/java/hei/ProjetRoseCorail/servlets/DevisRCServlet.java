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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/administration/devisRC")
public class DevisRCServlet extends GenericServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        String statut=(String) req.getSession().getAttribute("statut");
        DevisLibrary devisLibrary=DevisLibrary.getInstance();
        List<Devis> lesdevisRC=devisLibrary.listlesdevis();
        List<DevisVuParRC> lesdevisvuparRC = new ArrayList<>();

        for (int i =0; i<lesdevisRC.size();i++){
            Integer nbarticles=0;
            Double prixtotal=0.0;
            Integer idDevis=lesdevisRC.get(i).getId_devis();
            Devis devis=DevisLibrary.getInstance().getDevisByiD(idDevis);
            CompteClient compteClient= CompteClientLibrary.getInstance().getCompteClientById(devis.getId_compte_client());
            List<LigneDevis> leslignesdecedevis= LigneDevisLibrary.getInstance().listLignesDevisPourUnDevis(idDevis);
            for (int j=0;j<leslignesdecedevis.size();j++){
                Article article= ArticleLibrary.getInstance().getArticleById(leslignesdecedevis.get(j).getId_article());
                nbarticles=nbarticles+leslignesdecedevis.get(j).quantite;
                prixtotal=prixtotal+leslignesdecedevis.get(j).quantite*article.getPrix();
            }

            lesdevisvuparRC.add(new DevisVuParRC(
                    idDevis,
                    compteClient.getNom_boutique(),
                    lesdevisRC.get(i).getDate(),
                    nbarticles,
                    prixtotal,
                    lesdevisRC.get(i).getEtat()
            ));
        }

        webContext.setVariable("lesdevisRC",lesdevisvuparRC);

        webContext.setVariable("statut",statut);

        templateEngine.process("/administration/devisRC", webContext, resp.getWriter());
    }
}