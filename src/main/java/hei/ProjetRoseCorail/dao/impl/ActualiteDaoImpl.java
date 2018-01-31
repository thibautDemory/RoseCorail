package hei.ProjetRoseCorail.dao.impl;

import hei.ProjetRoseCorail.dao.ActualiteDao;
import hei.ProjetRoseCorail.entities.Actualite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ActualiteDaoImpl implements ActualiteDao {
    @Override
    public Actualite addActualite(Actualite actualite) {
        String query = "INSERT INTO actualite(titre, contenu, image) VALUES(?,?,?)";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, actualite.getTitreActualite());
                statement.setString(2, actualite.getContenu());
                statement.setString(3, actualite.getImageActualite());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
