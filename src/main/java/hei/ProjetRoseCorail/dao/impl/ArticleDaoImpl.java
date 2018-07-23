package hei.ProjetRoseCorail.dao.impl;

import hei.ProjetRoseCorail.dao.ArticleDao;
import hei.ProjetRoseCorail.entities.Article;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDaoImpl implements ArticleDao {
    /**
     * Cette méthode permet de lister tous les articles qui sont actuellement actifs
     * @return la liste des articles actifs
     */
    @Override
    public List<Article> listArticlesActifs() {
        String query = "SELECT * FROM article where actif=1;";
        List<Article> listofArticles = new ArrayList<>();
        try (
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                listofArticles.add(new Article(
                        resultSet.getInt("id_article"),
                        resultSet.getInt("id_sous_categorie"),
                        resultSet.getString("nom_article"),
                        resultSet.getString("reference"),
                        resultSet.getString("description"),
                        resultSet.getBinaryStream("image"),
                        resultSet.getString("dimensions"),
                        resultSet.getDouble("prix"),
                        resultSet.getInt("lot_vente"),
                        resultSet.getInt("actif")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listofArticles;
    }
    /**
     * Cette méthode permet de lister tous les articles faisant partie de la catégorie plat qui sont actuellement actifs
     * @return la liste des articles de la catégorie plat, actifs
     */
    @Override
    public List<Article> listArticlesPlats() {
        String query = "SELECT * FROM article WHERE (id_sous_categorie=1 or id_sous_categorie=2 or id_sous_categorie=3) AND actif=1;";
        List<Article> listofArticles = new ArrayList<>();
        try (
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                listofArticles.add(new Article(
                        resultSet.getInt("id_article"),
                        resultSet.getInt("id_sous_categorie"),
                        resultSet.getString("nom_article"),
                        resultSet.getString("reference"),
                        resultSet.getString("description"),
                        resultSet.getBinaryStream("image"),
                        resultSet.getString("dimensions"),
                        resultSet.getDouble("prix"),
                        resultSet.getInt("lot_vente"),
                        resultSet.getInt("actif")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listofArticles;
    }
    /**
     * Cette méthode permet de lister tous les articles de la sous-catégorie Plat à cake qui sont actuellement actifs
     * @return la liste des articles de la sous-catégorie Plat à cake actifs
     */
    @Override
    public List<Article> listArticlesPlataCake() {
        String query = "SELECT * FROM article WHERE id_sous_categorie=1 AND actif=1;";
        List<Article> listofArticles = new ArrayList<>();
        try (
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                listofArticles.add(new Article(
                        resultSet.getInt("id_article"),
                        resultSet.getInt("id_sous_categorie"),
                        resultSet.getString("nom_article"),
                        resultSet.getString("reference"),
                        resultSet.getString("description"),
                        resultSet.getBinaryStream("image"),
                        resultSet.getString("dimensions"),
                        resultSet.getDouble("prix"),
                        resultSet.getInt("lot_vente"),
                        resultSet.getInt("actif")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listofArticles;
    }

    /**
     * Cette méthode permet de lister tous les articles de la sous-catégorie Plat à fromage qui sont actuellement actifs
     * @return la liste des articles de la sous-catégorie Plat à fromage actifs
     */
    @Override
    public List<Article> listArticlesPlataFromage() {
        String query = "SELECT * FROM article WHERE id_sous_categorie=2 AND actif=1;";
        List<Article> listofArticles = new ArrayList<>();
        try (
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                listofArticles.add(new Article(
                        resultSet.getInt("id_article"),
                        resultSet.getInt("id_sous_categorie"),
                        resultSet.getString("nom_article"),
                        resultSet.getString("reference"),
                        resultSet.getString("description"),
                        resultSet.getBinaryStream("image"),
                        resultSet.getString("dimensions"),
                        resultSet.getDouble("prix"),
                        resultSet.getInt("lot_vente"),
                        resultSet.getInt("actif")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listofArticles;
    }


    /**
     * Cette méthode permet de lister tous les articles de la sous-catégorie Coupelles apéritifs qui sont actuellement actifs
     * @return la liste des articles de la sous-catégorie Coupelle apéritifs actifs
     */
    @Override
    public List<Article> listArticlesCoupellesAperitif() {
        String query = "SELECT * FROM article WHERE id_sous_categorie=3 AND actif=1;";
        List<Article> listofArticles = new ArrayList<>();
        try (
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                listofArticles.add(new Article(
                        resultSet.getInt("id_article"),
                        resultSet.getInt("id_sous_categorie"),
                        resultSet.getString("nom_article"),
                        resultSet.getString("reference"),
                        resultSet.getString("description"),
                        resultSet.getBinaryStream("image"),
                        resultSet.getString("dimensions"),
                        resultSet.getDouble("prix"),
                        resultSet.getInt("lot_vente"),
                        resultSet.getInt("actif")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listofArticles;
    }
    /**
     * Cette méthode permet de lister tous les articles de la catégorie Porte couteaux qui sont actuellement actifs (à noter que la catégorie porte-couteaux ne contient qu'une seule sous-catégorie: porte-couteaux)
     * @return la liste des articles de la catégorie Porte couteaux actifs
     */
    @Override
    public List<Article> listArticlesPorteCouteaux() {
        String query = "SELECT * FROM article WHERE id_sous_categorie=4 AND actif=1;";
        List<Article> listofArticles = new ArrayList<>();
        try (
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                listofArticles.add(new Article(
                        resultSet.getInt("id_article"),
                        resultSet.getInt("id_sous_categorie"),
                        resultSet.getString("nom_article"),
                        resultSet.getString("reference"),
                        resultSet.getString("description"),
                        resultSet.getBinaryStream("image"),
                        resultSet.getString("dimensions"),
                        resultSet.getDouble("prix"),
                        resultSet.getInt("lot_vente"),
                        resultSet.getInt("actif")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listofArticles;
    }

    /**
     * Cette méthode permet de lister tous les articles de la catégorie Articles de décoration qui sont actuellement actifs
     * @return la liste des articles de la catégorie Articles de décoration actifs
     */

    @Override
    public List<Article> listArticlesDeco() {
        String query = "SELECT * FROM article WHERE (id_sous_categorie=5 or id_sous_categorie=6) AND actif=1;";
        List<Article> listofArticles = new ArrayList<>();
        try (
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                listofArticles.add(new Article(
                        resultSet.getInt("id_article"),
                        resultSet.getInt("id_sous_categorie"),
                        resultSet.getString("nom_article"),
                        resultSet.getString("reference"),
                        resultSet.getString("description"),
                        resultSet.getBinaryStream("image"),
                        resultSet.getString("dimensions"),
                        resultSet.getDouble("prix"),
                        resultSet.getInt("lot_vente"),
                        resultSet.getInt("actif")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listofArticles;
    }
    /**
     * Cette méthode permet de lister tous les articles de la sous-catégorie Dessous de verre qui sont actuellement actifs
     * @return la liste des articles de la sous-catégorie Dessous de verre actifs
     */
    @Override
    public List<Article> listArticlesDecoDessousVerre() {
        String query = "SELECT * FROM article WHERE id_sous_categorie=5 AND actif=1;";
        List<Article> listofArticles = new ArrayList<>();
        try (
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                listofArticles.add(new Article(
                        resultSet.getInt("id_article"),
                        resultSet.getInt("id_sous_categorie"),
                        resultSet.getString("nom_article"),
                        resultSet.getString("reference"),
                        resultSet.getString("description"),
                        resultSet.getBinaryStream("image"),
                        resultSet.getString("dimensions"),
                        resultSet.getDouble("prix"),
                        resultSet.getInt("lot_vente"),
                        resultSet.getInt("actif")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listofArticles;
    }
    /**
     * Cette méthode permet de lister tous les articles de la sous-catégorie Dessous de plat qui sont actuellement actifs
     * @return la liste des articles de la sous-catégorie Dessous de plat actifs
     */
    @Override
    public List<Article> listArticlesDecoDessousPlat() {
        String query = "SELECT * FROM article WHERE id_sous_categorie=6 AND actif=1;";
        List<Article> listofArticles = new ArrayList<>();
        try (
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                listofArticles.add(new Article(
                        resultSet.getInt("id_article"),
                        resultSet.getInt("id_sous_categorie"),
                        resultSet.getString("nom_article"),
                        resultSet.getString("reference"),
                        resultSet.getString("description"),
                        resultSet.getBinaryStream("image"),
                        resultSet.getString("dimensions"),
                        resultSet.getDouble("prix"),
                        resultSet.getInt("lot_vente"),
                        resultSet.getInt("actif")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listofArticles;
    }

    /**
     * Cette méthode permet de lister tous les articles de la catégorie Maison qui sont actuellement actifs(à noter que la catégorie Maison ne contient qu'une seule sous-catégorie: Maison)
     * @return la liste des articles de la catégorie Maison actifs
     */
    @Override
    public List<Article> listArticlesMaison() {
        String query = "SELECT * FROM article WHERE id_sous_categorie=7 AND actif=1;";
        List<Article> listofArticles = new ArrayList<>();
        try (
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                listofArticles.add(new Article(
                        resultSet.getInt("id_article"),
                        resultSet.getInt("id_sous_categorie"),
                        resultSet.getString("nom_article"),
                        resultSet.getString("reference"),
                        resultSet.getString("description"),
                        resultSet.getBinaryStream("image"),
                        resultSet.getString("dimensions"),
                        resultSet.getDouble("prix"),
                        resultSet.getInt("lot_vente"),
                        resultSet.getInt("actif")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listofArticles;
    }


    /**
     * Cette méthode permet de récuperer un article en particulier, en le distinguant des autres grâce à son nom
     * @param nom est le nom de l'article que l'on veut récuperer
     * @return l'article que l'on veut récuperer
     */
    @Override
    public Article getArticleByNom(String nom) {
        String query="SELECT * FROM article WHERE nom_article=? AND actif=1;";
        try(Connection connection = DataSourceProvider.getDataSource().getConnection();
            PreparedStatement statement= connection.prepareStatement(query)){
            statement.setString(1,nom);
            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    return new Article(
                            resultSet.getInt("id_article"),
                            resultSet.getInt("id_sous_categorie"),
                            resultSet.getString("nom_article"),
                            resultSet.getString("reference"),
                            resultSet.getString("description"),
                            resultSet.getBinaryStream("image"),
                            resultSet.getString("dimensions"),
                            resultSet.getDouble("prix"),
                            resultSet.getInt("lot_vente"),
                            resultSet.getInt("actif"));
                }
            }
        }catch (SQLException e){
                e.printStackTrace();
        }
        return null;
    }

    /**
     * Cette méthode permet de récuperer un article en particulier, en le distinguant des autres grâce à son identifiant
     * @param id est l'identifiant de l'article que l'on souhaite récuperer
     * @return l'article que l'on souhaite récuperer
     */
    @Override
    public Article getArticleById(Integer id) {
        String query="SELECT * FROM article WHERE id_article=?;";
        try(Connection connection = DataSourceProvider.getDataSource().getConnection();
            PreparedStatement statement= connection.prepareStatement(query)){
            statement.setInt(1,id);
            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    return new Article(
                            resultSet.getInt("id_article"),
                            resultSet.getInt("id_sous_categorie"),
                            resultSet.getString("nom_article"),
                            resultSet.getString("reference"),
                            resultSet.getString("description"),
                            resultSet.getBinaryStream("image"),
                            resultSet.getString("dimensions"),
                            resultSet.getDouble("prix"),
                            resultSet.getInt("lot_vente"),
                            resultSet.getInt("actif"));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Cette méthode permet d'ajouter un article à la base de donnée
     * @param article l'article que l'on souhaite ajouter
     * @return l'article que l'on a ajouté à la BDD
     */
    @Override
    public Article addArticle(Article article) {
        String query="INSERT INTO article(id_sous_categorie,nom_article,reference,description,image,dimensions,prix,lot_vente,actif) VALUES(?,?,?,?,?,?,?,?,?);";
        try(Connection connection = DataSourceProvider.getDataSource().getConnection();
        PreparedStatement statement=connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS)){
            statement.setInt(1,article.getId_sous_categorie());
            statement.setString(2,article.getNom_article());
            statement.setString(3,article.getReference());
            statement.setString(4,article.getDescription());
            statement.setBinaryStream(5,article.getImage());
            statement.setString(6,article.getDimension());
            statement.setDouble(7,article.getPrix());
            statement.setInt(8,article.getLot_vente());
            statement.setInt(9,article.getActif());
            statement.executeUpdate();
            try(ResultSet ids=statement.getGeneratedKeys()){
                if(ids.next()){
                    int generatedId = ids.getInt(1);
                    article.setId_article(generatedId);
                    return article;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Cette méthode permet de rendre inactif un article.
     * Il est parfois préferable de rendre inactif un article plutot que de le supprimer, pour éviter d'avoir à supprimer les couleurs et les devis qui lui sont liés
     * @param articleId l'identifiant de l'article que l'on veut rendre inactif
     */
    @Override
    public void rendreInactifArticle(Integer articleId) {
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "UPDATE article SET actif=0 where id_article=?")) {
                statement.setInt(1,articleId );
                statement.executeUpdate();
            }
        }catch (SQLException e) {
            // Manage Exception
            e.printStackTrace();
        }
    }

    /**
     * Cette méthode permet de supprimer un article.
     * Cela sert par exemple lorsqu'on veut modifier un article, on supprime complétement un article et on le reconstruit de zéro.
     * Il est plus simple de faire comme cela, plutot que de modifier un champ en particulier, vu qu'on ne sait pas quel attribut va être modifié
     * @param articleId l'identifiant de l'article à supprimer
     */
    @Override
    public void deleteArticle(Integer articleId) {
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "delete from article where id_article=?")) {
                statement.setInt(1,articleId );
                statement.executeUpdate();
            }
        }catch (SQLException e) {
            // Manage Exception
            e.printStackTrace();
        }
    }

}