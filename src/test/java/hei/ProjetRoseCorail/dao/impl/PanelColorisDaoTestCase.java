package hei.ProjetRoseCorail.dao.impl;

import hei.ProjetRoseCorail.dao.PanelColorisDao;
import hei.ProjetRoseCorail.entities.Panelcoloris;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

public class PanelColorisDaoTestCase {
    private PanelColorisDao panelColorisDao=new PanelColorisDaoImpl();

    @Before
    public void initDb() throws Exception {
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DELETE FROM panelcoloris");
            stmt.executeUpdate("INSERT INTO `panelcoloris`(`id_panelcoloris`,`legende`,`image`,`saison`) VALUES (1, 'bleu cyan 1', 'imagebleucyan', 'Printemps')");
            stmt.executeUpdate("INSERT INTO `panelcoloris`(`id_panelcoloris`,`legende`,`image`,`saison`) VALUES (2, 'bleu clair 2', 'imagebleuclair', 'Hiver')");
            stmt.executeUpdate("INSERT INTO `panelcoloris`(`id_panelcoloris`,`legende`,`image`,`saison`) VALUES (3, 'bleu fonce 3', 'imagebleufonce', 'Automne')");
        }
    }
    @Test
    public void shouldListPanelColoris() {
        // WHEN
        List<Panelcoloris> listpanelcoloris = panelColorisDao.listPanelColoris();
        // THEN
        assertThat(listpanelcoloris).hasSize(3);
        assertThat(listpanelcoloris).extracting("idPanelColoris", "legende", "image", "saison").containsOnly(
                tuple(1, "bleu cyan 1", "imagebleucyan", "Printemps"),
                tuple(2, "bleu clair 2", "imagebleuclair","Hiver") ,
                tuple(3, "bleu fonce 3", "imagebleufonce", "Automne"));
    }
    @Test
    public void shouldListPanelColorisParSaison(){
        //WHEN
        List<Panelcoloris> lespanelscolorisdunesaison = panelColorisDao.listPanelColorisParSaison("Hiver");

        //THEN
        assertThat(lespanelscolorisdunesaison).hasSize(1);
        assertThat(lespanelscolorisdunesaison).extracting("idPanelColoris","legende","image","saison").containsOnly(
                tuple(2,"bleu clair 2","imagebleuclair","Hiver")
        );

    }

    @Test
    public void shouldGetPanelColorisById(){
        // WHEN
        Panelcoloris panelcoloris = panelColorisDao.getPanelColorisById(2);
        // THEN
        assertThat(panelcoloris).isNotNull();
        assertThat(panelcoloris.getIdPanelColoris()).isEqualTo(2);
        assertThat(panelcoloris.getLegende()).isEqualTo("bleu clair 2");
        assertThat(panelcoloris.getImage()).isEqualTo("imagebleuclair");
        assertThat(panelcoloris.getSaison()).isEqualTo("Hiver");
    }
    @Test
    public void shouldAddPanelColoris() throws Exception {
        // GIVEN
        Panelcoloris newPanelColoris = new Panelcoloris(null, "bleu mer 4",
                "bleumer", "Hiver");
        // WHEN
        Panelcoloris createdPanelColoris = panelColorisDao.addPanelColoris(newPanelColoris);
        // THEN
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM panelcoloris WHERE legende='bleu mer 4'")) {
                assertThat(rs.next()).isTrue();
                assertThat(rs.getInt("id_panelcoloris")).isGreaterThan(0);
                assertThat(rs.getString("legende")).isEqualTo("bleu mer 4");
                assertThat(rs.getString("image")).isEqualTo("bleumer");
                assertThat(rs.getString("saison")).isEqualTo("Hiver");
                assertThat(rs.next()).isFalse();
            }
        }
    }

    @Test
    public void shouldDeletePanelColoris(){
        panelColorisDao.deletePanelColoris(1);

        List<Panelcoloris> listpanelcoloris = panelColorisDao.listPanelColoris();
        // THEN
        assertThat(listpanelcoloris).hasSize(2);
        assertThat(listpanelcoloris).extracting("idPanelColoris", "legende", "image", "saison").containsOnly(
                tuple(2, "bleu clair 2", "imagebleuclair","Hiver") ,
                tuple(3, "bleu fonce 3", "imagebleufonce", "Automne")
        );
    }
}
