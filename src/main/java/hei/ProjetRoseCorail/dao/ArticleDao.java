package hei.ProjetRoseCorail.dao;

import hei.ProjetRoseCorail.entities.Article;

import java.util.List;

public interface ArticleDao {
    public List<Article> listArticles();
    public List<Article> listArticlesPlats();
    public List<Article> listArticlesPlataFromage();
    public List<Article> listArticlesPlataCake();
    public List<Article> listArticlesCoupelles();
    public List<Article> listArticlesDeco();
    public List<Article> listArticlesDecoTable();
    public List<Article> listArticlesMaison();
    public List<Article> listArticlesPorteCouteaux();
    public Article getArticleByNom (String nom);
    public Article getArticleById (Integer id);
    public Article addArticle(Article article);
    public void deleteArticle(Integer articleId);
    public Article modifierArticle(Article article);

}
