package hei.ProjetRoseCorail.dao.impl;

import hei.ProjetRoseCorail.dao.CompteRoseCorailDao;
import hei.ProjetRoseCorail.entities.CompteRoseCorail;

import java.sql.*;

public class CompteRoseCorailDaoImpl implements CompteRoseCorailDao {


    /**
     * Cette méthode permet d'avoir l'objet compte administrateur grâce à son email
     * @param mail = email de l'administrateur
     * @return l'objet compte administrateur
     */
    @Override
    public CompteRoseCorail getCompteRoseCorailByMail(String mail) {
        String query = "SELECT * FROM compterosecorail where email=?;";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, mail);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new CompteRoseCorail(
                            resultSet.getInt("id_compte_RC"),
                            resultSet.getString("email"),
                            resultSet.getString("mdp"),
                            resultSet.getString("numero_tel"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Cette méthode permet d'avoir l'objet compte administrateur grâce à son identifiant
     * @param id = identifiant de l'administrateur
     * @return l'objet compte administrateur
     */
    @Override
    public CompteRoseCorail getCompteRoseCorailById(Integer id) {
        String query = "SELECT * FROM compterosecorail where id_compte_RC=?;";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new CompteRoseCorail(
                            resultSet.getInt("id_compte_RC"),
                            resultSet.getString("email"),
                            resultSet.getString("mdp"),
                            resultSet.getString("numero_tel"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Cette méthode permet de mettre à jour le mot de passe du compte administrateur
     * @param id = l'identifiant du compte administrateur
     * @param pwd = le nouveau mot de passe du compte administrateur
     */
    @Override
    public void updatePassword(Integer id, String pwd) {
        String query = "UPDATE compterosecorail SET mdp=? WHERE id_compte_RC=?;";
            try (Connection connection = DataSourceProvider.getDataSource().getConnection();
                 PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, pwd);
                statement.setInt(2,id);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    /**
     * Cette méthode permet de mettre à jour les informations du compte administrateur sans modifier
     * le mot de passe
     * @param compteRoseCorail = objet compte administrateur
     * @return l'objet compte administrateur avec les modifications
     */
    @Override
    public CompteRoseCorail updateCompteRoseCorailWithoutPassword(CompteRoseCorail compteRoseCorail) {
        String query = "UPDATE compterosecorail SET email=?, numero_tel=? WHERE id_compte_RC=?";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, compteRoseCorail.getEmail());
            statement.setString(2, compteRoseCorail.getNumero_tel());
            statement.setInt(3, compteRoseCorail.getId_compte_rose_corail());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}


