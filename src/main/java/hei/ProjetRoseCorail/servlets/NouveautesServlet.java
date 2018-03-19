package hei.ProjetRoseCorail.servlets;

import hei.ProjetRoseCorail.entities.Article;
import hei.ProjetRoseCorail.managers.ArticleLibrary;
import hei.ProjetRoseCorail.managers.CompteClientLibrary;
import hei.ProjetRoseCorail.managers.CompteRoseCorailLibrary;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet("/nouveautes")
public class NouveautesServlet extends GenericServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CompteClientLibrary compteClientLibrary = CompteClientLibrary.getInstance();
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        String statut=(String) req.getSession().getAttribute("statut");


        ArticleLibrary articleLibrary = ArticleLibrary.getInstance();
        List<Article> listOfArticles =  articleLibrary.listarticles();
        List<Article> listOf9LastArticles = new ArrayList<Article>();

        int listSize =  listOfArticles.size();
        if(listSize > 9){
            for(int i=(listSize-1); i>=(listSize-9); i--){
                listOf9LastArticles.add(listOfArticles.get(i));
            }
        }else{
            for(int i=(listSize-1); i>=0; i--){
                listOf9LastArticles.add(listOfArticles.get(i));
            }
        }
        System.out.println("Size : "+listSize);
        System.out.println("Real size : "+listOf9LastArticles.size());
        webContext.setVariable("listOf9LastArticles",listOf9LastArticles);


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


        templateEngine.process("nouveautes", webContext, resp.getWriter());
    }
}
