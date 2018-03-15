package hei.ProjetRoseCorail.servlets;

import hei.ProjetRoseCorail.managers.ArticleLibrary;
import hei.ProjetRoseCorail.managers.PossederLibrary;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Integer.parseInt;
@WebServlet("/SupprimerArticle")
public class SupprimerArticleServlet extends GenericServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idArticle=Integer.parseInt(req.getParameter("id"));
        PossederLibrary.getInstance().deletePossederForArticle(idArticle);
        ArticleLibrary.getInstance().deleteArticle(idArticle);


        System.out.println("cet article a bien été supprimer");
        resp.sendRedirect("/lesPlats");
    }
}
