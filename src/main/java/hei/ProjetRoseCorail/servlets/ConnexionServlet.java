package hei.ProjetRoseCorail.servlets;

import com.sun.org.apache.xpath.internal.operations.Bool;
import hei.ProjetRoseCorail.dao.impl.CompteClientDaoImpl;
import hei.ProjetRoseCorail.entities.CompteClient;
import hei.ProjetRoseCorail.entities.CompteRoseCorail;
import hei.ProjetRoseCorail.managers.CompteClientLibrary;
import hei.ProjetRoseCorail.managers.CompteRoseCorailLibrary;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/connexion")
public class ConnexionServlet extends GenericServlet{
 CompteClientLibrary compteClientLibrary = CompteClientLibrary.getInstance();
 CompteRoseCorailLibrary compteRoseCorailLibrary=CompteRoseCorailLibrary.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        String statut=(String) req.getSession().getAttribute("statut");
        Boolean mauvaismotdepasse=(Boolean) req.getSession().getAttribute("mauvaismotdepasse");
        webContext.setVariable("mauvaismotdepasse",mauvaismotdepasse);
        if (statut==null||"".equals(statut)){
            statut="visiteur";
        }
        webContext.setVariable("statut",statut);


        templateEngine.process("connexion", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String adresseEmailRentree=req.getParameter("email");
        String motDePasseRentree=req.getParameter("pwd");
        if(adresseEmailRentree.equals("beatrice.roquette@rosecorail.com")){
            CompteRoseCorail administrateur = compteRoseCorailLibrary.getCompteRoseCorailByMail(adresseEmailRentree);
            if (administrateur.getMdp().equals(motDePasseRentree)){
                req.getSession().setAttribute("idAdmin",administrateur.getId_compte_rose_corail());
                req.getSession().setAttribute("nom","Roquette");
                req.getSession().setAttribute("prenom","Béatrice");
                req.getSession().setAttribute("statut","admin");
                resp.sendRedirect("accueil");
            }
            else{
                System.out.println("Mot de passe de l'administrateur incorrect !");
            }
        }else{
            List<CompteClient> lesclients=compteClientLibrary.listComptesClients();
            boolean emailexiste=false;
            int i=0;

            while (i<lesclients.size() && emailexiste==false){
                if(lesclients.get(i).getEmail().equals(adresseEmailRentree)){
                    emailexiste=true;
                }
                i++;
            }
            if(emailexiste){
                CompteClient client= compteClientLibrary.getCompteClientByMail(adresseEmailRentree);
                if (client.getMdp().equals(motDePasseRentree)) {
                    req.getSession().setAttribute("idClient", client.getId_compte_client());
                    req.getSession().setAttribute("nom", client.getNom_gerant());
                    req.getSession().setAttribute("prenom", client.getPrenom_gerant());
                    req.getSession().setAttribute("statut", "client");
                    resp.sendRedirect("accueil");
                }else{
                    req.getSession().setAttribute("mauvaismotdepasse",true);
                    resp.sendRedirect("connexion");
                }
            }else{
                resp.sendRedirect("/connexion");
                System.out.println("Ce compte n'existe pas");
            }

        }
    }
}
