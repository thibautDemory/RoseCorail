package hei.ProjetRoseCorail.servlets;

import hei.ProjetRoseCorail.entities.Article;
import hei.ProjetRoseCorail.managers.ArticleLibrary;
import hei.ProjetRoseCorail.managers.PossederLibrary;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Integer.bitCount;
import static java.lang.Integer.parseInt;
@WebServlet("/administration/SupprimerArticle")
public class SupprimerArticleServlet extends GenericServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idArticle=Integer.parseInt(req.getParameter("id"));
        Article articleasuppr=ArticleLibrary.getInstance().getArticleById(idArticle);
        Integer souscategorie=articleasuppr.getId_sous_categorie();
        ArticleLibrary.getInstance().rendreinactifArticle(idArticle);
        switch(souscategorie ){
            case 1:
                resp.sendRedirect("/lesPlats?Modification=active");
                break;
            case 2:
                resp.sendRedirect("/lesPlats?Modification=active");
                break;
            case 3:
                resp.sendRedirect("/lesPlats?Modification=active");
                break;
            case 4:
                resp.sendRedirect("/lesportecouteaux?Modification=active");
                break;
            case 5:
                resp.sendRedirect("/décorationdetable?Modification=active");
                break;
            case 6:
                resp.sendRedirect("/décorationdetable?Modification=active");
                break;
            case 7:
                resp.sendRedirect("/lamaison?Modification=active");
                break;
            default:
                resp.sendRedirect("/uneerreur");

        }


        System.out.println("cet article a bien été supprimer");

    }
}
