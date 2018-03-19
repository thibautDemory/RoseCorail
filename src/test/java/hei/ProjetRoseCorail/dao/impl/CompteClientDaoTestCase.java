package hei.ProjetRoseCorail.dao.impl;

import hei.ProjetRoseCorail.dao.ActualiteDao;
import hei.ProjetRoseCorail.dao.CompteClientDao;
import hei.ProjetRoseCorail.entities.Actualite;
import hei.ProjetRoseCorail.entities.CompteClient;
import hei.ProjetRoseCorail.entities.CompteRoseCorail;
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
            stmt.executeUpdate("DELETE FROM lignedevis");
            stmt.executeUpdate("DELETE FROM definir");
            stmt.executeUpdate("DELETE FROM devis");
            stmt.executeUpdate("DELETE FROM compteclient");
            stmt.executeUpdate("INSERT INTO `compteclient`(`id_compte_client`,`email`,`nom_boutique`,`nom_gerant`,`prenom_gerant`,`adresse`,`ville`,`code_postal`,`mdp`, `numero_tel`, `num_tva`, `site_internet`, `description_activite`, numero_panier_actif) VALUES (1,'william@evrard.fr', 'HEI', 'EVRARD', 'William', '26 BD Bigo Danel', 'Lille', '59000', 'monMDP1', '0606060606', 'FR 40 123456824', 'william.evrard.fr', 'description1',1)");
            stmt.executeUpdate("INSERT INTO `compteclient`(`id_compte_client`,`email`,`nom_boutique`,`nom_gerant`,`prenom_gerant`,`adresse`,`ville`,`code_postal`,`mdp`, `numero_tel`, `num_tva`, `site_internet`, `description_activite`, numero_panier_actif) VALUES (2,'thibaut@demory.fr', 'ISA', 'DEMORY', 'Thibaut', '20 rue Beaucourt', 'Lille', '59000', 'monMDP2', '0606060607', 'FR 41 123456824', 'thib.demory.fr', 'description2',2)");
            stmt.executeUpdate("INSERT INTO `compteclient`(`id_compte_client`,`email`,`nom_boutique`,`nom_gerant`,`prenom_gerant`,`adresse`,`ville`,`code_postal`,`mdp`, `numero_tel`, `num_tva`, `site_internet`, `description_activite`, numero_panier_actif) VALUES (3,'arnold@blyau.fr', 'ISEN', 'BLYAU', 'Arnold', '54 rue Paul Bocuse', 'Lille', '59000', 'monMDP3', '0606060608', 'FR 42 123456824', 'arnold.blyau.fr', 'description3',3)");
        }
    }

    @Test
    public void shouldListCompteClient() {
        // WHEN
        List<CompteClient> compteClients = compteClientDao.listComptesClient();
        // THEN
        assertThat(compteClients).hasSize(3);
        assertThat(compteClients).extracting("id_compte_client","email","nom_boutique","nom_gerant","prenom_gerant","adresse","ville","code_postal","mdp", "numero_tel","num_tva","site_internet","description_activite","numero_panier_actif").containsOnly(
                tuple(1,"william@evrard.fr", "HEI", "EVRARD", "William", "26 BD Bigo Danel", "Lille", "59000", "monMDP1", "0606060606", "FR 40 123456824", "william.evrard.fr", "description1",1),
                tuple(2,"thibaut@demory.fr", "ISA", "DEMORY", "Thibaut", "20 rue Beaucourt", "Lille", "59000", "monMDP2", "0606060607", "FR 41 123456824", "thib.demory.fr", "description2",2),
                tuple(3,"arnold@blyau.fr", "ISEN", "BLYAU", "Arnold", "54 rue Paul Bocuse", "Lille", "59000", "monMDP3", "0606060608", "FR 42 123456824", "arnold.blyau.fr", "description3",3)
        );
    }

    @Test
    public void shouldGetCompteClientById(){
        //WHEN
        CompteClient compteClient=compteClientDao.getCompteClientById(1);
        //THEN
        assertThat(compteClient).isNotNull();
        assertThat(compteClient.getAdresse()).isEqualTo("26 BD Bigo Danel");
        assertThat(compteClient.getId_compte_client()).isEqualTo(1);
        assertThat(compteClient.getEmail()).isEqualTo("william@evrard.fr");
        assertThat(compteClient.getNom_boutique()).isEqualTo("HEI");
        assertThat(compteClient.getNom_gerant()).isEqualTo("EVRARD");
        assertThat(compteClient.getPrenom_gerant()).isEqualTo("William");
        assertThat(compteClient.getVille()).isEqualTo("Lille");
        assertThat(compteClient.getCode_postal()).isEqualTo("59000");
        assertThat(compteClient.getMdp()).isEqualTo("monMDP1");
        assertThat(compteClient.getNumero_tel()).isEqualTo("0606060606");
        assertThat(compteClient.getNum_tva()).isEqualTo("FR 40 123456824");
        assertThat(compteClient.getSite_internet()).isEqualTo("william.evrard.fr");
        assertThat(compteClient.getDescription_activite()).isEqualTo("description1");
        assertThat(compteClient.getNumero_panier_actif()).isEqualTo(1);
    }

    @Test
    public void shouldGetCompteClientByMail(){
        //WHEN
        CompteClient compteClient=compteClientDao.getCompteClientByMail("william@evrard.fr");
        //THEN
        assertThat(compteClient).isNotNull();
        assertThat(compteClient.getAdresse()).isEqualTo("26 BD Bigo Danel");
        assertThat(compteClient.getId_compte_client()).isEqualTo(1);
        assertThat(compteClient.getEmail()).isEqualTo("william@evrard.fr");
        assertThat(compteClient.getNom_boutique()).isEqualTo("HEI");
        assertThat(compteClient.getNom_gerant()).isEqualTo("EVRARD");
        assertThat(compteClient.getPrenom_gerant()).isEqualTo("William");
        assertThat(compteClient.getVille()).isEqualTo("Lille");
        assertThat(compteClient.getCode_postal()).isEqualTo("59000");
        assertThat(compteClient.getMdp()).isEqualTo("monMDP1");
        assertThat(compteClient.getNumero_tel()).isEqualTo("0606060606");
        assertThat(compteClient.getNum_tva()).isEqualTo("FR 40 123456824");
        assertThat(compteClient.getSite_internet()).isEqualTo("william.evrard.fr");
        assertThat(compteClient.getDescription_activite()).isEqualTo("description1");
        assertThat(compteClient.getNumero_panier_actif()).isEqualTo(1);
    }
    @Test
    public void shouldAddCompteClient() throws Exception {
        // GIVEN
        CompteClient newCompteClient = new CompteClient(null, "my new email",
                "my new shop's name", "my new boss's name", "my new boss's firstname",
                "my new adress", "my new city", "02160", "my new password", "0323249007",
                "my new TVA", "my new Web site", "my new description");
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
                assertThat(rs.getString("prenom_gerant")).isEqualTo("my new boss's firstname");
                assertThat(rs.getString("adresse")).isEqualTo("my new adress");
                assertThat(rs.getString("ville")).isEqualTo("my new city");
                assertThat(rs.getString("code_postal")).isEqualTo("02160");
                assertThat(rs.getString("mdp")).isEqualTo("my new password");
                assertThat(rs.getString("numero_tel")).isEqualTo("0323249007");
                assertThat(rs.getString("num_tva")).isEqualTo("my new TVA");
                assertThat(rs.getString("site_internet")).isEqualTo("my new Web site");
                assertThat(rs.getString("description_activite")).isEqualTo("my new description");
                assertThat(rs.next()).isFalse();
            }
        }
    }

    @Test
    public void shouldUpdateCompteClientWithoutPassword() throws Exception {
        CompteClient compteClient = new CompteClient(1,"william@evrard.COM", "Hei", "EVRARD", "William", "26 BD Bigo Danel", "Lille", "59010", "0606060606", "FR 40 123456824", "william.evrard.fr", "description1");

        CompteClient updateCompteClient = compteClientDao.updateCompteClientWithoutPassword(compteClient);

        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM compteclient WHERE email = 'william@evrard.COM'")) {
                assertThat(rs.next()).isTrue();
                assertThat(rs.getInt("id_compte_client")).isGreaterThan(0);
                assertThat(rs.getString("email")).isEqualTo("william@evrard.COM");
                assertThat(rs.getString("nom_boutique")).isEqualTo("Hei");
                assertThat(rs.getString("nom_gerant")).isEqualTo("EVRARD");
                assertThat(rs.getString("prenom_gerant")).isEqualTo("William");
                assertThat(rs.getString("adresse")).isEqualTo("26 BD Bigo Danel");
                assertThat(rs.getString("ville")).isEqualTo("Lille");
                assertThat(rs.getString("code_postal")).isEqualTo("59010");
                assertThat(rs.getString("numero_tel")).isEqualTo("0606060606");
                assertThat(rs.getString("num_tva")).isEqualTo("FR 40 123456824");
                assertThat(rs.getString("site_internet")).isEqualTo("william.evrard.fr");
                assertThat(rs.getString("description_activite")).isEqualTo("description1");
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
        assertThat(comptesClient).extracting("id_compte_client","email","nom_boutique","nom_gerant", "prenom_gerant", "adresse","ville","code_postal","mdp", "numero_tel", "num_tva", "site_internet", "description_activite","numero_panier_actif").containsOnly(
                tuple(2,"thibaut@demory.fr", "ISA", "DEMORY", "Thibaut", "20 rue Beaucourt", "Lille", "59000", "monMDP2", "0606060607", "FR 41 123456824", "thib.demory.fr", "description2",2),
                tuple(3,"arnold@blyau.fr", "ISEN", "BLYAU", "Arnold", "54 rue Paul Bocuse", "Lille", "59000", "monMDP3", "0606060608", "FR 42 123456824", "arnold.blyau.fr", "description3",3)
        );
    }

    @Test
    public void shouldUpdatePassword(){
        //DO
        compteClientDao.updatePassword(2,"NewPassword");
        //WHEN
        CompteClient compteClient=compteClientDao.getCompteClientById(2);
        //THEN
        assertThat(compteClient).isNotNull();
        assertThat(compteClient.getId_compte_client()).isEqualTo(2);
        assertThat(compteClient.getEmail()).isEqualTo("thibaut@demory.fr");
        assertThat(compteClient.getNom_boutique()).isEqualTo("ISA");
        assertThat(compteClient.getNom_gerant()).isEqualTo("DEMORY");
        assertThat(compteClient.getPrenom_gerant()).isEqualTo("Thibaut");
        assertThat(compteClient.getAdresse()).isEqualTo("20 rue Beaucourt");
        assertThat(compteClient.getVille()).isEqualTo("Lille");
        assertThat(compteClient.getCode_postal()).isEqualTo("59000");
        assertThat(compteClient.getMdp()).isEqualTo("NewPassword");
        assertThat(compteClient.getNumero_tel()).isEqualTo("0606060607");
        assertThat(compteClient.getNum_tva()).isEqualTo("FR 41 123456824");
        assertThat(compteClient.getSite_internet()).isEqualTo("thib.demory.fr");
        assertThat(compteClient.getDescription_activite()).isEqualTo("description2");
        assertThat(compteClient.getNumero_panier_actif()).isEqualTo(2);
    }

    @Test
    public void shouldChangerNumeroPanierActif(){
        //DO
        compteClientDao.changerNumeroPanierActif(3,5);
        //WHEN
        CompteClient compteClient=compteClientDao.getCompteClientById(3);
        //THEN
        assertThat(compteClient).isNotNull();
        assertThat(compteClient.getNumero_panier_actif()).isEqualTo(5);
    }

}
