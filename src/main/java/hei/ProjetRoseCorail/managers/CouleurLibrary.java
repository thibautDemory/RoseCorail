package hei.ProjetRoseCorail.managers;

import hei.ProjetRoseCorail.dao.ActualiteDao;
import hei.ProjetRoseCorail.dao.CouleurDao;
import hei.ProjetRoseCorail.dao.impl.ActualiteDaoImpl;
import hei.ProjetRoseCorail.dao.impl.CouleurDaoImpl;
import hei.ProjetRoseCorail.entities.Actualite;
import hei.ProjetRoseCorail.entities.Couleur;

import java.util.List;

public class CouleurLibrary {
    private static class CouleurLibraryHolder {
        private final static CouleurLibrary instance = new CouleurLibrary();
    }

    public static CouleurLibrary getInstance() {
        return CouleurLibrary.CouleurLibraryHolder.instance;
    }

    private CouleurDao couleurDao = new CouleurDaoImpl();

    public Couleur addCouleur(Couleur couleur) {
        if (couleur == null) {
            throw new IllegalArgumentException("La couleur ne peut pas être nul.");
        }
        if (couleur.getNom_couleur() == null || "".equals(couleur.getNom_couleur())) {
            throw new IllegalArgumentException("Le nom de la couleur ne peut pas être nul.");
        }
        if ( "".equals(couleur.getNumero_couleur())) {
            throw new IllegalArgumentException("Le numéro de la couleur ne pas être nul.");
        }
        if (couleur.getImage_couleur() == null) {
            throw new IllegalArgumentException("L'image de la couleur ne pas être nul.");
        }
        if (couleur.getSaison() == null) {
            throw new IllegalArgumentException("La saison de la couleur ne pas être nul.");
        }
        return couleurDao.addCouleur(couleur);

    }
    public List<Couleur> listCouleursActives(){return couleurDao.listCouleursActives();}

    public void deleteCouleur(int idCouleur){
        couleurDao.deleteCouleur(idCouleur);
    }

    public Couleur getCouleurByID(Integer idCouleur){
        return couleurDao.getCouleurByID(idCouleur);
    }
    public Couleur getCouleurByName(String name){
        return couleurDao.getCouleurByName(name);
    }
}
