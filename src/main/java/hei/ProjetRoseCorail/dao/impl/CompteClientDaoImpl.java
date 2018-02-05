package hei.ProjetRoseCorail.dao.impl;

import hei.ProjetRoseCorail.dao.CompteClientDao;
import hei.ProjetRoseCorail.entities.CompteClient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompteClientDaoImpl implements CompteClientDao {
    @Override
    public List<CompteClient> listComptesClient(){
        String query = "SELECT * FROM compteclient ORDER BY id_compte_client";
        List<CompteClient> listOfComptesClient = new ArrayList<>();
        try (
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                listOfComptesClient.add(
                        new CompteClient(
                                resultSet.getInt("id_compte_client"),
                                resultSet.getString("email"),
                                resultSet.getString("nom_boutique"),
                                resultSet.getString("nom_gerant"),
                                resultSet.getString("adresse"),
                                resultSet.getString("ville"),
                                resultSet.getString("code_postal"),
                                resultSet.getString("mdp"),
                                resultSet.getString("numero_tel")
                                )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfComptesClient;

    }

    @Override
    public CompteClient addCompteClient(CompteClient compteClient) {
        String query = "INSERT INTO compteclient(email, nom_boutique, nom_gerant, adresse, ville, code_postal, mdp,  numero_tel) VALUES(?,?,?,?,?,?,?,?)";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, compteClient.getEmail());
                statement.setString(2, compteClient.getNom_boutique());
                statement.setString(3, compteClient.getNom_gerant());
                statement.setString(4, compteClient.getAdresse());
                statement.setString(5, compteClient.getVille());
                statement.setString(6, compteClient.getCode_postal());
                statement.setString(7, compteClient.getMdp());
                statement.setString(8, compteClient.getNumero_tel());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteCompteClient(Integer idCompteClient){
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "delete from compteclient where id_compte_client=?")) {
                statement.setInt(1,idCompteClient );
                statement.executeUpdate();
            }
        }catch (SQLException e) {
            // Manage Exception
            e.printStackTrace();
        }
    }

}
