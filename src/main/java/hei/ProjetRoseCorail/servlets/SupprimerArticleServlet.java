package hei.ProjetRoseCorail.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Integer.parseInt;

public class SupprimerArticleServlet extends GenericServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        


        System.out.println("cet article a bien été supprimer");
        resp.sendRedirect("/lesPlats");
    }
}
