package hei.ProjetRoseCorail.dao.impl;

import hei.ProjetRoseCorail.dao.PhotoPresentationDao;
import hei.ProjetRoseCorail.entities.PhotosPresentation;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

public class PhotoPresentationDaoTestCase {
    private PhotoPresentationDao photoPresentationDao=new PhotoPresentationDaoImpl();
    @Before
    public void initDb() throws Exception {
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DELETE FROM photopresentation");
            stmt.executeUpdate("INSERT INTO `photopresentation`(`id_photo`,`page`,`adresse`) VALUES (1, 'accueil', '/RoseCorail/images/accueil/image1.jpg')");
            stmt.executeUpdate("INSERT INTO `photopresentation`(`id_photo`,`page`,`adresse`) VALUES (2, 'accueil', '/RoseCorail/images/accueil/image2.jpg')");
            stmt.executeUpdate("INSERT INTO `photopresentation`(`id_photo`,`page`,`adresse`) VALUES (3, 'collection', '/RoseCorail/images/collection/image3.jpg')");
        }
    }

    @Test
    public void shouldListPhotosPresentation() {
        // WHEN
        List<PhotosPresentation> listphotospresentation = photoPresentationDao.listphotospresentation();
        // THEN
        assertThat(listphotospresentation).hasSize(3);
        assertThat(listphotospresentation).extracting("id_photo", "page", "adresse").containsOnly(
                tuple(1, "accueil", "/RoseCorail/images/accueil/image1.jpg"),
                tuple(2, "accueil", "/RoseCorail/images/accueil/image2.jpg"),
                tuple(3, "collection", "/RoseCorail/images/collection/image3.jpg"));
    }

    @Test
    public void shouldListPhotosPresenationParPage(){
        //When
        List<PhotosPresentation> listphotosAccueil=photoPresentationDao.listphotosparpage("accueil");
        //THEN
        assertThat(listphotosAccueil).hasSize(2);
        assertThat(listphotosAccueil).extracting("id_photo", "page", "adresse").containsOnly(
                tuple(1, "accueil", "/RoseCorail/images/accueil/image1.jpg"),
                tuple(2, "accueil", "/RoseCorail/images/accueil/image2.jpg"));
    }

    @Test
    public void shouldGetPhotoPresentationById(){
        // WHEN
        PhotosPresentation photosPresentation = photoPresentationDao.getPhotoPresentationById(2);
        // THEN
        assertThat(photosPresentation).isNotNull();
        assertThat(photosPresentation.getId_photo()).isEqualTo(2);
        assertThat(photosPresentation.getPage()).isEqualTo("accueil");
        assertThat(photosPresentation.getAdresse()).isEqualTo("/RoseCorail/images/accueil/image2.jpg");
    }
    @Test
    public void shouldAddPhotoPresentation() throws Exception{
        // GIVEN
        PhotosPresentation newPhotoPresentation = new PhotosPresentation(null, "collection","/RoseCorail/images/collection/image4.jpg");
        // WHEN
        PhotosPresentation createdPhotoPresentation = photoPresentationDao.addPhotoPresentation(newPhotoPresentation);
        // THEN
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM photopresentation WHERE adresse='/RoseCorail/images/collection/image4.jpg'")) {
                assertThat(rs.next()).isTrue();
                assertThat(rs.getInt("id_photo")).isGreaterThan(0);
                assertThat(rs.getString("page")).isEqualTo("collection");
                assertThat(rs.getString("adresse")).isEqualTo("/RoseCorail/images/collection/image4.jpg");
                assertThat(rs.next()).isFalse();
            }
        }
    }

    @Test
    public void shouldDeletePhotoPresentation(){
        photoPresentationDao.deletePhotoPresentation(1);

        List<PhotosPresentation> listphotospresentation = photoPresentationDao.listphotospresentation();
        // THEN
        assertThat(listphotospresentation).hasSize(2);
        assertThat(listphotospresentation).extracting("id_photo", "page", "adresse").containsOnly(
                tuple(2, "accueil", "/RoseCorail/images/accueil/image2.jpg"),
                tuple(3, "collection", "/RoseCorail/images/collection/image3.jpg"));
    }

}
