package hei.ProjetRoseCorail.servlets;

import hei.ProjetRoseCorail.managers.DevisLibrary;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/administration/SuppressionDevis")
public class SuppressionDevisServlet extends GenericServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            DevisLibrary devisLibrary=DevisLibrary.getInstance();
            WebContext webContext = new WebContext(req, resp, req.getServletContext());
            TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
            String idasupprimer= req.getParameter("id");
            devisLibrary.annulerdevis(Integer.parseInt(idasupprimer));


            resp.sendRedirect("/RoseCorail/devisRC");
    }
}
