package hei.ProjetRoseCorail.dao.impl;

import hei.ProjetRoseCorail.dao.CompteRoseCorailDao;
import hei.ProjetRoseCorail.entities.CompteRoseCorail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CompteRoseCorailDaoImpl implements CompteRoseCorailDao {


    @Override
    public CompteRoseCorail getCompteRoseCorailByMail(String mail) {
        String query ="SELECT * FROM compterosecorail where email=?;";
        try(Connection connection = DataSourceProvider.getDataSource().getConnection();
            PreparedStatement statement= connection.prepareStatement(query)){
            statement.setString(1,mail);
            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new CompteRoseCorail(
                            resultSet.getInt("id_compte_RC"),
                            resultSet.getString("email"),
                            resultSet.getString("mdp"),
                            resultSet.getString("numero_tel"));
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public CompteRoseCorail getCompteRoseCorailById(Integer id) {
        String query ="SELECT * FROM compterosecorail where id_compte_RC=?;";
        try(Connection connection = DataSourceProvider.getDataSource().getConnection();
            PreparedStatement statement= connection.prepareStatement(query)){
            statement.setInt(1,id);
            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new CompteRoseCorail(
                            resultSet.getInt("id_compte_RC"),
                            resultSet.getString("email"),
                            resultSet.getString("mdp"),
                            resultSet.getString("numero_tel"));
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}