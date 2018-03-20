package hei.ProjetRoseCorail.servlets;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@MultipartConfig
@WebServlet("/administration/modifierphotosaccueil")
public class ModifierPhotosAccueilServlet extends GenericServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        String statut=(String) req.getSession().getAttribute("statut");

        webContext.setVariable("statut",statut);

        templateEngine.process("/administration/modifierphotosaccueil", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //on récupère les param
        String nomphoto;
        String photo;
        File filequicontientlimage;
        try{
            nomphoto=req.getParameter("photo");
            filequicontientlimage= new File("D:\\Informatique\\Projet 100h\\RoseCorail\\src\\main\\webapp\\images\\"+nomphoto);
            filequicontientlimage.mkdirs();
            Part imagePart = req.getPart("nouvellephoto");
            imagePart.write(filequicontientlimage.getAbsolutePath()+"/image.jpg");
        }
        catch (IllegalArgumentException e){
            String error=e.getMessage();
            System.out.println(error);
        }
        resp.sendRedirect("/administration/modifierphotosaccueil");
    }
}
