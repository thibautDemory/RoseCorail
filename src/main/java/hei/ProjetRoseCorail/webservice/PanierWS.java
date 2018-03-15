package hei.ProjetRoseCorail.webservice;


import hei.ProjetRoseCorail.managers.LigneDevisLibrary;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;


@Path("PanierWS")
public class PanierWS {

    LigneDevisLibrary ligneDevisLibrary= LigneDevisLibrary.getInstance();
    @POST
    public Response modifierLigneDevisQuantite(@FormParam("idLigneDevis") int idLigneDevis,@FormParam("quantite") int quantite){
         ligneDevisLibrary.modifierLigneDevisQuantite(idLigneDevis,quantite);
         return Response.ok().build();
    }
}
