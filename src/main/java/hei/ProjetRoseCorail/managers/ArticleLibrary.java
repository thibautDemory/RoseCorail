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
    public Article addArticle(Article article){return articleDao.addArticle(article);}

    public List<Article> listarticles (){return articleDao.listArticles();}
    public List<Article> listPlats (){return articleDao.listArticles();}
    public List<Article> listDecos (){return articleDao.listArticlesDeco();}
    public List<Article> listPortesCouteaux (){return articleDao.listArticlesPorteCouteaux();}

    public void deleteArticle(Integer id){articleDao.deleteArticle(id);}

}
