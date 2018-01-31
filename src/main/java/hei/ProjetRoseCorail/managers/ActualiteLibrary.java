package hei.ProjetRoseCorail.managers;

import hei.ProjetRoseCorail.dao.ActualiteDao;
import hei.ProjetRoseCorail.dao.impl.ActualiteDaoImpl;
import hei.ProjetRoseCorail.entities.Actualite;

public class ActualiteLibrary {

    private static class ActualiteLibraryHolder {
        private final static ActualiteLibrary instance = new ActualiteLibrary();
    }

    public static ActualiteLibrary getInstance() {
        return ActualiteLibraryHolder.instance;
    }

    private ActualiteDao actualiteDao = new ActualiteDaoImpl();

    public Actualite addActualite(Actualite actualite) {
        if (actualite == null) {
            throw new IllegalArgumentException("The cocktail should not be null.");
        }
        if (actualite.getTitreActualite() == null || "".equals(actualite.getTitreActualite())) {
            throw new IllegalArgumentException("Le titre de l'actualite ne peut pas être nul.");
        }
        if (actualite.getContenu() == null || "".equals(actualite.getContenu())) {
            throw new IllegalArgumentException("Le contenu de l'actualité ne pas être nul.");
        }
        if (actualite.getImageActualite() == null) {
            throw new IllegalArgumentException("L'image de l'actualité ne pas être nul.");
        }
        return actualiteDao.addActualite(actualite);

    }

}
