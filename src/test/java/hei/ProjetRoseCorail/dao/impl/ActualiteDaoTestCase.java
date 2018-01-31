package hei.ProjetRoseCorail.dao.impl;

import hei.ProjetRoseCorail.dao.ActualiteDao;
import hei.ProjetRoseCorail.entities.Actualite;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.assertj.core.api.Assertions.assertThat;

public class ActualiteDaoTestCase {

    private ActualiteDao actualiteDao = new ActualiteDaoImpl();

    @Before
    public void initDb() throws Exception {
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DELETE FROM actualite");
            stmt.executeUpdate("INSERT INTO `actualite`(`id_actualite`,`titre`,`contenu`,`image`) VALUES (1, 'Salon de Paris', 'BLAblaBLAblaBLAblaBLAblaBLAblaBLAblaPARIS', 'imageSalonParis')");
            stmt.executeUpdate("INSERT INTO `actualite`(`id_actualite`,`titre`,`contenu`,`image`) VALUES (2, 'Salon de Reims', 'BLAblaBLAblaBLAblaBLAblaBLAblaBLAblaREIMS', 'imageSalonREIMS')");
            stmt.executeUpdate("INSERT INTO `actualite`(`id_actualite`,`titre`,`contenu`,`image`) VALUES (3, 'Salon de Picardie', 'BLAblaBLAblaBLAblaBLAblaBLAblaBLAblaPICARDIE', 'imageSalonPICARDIE')");
        }
    }

    @Test
    public void shouldAddActualite() throws Exception {
        // GIVEN
        Actualite newActualite = new Actualite(null, "my new title",
                "my new contenu", "my new image");
        // WHEN
        Actualite createdActualite = actualiteDao.addActualite(newActualite);
        // THEN
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM actualite WHERE titre = 'my new title'")) {
                assertThat(rs.next()).isTrue();
                assertThat(rs.getInt("id_actualite")).isGreaterThan(0);
                assertThat(rs.getString("titre")).isEqualTo("my new title");
                assertThat(rs.getString("contenu")).isEqualTo("my new contenu");
                assertThat(rs.getString("image")).isEqualTo("my new image");
                assertThat(rs.next()).isFalse();
            }
        }
    }

}
