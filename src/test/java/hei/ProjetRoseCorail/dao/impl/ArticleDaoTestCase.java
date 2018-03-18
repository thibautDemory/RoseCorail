package hei.ProjetRoseCorail.dao.impl;

import hei.ProjetRoseCorail.dao.ArticleDao;
import hei.ProjetRoseCorail.entities.Article;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

public class ArticleDaoTestCase {
    private ArticleDao articleDao=new ArticleDaoImpl();

    @Before
    public void initDb() throws Exception {
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DELETE FROM article");
            stmt.executeUpdate("INSERT INTO article(`id_article`,`id_sous_categorie`,`nom_article`,`reference`,`description`,`image`,`dimensions`,`prix`, `lot_vente`,actif) VALUES (1,1, 'Plats à cake', '6030 407', 'Plat à cake', 'imageplat1', '32 x 15 cm', 10.0, 1,1)");
            stmt.executeUpdate("INSERT INTO article(`id_article`,`id_sous_categorie`,`nom_article`,`reference`,`description`,`image`,`dimensions`,`prix`, `lot_vente`,actif) VALUES (2,1, 'Plat à cake avec bords', '6030 414', 'Plat à cake avec bords', 'imageplat2', '32 x 15 cm', 12.0, 1,1)");
            stmt.executeUpdate("INSERT INTO article(`id_article`,`id_sous_categorie`,`nom_article`,`reference`,`description`,`image`,`dimensions`,`prix`, `lot_vente`,actif) VALUES (3,2, 'Plats à fromage', '6030 062', 'Plat carré', 'imageplat3', '30 x 30 cm', 14.0, 1,1)");

        }
    }

    @Test
    public void shouldListArticlesActifs() {
        // WHEN
        List<Article> articles = articleDao.listArticlesActifs();
        // THEN
        assertThat(articles).hasSize(3);
        assertThat(articles).extracting("id_article", "id_sous_categorie", "nom_article", "reference","description","image","dimension","prix","lot_vente","actif").containsOnly(
                tuple(1,1, "Plats à cake", "6030 407", "Plat à cake", "imageplat1", "32 x 15 cm", 10.0, 1,1),
                tuple(2,1, "Plat à cake avec bords", "6030 414", "Plat à cake avec bords", "imageplat2", "32 x 15 cm", 12.0, 1,1),
                tuple(3,2, "Plats à fromage", "6030 062", "Plat carré", "imageplat3", "30 x 30 cm", 14.0, 1,1)
        );
    }
    @Test
    public void shouldGetArticlebyID() {
        // WHEN
        Article article = articleDao.getArticleById(1);
        // THEN
        assertThat(article).isNotNull();
        assertThat(article.getId_sous_categorie()).isEqualTo(1);
        assertThat(article.getNom_article()).isEqualTo("Plats à cake");
        assertThat(article.getReference()).isEqualTo("6030 407");
        assertThat(article.getDescription()).isEqualTo("Plat à cake");
        assertThat(article.getImage()).isEqualTo("imageplat1");
        assertThat(article.getDimension()).isEqualTo("32 x 15 cm");
        assertThat(article.getPrix()).isEqualTo(10.0);
        assertThat(article.getLot_vente()).isEqualTo(1);
        assertThat(article.getActif()).isEqualTo(1);
    }

    @Test
    public void shouldGetArticlebyName() {
        // WHEN
        Article article = articleDao.getArticleByNom("Plats à cake");
        // THEN
        assertThat(article).isNotNull();
        assertThat(article.getId_sous_categorie()).isEqualTo(1);
        assertThat(article.getNom_article()).isEqualTo("Plats à cake");
        assertThat(article.getReference()).isEqualTo("6030 407");
        assertThat(article.getDescription()).isEqualTo("Plat à cake");
        assertThat(article.getImage()).isEqualTo("imageplat1");
        assertThat(article.getDimension()).isEqualTo("32 x 15 cm");
        assertThat(article.getPrix()).isEqualTo(10.0);
        assertThat(article.getLot_vente()).isEqualTo(1);
        assertThat(article.getActif()).isEqualTo(1);
    }

    @Test
    public void shouldAddArticle() throws Exception {
        // GIVEN
        Article newArticle = new Article(null, 2,
                "plat rond", "612 612", "plat rond de thibaut", "imageplatrond", "32 x 32 cm", 13.5, 2, 1);
        // WHEN
        Article createdArticle = articleDao.addArticle(newArticle);
        // THEN
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM article WHERE nom_article = 'plat rond'")) {
                assertThat(rs.next()).isTrue();
                assertThat(rs.getInt("id_sous_categorie")).isGreaterThan(0);
                assertThat(rs.getString("nom_article")).isEqualTo("plat rond");
                assertThat(rs.getString("reference")).isEqualTo("612 612");
                assertThat(rs.getString("description")).isEqualTo("plat rond de thibaut");
                assertThat(rs.getString("image")).isEqualTo("imageplatrond");
                assertThat(rs.getString("dimensions")).isEqualTo("32 x 32 cm");
                assertThat(rs.getDouble("prix")).isEqualTo(13.5);
                assertThat(rs.getInt("lot_vente")).isEqualTo(2);
                assertThat(rs.getInt("actif")).isEqualTo(1);
                assertThat(rs.next()).isFalse();
            }
        }
    }

    @Test
    public void shouldDeleteArticle(){
        articleDao.deleteArticle(1);

        List<Article> articles = articleDao.listArticlesActifs();
        // THEN
        assertThat(articles).hasSize(2);
        assertThat(articles).extracting("id_article", "id_sous_categorie", "nom_article", "reference","description","image","dimension","prix","lot_vente","actif").containsOnly(
                tuple(2,1, "Plat à cake avec bords", "6030 414", "Plat à cake avec bords", "imageplat2", "32 x 15 cm", 12.0, 1,1),
                tuple(3,2, "Plats à fromage", "6030 062", "Plat carré", "imageplat3", "30 x 30 cm", 14.0, 1,1)
        );
    }

}
