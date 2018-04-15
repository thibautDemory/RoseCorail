package hei.ProjetRoseCorail.dao.impl;

import hei.ProjetRoseCorail.dao.DevisDao;
import hei.ProjetRoseCorail.entities.Devis;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DevisDaoImpl implements DevisDao {

    /**
     * Cette méthode permet d'ajouter un devis à la BDD
     * @param devis = objet devis que l'on souhaite ajouter
     * @return objet devis que l'on a ajouté
     */
    @Override
    public Devis creerUnDevis(Devis devis) {
        String query = "INSERT INTO devis(id_compte_client,date_creation,etat,etatPanier) VALUES(?,?,?,?)";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, devis.getId_compte_client());
            statement.setDate(2, Date.valueOf(devis.getDate()));
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

    /**
     * Cette méthode permet d'avoir la liste des devis d'un client
     * @param idCompteClient = identifiant du compte client qui possède les devis que l'on demande
     * @return la liste des devis du client voulu
     */
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
                                    resultSet.getDate("date_creation").toLocalDate(),
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

    /**
     * Cette méthode permet d'avoir la liste de tous les devis de la BDD
     * @return la liste de tous les devis de la BDD
     */
    @Override
    public List<Devis> listDevis() {
        String query = "SELECT * FROM devis WHERE etatPanier=FALSE;";
        List<Devis> listofCouleursPourUnArticle = new ArrayList<>();
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            try(ResultSet resultSet = statement.executeQuery()){

                while (resultSet.next()) {
                    listofCouleursPourUnArticle.add(new Devis(
                            resultSet.getInt("id_devis"),
                            resultSet.getInt("id_compte_client"),
                            resultSet.getDate("date_creation").toLocalDate(),
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

    /**
     * Cette méthode permet d'avoir le panier d'un client, c'est-à-dire l'ensemble des articles que le
     * client en question a dans son panier
     * @param idcompteclient = identifiant du client dont on veut connaître le contenu du panier
     * @return l'objet "Devis" correspondant au panier du client voulu
     */
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
                            resultSet.getDate("date_creation").toLocalDate(),
                            resultSet.getString("etat"),
                            resultSet.getBoolean("etatPanier"));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Cette méthode permet d'avoir un devis grâce à son identifiant
     * @param idDevis = identifiant du devis que l'on veut
     * @return l'objet "Devis" que l'on souhaite
     */
    @Override
    public Devis getDevisById(Integer idDevis) {
        String query="SELECT * FROM devis WHERE id_devis=?;";
        try(Connection connection = DataSourceProvider.getDataSource().getConnection();
            PreparedStatement statement= connection.prepareStatement(query)){
            statement.setInt(1,idDevis);
            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    return new Devis(
                            resultSet.getInt("id_devis"),
                            resultSet.getInt("id_compte_client"),
                            resultSet.getDate("date_creation").toLocalDate(),
                            resultSet.getString("etat"),
                            resultSet.getBoolean("etatPanier"));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Cette méthode permet de modifier la date d'un devis grâce à son identifiant
     * @param idDevis = l'identifiant du devis
     */
    @Override
    public void changerDateDevis(Integer idDevis) {
        LocalDate maintenant=LocalDate.now();
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "UPDATE devis SET date_creation = ? WHERE id_devis =?;")) {
                statement.setDate(1,Date.valueOf(maintenant) );
                statement.setInt(2,idDevis );
                statement.executeUpdate();
            }
        }catch (SQLException e) {
            // Manage Exception
            e.printStackTrace();
        }

    }

    /**
     * Cette méthode permet de modifier l'état d'un devis, l'état devient "en préparation"
     * @param idDevis = l'identifiant de l'objet devis
     */
    @Override
    public void dePanieraEnPreparation(Integer idDevis) {
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "UPDATE devis SET etat = 'en préparation', etatPanier=FALSE WHERE id_devis =?;")) {
                statement.setInt(1,idDevis );
                statement.executeUpdate();
            }
        }catch (SQLException e) {
            // Manage Exception
            e.printStackTrace();
        }

    }

    /**
     * Cette méthode permet de changer l'état d'un devis et de la passer à l'état "annulé"
     * @param idDevis = l'identifiant de l'objet devis
     */
    @Override
    public void annulerDevis(Integer idDevis) {
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "UPDATE devis SET etat = 'annulé', etatPanier=FALSE WHERE id_devis =?;")) {
                statement.setInt(1,idDevis );
                statement.executeUpdate();
            }
        }catch (SQLException e) {
            // Manage Exception
            e.printStackTrace();
        }
    }

    /**
     * Cette méthode permet de changer l'état d'un devis
     * @param idDevis = l'identifiant de l'objet devis
     * @param etat = une chaine de caractère qui est soit "en préparation", soit "panier", soit "annulé",
     *             soit expédié
     */
    @Override
    public void changerEtatDevis(Integer idDevis, String etat) {
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "UPDATE devis SET etat = ?, etatPanier=FALSE WHERE id_devis =?;")) {
                statement.setString(1,etat );
                statement.setInt(2,idDevis );
                statement.executeUpdate();
            }
        }catch (SQLException e) {
            // Manage Exception
            e.printStackTrace();
        }
    }

}
