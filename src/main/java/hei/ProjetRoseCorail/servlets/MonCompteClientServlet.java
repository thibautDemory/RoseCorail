package hei.ProjetRoseCorail.servlets;

import hei.ProjetRoseCorail.dao.CompteClientDao;
import hei.ProjetRoseCorail.entities.Actualite;
import hei.ProjetRoseCorail.entities.CompteClient;
import hei.ProjetRoseCorail.managers.ActualiteLibrary;
import hei.ProjetRoseCorail.managers.CompteClientLibrary;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import static java.lang.Integer.parseInt;

@WebServlet("/monCompteClient")
public class MonCompteClientServlet extends GenericServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        String statut=(String) req.getSession().getAttribute("statut");

        if (statut==null||"".equals(statut)){
            statut="visiteur";
        }else{
            String nom=req.getSession().getAttribute("nom").toString();
            String prenom=req.getSession().getAttribute("prenom").toString();
            webContext.setVariable("prenom",prenom);
            webContext.setVariable("nom",nom);
        }
        System.out.println(statut);
        webContext.setVariable("statut",statut);

        Integer idClient= (Integer) req.getSession().getAttribute("idClient");
        CompteClient compteClient = CompteClientLibrary.getInstance().getCompteClientByIdWithoutIdPanier(idClient);

        int idCompteClient = compteClient.getId_compte_client();
        String email = compteClient.getEmail();
        String nomBoutique = compteClient.getNom_boutique();
        String nomGerant = compteClient.getNom_gerant();
        String prenomGerant = compteClient.getPrenom_gerant();
        String adresse = compteClient.getAdresse();
        String ville = compteClient.getVille();
        String codePostal = compteClient.getCode_postal();
        String numeroTel = compteClient.getNumero_tel();
        String numeroTva = compteClient.getNum_tva();
        String siteInternet = compteClient.getSite_internet();
        String descriptionActivite = compteClient.getDescription_activite();

        webContext.setVariable("idCompteClient", idCompteClient);
        webContext.setVariable("email", email);
        webContext.setVariable("nomBoutique", nomBoutique);
        webContext.setVariable("nomGerant", nomGerant);
        webContext.setVariable("prenomGerant", prenomGerant);
        webContext.setVariable("adresse", adresse);
        webContext.setVariable("ville", ville);
        webContext.setVariable("codePostal", codePostal);
        webContext.setVariable("numeroTel", numeroTel);
        webContext.setVariable("numeroTva", numeroTva);
        webContext.setVariable("siteInternet", siteInternet);
        webContext.setVariable("descriptionActivite", descriptionActivite);

        // On prépare le filtre de date du mois actuel pour la page "fragment.html"
        LocalDate maintenant=LocalDate.now();
        String anneeMoisActuelle = maintenant.toString().substring(0,7);
        webContext.setVariable("anneeMoisActuelle",anneeMoisActuelle);

        templateEngine.process("monCompteClient", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // GET PARAMETERS
        int idCompteClient = 0;
        String nomGerant = null;
        String prenomGerant = null;
        String nomBoutique = null;
        String codePostal = null;
        String ville = null;
        String email = null;
        String numeroTva = null;
        String numeroTel = null;
        String siteInternet = null;
        String descriptionActivite = null;
        String adresse = null;

        try{
            idCompteClient = parseInt(req.getParameter("idCompteClient"));
            nomGerant = req.getParameter("nomGerant");
            prenomGerant = req.getParameter("prenomGerant");
            nomBoutique = req.getParameter("nomBoutique");
            codePostal = req.getParameter("codePostal");
            ville = req.getParameter("ville");
            email = req.getParameter("email");
            numeroTva = req.getParameter("numeroTva");
            numeroTel = req.getParameter("numeroTel");
            siteInternet = req.getParameter("siteInternet");
            descriptionActivite = req.getParameter("descriptionActivite");
            adresse = req.getParameter("adresse");

        }catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();
            req.getSession().setAttribute("errorMessage", errorMessage);
            resp.sendRedirect("/RoseCorail/monCompteClient");
        }


        System.out.println("id = "+idCompteClient+" ; nomGerant = "+nomGerant+" ; prenomGerant = "+prenomGerant+" ; nomBoutique = "+nomBoutique+" ; email = "+email);
        System.out.println("codePostal = "+codePostal+" ; ville = "+ville+" ; TVA = "+numeroTva+" ; telephone = "+numeroTel+" ; siteInternet = "+siteInternet);
        System.out.println("description = "+descriptionActivite+" ; adresse = "+adresse);

        // UPDATE CompteClient
        CompteClient compteClient = new CompteClient(idCompteClient, email, nomBoutique, nomGerant, prenomGerant, adresse, ville, codePostal, numeroTel, numeroTva, siteInternet, descriptionActivite);
        try {
            CompteClient updateCompteClient = CompteClientLibrary.getInstance().updateCompteClientWithoutPassword(compteClient);

            // REDIRECT TO ACCUEIL
            resp.sendRedirect(String.format("/RoseCorail/accueil"));
        } catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();

            req.getSession().setAttribute("errorMessage", errorMessage);

            resp.sendRedirect("/RoseCorail/monCompteClient");
        }
    }
}
