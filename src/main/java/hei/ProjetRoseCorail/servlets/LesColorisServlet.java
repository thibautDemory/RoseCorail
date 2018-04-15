package hei.ProjetRoseCorail.servlets;

import hei.ProjetRoseCorail.entities.ListeDesSaisons;
import hei.ProjetRoseCorail.entities.Panelcoloris;
import hei.ProjetRoseCorail.managers.PanelColorisLibrary;
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

@WebServlet("/lesColoris")
public class LesColorisServlet extends GenericServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        String statut=(String) req.getSession().getAttribute("statut");
        String modification= req.getParameter("Modification");
        PanelColorisLibrary panelColorisLibrary=PanelColorisLibrary.getInstance();


        if (statut==null||"".equals(statut)||"visiteur".equals(statut)){
            statut="visiteur";
        }else{
            String nom=req.getSession().getAttribute("nom").toString();
            String prenom=req.getSession().getAttribute("prenom").toString();
            webContext.setVariable("prenom",prenom);
            webContext.setVariable("nom",nom);
        }
        //La liste des saisons
        ListeDesSaisons listeDesSaisons = new ListeDesSaisons();
        List<String> lessaisonsenString = new ArrayList<>();
        for (int i =0; i<listeDesSaisons.lessaisons.size();i++){
            lessaisonsenString.add(listeDesSaisons.lessaisons.get(i));
            System.out.println(lessaisonsenString.get(i));
        }
        webContext.setVariable("lessaisons",lessaisonsenString);

        String saisonselect=req.getParameter("saison");
        System.out.println("la saison en cours est:" + saisonselect);
        List<Panelcoloris> lespanelscoloris = panelColorisLibrary.listpanelcolorisparsaison(saisonselect);

        System.out.println(statut);
        webContext.setVariable("statut",statut);
        webContext.setVariable("lespanelscoloris",lespanelscoloris);
        webContext.setVariable("saisonencours",saisonselect);
        webContext.setVariable("modification",modification);

        // On prépare le filtre de date du mois actuel pour la page "fragment.html"
        LocalDate maintenant=LocalDate.now();
        String anneeMoisActuelle = maintenant.toString().substring(0,7);
        webContext.setVariable("anneeMoisActuelle",anneeMoisActuelle);

        templateEngine.process("lescoloris", webContext, resp.getWriter());
    }
}
