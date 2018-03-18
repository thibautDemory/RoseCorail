package hei.ProjetRoseCorail.dao.impl;

import hei.ProjetRoseCorail.dao.CouleurDao;
import hei.ProjetRoseCorail.entities.Actualite;
import hei.ProjetRoseCorail.entities.Article;
import hei.ProjetRoseCorail.entities.Couleur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CouleurDaoImpl implements CouleurDao {
    @Override
    public List<Couleur> listCouleursActives(){
        String query = "SELECT * FROM couleur WHERE actif=1 ORDER BY id_couleur  ;";
        List<Couleur> listOfCouleurs = new ArrayList<>();

        try (
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                listOfCouleurs.add(
                        new Couleur(
                                resultSet.getInt("id_couleur"),
                                resultSet.getString("nom_couleur"),
                                resultSet.getString("num_couleur"),
                                resultSet.getString("image"),
                                resultSet.getString("saison"),
                                resultSet.getInt("actif"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfCouleurs;

    }

    @Override
    public Couleur getCouleurByID(Integer id){
        String query="SELECT * FROM couleur WHERE id_couleur=?;";
        try(Connection connection = DataSourceProvider.getDataSource().getConnection();
            PreparedStatement statement= connection.prepareStatement(query)){
            statement.setInt(1,id);
            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    return new Couleur(
                            resultSet.getInt("id_couleur"),
                            resultSet.getString("nom_couleur"),
                            resultSet.getString("num_couleur"),
                            resultSet.getString("image"),
                            resultSet.getString("saison"),
                            resultSet.getInt("actif"));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Couleur getCouleurByName(String nom) {
        String query="SELECT * FROM couleur WHERE nom_couleur=?;";
        try(Connection connection = DataSourceProvider.getDataSource().getConnection();
            PreparedStatement statement= connection.prepareStatement(query)){
            statement.setString(1,nom);
            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    return new Couleur(
                            resultSet.getInt("id_couleur"),
                            resultSet.getString("nom_couleur"),
                            resultSet.getString("num_couleur"),
                            resultSet.getString("image"),
                            resultSet.getString("saison"),
                            resultSet.getInt("actif"));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Couleur addCouleur(Couleur couleur){
        String query = "INSERT INTO couleur(nom_couleur, num_couleur, image, saison, actif) VALUES(?,?,?,?,?)";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, couleur.getNom_couleur());
                statement.setString(2, couleur.getNumero_couleur());
                statement.setString(3, couleur.getImage_couleur());
                statement.setString(4, couleur.getSaison());
                statement.setInt(5, couleur.getActif());
                statement.executeUpdate();
                try (ResultSet ids = statement.getGeneratedKeys()) {
                    if(ids.next()) {
                        int generatedId = ids.getInt(1);
                        couleur.setId_couleur(generatedId);
                        return couleur;

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteCouleur(Integer idCouleur){
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "UPDATE couleur SET actif=0 where id_couleur=?")) {
                statement.setInt(1,idCouleur );
                statement.executeUpdate();
            }
        }catch (SQLException e) {
            // Manage Exception
            e.printStackTrace();
        }
    }
}
