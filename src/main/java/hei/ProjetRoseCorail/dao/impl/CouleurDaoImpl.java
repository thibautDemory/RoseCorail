package hei.ProjetRoseCorail.dao.impl;

import hei.ProjetRoseCorail.dao.CouleurDao;
import hei.ProjetRoseCorail.entities.Couleur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CouleurDaoImpl implements CouleurDao {
    @Override
    public Couleur addCouleur(Couleur couleur){
        String query = "INSERT INTO couleur(nom_couleur, num_couleur, image, saison) VALUES(?,?,?,?)";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, couleur.getNom_couleur());
                statement.setString(2, couleur.getNumero_couleur());
                statement.setString(3, couleur.getImage_couleur());
                statement.setString(4, couleur.getSaison());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
