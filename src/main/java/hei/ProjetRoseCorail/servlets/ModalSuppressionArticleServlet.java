package hei.ProjetRoseCorail.servlets;

import hei.ProjetRoseCorail.entities.Article;
import hei.ProjetRoseCorail.managers.ArticleLibrary;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/administration/ModalSuppressionArticle")
public class ModalSuppressionArticleServlet extends GenericServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        ArticleLibrary articleLibrary=ArticleLibrary.getInstance();

        Integer id=Integer.parseInt(req.getParameter("id"));
        Article cetArticle= articleLibrary.getArticleById(id);
        webContext.setVariable("cetarticle",cetArticle);

        templateEngine.process("/administration/ModalSuppressionArticle", webContext, resp.getWriter());
    }
}
