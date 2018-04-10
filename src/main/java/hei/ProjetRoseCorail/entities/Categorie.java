package hei.ProjetRoseCorail.entities;

/**
 * Une catégorie est définie par:
 * un identifiant unique pour cette catégorie
 * le nom de la catégorie.
 */
public class Categorie {
    private Integer id_categorie;
    private String nom_categorie;

    public Categorie(Integer id_categorie, String nom_categorie) {
        this.id_categorie = id_categorie;
        this.nom_categorie = nom_categorie;
    }

    public Integer getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(Integer id_categorie) {
        this.id_categorie = id_categorie;
    }

    public String getNom_categorie() {
        return nom_categorie;
    }

    public void setNom_categorie(String nom_categorie) {
        this.nom_categorie = nom_categorie;
    }
}
