package hei.ProjetRoseCorail.servlets;

import hei.ProjetRoseCorail.entities.Article;
import hei.ProjetRoseCorail.entities.Panelcoloris;
import hei.ProjetRoseCorail.managers.ArticleLibrary;
import hei.ProjetRoseCorail.managers.PanelColorisLibrary;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/administration/ModalSuppressionPanelColoris")
public class ModalSuppressionPanelColorisServlet extends GenericServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        PanelColorisLibrary panelColorisLibrary=PanelColorisLibrary.getInstance();

        Integer id=Integer.parseInt(req.getParameter("id"));
        Panelcoloris cepanelcoloris= panelColorisLibrary.getPanelColorisById(id);
        webContext.setVariable("cepanelcoloris",cepanelcoloris);

        templateEngine.process("ModalSuppressionPanelColoris", webContext, resp.getWriter());
    }
}