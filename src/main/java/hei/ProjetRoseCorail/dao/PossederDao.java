package hei.ProjetRoseCorail.dao;

import hei.ProjetRoseCorail.entities.Article;
import hei.ProjetRoseCorail.entities.Couleur;
import hei.ProjetRoseCorail.entities.Posseder;

import java.util.List;

public interface PossederDao {
    public List<Couleur> listCouleursPourUnArticle(Integer id_article);
    public List<Article> listArticlesPourUneCouleur( Integer id_couleur);
    public List<Posseder> listLesPosseder();
    public Posseder addPosseder(Posseder posseder);
    public Posseder modifierPosseder (Posseder posseder);
    public void deletePossederForCouleur(Integer idcouleur);
    public void deletePosseder(Integer idPosseder);
}
