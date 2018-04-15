package hei.ProjetRoseCorail.dao.impl;

import hei.ProjetRoseCorail.dao.CouleurDao;
import hei.ProjetRoseCorail.entities.Actualite;
import hei.ProjetRoseCorail.entities.Article;
import hei.ProjetRoseCorail.entities.Couleur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CouleurDaoImpl implements CouleurDao {

    /**
     * Cette méthode permet d'avoir la liste des couleurs disponibles (de tous les articles)
     * @return la liste des couleurs disponibles
     */
    @Override
    public List<Couleur> listCouleursActives(){
        String query = "SELECT * FROM couleur WHERE actif=1 ORDER BY nom_couleur;";
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

    /**
     * Cette méthode permet d'avoir une couleur grâce à son identifiant
     * @param id = l'identifiant de la couleur voulue
     * @return la couleur souhaitée
     */
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

    /**
     * Cette méthode permet d'avoir une couleur grâce à son paramètre "nom"
     * @param nom = le nom de la couleur souhaitée
     * @return l'objet couleur souhaité
     */
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

    /**
     * Cette méthode permet d'ajouter une couleur à la BDD
     * @param couleur = objet couleur que l'on souhaite ajouter à la BDD
     * @return la couleur qu'on a ajouté à la BDD
     */
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

    /**
     * Cette méthode permet de désactiver une couleur. Cela remplace la suppression de couleur, la suppression
     * d'une couleur aurait entraîné trop de complexité, car les couleurs sont liées aux articles.
     * @param idCouleur = l'identifiant de la couleur que l'on souhaite désactiver (supprimer)
     */
    @Override
    public void rendreCouleurInactive(Integer idCouleur) {
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

    /**
     * Cette méthode permet de supprimer une couleur de la BDD (cette méthode n'est pas utilisée en pratique)
     * @param idCouleur = l'identifiant de la couleur que l'on souhaite supprimer
     */
    @Override
    public void deleteCouleur(Integer idCouleur){
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "delete from couleur where id_couleur=?")) {
                statement.setInt(1,idCouleur );
                statement.executeUpdate();
            }
        }catch (SQLException e) {
            // Manage Exception
            e.printStackTrace();
        }
    }
}
