package hei.ProjetRoseCorail.servlets;

import hei.ProjetRoseCorail.entities.PhotosPresentation;
import hei.ProjetRoseCorail.managers.PhotoPresentationLibrary;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/presentationCollection")
public class PresentationCollectionServlet extends GenericServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        String statut=(String) req.getSession().getAttribute("statut");

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
        List<PhotosPresentation> lesphotoscollection= PhotoPresentationLibrary.getInstance().listphotoCollection();
        webContext.setVariable("lesphotoscollection",lesphotoscollection);

        templateEngine.process("presentationcollection", webContext, resp.getWriter());
    }
}
