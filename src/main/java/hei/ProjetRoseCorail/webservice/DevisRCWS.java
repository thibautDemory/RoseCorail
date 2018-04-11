package hei.ProjetRoseCorail.webservice;

import hei.ProjetRoseCorail.managers.DevisLibrary;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("DevisRCWS")
public class DevisRCWS {
    DevisLibrary devisLibrary=DevisLibrary.getInstance();
    @POST
    public Response modifierEtatDevis(@FormParam("idDevis") int idDevis, @FormParam("etat") String etat){
        devisLibrary.changerEtatDevis(idDevis,etat);
        System.out.println("l'état a bien changé");
        return Response.ok().build();
    }

}
