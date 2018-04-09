package hei.ProjetRoseCorail.dao.impl;

import hei.ProjetRoseCorail.dao.PhotoPresentationDao;
import hei.ProjetRoseCorail.entities.PhotosPresentation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhotoPresentationDaoImpl implements PhotoPresentationDao {
    @Override
    public List<PhotosPresentation> listphotospresentation() {
        String query = "SELECT * FROM photopresentation ORDER BY id_photo";
        List<PhotosPresentation> listofPhotosPresentation = new ArrayList<>();

        try (
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                listofPhotosPresentation.add(
                        new PhotosPresentation(
                                resultSet.getInt("id_photo"),
                                resultSet.getString("page"),
                                resultSet.getString("adresse"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listofPhotosPresentation;
    }

    @Override
    public List<PhotosPresentation> listphotosparpage(String page) {
        String query="SELECT * FROM photopresentation WHERE page=?;";
        List<PhotosPresentation> listPhotoPresentationAccueil=new ArrayList<>();
        try(Connection connection = DataSourceProvider.getDataSource().getConnection();
            PreparedStatement statement= connection.prepareStatement(query)){
            statement.setString(1,page);
            try(ResultSet resultSet = statement.executeQuery()){
                while(resultSet.next()){
                    listPhotoPresentationAccueil.add(new PhotosPresentation(
                            resultSet.getInt("id_photo"),
                            resultSet.getString("page"),
                            resultSet.getString("adresse")));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return listPhotoPresentationAccueil;
    }



    @Override
    public PhotosPresentation addPhotoPresentation(PhotosPresentation photosPresentation) {
        String query = "INSERT INTO photopresentation(page, adresse) VALUES(?,?)";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, photosPresentation.getPage());
                statement.setString(2, photosPresentation.getAdresse());
                statement.executeUpdate();
                try(ResultSet ids=statement.getGeneratedKeys()){
                    if(ids.next()){
                        int generatedId = ids.getInt(1);
                        photosPresentation.setId_photo(generatedId);
                        return photosPresentation;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public PhotosPresentation getPhotoPresentationById(Integer idPhoto) {
        String query="SELECT * FROM photopresentation WHERE id_photo=?;";
        try(Connection connection = DataSourceProvider.getDataSource().getConnection();
            PreparedStatement statement= connection.prepareStatement(query)){
            statement.setInt(1,idPhoto);
            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    return new PhotosPresentation(
                            resultSet.getInt("id_photo"),
                            resultSet.getString("page"),
                            resultSet.getString("adresse"));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deletePhotoPresentation(Integer idPhoto) {
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "delete from photopresentation where id_photo=?")) {
                statement.setInt(1,idPhoto );
                statement.executeUpdate();
            }
        }catch (SQLException e) {
            // Manage Exception
            e.printStackTrace();
        }
    }

    @Override
    public void modifierPhoto(String adresse, Integer idPhoto) {
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "UPDATE photopresentation SET adresse = ? WHERE id_photo =?;")) {
                statement.setString(1,adresse );
                statement.setInt(2,idPhoto );
                statement.executeUpdate();
            }
        }catch (SQLException e) {
            // Manage Exception
            e.printStackTrace();
        }
    }
}
