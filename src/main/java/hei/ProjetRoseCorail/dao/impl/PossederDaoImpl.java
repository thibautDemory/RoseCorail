package hei.ProjetRoseCorail.dao.impl;

import hei.ProjetRoseCorail.dao.PossederDao;
import hei.ProjetRoseCorail.entities.Article;
import hei.ProjetRoseCorail.entities.Couleur;
import hei.ProjetRoseCorail.entities.Posseder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PossederDaoImpl implements PossederDao{

    @Override
    public List<Couleur> listCouleursPourUnArticle(Integer id_article) {
        String query = "SELECT * from couleur join posseder on couleur.id_couleur=couleur.id_couleur WHERE posseder.id_article= ?;";
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
        String query = "SELECT * from article join posseder on article.id_article=posseder.id_article WHERE posseder.id_couleur= ?;";
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
                            resultSet.getString("dimension"),
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
        return null;
    }

    @Override
    public Posseder modifierPosseder(Posseder posseder) {
        return null;
    }
}
