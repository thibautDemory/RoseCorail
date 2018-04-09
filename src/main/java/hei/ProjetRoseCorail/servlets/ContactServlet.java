package hei.ProjetRoseCorail.servlets;

import hei.ProjetRoseCorail.entities.CompteClient;
import hei.ProjetRoseCorail.entities.CompteRoseCorail;
import hei.ProjetRoseCorail.entities.Mail;
import hei.ProjetRoseCorail.entities.PasswordGenerator;
import hei.ProjetRoseCorail.managers.CompteRoseCorailLibrary;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/Contact")
public class ContactServlet extends GenericServlet{
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
        CompteRoseCorailLibrary compteRoseCorailLibrary=CompteRoseCorailLibrary.getInstance();
        CompteRoseCorail compteRoseCorail=compteRoseCorailLibrary.getCompteRoseCorailById(1);
        webContext.setVariable("compteRoseCorail",compteRoseCorail);
        webContext.setVariable("statut",statut);

        templateEngine.process("Contact", webContext, resp.getWriter());
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email=req.getParameter("email-contact");
        String nom = req.getParameter("nom-contact");
        String message = req.getParameter("message-contact");

        Mail sendingMail = new Mail(email);
        sendingMail.sendMailWithMessageAndName(email,nom,message);

        System.out.println(email);

        resp.sendRedirect(String.format("/RoseCorail/accueil"));


    }
}

