package hei.ProjetRoseCorail.dao.impl;

import hei.ProjetRoseCorail.dao.PanelColorisDao;
import hei.ProjetRoseCorail.entities.Panelcoloris;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PanelColorisDaoImpl implements PanelColorisDao {

    /**
     * Cette méthode permet d'avoir la liste de tous les objets "Panelcoloris" de la BDD
     * @return la liste de tous les objets "Panelcoloris" de la BDD
     */
    @Override
    public List<Panelcoloris> listPanelColoris() {
        String query = "SELECT * FROM panelcoloris ORDER BY id_panelcoloris";
        List<Panelcoloris> listOfPanelColoris = new ArrayList<>();

        try (
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                listOfPanelColoris.add(
                        new Panelcoloris(
                                resultSet.getInt("id_panelcoloris"),
                                resultSet.getString("legende"),
                                resultSet.getString("image"),
                                resultSet.getString("saison"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfPanelColoris;
    }

    /**
     * Cette méthode permet d'avoir la liste de tous les objets "Panelcoloris" pour une saison donnée
     * @param saison = String qui défini la saison que l'on souhaite
     * @return la liste de tous les objets "Panelcoloris" pour une saison donnée
     */
    @Override
    public List<Panelcoloris> listPanelColorisParSaison(String saison) {
        String query="SELECT * FROM panelcoloris WHERE saison=?;";
        List<Panelcoloris> listPanelColoris=new ArrayList<>();
        try(Connection connection = DataSourceProvider.getDataSource().getConnection();
            PreparedStatement statement= connection.prepareStatement(query)){
            statement.setString(1,saison);
            try(ResultSet resultSet = statement.executeQuery()){
                while(resultSet.next()){
                    listPanelColoris.add(new Panelcoloris(
                            resultSet.getInt("id_panelcoloris"),
                            resultSet.getString("legende"),
                            resultSet.getString("image"),
                            resultSet.getString("saison")));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return listPanelColoris;
    }

    /**
     * Cette méthode permet d'avoir un objet "PanelColoris" grâce à son identifiant
     * @param id = l'identifiant de l'objet "PanelColoris" que l'on souhaite
     * @return l'objet "PanelColoris" souhaité
     */
    @Override
    public Panelcoloris getPanelColorisById(Integer id) {
        String query="SELECT * FROM panelcoloris WHERE id_panelcoloris=?;";
        try(Connection connection = DataSourceProvider.getDataSource().getConnection();
            PreparedStatement statement= connection.prepareStatement(query)){
            statement.setInt(1,id);
            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    return new Panelcoloris(
                            resultSet.getInt("id_panelcoloris"),
                            resultSet.getString("legende"),
                            resultSet.getString("image"),
                            resultSet.getString("saison"));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Cette méthode permet d'ajouter un objet "PanelColoris" à la BDD
     * @param panelcoloris = l'objet "PanelColoris" que l'on veut ajouter à la BDD
     * @return l'objet "PanelColoris" ajouté à la BDD
     */
    @Override
    public Panelcoloris addPanelColoris(Panelcoloris panelcoloris) {
        String query = "INSERT INTO panelcoloris(legende, image, saison) VALUES(?,?,?)";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, panelcoloris.getLegende());
                statement.setString(2, panelcoloris.getImage());
                statement.setString(3, panelcoloris.getSaison());
                statement.executeUpdate();
                try(ResultSet ids=statement.getGeneratedKeys()){
                    if(ids.next()){
                        int generatedId = ids.getInt(1);
                        panelcoloris.setIdPanelColoris(generatedId);
                        return panelcoloris;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Cette méthode permet de supprimer un objet "PanelColoris" grâce à son identifiant
     * @param idPanelColoris = l'identifiant de l'objet "PanelColoris" que l'on souhaite supprimer
     */
    @Override
    public void deletePanelColoris(Integer idPanelColoris) {
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "delete from panelcoloris where id_panelcoloris=?")) {
                statement.setInt(1,idPanelColoris );
                statement.executeUpdate();
            }
        }catch (SQLException e) {
            // Manage Exception
            e.printStackTrace();
        }
    }
}
