package hei.ProjetRoseCorail.entities;

public class SousCategorie {
    private Integer id_sous_categorie;
    private Integer id_categorie;
    private String nom_sous_categorie;

    public SousCategorie(Integer id_sous_categorie, Integer id_categorie, String nom_sous_categorie) {
        this.id_sous_categorie = id_sous_categorie;
        this.id_categorie = id_categorie;
        this.nom_sous_categorie = nom_sous_categorie;
    }

    public Integer getId_sous_categorie() {
        return id_sous_categorie;
    }

    public void setId_sous_categorie(Integer id_sous_categorie) {
        this.id_sous_categorie = id_sous_categorie;
    }

    public Integer getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(Integer id_categorie) {
        this.id_categorie = id_categorie;
    }

    public String getNom_sous_categorie() {
        return nom_sous_categorie;
    }

    public void setNom_sous_categorie(String nom_sous_categorie) {
        this.nom_sous_categorie = nom_sous_categorie;
    }
}
