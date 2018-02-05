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
                                resultSet.getString("prenom_gerant"),
                                resultSet.getString("adresse"),
                                resultSet.getString("ville"),
                                resultSet.getString("code_postal"),
                                resultSet.getString("mdp"),
                                resultSet.getString("numero_tel"),
                                resultSet.getString("num_tva"),
                                resultSet.getString("site_internet"),
                                resultSet.getString("description_activite")
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
        String query = "INSERT INTO compteclient(email, nom_boutique, nom_gerant, prenom_gerant, adresse, ville, code_postal, mdp,  numero_tel, num_tva, site_internet, description_activite) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, compteClient.getEmail());
                statement.setString(2, compteClient.getNom_boutique());
                statement.setString(3, compteClient.getNom_gerant());
                statement.setString(4, compteClient.getPrenom_gerant());
                statement.setString(5, compteClient.getAdresse());
                statement.setString(6, compteClient.getVille());
                statement.setString(7, compteClient.getCode_postal());
                statement.setString(8, compteClient.getMdp());
                statement.setString(9, compteClient.getNumero_tel());
                statement.setString(10, compteClient.getNum_tva());
                statement.setString(11, compteClient.getSite_internet());
                statement.setString(12, compteClient.getDescription_activite());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public CompteClient addCompteClientWithoutPassword(CompteClient compteClient) {
        String query = "INSERT INTO compteclient(email, nom_boutique, nom_gerant, prenom_gerant, adresse, ville, code_postal,  numero_tel, num_tva, site_internet, description_activite) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, compteClient.getEmail());
                statement.setString(2, compteClient.getNom_boutique());
                statement.setString(3, compteClient.getNom_gerant());
                statement.setString(4, compteClient.getPrenom_gerant());
                statement.setString(5, compteClient.getAdresse());
                statement.setString(6, compteClient.getVille());
                statement.setString(7, compteClient.getCode_postal());
                statement.setString(8, compteClient.getNumero_tel());
                statement.setString(9, compteClient.getNum_tva());
                statement.setString(10, compteClient.getSite_internet());
                statement.setString(11, compteClient.getDescription_activite());
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
