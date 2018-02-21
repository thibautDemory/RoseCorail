package hei.ProjetRoseCorail.dao.impl;

import hei.ProjetRoseCorail.dao.LigneDevisDao;
import hei.ProjetRoseCorail.entities.Couleur;
import hei.ProjetRoseCorail.entities.LigneDevis;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class LigneDevisDaoImpl implements LigneDevisDao{

    @Override
    public LigneDevis addLigneDevis(LigneDevis ligneDevis) {
        String query = "INSERT INTO lignedevis(id_couleur,id_devis,id_article) VALUES(?, ?, ?)";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1,ligneDevis.getId_couleur() );
            statement.setInt(2,ligneDevis.getId_devis() );
            statement.setInt(3,ligneDevis.getId_article() );
            statement.executeUpdate();

            try (ResultSet ids = statement.getGeneratedKeys()) {
                if(ids.next()) {
                    int generatedId = ids.getInt(1);
                    ligneDevis.setId_ligne_devis(generatedId);
                    return ligneDevis;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<LigneDevis> listAllLigneDevis() {
        String query = "SELECT * FROM lignedevis ;";
        List<LigneDevis> listlignesdevispourunecouleur = new ArrayList<>();
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            try(ResultSet resultSet = statement.executeQuery()){

                while (resultSet.next()) {
                    listlignesdevispourunecouleur.add(new LigneDevis
                            (resultSet.getInt("id_ligne_devis"),
                                    resultSet.getInt("id_couleur"),
                                    resultSet.getInt("id_devis"),
                                    resultSet.getInt("id_article"))
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listlignesdevispourunecouleur;
    }

    @Override
    public List<LigneDevis> listLignesDevisPourUneCouleur(Integer idCouleur) {
        String query = "SELECT * FROM lignedevis JOIN couleur ON couleur.id_couleur=lignedevis.id_couleur WHERE couleur.id_couleur= ?;";
        List<LigneDevis> listlignesdevispourunecouleur = new ArrayList<>();
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idCouleur);
            try(ResultSet resultSet = statement.executeQuery()){

                while (resultSet.next()) {
                    listlignesdevispourunecouleur.add(new LigneDevis
                            (resultSet.getInt("id_ligne_devis"),
                                    resultSet.getInt("id_couleur"),
                                    resultSet.getInt("id_devis"),
                                    resultSet.getInt("id_article"))
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listlignesdevispourunecouleur;
    }

    @Override
    public List<LigneDevis> listLignesDevisPourUnArticle(Integer idArticle) {
        return null;
    }

    @Override
    public List<LigneDevis> listLignesDevisPourUnDevis(Integer idDevis) {
        return null;
    }

    @Override
    public void deleteLigneDevis(Integer idLigneDevis) {
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "delete from lignedevis where id_ligne_devis=?")) {
                statement.setInt(1,idLigneDevis );
                statement.executeUpdate();
            }
        }catch (SQLException e) {
            // Manage Exception
            e.printStackTrace();
        }
    }

    @Override
    public void deleteLigneDevisForOneCouleur(Integer idCouleur) {
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "delete from lignedevis where id_couleur=?")) {
                statement.setInt(1,idCouleur );
                statement.executeUpdate();
            }
        }catch (SQLException e) {
            // Manage Exception
            e.printStackTrace();
        }
    }
}
