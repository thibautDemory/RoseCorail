package hei.ProjetRoseCorail.dao.impl;

import hei.ProjetRoseCorail.dao.DevisDao;
import hei.ProjetRoseCorail.entities.Devis;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DevisDaoImpl implements DevisDao {
    @Override
    public Devis creerUnDevis(Devis devis) {
        String query = "INSERT INTO devis(id_compte_client,date,etat,etatPanier) VALUES(?,?,?,?)";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, devis.getId_compte_client());
            statement.setDate(2, new java.sql.Date(devis.getDate().getTime()));
            statement.setString(3, devis.getEtat());
            statement.setBoolean(4, devis.getEtatPanier());
            statement.executeUpdate();
            try (ResultSet ids = statement.getGeneratedKeys()) {
                if(ids.next()) {
                    int generatedId = ids.getInt(1);
                    devis.setId_devis(generatedId);
                    return devis;

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Devis> listDevisByCompteClient(Integer idCompteClient) {
        String query = "SELECT * FROM devis WHERE id_compte_client= ? AND etatPanier=FALSE;";
        List<Devis> listofCouleursPourUnArticle = new ArrayList<>();
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idCompteClient);
            try(ResultSet resultSet = statement.executeQuery()){

                while (resultSet.next()) {
                    listofCouleursPourUnArticle.add(new Devis(
                                    resultSet.getInt("id_devis"),
                                    resultSet.getInt("id_compte_client"),
                                    resultSet.getDate("date"),
                                    resultSet.getString("etat"),
                                    resultSet.getBoolean("etatPanier"))
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listofCouleursPourUnArticle;
    }

    @Override
    public Devis getDevisByArticle(Integer idArticle) {
        return null;
    }

    @Override
    public Devis getPanierClient(Integer idcompteclient) {
        String query="SELECT * FROM devis WHERE id_compte_client=? AND etatPanier=true;";
        try(Connection connection = DataSourceProvider.getDataSource().getConnection();
            PreparedStatement statement= connection.prepareStatement(query)){
            statement.setInt(1,idcompteclient);
            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    return new Devis(
                            resultSet.getInt("id_devis"),
                            resultSet.getInt("id_compte_client"),
                            resultSet.getDate("date"),
                            resultSet.getString("etat"),
                            resultSet.getBoolean("etatPanier"));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
