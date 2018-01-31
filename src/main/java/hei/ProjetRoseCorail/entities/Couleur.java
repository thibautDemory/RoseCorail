package hei.ProjetRoseCorail.entities;

public class Couleur {
    private Integer id_couleur;
    private String nom_couleur;
    private Integer numero_couleur;
    private String image_couleur;
    private String saison;

    public Couleur(Integer id_couleur, String nom_couleur, Integer numero_couleur, String image_couleur, String saison) {
        this.id_couleur = id_couleur;
        this.nom_couleur = nom_couleur;
        this.numero_couleur = numero_couleur;
        this.image_couleur = image_couleur;
        this.saison = saison;
    }

    public Integer getId_couleur() {
        return id_couleur;
    }

    public void setId_couleur(Integer id_couleur) {
        this.id_couleur = id_couleur;
    }

    public String getNom_couleur() {
        return nom_couleur;
    }

    public void setNom_couleur(String nom_couleur) {
        this.nom_couleur = nom_couleur;
    }

    public Integer getNumero_couleur() {
        return numero_couleur;
    }

    public void setNumero_couleur(Integer numero_couleur) {
        this.numero_couleur = numero_couleur;
    }

    public String getImage_couleur() {
        return image_couleur;
    }

    public void setImage_couleur(String image_couleur) {
        this.image_couleur = image_couleur;
    }

    public String getSaison() {
        return saison;
    }

    public void setSaison(String saison) {
        this.saison = saison;
    }
}
