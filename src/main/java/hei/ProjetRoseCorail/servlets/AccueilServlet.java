package hei.ProjetRoseCorail.servlets;


import hei.ProjetRoseCorail.entities.CompteClient;
import hei.ProjetRoseCorail.managers.CompteClientLibrary;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/accueil")
public class AccueilServlet extends GenericServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CompteClientLibrary compteClientLibrary = CompteClientLibrary.getInstance();
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        String statut=(String) req.getSession().getAttribute("statut");
        //CompteClient client=compteClientLibrary.getCompteClientById(Integer.valueOf(req.getSession().getAttribute("idClient").toString()));


        if (statut==null||"".equals(statut)){
            statut="visiteur";
        }else{
            String nom=req.getSession().getAttribute("nomClient").toString();
            String prenom=req.getSession().getAttribute("prenomClient").toString();
            webContext.setVariable("prenom",prenom);
            webContext.setVariable("nom",nom);
        }
        System.out.println(statut);
        webContext.setVariable("statut",statut);


        templateEngine.process("accueil", webContext, resp.getWriter());
    }
}
