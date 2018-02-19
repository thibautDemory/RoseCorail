package hei.ProjetRoseCorail.dao;

import hei.ProjetRoseCorail.entities.Article;

import java.util.List;

public interface ArticleDao {
    public List<Article> listArticles();
    public List<Article> listArticlesPlats();
    public List<Article> listArticlesPlataFromage();
    public List<Article> listArticlesPlataCake();
    public List<Article> listArticlesCoupellesAperitif();
    public List<Article> listArticlesPorteCouteaux();
    public List<Article> listArticlesDeco();
    public List<Article> listArticlesDecoDessousVerre();
    public List<Article> listArticlesDecoDessousPlat();
    public List<Article> listArticlesMaison();

    public Article getArticleByNom (String nom);
    public Article getArticleById (Integer id);
    public Article addArticle(Article article);
    public void deleteArticle(Integer articleId);
    public Article modifierArticle(Article article);

}
