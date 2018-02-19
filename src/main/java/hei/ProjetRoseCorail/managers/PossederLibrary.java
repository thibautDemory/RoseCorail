package hei.ProjetRoseCorail.managers;

import hei.ProjetRoseCorail.dao.PossederDao;
import hei.ProjetRoseCorail.dao.impl.PossederDaoImpl;
import hei.ProjetRoseCorail.entities.Posseder;

public class PossederLibrary {
    private static class PossederLibraryHolder {
        private final static PossederLibrary instance= new PossederLibrary();
    }
    public static PossederLibrary getInstance() {return PossederLibrary.PossederLibraryHolder.instance;}

    private PossederDao possederDao=new PossederDaoImpl();

    public Posseder addPosseder(Posseder posseder){
        if (posseder==null){
            throw new IllegalArgumentException("Le Posseder ne peut pas être nul.");
        }
        if (posseder.getId_article()==null){
            throw new IllegalArgumentException("Le Posseder doit avoir un idarticle.");
        }
        if (posseder.getId_couleur()==null){
            throw new IllegalArgumentException("Le Posseder doit avoir une couleur.");
        }
        return possederDao.addPosseder(posseder);
    }

}
