package hei.ProjetRoseCorail.dao.impl;

import hei.ProjetRoseCorail.dao.DevisDao;
import hei.ProjetRoseCorail.entities.Devis;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

public class DevisDaoTestCase {
    private DevisDao devisDao = new DevisDaoImpl();
    @Before
    public void initDb() throws Exception {
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DELETE FROM lignedevis");
            stmt.executeUpdate("DELETE FROM definir");
            stmt.executeUpdate("DELETE FROM devis");
            stmt.executeUpdate("ALTER TABLE devis AUTO_INCREMENT=0");
            stmt.executeUpdate("INSERT INTO devis(id_devis,id_compte_client,date,etat,etatPanier) VALUES (1,1,'2017-04-06','demandé',false)");
            stmt.executeUpdate("INSERT INTO devis(id_devis,id_compte_client,date,etat,etatPanier) VALUES (2,1,'2017-04-06','en cours',false)");
            stmt.executeUpdate("INSERT INTO devis(id_devis,id_compte_client,date,etat,etatPanier) VALUES (3,2,'2017-04-06','expedié',false)");
            stmt.executeUpdate("INSERT INTO devis(id_devis,id_compte_client,date,etat,etatPanier) VALUES (4,1,'2017-04-06','panier',true)");
        }
    }
/*
    @Test
    public void shouldCreerUnDevis()throws Exception {
        // GIVEN
        SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yy");


        Devis  newDevis = new Devis(null, 3, maintenant, "demandé", true);
        // WHEN
        Devis createdDevis = devisDao.creerUnDevis(newDevis);
        // THEN
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM devis WHERE id_devis = 5")) {
                assertThat(rs.next()).isTrue();
                assertThat(rs.getInt("id_devis")).isGreaterThan(0);
                assertThat(rs.getInt("id_compte_client")).isEqualTo(3);
                assertThat(rs.getDate("date")).isEqualTo(maintenant);
                assertThat(rs.getString("etat")).isEqualTo("demandé");
                assertThat(rs.getString("etatPanier")).isEqualTo("true");
                assertThat(rs.next()).isFalse();
            }
        }
    }*/

    @Test
    public void shouldListDevisByCompteClient(){
        //WHEN
        List<Devis> lesdevisdunclient = devisDao.listDevisByCompteClient(1);
        //THEN
        assertThat(lesdevisdunclient).hasSize(1);
        assertThat(lesdevisdunclient).extracting("id_devis","id_compte_client","date","etat","etatPanier").containsOnly(
                tuple(1,1,2017-04-06,"demandé",false),
                tuple(2,1,2017-04-06,"en cours",false)
        );
    }

    @Test
    public void shouldGetPanierByCompteClient(){
        //WHEN
        Devis lepanierdunclient = devisDao.getPanierClient(1);
        //THEN
        assertThat(lepanierdunclient).isNotNull();
        assertThat(lepanierdunclient.getDate()).isEqualTo(new Date());
        assertThat(lepanierdunclient.getId_compte_client()).isEqualTo(1);
        assertThat(lepanierdunclient.getEtat()).isEqualTo("panier");
        assertThat(lepanierdunclient.getEtatPanier()).isEqualTo(true);

    }

}