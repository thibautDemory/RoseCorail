package hei.ProjetRoseCorail.dao.impl;

import hei.ProjetRoseCorail.dao.ActualiteDao;
import hei.ProjetRoseCorail.entities.Actualite;
import hei.ProjetRoseCorail.entities.Couleur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActualiteDaoImpl implements ActualiteDao {
    /**
     * Cette méthode permet de retourner la liste de toutes les actualités
     * @return les actualités
     */
    @Override
    public List<Actualite> listActualites(){
        String query = "SELECT * FROM actualite ORDER BY id_actualite";
        List<Actualite> listOfActualites = new ArrayList<>();

        try (
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                listOfActualites.add(
                        new Actualite(
                                resultSet.getInt("id_actualite"),
                                resultSet.getString("titre"),
                                resultSet.getString("contenu"),
                                resultSet.getString("image"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfActualites;

    }

    /**
     * Cette méthode permet de retourner une actualité en particulier
     * @param id c'est l'identifiant dans la base de donnée de l'actualité que l'on veut retourner
     * @return l'actualité dont l'id a été rentré en paramètre
     */
    @Override
    public Actualite getActualiteByID(Integer id){
        String query="SELECT * FROM actualite WHERE id_actualite=?;";
        try(Connection connection = DataSourceProvider.getDataSource().getConnection();
            PreparedStatement statement= connection.prepareStatement(query)){
            statement.setInt(1,id);
            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    return new Actualite(
                            resultSet.getInt("id_actualite"),
                            resultSet.getString("titre"),
                            resultSet.getString("contenu"),
                            resultSet.getString("image"));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Cette méthode permet de rajouter une actualité à la base de donnée, et donc au site
     * @param actualite contient tous les attributs de l'actualité que l'on veut rajouter
     * @return l'actualité que l'on vient de créer, avec son identifiant
     */
    @Override
    public Actualite addActualite(Actualite actualite) {
        String query = "INSERT INTO actualite(titre, contenu, image) VALUES(?,?,?)";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, actualite.getTitreActualite());
                statement.setString(2, actualite.getContenu());
                statement.setString(3, actualite.getImageActualite());
                statement.executeUpdate();
                try(ResultSet ids=statement.getGeneratedKeys()){
                    if(ids.next()){
                        int generatedId = ids.getInt(1);
                        actualite.setIdActualite(generatedId);
                        return actualite;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Cette méthode permet de supprimer une actualité
     * @param idActualite est l'identifiant de l'actualité à supprimer
     */
    @Override
    public void deleteActualite(Integer idActualite){
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "delete from actualite where id_actualite=?")) {
                statement.setInt(1,idActualite );
                statement.executeUpdate();
            }
        }catch (SQLException e) {
            // Manage Exception
            e.printStackTrace();
        }
    }
}
