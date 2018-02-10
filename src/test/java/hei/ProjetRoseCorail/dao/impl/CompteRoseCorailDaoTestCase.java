package hei.ProjetRoseCorail.dao.impl;

import hei.ProjetRoseCorail.dao.CompteRoseCorailDao;
import hei.ProjetRoseCorail.entities.CompteClient;
import hei.ProjetRoseCorail.entities.CompteRoseCorail;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Statement;

import static org.assertj.core.api.Assertions.assertThat;

public class CompteRoseCorailDaoTestCase {
    private CompteRoseCorailDao compteRoseCorailDao = new CompteRoseCorailDaoImpl();

    @Before
    public void initDb() throws Exception {
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DELETE FROM compterosecorail");
            stmt.executeUpdate("INSERT INTO `compterosecorail`(`id_compte_RC`,`email`,`mdp`, `numero_tel`) VALUES (1,'beatrice.roquette@rosecorail.com','monmdp','0623136482')");

        }
    }
    @Test
    public void shouldGetCompteRoseCorailById(){
        //WHEN
        CompteRoseCorail compteRoseCorail=compteRoseCorailDao.getCompteRoseCorailById(1);
        //THEN
        assertThat(compteRoseCorail).isNotNull();
        assertThat(compteRoseCorail.getId_compte_rose_corail()).isEqualTo(1);
        assertThat(compteRoseCorail.getEmail()).isEqualTo("beatrice.roquette@rosecorail.com");
        assertThat(compteRoseCorail.getMdp()).isEqualTo("monmdp");
        assertThat(compteRoseCorail.getNumero_tel()).isEqualTo("0623136482");
    }

    @Test
    public void shouldGetCompteRoseCorailByMail(){
        //WHEN
        CompteRoseCorail compteRoseCorail=compteRoseCorailDao.getCompteRoseCorailByMail("beatrice.roquette@rosecorail.com");
        //THEN
        assertThat(compteRoseCorail).isNotNull();
        assertThat(compteRoseCorail.getId_compte_rose_corail()).isEqualTo(1);
        assertThat(compteRoseCorail.getEmail()).isEqualTo("beatrice.roquette@rosecorail.com");
        assertThat(compteRoseCorail.getMdp()).isEqualTo("monmdp");
        assertThat(compteRoseCorail.getNumero_tel()).isEqualTo("0623136482");
    }

    @Test
    public void shouldUpdatePassword(){
        //DO
        compteRoseCorailDao.updatePassword(1,"NewPassword");
        //WHEN
        CompteRoseCorail compteRoseCorail=compteRoseCorailDao.getCompteRoseCorailById(1);
        //THEN
        assertThat(compteRoseCorail).isNotNull();
        assertThat(compteRoseCorail.getId_compte_rose_corail()).isEqualTo(1);
        assertThat(compteRoseCorail.getEmail()).isEqualTo("beatrice.roquette@rosecorail.com");
        assertThat(compteRoseCorail.getMdp()).isEqualTo("NewPassword");
        assertThat(compteRoseCorail.getNumero_tel()).isEqualTo("0623136482");
    }
}
