package hei.ProjetRoseCorail.servlets;

import hei.ProjetRoseCorail.entities.Devis;
import hei.ProjetRoseCorail.managers.DevisLibrary;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/ModalAnnulationDevis")
public class ModalAnnulationDevisServlet extends GenericServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        Integer id=Integer.parseInt(req.getParameter("id"));
        Devis devis=DevisLibrary.getInstance().getDevisByiD(id);
        System.out.println(devis.getId_devis());
        webContext.setVariable("cedevis",devis);
        templateEngine.process("ModalAnnulationDevis", webContext, resp.getWriter());
    }
}
