package hei.ProjetRoseCorail.dao.impl;

import hei.ProjetRoseCorail.dao.ActualiteDao;
import hei.ProjetRoseCorail.dao.CompteClientDao;
import hei.ProjetRoseCorail.entities.Actualite;
import hei.ProjetRoseCorail.entities.CompteClient;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

public class CompteClientDaoTestCase {

    private CompteClientDao compteClientDao = new CompteClientDaoImpl();

    @Before
    public void initDb() throws Exception {
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DELETE FROM compteclient");
            stmt.executeUpdate("INSERT INTO `compteclient`(`id_compte_client`,`email`,`nom_boutique`,`nom_gerant`,`adresse`,`ville`,`code_postal`,`mdp`, `numero_tel`) VALUES (1,'william@evrard.fr', 'HEI', 'EVRARD', '26 BD Bigo Danel', 'Lille', '59000', 'monMDP1', '0606060606')");
            stmt.executeUpdate("INSERT INTO `compteclient`(`id_compte_client`,`email`,`nom_boutique`,`nom_gerant`,`adresse`,`ville`,`code_postal`,`mdp`, `numero_tel`) VALUES (2,'thibaut@demory.fr', 'ISA', 'DEMORY', '20 rue Beaucourt', 'Lille', '59000', 'monMDP2', '0606060607')");
            stmt.executeUpdate("INSERT INTO `compteclient`(`id_compte_client`,`email`,`nom_boutique`,`nom_gerant`,`adresse`,`ville`,`code_postal`,`mdp`, `numero_tel`) VALUES (3,'arnold@blyau.fr', 'ISEN', 'BLYAU', '54 rue Paul Bocuse', 'Lille', '59000', 'monMDP3', '0606060608')");
        }
    }

    @Test
    public void shouldListCompteClient() {
        // WHEN
        List<CompteClient> compteClients = compteClientDao.listComptesClient();
        // THEN
        assertThat(compteClients).hasSize(3);
        assertThat(compteClients).extracting("id_compte_client","email","nom_boutique","nom_gerant","adresse","ville","code_postal","mdp", "numero_tel").containsOnly(
                tuple(1,"william@evrard.fr", "HEI", "EVRARD", "26 BD Bigo Danel", "Lille", "59000", "monMDP1", "0606060606"),
                tuple(2,"thibaut@demory.fr", "ISA", "DEMORY", "20 rue Beaucourt", "Lille", "59000", "monMDP2", "0606060607"),
                tuple(3,"arnold@blyau.fr", "ISEN", "BLYAU", "54 rue Paul Bocuse", "Lille", "59000", "monMDP3", "0606060608")
        );
    }

    @Test
    public void shouldAddCompteClient() throws Exception {
        // GIVEN
        CompteClient newCompteClient = new CompteClient(null, "my new email",
                "my new shop's name", "my new boss's name", "my new adress",
                "my new city", "02160", "my new password", "0323249007");
        // WHEN
        CompteClient createdCompteClient = compteClientDao.addCompteClient(newCompteClient);
        // THEN
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM compteclient WHERE email = 'my new email'")) {
                assertThat(rs.next()).isTrue();
                assertThat(rs.getInt("id_compte_client")).isGreaterThan(0);
                assertThat(rs.getString("email")).isEqualTo("my new email");
                assertThat(rs.getString("nom_boutique")).isEqualTo("my new shop's name");
                assertThat(rs.getString("nom_gerant")).isEqualTo("my new boss's name");
                assertThat(rs.getString("adresse")).isEqualTo("my new adress");
                assertThat(rs.getString("ville")).isEqualTo("my new city");
                assertThat(rs.getString("code_postal")).isEqualTo("02160");
                assertThat(rs.getString("mdp")).isEqualTo("my new password");
                assertThat(rs.getString("numero_tel")).isEqualTo("0323249007");
                assertThat(rs.next()).isFalse();
            }
        }
    }

    @Test
    public void shouldDeleteCompteClient(){
        compteClientDao.deleteCompteClient(1);

        List<CompteClient> comptesClient = compteClientDao.listComptesClient();
        // THEN
        assertThat(comptesClient).hasSize(2);
        assertThat(comptesClient).extracting("id_compte_client","email","nom_boutique","nom_gerant","adresse","ville","code_postal","mdp", "numero_tel").containsOnly(
                tuple(2,"thibaut@demory.fr", "ISA", "DEMORY", "20 rue Beaucourt", "Lille", "59000", "monMDP2", "0606060607"),
                tuple(3,"arnold@blyau.fr", "ISEN", "BLYAU", "54 rue Paul Bocuse", "Lille", "59000", "monMDP3", "0606060608")
        );
    }
}
