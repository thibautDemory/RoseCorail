package hei.ProjetRoseCorail.dao.impl;

import hei.ProjetRoseCorail.dao.LigneDevisDao;
import hei.ProjetRoseCorail.entities.LigneDevis;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

public class LigneDevisDaoTestCase {
    private LigneDevisDao ligneDevisDao= new LigneDevisDaoImpl();

    @Before
    public void initDb() throws Exception {
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DELETE FROM lignedevis");
            stmt.executeUpdate("ALTER TABLE lignedevis AUTO_INCREMENT=0");
            stmt.executeUpdate("INSERT INTO `lignedevis`(id_ligne_devis,id_couleur,id_devis,id_article, quantite) VALUES (1,1,1,3,5)");
            stmt.executeUpdate("INSERT INTO `lignedevis`(id_ligne_devis,id_couleur,id_devis,id_article, quantite) VALUES (2,1,2,3,5)");
            stmt.executeUpdate("INSERT INTO `lignedevis`(id_ligne_devis,id_couleur,id_devis,id_article, quantite) VALUES (3,2,2,3,5)");
        }
    }
    @Test
    public void shouldaddLigneDevis()throws Exception{
        //given
        LigneDevis ligneDevis = new LigneDevis(null,1,2,3,5);
        //WHEN
        LigneDevis createdLigneDevis=ligneDevisDao.addLigneDevis(ligneDevis);
        //THEN
        assertThat(createdLigneDevis).isNotNull();
        assertThat(createdLigneDevis.getId_ligne_devis()).isNotNull();
        assertThat(createdLigneDevis.getId_couleur()).isEqualTo(1);
        assertThat(createdLigneDevis.getId_devis()).isEqualTo(2);
        assertThat(createdLigneDevis.getId_article()).isEqualTo(3);
        assertThat(createdLigneDevis.getQuantite()).isEqualTo(5);

        try (Connection connection=DataSourceProvider.getDataSource().getConnection();
             Statement stmt=connection.createStatement()){
            try(ResultSet rs=stmt.executeQuery("SELECT lignedevis.id_ligne_devis, lignedevis.id_couleur, lignedevis.id_devis, lignedevis.id_article, lignedevis.quantite " +
                    "FROM lignedevis WHERE lignedevis.id_ligne_devis=4;")){
                assertThat(rs.next()).isTrue();
                assertThat(rs.getInt("id_ligne_devis")).isEqualTo(createdLigneDevis.getId_ligne_devis());
                assertThat(rs.getInt("id_couleur")).isEqualTo(createdLigneDevis.getId_couleur());
                assertThat(rs.getInt("id_devis")).isEqualTo(createdLigneDevis.getId_devis());
                assertThat(rs.getInt("id_article")).isEqualTo(createdLigneDevis.getId_article());
                assertThat(rs.getInt("quantite")).isEqualTo(createdLigneDevis.getQuantite());
                assertThat(rs.next()).isFalse();

            }
        }
    }
    @Test
    public void shouldlistLignesDevisPourUneCouleur(){
        //WHEN
        List<LigneDevis> leslignesdevispourunecouleur = ligneDevisDao.listLignesDevisPourUneCouleur(2);
        //THEN
        assertThat(leslignesdevispourunecouleur).hasSize(1);
        assertThat(leslignesdevispourunecouleur).extracting("id_ligne_devis","id_couleur","id_devis","id_article", "quantite").containsOnly(
                tuple(3,2,2,3,5)
        );
    }

    @Test
    public void shouldDeleteLigneDevisPourUneCouleur(){
        ligneDevisDao.deleteLigneDevisForOneCouleur(2);
        List<LigneDevis> leslignesdevis=ligneDevisDao.listAllLigneDevis();
        //THEN
        assertThat(leslignesdevis).hasSize(2);
        assertThat(leslignesdevis).extracting("id_ligne_devis","id_couleur","id_devis","id_article","quantite").containsOnly(
                tuple(1,1,1,3,5),
                tuple(2,1,2,3,5)
        );

    }
}
