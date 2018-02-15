package hei.ProjetRoseCorail.entities;

public class Posseder {


    private Integer id_posseder;
    private Integer id_couleur;
    private Integer id_article;

    public Posseder(Integer id_posseder, Integer id_couleur, Integer id_article) {
        this.id_posseder= id_posseder;
        this.id_couleur = id_couleur;
        this.id_article = id_article;
    }

    public Integer getId_couleur() {
        return id_couleur;
    }

    public void setId_couleur(Integer id_couleur) {
        this.id_couleur = id_couleur;
    }

    public Integer getId_article() {
        return id_article;
    }

    public void setId_article(Integer id_article) {
        this.id_article = id_article;
    }

    public Integer getId_posseder() {
        return id_posseder;
    }

    public void setId_posseder(Integer id_posseder) {
        this.id_posseder = id_posseder;
    }
}
