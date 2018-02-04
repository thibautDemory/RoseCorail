package hei.ProjetRoseCorail.dao.impl;

import hei.ProjetRoseCorail.dao.CouleurDao;
import hei.ProjetRoseCorail.entities.Couleur;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.assertj.core.api.Assertions.assertThat;

public class CouleurDaoTestCase {

    private CouleurDao couleurDao = new CouleurDaoImpl();

    @Before
    public void initDb() throws Exception {
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DELETE FROM couleur");
            stmt.executeUpdate("INSERT INTO `couleur`(`id_couleur`,`nom_couleur`,`num_couleur`,`image`,`saison`) VALUES (1, 'Bleu gris', '087', 'image1', 'Printemps-Eté 2017')");
            stmt.executeUpdate("INSERT INTO `couleur`(`id_couleur`,`nom_couleur`,`num_couleur`,`image`,`saison`) VALUES (2, 'Bleu glacier', '097', 'image1', 'Printemps-Eté 2017')");
            stmt.executeUpdate("INSERT INTO `couleur`(`id_couleur`,`nom_couleur`,`num_couleur`,`image`,`saison`) VALUES (3, 'Bleu vert', '08', 'image1', 'Printemps-Eté 2017')");
            stmt.executeUpdate("INSERT INTO `couleur`(`id_couleur`,`nom_couleur`,`num_couleur`,`image`,`saison`) VALUES (4, 'Sapin bleuté', '091', 'image1', 'Printemps-Eté 2017')");
        }
    }


    @Test
    public void shouldAddCouleur() throws Exception {
        // GIVEN
        Couleur newCouleur = new Couleur(null, "my new name of color",
                666, "my new image", "my new season");
        // WHEN
        Couleur createdCouleur = couleurDao.addCouleur(newCouleur);
        // THEN
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM couleur WHERE nom_couleur = 'my new name of color'")) {
                assertThat(rs.next()).isTrue();
                assertThat(rs.getInt("id_couleur")).isGreaterThan(0);
                assertThat(rs.getString("nom_couleur")).isEqualTo("my new name of color");
                assertThat(rs.getInt("num_couleur")).isEqualTo(666);
                assertThat(rs.getString("image")).isEqualTo("my new image");
                assertThat(rs.getString("saison")).isEqualTo("my new season");
                assertThat(rs.next()).isFalse();
            }
        }
    }
}
