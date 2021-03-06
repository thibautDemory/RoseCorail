package hei.ProjetRoseCorail.managers;

import hei.ProjetRoseCorail.dao.ActualiteDao;
import hei.ProjetRoseCorail.dao.impl.ActualiteDaoImpl;
import hei.ProjetRoseCorail.entities.Actualite;

import java.util.List;

public class ActualiteLibrary {

    private static class ActualiteLibraryHolder {
        private final static ActualiteLibrary instance = new ActualiteLibrary();
    }

    public static ActualiteLibrary getInstance() {
        return ActualiteLibraryHolder.instance;
    }

    private ActualiteDao actualiteDao = new ActualiteDaoImpl();

    public List<Actualite> listActualites(){
        return actualiteDao.listActualites();
    }

    public Actualite getActualiteByID(int id){
        return actualiteDao.getActualiteByID(id);
    }

    public Actualite addActualite(Actualite actualite) {
        if (actualite == null) {
            throw new IllegalArgumentException("L'actualite ne peut pas être nulle.");
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

    public void deleteActualite(Integer idActualite){
        actualiteDao.deleteActualite(idActualite);
    }


}
