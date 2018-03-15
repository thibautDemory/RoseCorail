package hei.ProjetRoseCorail.managers;

import hei.ProjetRoseCorail.dao.PossederDao;
import hei.ProjetRoseCorail.dao.impl.PossederDaoImpl;
import hei.ProjetRoseCorail.entities.Article;
import hei.ProjetRoseCorail.entities.Couleur;
import hei.ProjetRoseCorail.entities.Posseder;

import java.util.List;

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
    public List<Article> listArticlesPourUneCouleur(Integer idCouleur){
        return possederDao.listArticlesPourUneCouleur(idCouleur);
    }
    public List<Couleur> listCouleursPourUnArticle(Integer idArticle){
        return possederDao.listCouleursPourUnArticle(idArticle);
    }
    public void deletePossederForCouleur(Integer idcoloris){
        possederDao.deletePossederForCouleur(idcoloris);
    }
    public void deletePossederForArticle(Integer idArticle){
        possederDao.deletePossederForArticle(idArticle);
    }

}
