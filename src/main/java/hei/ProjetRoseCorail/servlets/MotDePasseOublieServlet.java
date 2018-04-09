package hei.ProjetRoseCorail.servlets;

import hei.ProjetRoseCorail.entities.CompteClient;
import hei.ProjetRoseCorail.entities.EncodingPassword;
import hei.ProjetRoseCorail.entities.Mail;
import hei.ProjetRoseCorail.entities.PasswordGenerator;
import hei.ProjetRoseCorail.managers.ActualiteLibrary;
import hei.ProjetRoseCorail.managers.CompteClientLibrary;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import static java.lang.Integer.parseInt;

@WebServlet("/motDePasseOublie")
public class MotDePasseOublieServlet extends GenericServlet {
    CompteClientLibrary compteClientLibrary = CompteClientLibrary.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        String statut=(String) req.getSession().getAttribute("statut");
        Boolean emailEntre = (Boolean) req.getSession().getAttribute("emailEntre");
        webContext.setVariable("emailEntre",emailEntre);

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
        templateEngine.process("motDePasseOublie", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String adresseEmail=req.getParameter("email");
        System.out.println(adresseEmail);
        List<CompteClient> lesclients = compteClientLibrary.listComptesClients();

        int n = 0;
        boolean flag = false;
        int idClient;

        while(flag == false){
            if(lesclients.get(n).getEmail().equals(adresseEmail)) {
                flag = true;
                PasswordGenerator passwordGenerator = new PasswordGenerator();
                String password = passwordGenerator.generatePassword(10);
                EncodingPassword endocingObject = new EncodingPassword();
                String passwordEncoded = endocingObject.encodePassword(password);

                Mail mailMDP = new Mail(adresseEmail);
                mailMDP.mailing(adresseEmail,password);
                CompteClient compteClient = compteClientLibrary.getCompteClientByMail(adresseEmail);
                idClient = compteClient.getId_compte_client();
                compteClientLibrary.updatePassword(idClient,passwordEncoded);
                System.out.println("Mail envoyé !");
                req.getSession().setAttribute("emailEntre",true);

                resp.sendRedirect(String.format("/RoseCorail/accueil"));
            }else if(n==lesclients.size()-1){
                flag = true;
                System.out.println("Pas trouvé");
                req.getSession().setAttribute("emailEntre",false);
                resp.sendRedirect(String.format("/RoseCorail/motDePasseOublie"));
            }else{
                n++;
            }
        }
    }
}
