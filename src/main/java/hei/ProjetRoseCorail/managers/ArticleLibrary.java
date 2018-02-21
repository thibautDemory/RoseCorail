package hei.ProjetRoseCorail.managers;

import hei.ProjetRoseCorail.dao.ArticleDao;
import hei.ProjetRoseCorail.dao.impl.ArticleDaoImpl;
import hei.ProjetRoseCorail.entities.Article;

import java.util.List;

public class ArticleLibrary {
    private static class ArticleLibraryHolder {
        private final static ArticleLibrary instance = new ArticleLibrary();
    }

    public static ArticleLibrary getInstance() {
        return ArticleLibraryHolder.instance;
    }

    private ArticleDao articleDao = new ArticleDaoImpl();
    public Article addArticle(Article article){
        if (article == null) {
            throw new IllegalArgumentException("L'article ne peut pas etre vide");
        }
        if (article.getDescription()== null || "".equals(article.getDescription())) {
            throw new IllegalArgumentException("La description de l'article doit être remplis");
        }
        if (article.getDimension()== null || "".equals(article.getDimension())) {
            throw new IllegalArgumentException("La dimension de l'article doit être remplis");
        }
        if (article.getId_sous_categorie()== null || "".equals(article.getId_sous_categorie())) {
            throw new IllegalArgumentException("La sous catégorie de l'article doit être remplis");
        }
        if (article.getImage()== null || "".equals(article.getImage())) {
            throw new IllegalArgumentException("L'image de l'article doit être rempli");
        }
        if (article.getLot_vente()== null || "".equals(article.getLot_vente())) {
            throw new IllegalArgumentException("Le lot de vente de l'article doit etre renseigné");
        }
        if (article.getNom_article()== null || "".equals(article.getNom_article())) {
            throw new IllegalArgumentException("Le nom de l'article doit être remplis");
        }
        if (article.getPrix()==0.0) {
            throw new IllegalArgumentException("Le prix de l'article doit être remplis");
        }
        if (article.getReference()== null || "".equals(article.getReference())) {
            throw new IllegalArgumentException("La réference de l'article doit être remplis");
        }
        return articleDao.addArticle(article);}

    public List<Article> listarticles (){return articleDao.listArticles();}
    //liste des plats
    public List<Article> listPlats (){return articleDao.listArticles();}
    public List<Article> listPlatsPlatACake (){return articleDao.listArticlesPlataCake();}
    public List<Article> listPlatsPlatAFromage (){return articleDao.listArticlesPlataFromage();}
    public List<Article> listPlatsCoupelle (){return articleDao.listArticlesCoupellesAperitif();}
    //liste des portes couteaux
    public List<Article> listPortesCouteaux (){return articleDao.listArticlesPorteCouteaux();}
    // liste des élements de déco
    public List<Article> listDecos (){return articleDao.listArticlesDeco();}
    public List<Article> listDecosDessousPlat (){return articleDao.listArticlesDecoDessousPlat();}
    public List<Article> listDecosDessousVerre (){return articleDao.listArticlesDecoDessousVerre();}
    //liste des articles de la maison
    public List<Article> listeArticlesMaison (){return articleDao.listArticlesMaison();}

    public Article getArticleById(Integer idarticle){return articleDao.getArticleById(idarticle);}


    public void deleteArticle(Integer id){articleDao.deleteArticle(id);}

}
