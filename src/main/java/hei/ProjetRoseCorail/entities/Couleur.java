package hei.ProjetRoseCorail.entities;

/**
 * Une couleur est définie par:
 * Un identifiant unique
 * Un nom
 * Un numéro associé (c'est un code propre à la société)
 * Une image représentative de la couleur
 * Une saison, les couleurs des articles vont changer en fonction des saisons
 * L'attribut actif qui permet de savoir si cette couleur est encore utilisé pour les articles en vente. Les couleurs non actives peuvent toujours apparaitre dans les statistiques.
 */
public class Couleur {
    private Integer id_couleur;
    private String nom_couleur;
    private String numero_couleur;
    private String image_couleur;
    private String saison;
    private Integer actif;

    public Couleur(Integer id_couleur, String nom_couleur, String numero_couleur, String image_couleur, String saison,Integer actif) {
        this.id_couleur = id_couleur;
        this.nom_couleur = nom_couleur;
        this.numero_couleur = numero_couleur;
        this.image_couleur = image_couleur;
        this.saison = saison;
        this.actif=actif;
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

    public String getNumero_couleur() {
        return numero_couleur;
    }

    public void setNumero_couleur(String numero_couleur) {
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

    public Integer getActif() {
        return actif;
    }

    public void setActif(Integer actif) {
        this.actif = actif;
    }
}
