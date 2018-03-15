package hei.ProjetRoseCorail.managers;

import hei.ProjetRoseCorail.dao.LigneDevisDao;
import hei.ProjetRoseCorail.dao.impl.LigneDevisDaoImpl;
import hei.ProjetRoseCorail.entities.LigneDevis;

import java.util.List;

public class LigneDevisLibrary {
    private static class LigneDevisLibraryHolder {
        private final static LigneDevisLibrary instance= new LigneDevisLibrary();
    }
    public static LigneDevisLibrary getInstance() {return LigneDevisLibrary.LigneDevisLibraryHolder.instance;}

    private LigneDevisDao ligneDevisDao=new LigneDevisDaoImpl();
    public LigneDevis addLigneDevis(LigneDevis ligneDevis){return ligneDevisDao.addLigneDevis(ligneDevis); }


    public List<LigneDevis> listAllLigneDevis(){return ligneDevisDao.listAllLigneDevis();}

    public List<LigneDevis> listLignesDevisPourUneCouleur(Integer idCouleur){return ligneDevisDao.listLignesDevisPourUneCouleur(idCouleur);}
    public List<LigneDevis> listLignesDevisPourUnArticle(Integer idArticle){return ligneDevisDao.listLignesDevisPourUnArticle(idArticle);};
    public List<LigneDevis> listLignesDevisPourUnDevis(Integer idDevis){return ligneDevisDao.listLignesDevisPourUnDevis(idDevis);}
    public void modifierLigneDevisQuantite(Integer idlignedevis, Integer quantite){ ligneDevisDao.modifierQuantiteLigneDevis(idlignedevis,quantite);
    }
    public void deleteLigneDevis(Integer idLigneDevis){ligneDevisDao.deleteLigneDevis(idLigneDevis);};
    public void deleteLigneDevisForOneCouleur(Integer idCouleur){ligneDevisDao.deleteLigneDevisForOneCouleur(idCouleur);};
}
