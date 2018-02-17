package hei.ProjetRoseCorail.dao.impl;

import hei.ProjetRoseCorail.dao.PossederDao;
import hei.ProjetRoseCorail.entities.Article;
import hei.ProjetRoseCorail.entities.Couleur;
import hei.ProjetRoseCorail.entities.Posseder;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

public class PossederDaoTestCase {

    private PossederDao possederDao= new PossederDaoImpl();

    @Before
    public void initDb() throws Exception {
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DELETE FROM posseder");
            stmt.executeUpdate("INSERT INTO `posseder`(id_posseder,id_couleur,id_article) VALUES (1,1,1)");
            stmt.executeUpdate("INSERT INTO `posseder`(id_posseder,id_couleur,id_article) VALUES (2,1,2)");
            stmt.executeUpdate("INSERT INTO `posseder`(id_posseder,id_couleur,id_article) VALUES (3,2,2)");
        }
    }

    @Test
    public void shouldListCouleurParArticle(){
        //WHEN
        List<Couleur> lescouleursdunarticle = possederDao.listCouleursPourUnArticle(1);
        //THEN
        assertThat(lescouleursdunarticle).hasSize(1);
        assertThat(lescouleursdunarticle).extracting("id_couleur","nom_couleur","num_couleur","image","saison").containsOnly(
                tuple(1,"Bleu gris","087", "image1","Printemps-Eté 2017")
        );
    }

    @Test
    public void shouldListArticleParCouleur(){
        //WHEN
        List<Article> lesarticlesdunecouleur = possederDao.listArticlesPourUneCouleur(1);
        //THEN
        assertThat(lesarticlesdunecouleur).hasSize(2);
        assertThat(lesarticlesdunecouleur).extracting("id_article","id_sous_categorie","nom_article","reference","description","image","dimension","prix","lot_vente").containsOnly(
                tuple(1,1,"Plats à cake","6030 407","Plat à cake","imagePlatàCake","32 x 15 cm",10.0,1),
                tuple(2,1,"Plat à cake avec bords","6030 414","Plat à cake avec bords","imagePlatàCakeAvecBords","32 x 15 cm",12.0,1)
        );
    }
    @Test
    public void shouldaddPosseder()throws Exception{
        //given
        Posseder posseder = new Posseder(null,2,3);
        //WHEN
        Posseder createdposseder=possederDao.addPosseder(posseder);
        //THEN
        assertThat(createdposseder).isNotNull();
        assertThat(createdposseder.getId_posseder()).isNotNull();
        assertThat(createdposseder.getId_article()).isEqualTo(3);
        assertThat(createdposseder.getId_couleur()).isEqualTo(2);

        try (Connection connection=DataSourceProvider.getDataSource().getConnection();
        Statement stmt=connection.createStatement()){
            try(ResultSet rs=stmt.executeQuery("SELECT posseder.id_posseder, posseder.id_couleur, id_article, couleur.nom_couleur, article.nom_article" +
                    "FROM posseder join couleur on couleur.id_couleur=posseder.id_couleur JOIN article on article.id_article=posseder.id_article WHERE id_posseder=4;")){
                assertThat(rs.next()).isTrue();
                assertThat(rs.getInt("id_posseder")).isEqualTo(createdposseder.getId_posseder());
                assertThat(rs.getInt("id_couleur")).isEqualTo(createdposseder.getId_couleur());
                assertThat(rs.getInt("id_article")).isEqualTo(createdposseder.getId_article());
                assertThat(rs.getString("nom_couleur")).isEqualTo("Bleu glacier");
                assertThat(rs.getString("nom_article")).isEqualTo("Plats à fromage");
                assertThat(rs.next()).isFalse();

            }
        }
    }
}
