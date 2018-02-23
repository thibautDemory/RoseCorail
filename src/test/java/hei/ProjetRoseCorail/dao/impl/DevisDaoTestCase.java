package hei.ProjetRoseCorail.dao.impl;

import hei.ProjetRoseCorail.dao.DevisDao;
import hei.ProjetRoseCorail.entities.Devis;
import org.assertj.core.internal.cglib.core.Local;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
            stmt.executeUpdate("INSERT INTO devis(id_devis,id_compte_client,date_creation,etat,etatPanier) VALUES (1,1,'2017-04-06','demandé',false)");
            stmt.executeUpdate("INSERT INTO devis(id_devis,id_compte_client,date_creation,etat,etatPanier) VALUES (2,1,'2017-04-06','en cours',false)");
            stmt.executeUpdate("INSERT INTO devis(id_devis,id_compte_client,date_creation,etat,etatPanier) VALUES (3,2,'2017-04-06','expedié',false)");
            stmt.executeUpdate("INSERT INTO devis(id_devis,id_compte_client,date_creation,etat,etatPanier) VALUES (4,1,'2017-04-06','panier',true)");
        }
    }

    @Test
    public void shouldCreerUnDevis()throws Exception {
        // GIVEN
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDate maintenant = currentTime.toLocalDate();


        Devis  newDevis = new Devis(null, 3, LocalDate.now(), "demandé", true);
        // WHEN
        Devis createdDevis = devisDao.creerUnDevis(newDevis);

        // THEN
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM devis WHERE id_devis = 5")) {
                assertThat(rs.next()).isTrue();
                assertThat(rs.getInt("id_devis")).isGreaterThan(0);
                assertThat(rs.getInt("id_compte_client")).isEqualTo(3);
                assertThat(rs.getDate("date_creation").toLocalDate()).isEqualTo(maintenant);
                assertThat(rs.getString("etat")).isEqualTo("demandé");
                assertThat(rs.getBoolean("etatPanier")).isEqualTo(true);
                assertThat(rs.next()).isFalse();
            }
        }
    }

    @Test
    public void shouldListDevisByCompteClient(){
        //WHEN
        List<Devis> lesdevisdunclient = devisDao.listDevisByCompteClient(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date1 = LocalDate.parse("2017-04-06",formatter);
        LocalDate date2 = LocalDate.parse("2017-04-06",formatter);
        //THEN
        assertThat(lesdevisdunclient).hasSize(2);
        assertThat(lesdevisdunclient).extracting("id_devis","id_compte_client","date_creation","etat","etatPanier").containsOnly(
                tuple(1,1,date1,"demandé",false),
                tuple(2,1,date2,"en cours",false)
        );
    }

    @Test
    public void shouldGetPanierByCompteClient(){
        //WHEN
        Devis lepanierdunclient = devisDao.getPanierClient(1);
        //THEN
        assertThat(lepanierdunclient).isNotNull();
        assertThat(lepanierdunclient.getDate()).isEqualTo("2017-04-06");
        assertThat(lepanierdunclient.getId_compte_client()).isEqualTo(1);
        assertThat(lepanierdunclient.getEtat()).isEqualTo("panier");
        assertThat(lepanierdunclient.getEtatPanier()).isEqualTo(true);

    }

    @Test
    public void shouldpasserdepanieraEnPreparation(){
        devisDao.dePanieraEnPreparation(4);
        Devis undevis=devisDao.getDevisById(4);
        assertThat(undevis).isNotNull();
        assertThat(undevis.getId_compte_client()).isEqualTo(1);
        assertThat(undevis.getId_devis()).isEqualTo(4);
        assertThat(undevis.getEtatPanier()).isEqualTo(false);
        assertThat(undevis.getEtat()).isEqualTo("en preparation");
        assertThat(undevis.getDate()).isEqualTo("2017-04-06");
    }

}
