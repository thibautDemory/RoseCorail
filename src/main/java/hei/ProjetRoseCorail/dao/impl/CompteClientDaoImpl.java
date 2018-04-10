package hei.ProjetRoseCorail.dao.impl;

import hei.ProjetRoseCorail.dao.CompteClientDao;
import hei.ProjetRoseCorail.entities.CompteClient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompteClientDaoImpl implements CompteClientDao {
    /**
     * Cette méthode permet de faire la liste des comptes clients existants
     * @return la liste des comptes clients existants
     */
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
                                resultSet.getString("description_activite"),
                                resultSet.getInt("numero_panier_actif")
                                )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfComptesClient;

    }

    /**
     * Cette méthode permet de récuperer un compte client en particulier
     * @param id_compte_client est l'identifiant du compte client que l'on veut retourner
     * @return le compte client en question
     */
    @Override
    public CompteClient getCompteClientById(Integer id_compte_client) {
        String query ="SELECT * FROM compteclient where id_compte_client=?;";
        try(Connection connection = DataSourceProvider.getDataSource().getConnection();
            PreparedStatement statement= connection.prepareStatement(query)){
            statement.setInt(1,id_compte_client);
            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new CompteClient(
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
                            resultSet.getString("description_activite"),
                            resultSet.getInt("numero_panier_actif")
                    );
                }
            }

        }catch (SQLException e){
                e.printStackTrace();
        }
        return null;
    }

    /**
     * Cette méthode permet de récuperer un compte client récemment créer et qui
     * @param id_compte_client
     * @return
     */
    @Override
    public CompteClient getCompteClientByIdWithoutIdPanier(Integer id_compte_client){
        String query ="SELECT * FROM compteclient where id_compte_client=?;";
        try(Connection connection = DataSourceProvider.getDataSource().getConnection();
            PreparedStatement statement= connection.prepareStatement(query)){
            statement.setInt(1,id_compte_client);
            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new CompteClient(
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
                    );
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public CompteClient getCompteClientByMail(String mail) {
        String query ="SELECT * FROM compteclient where email=?;";
        try(Connection connection = DataSourceProvider.getDataSource().getConnection();
            PreparedStatement statement= connection.prepareStatement(query)){
            statement.setString(1,mail);
            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new CompteClient(
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
                            resultSet.getString("description_activite"),
                            resultSet.getInt("numero_panier_actif"));
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public CompteClient addCompteClient(CompteClient compteClient) {
        String query = "INSERT INTO compteclient(email, nom_boutique, nom_gerant, prenom_gerant, adresse, ville, code_postal, mdp,  numero_tel, num_tva, site_internet, description_activite) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
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

                try (ResultSet ids = statement.getGeneratedKeys()) {
                    if (ids.next()) {
                        int generatedId = ids.getInt(1);
                        compteClient.setId_compte_client(generatedId);
                        return compteClient;
                    }
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public CompteClient updateCompteClientWithoutPassword(CompteClient compteClient) {
        String query = "UPDATE compteclient SET email=?, nom_boutique=?, nom_gerant=?, prenom_gerant=?, adresse=?, ville=?, code_postal=?, numero_tel=?, num_tva=?, site_internet=?, description_activite=? WHERE id_compte_client=?";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
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
            statement.setInt(12, compteClient.getId_compte_client());
            statement.executeUpdate();

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

    @Override
    public void updatePassword(Integer id, String pwd) {
        String query = "UPDATE compteclient SET mdp=? WHERE id_compte_client=?;";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, pwd);
            statement.setInt(2,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void changerNumeroPanierActif(Integer idcompteclient, Integer nouveaunumeropanier) {
        String query = "UPDATE compteclient SET numero_panier_actif=? WHERE id_compte_client=?;";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, nouveaunumeropanier);
            statement.setInt(2,idcompteclient);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
