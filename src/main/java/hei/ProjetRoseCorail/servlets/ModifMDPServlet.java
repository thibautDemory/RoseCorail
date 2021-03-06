package hei.ProjetRoseCorail.servlets;

import hei.ProjetRoseCorail.entities.Actualite;
import hei.ProjetRoseCorail.entities.CompteClient;
import hei.ProjetRoseCorail.entities.CompteRoseCorail;
import hei.ProjetRoseCorail.entities.EncodingPassword;
import hei.ProjetRoseCorail.managers.ActualiteLibrary;
import hei.ProjetRoseCorail.managers.CompteClientLibrary;
import hei.ProjetRoseCorail.managers.CompteRoseCorailLibrary;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/modifMDP")
public class ModifMDPServlet extends GenericServlet{
    CompteClientLibrary compteClientLibrary = CompteClientLibrary.getInstance();
    CompteRoseCorailLibrary compteRoseCorailLibrary=CompteRoseCorailLibrary.getInstance();
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
        webContext.setVariable("statut",statut);

        // On prépare le filtre de date du mois actuel pour la page "fragment.html"
        LocalDate maintenant=LocalDate.now();
        String anneeMoisActuelle = maintenant.toString().substring(0,7);
        webContext.setVariable("anneeMoisActuelle",anneeMoisActuelle);

        templateEngine.process("/modifMDP", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CompteClient> listClients = compteClientLibrary.listComptesClients();

        // GET PARAM
        String oldPassword = null;
        String newMDP1 = null;
        String newMDP2 = null;

        oldPassword = req.getParameter("oldPassword");
        newMDP1 = req.getParameter("newMDP1");
        newMDP2 = req.getParameter("newMDP2");

        EncodingPassword encodingObject = new EncodingPassword();
        String oldPasswordEncoder = encodingObject.encodePassword(oldPassword);
        String newMDP2Encoder = encodingObject.encodePassword(newMDP2);

        String statut = (String) req.getSession().getAttribute("statut");

        System.out.println(statut);

        if(statut.equals("client")){
            int idClient = (int) req.getSession().getAttribute("idClient");
            String mdp = compteClientLibrary.getCompteClientById(idClient).getMdp();
            if(newMDP1.equals(newMDP2)){
                if(mdp.equals(oldPasswordEncoder)){
                    try {
                        CompteClientLibrary.getInstance().updatePassword(idClient,newMDP2Encoder);
                        // REDIRECT TO Accueil
                        resp.sendRedirect(String.format("/RoseCorail/accueil"));
                    } catch (IllegalArgumentException e) {
                        String errorMessage = e.getMessage();
                        req.getSession().setAttribute("errorMessage", errorMessage);
                        resp.sendRedirect("/RoseCorail/modifMDP");
                    }
                }else{
                    System.out.println("L'ancien mot de passe est incorrect");
                }
            }else{
                System.out.println("Il faut rentrer deux fois le même mot de passe (dans \"Nouveau mot de passe\" et \"Confirmation du nouveau mot de passe\")");
            }
        }else if(statut.equals("admin")){
            int idAdmin = (int) req.getSession().getAttribute("idAdmin");
            String mdp = compteRoseCorailLibrary.getCompteRoseCorailById(idAdmin).getMdp();
            if(newMDP1.equals(newMDP2)){
                if(mdp.equals(oldPasswordEncoder)){
                    try {
                        CompteRoseCorailLibrary.getInstance().updatePassword(idAdmin,newMDP2Encoder);
                        // REDIRECT TO Accueil
                        resp.sendRedirect(String.format("/RoseCorail/accueil"));
                    } catch (IllegalArgumentException e) {
                        String errorMessage = e.getMessage();
                        req.getSession().setAttribute("errorMessage", errorMessage);
                        resp.sendRedirect("/RoseCorail/modifMDP");
                    }
                }else{
                    System.out.println("L'ancien mot de passe est incorrect");
                    resp.sendRedirect("/RoseCorail/modifMDP");
                }
            }else{
                System.out.println("Il faut rentrer deux fois le même mot de passe (dans \"Nouveau mot de passe\" et \"Confirmation du nouveau mot de passe\")");
                resp.sendRedirect("/RoseCorail/modifMDP");
            }
        }

    }
}
