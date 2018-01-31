package hei.ProjetRoseCorail.entities;

public class LigneDevis {
    private Integer id_ligne_devis;
    private Integer id_couleur;
    private Integer id_devis;
    private Integer id_article;

    public LigneDevis(Integer id_ligne_devis, Integer id_couleur, Integer id_devis, Integer id_article) {
        this.id_ligne_devis = id_ligne_devis;
        this.id_couleur = id_couleur;
        this.id_devis = id_devis;
        this.id_article = id_article;
    }

    public Integer getId_ligne_devis() {
        return id_ligne_devis;
    }

    public void setId_ligne_devis(Integer id_ligne_devis) {
        this.id_ligne_devis = id_ligne_devis;
    }

    public Integer getId_couleur() {
        return id_couleur;
    }

    public void setId_couleur(Integer id_couleur) {
        this.id_couleur = id_couleur;
    }

    public Integer getId_devis() {
        return id_devis;
    }

    public void setId_devis(Integer id_devis) {
        this.id_devis = id_devis;
    }

    public Integer getId_article() {
        return id_article;
    }

    public void setId_article(Integer id_article) {
        this.id_article = id_article;
    }
}
