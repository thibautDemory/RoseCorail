package hei.ProjetRoseCorail.servlets;

import hei.ProjetRoseCorail.entities.*;
import hei.ProjetRoseCorail.managers.ArticleLibrary;
import hei.ProjetRoseCorail.managers.CouleurLibrary;
import hei.ProjetRoseCorail.managers.DevisLibrary;
import hei.ProjetRoseCorail.managers.PossederLibrary;
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

@WebServlet("/modalClient")
public class ModalClientServlet extends GenericServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());


        Integer id=Integer.parseInt(req.getParameter("id"));
        req.getSession().setAttribute("idArticlequejeregarde",id);
        ArticleLibrary articleLibrary=ArticleLibrary.getInstance();
        CouleurLibrary couleurLibrary= CouleurLibrary.getInstance();
        PossederLibrary possederLibrary= PossederLibrary.getInstance();


        Article cetArticle= articleLibrary.getArticleById(id);
        List<Couleur> lescouleursdecetarticle=possederLibrary.listCouleursPourUnArticle(id);

        webContext.setVariable("cetarticle",cetArticle);
        webContext.setVariable("sescouleurs",lescouleursdecetarticle);


        templateEngine.process("modalClient", webContext, resp.getWriter());
    }


}
