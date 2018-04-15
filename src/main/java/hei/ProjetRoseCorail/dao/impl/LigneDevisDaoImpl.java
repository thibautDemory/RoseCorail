package hei.ProjetRoseCorail.dao.impl;

import hei.ProjetRoseCorail.dao.LigneDevisDao;
import hei.ProjetRoseCorail.entities.Couleur;
import hei.ProjetRoseCorail.entities.LigneDevis;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class LigneDevisDaoImpl implements LigneDevisDao{

    /**
     * Cette méthode permet d'ajouter un objet LigneDevis à la BDD
     * @param ligneDevis = objet "LigneDevis" que l'on souhaite ajouter à la BDD
     * @return objet "LigneDevis" que l'on souhaite ajouter à la BDD
     */
    @Override
    public LigneDevis addLigneDevis(LigneDevis ligneDevis) {
        String query = "INSERT INTO lignedevis(id_couleur,id_devis,id_article,quantite) VALUES(?, ?, ?, ?)";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1,ligneDevis.getId_couleur() );
            statement.setInt(2,ligneDevis.getId_devis() );
            statement.setInt(3,ligneDevis.getId_article() );
            statement.setInt(4,ligneDevis.getQuantite() );
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

    /**
     * Cette méthode permet d'avoir la liste de toutes les lignes devis de la BDD
     * @return la liste de toutes les lignes devis de la BDD
     */
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
                                    resultSet.getInt("id_article"),
                                    resultSet.getInt("quantite"))
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listlignesdevispourunecouleur;
    }

    /**
     * Cette méthode permet d'avoir la liste des objets "LigneDevis" pour une couleur donnée
     * @param idCouleur = identifiant de la couleur souhaitée
     * @return la liste des objets "LigneDevis" pour une couleur donnée
     */
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
                                    resultSet.getInt("id_article"),
                                    resultSet.getInt("quantite"))
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listlignesdevispourunecouleur;
    }

    /**
     * Cette méthode permet d'avoir la liste des objets "LigneDevis" pour un article donné
     * @param idArticle = identifiant de l'article souhaité
     * @return la liste des objets "LigneDevis" pour un article donné
     */
    @Override
    public List<LigneDevis> listLignesDevisPourUnArticle(Integer idArticle) {
        String query = "SELECT * FROM lignedevis JOIN article ON article.id_article=lignedevis.id_article WHERE article.id_article= ?;";
        List<LigneDevis> listlignesdevispourunarticle = new ArrayList<>();
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idArticle);
            try(ResultSet resultSet = statement.executeQuery()){

                while (resultSet.next()) {
                    listlignesdevispourunarticle.add(new LigneDevis
                            (resultSet.getInt("id_ligne_devis"),
                                    resultSet.getInt("id_couleur"),
                                    resultSet.getInt("id_devis"),
                                    resultSet.getInt("id_article"),
                                    resultSet.getInt("quantite"))
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listlignesdevispourunarticle;
    }

    /**
     * Cette méthode permet d'avoir la liste des objets "LigneDevis" pour un devis donné
     * @param idDevis = identifiant de l'objet "Devis" souhaité
     * @return la liste des objets "LigneDevis" pour un devis donné
     */
    @Override
    public List<LigneDevis> listLignesDevisPourUnDevis(Integer idDevis) {
        String query = "SELECT * FROM lignedevis WHERE id_devis= ?;";
        List<LigneDevis> listlignesdevispourUnDevis = new ArrayList<>();
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idDevis);
            try(ResultSet resultSet = statement.executeQuery()){

                while (resultSet.next()) {
                    listlignesdevispourUnDevis.add(new LigneDevis
                            (resultSet.getInt("id_ligne_devis"),
                                    resultSet.getInt("id_couleur"),
                                    resultSet.getInt("id_devis"),
                                    resultSet.getInt("id_article"),
                                    resultSet.getInt("quantite"))
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listlignesdevispourUnDevis;
    }

    /**
     * Cette méthode permet de modifier la quantité d'article sur un objet "LigneDevis"
     * @param idLigneDevis = identifiant de l'objet "LigneDevis"
     * @param quantite = la quantité d'article modifiée
     */
    @Override
    public void modifierQuantiteLigneDevis(Integer idLigneDevis,Integer quantite) {
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "UPDATE lignedevis SET quantite = ? WHERE id_ligne_devis =?;")) {
                statement.setInt(1,quantite );
                statement.setInt(2,idLigneDevis );
                statement.executeUpdate();
            }
        }catch (SQLException e) {
            // Manage Exception
            e.printStackTrace();
        }
    }

    /**
     * Cette méthode permet de supprimer un objet "LigneDevis" grâce à son identifiant
     * @param idLigneDevis = l'identifiant de l'objet "LigneDevis"
     */
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

    /**
     * Cette méthode permet de supprimer des objets "LigneDevis" de la BDD
     * grâce à l'identifiant d'un objet "Couleur"
     * @param idCouleur = l'identifiant d'un objet "Couleur"
     */
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

    /**
     * Cette méthode permet de supprimer des objets "LigneDevis" de la BDD
     * grâce à l'identifiant d'un objet "Couleur"
     * @param idArticle = l'identifiant d'un objet "Couleur"
     */
    @Override
    public void deleteLigneDevisForArticle(Integer idArticle) {
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "delete from lignedevis where id_article=?")) {
                statement.setInt(1,idArticle );
                statement.executeUpdate();
            }
        }catch (SQLException e) {
            // Manage Exception
            e.printStackTrace();
        }
    }
}
