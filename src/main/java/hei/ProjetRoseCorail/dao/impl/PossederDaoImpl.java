package hei.ProjetRoseCorail.dao.impl;

import hei.ProjetRoseCorail.dao.PossederDao;
import hei.ProjetRoseCorail.entities.Article;
import hei.ProjetRoseCorail.entities.Couleur;
import hei.ProjetRoseCorail.entities.Posseder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PossederDaoImpl implements PossederDao{

    @Override
    public List<Couleur> listCouleursPourUnArticle(Integer id_article) {
        String query = "SELECT * FROM couleur JOIN posseder ON couleur.id_couleur=couleur.id_couleur WHERE posseder.id_article= ?;";
        List<Couleur> listofCouleursPourUnArticle = new ArrayList<>();
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id_article);
            try(ResultSet resultSet = statement.executeQuery()){

                while (resultSet.next()) {
                    listofCouleursPourUnArticle.add(new Couleur
                            (resultSet.getInt("id_couleur"),
                            resultSet.getString("nom_couleur"),
                            resultSet.getString("num_couleur"),
                            resultSet.getString("image"),
                            resultSet.getString("saison"))
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listofCouleursPourUnArticle;
    }

    @Override
    public List<Article> listArticlesPourUneCouleur(Integer id_couleur) {
        String query = "SELECT * FROM article JOIN posseder ON article.id_article=posseder.id_article WHERE posseder.id_couleur= ?;";
        List<Article> listArticlesPourUneCouleur = new ArrayList<>();
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id_couleur);
            try(ResultSet resultSet = statement.executeQuery()){

                while (resultSet.next()) {
                    listArticlesPourUneCouleur.add(new Article(
                            resultSet.getInt("id_article"),
                            resultSet.getInt("id_sous_categorie"),
                            resultSet.getString("nom_article"),
                            resultSet.getString("reference"),
                            resultSet.getString("description"),
                            resultSet.getString("image"),
                            resultSet.getString("dimensions"),
                            resultSet.getDouble("prix"),
                            resultSet.getInt("lot_vente"))
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listArticlesPourUneCouleur;
    }

    @Override
    public Posseder addPosseder(Posseder posseder) {
        String query = "INSERT INTO posseder(id_couleur,id_article) VALUES(?, ?)";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1,posseder.getId_couleur() );
            statement.setInt(2,posseder.getId_article() );
            statement.executeUpdate();

            try (ResultSet ids = statement.getGeneratedKeys()) {
                if(ids.next()) {
                    int generatedId = ids.getInt(1);
                    posseder.setId_posseder(generatedId);
                    return posseder;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Posseder modifierPosseder(Posseder posseder) {
        return null;
    }
}
