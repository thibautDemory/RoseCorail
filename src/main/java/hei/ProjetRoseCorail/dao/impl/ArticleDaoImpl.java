package hei.ProjetRoseCorail.dao.impl;

import hei.ProjetRoseCorail.dao.ArticleDao;
import hei.ProjetRoseCorail.entities.Article;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDaoImpl implements ArticleDao {
    @Override
    public List<Article> listArticles() {
        String query = "SELECT * FROM article;";
        List<Article> listofArticles = new ArrayList<>();
        try (
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                listofArticles.add(new Article(
                        resultSet.getInt("id_article"),
                        resultSet.getInt("id_sous_categorie"),
                        resultSet.getString("nom_article"),
                        resultSet.getString("reference"),
                        resultSet.getString("description"),
                        resultSet.getString("image"),
                        resultSet.getString("dimension"),
                        resultSet.getDouble("prix"),
                        resultSet.getInt("lot_vente")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listofArticles;
    }

    @Override
    public List<Article> listArticlesPlats() {
        String query = "SELECT * FROM article WHERE id_sous_categorie=1 or id_sous_categorie=2 or id_sous_categorie=3;";
        List<Article> listofArticles = new ArrayList<>();
        try (
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                listofArticles.add(new Article(
                        resultSet.getInt("id_article"),
                        resultSet.getInt("id_sous_categorie"),
                        resultSet.getString("nom_article"),
                        resultSet.getString("reference"),
                        resultSet.getString("description"),
                        resultSet.getString("image"),
                        resultSet.getString("dimension"),
                        resultSet.getDouble("prix"),
                        resultSet.getInt("lot_vente")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listofArticles;
    }

    @Override
    public List<Article> listArticlesPlataFromage() {
        String query = "SELECT * FROM article WHERE id_sous_categorie=2;";
        List<Article> listofArticles = new ArrayList<>();
        try (
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                listofArticles.add(new Article(
                        resultSet.getInt("id_article"),
                        resultSet.getInt("id_sous_categorie"),
                        resultSet.getString("nom_article"),
                        resultSet.getString("reference"),
                        resultSet.getString("description"),
                        resultSet.getString("image"),
                        resultSet.getString("dimension"),
                        resultSet.getDouble("prix"),
                        resultSet.getInt("lot_vente")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listofArticles;
    }

    @Override
    public List<Article> listArticlesPlataCake() {
        String query = "SELECT * FROM article WHERE id_sous_categorie=1;";
        List<Article> listofArticles = new ArrayList<>();
        try (
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                listofArticles.add(new Article(
                        resultSet.getInt("id_article"),
                        resultSet.getInt("id_sous_categorie"),
                        resultSet.getString("nom_article"),
                        resultSet.getString("reference"),
                        resultSet.getString("description"),
                        resultSet.getString("image"),
                        resultSet.getString("dimension"),
                        resultSet.getDouble("prix"),
                        resultSet.getInt("lot_vente")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listofArticles;
    }

    @Override
    public List<Article> listArticlesCoupelles() {
        String query = "SELECT * FROM article WHERE id_sous_categorie=3;";
        List<Article> listofArticles = new ArrayList<>();
        try (
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                listofArticles.add(new Article(
                        resultSet.getInt("id_article"),
                        resultSet.getInt("id_sous_categorie"),
                        resultSet.getString("nom_article"),
                        resultSet.getString("reference"),
                        resultSet.getString("description"),
                        resultSet.getString("image"),
                        resultSet.getString("dimension"),
                        resultSet.getDouble("prix"),
                        resultSet.getInt("lot_vente")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listofArticles;
    }

    @Override
    public List<Article> listArticlesDeco() {
        String query = "SELECT * FROM article WHERE id_sous_categorie=5 or id_sous_categorie=6;";
        List<Article> listofArticles = new ArrayList<>();
        try (
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                listofArticles.add(new Article(
                        resultSet.getInt("id_article"),
                        resultSet.getInt("id_sous_categorie"),
                        resultSet.getString("nom_article"),
                        resultSet.getString("reference"),
                        resultSet.getString("description"),
                        resultSet.getString("image"),
                        resultSet.getString("dimension"),
                        resultSet.getDouble("prix"),
                        resultSet.getInt("lot_vente")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listofArticles;
    }

    @Override
    public List<Article> listArticlesDecoTable() {
        String query = "SELECT * FROM article WHERE id_sous_categorie=5;";
        List<Article> listofArticles = new ArrayList<>();
        try (
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                listofArticles.add(new Article(
                        resultSet.getInt("id_article"),
                        resultSet.getInt("id_sous_categorie"),
                        resultSet.getString("nom_article"),
                        resultSet.getString("reference"),
                        resultSet.getString("description"),
                        resultSet.getString("image"),
                        resultSet.getString("dimension"),
                        resultSet.getDouble("prix"),
                        resultSet.getInt("lot_vente")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listofArticles;
    }

    @Override
    public List<Article> listArticlesMaison() {
        String query = "SELECT * FROM article WHERE id_sous_categorie=6;";
        List<Article> listofArticles = new ArrayList<>();
        try (
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                listofArticles.add(new Article(
                        resultSet.getInt("id_article"),
                        resultSet.getInt("id_sous_categorie"),
                        resultSet.getString("nom_article"),
                        resultSet.getString("reference"),
                        resultSet.getString("description"),
                        resultSet.getString("image"),
                        resultSet.getString("dimension"),
                        resultSet.getDouble("prix"),
                        resultSet.getInt("lot_vente")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listofArticles;
    }

    @Override
    public List<Article> listArticlesPorteCouteaux() {
        String query = "SELECT * FROM article WHERE id_sous_categorie=4;";
        List<Article> listofArticles = new ArrayList<>();
        try (
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                listofArticles.add(new Article(
                        resultSet.getInt("id_article"),
                        resultSet.getInt("id_sous_categorie"),
                        resultSet.getString("nom_article"),
                        resultSet.getString("reference"),
                        resultSet.getString("description"),
                        resultSet.getString("image"),
                        resultSet.getString("dimension"),
                        resultSet.getDouble("prix"),
                        resultSet.getInt("lot_vente")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listofArticles;
    }

    @Override
    public Article getArticleByNom(String nom) {
        String query="SELECT * FROM article WHERE nom_article=?;";
        try(Connection connection = DataSourceProvider.getDataSource().getConnection();
            PreparedStatement statement= connection.prepareStatement(query)){
            statement.setString(1,nom);
            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    return new Article(
                            resultSet.getInt("id_article"),
                            resultSet.getInt("id_sous_categorie"),
                            resultSet.getString("nom_article"),
                            resultSet.getString("reference"),
                            resultSet.getString("description"),
                            resultSet.getString("image"),
                            resultSet.getString("dimension"),
                            resultSet.getDouble("prix"),
                            resultSet.getInt("lot_vente"));
                }
            }
        }catch (SQLException e){
                e.printStackTrace();
        }
        return null;
    }

    @Override
    public Article getArticleById(Integer id) {
        String query="SELECT * FROM article WHERE id_article=?;";
        try(Connection connection = DataSourceProvider.getDataSource().getConnection();
            PreparedStatement statement= connection.prepareStatement(query)){
            statement.setInt(1,id);
            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    return new Article(
                            resultSet.getInt("id_article"),
                            resultSet.getInt("id_sous_categorie"),
                            resultSet.getString("nom_article"),
                            resultSet.getString("reference"),
                            resultSet.getString("description"),
                            resultSet.getString("image"),
                            resultSet.getString("dimension"),
                            resultSet.getDouble("prix"),
                            resultSet.getInt("lot_vente"));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Article addArticle(Article article) {
        String query="INSERT INTO article(id_sous_categorie,nom_article,reference,description,image,dimensions,prix,lot_vente) VALUES(?,?,?,?,?,?,?,?);";
        try(Connection connection = DataSourceProvider.getDataSource().getConnection();
        PreparedStatement statement=connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS)){
            statement.setInt(1,article.getId_sous_categorie());
            statement.setString(2,article.getNom_article());
            statement.setString(3,article.getReference());
            statement.setString(4,article.getDescription());
            statement.setString(5,article.getImage());
            statement.setString(6,article.getDimension());
            statement.setDouble(7,article.getPrix());
            statement.setInt(8,article.getLot_vente());
            statement.executeUpdate();
            try(ResultSet ids=statement.getGeneratedKeys()){
                if(ids.next()){
                    int generatedId = ids.getInt(1);
                    article.setId_article(generatedId);
                    return article;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteArticle(Integer articleId) {
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "delete from article where id_article=?")) {
                statement.setInt(1,articleId );
                statement.executeUpdate();
            }
        }catch (SQLException e) {
            // Manage Exception
            e.printStackTrace();
        }
    }

    @Override
    public Article modifierArticle(Article article) {
        return null;
    }
}