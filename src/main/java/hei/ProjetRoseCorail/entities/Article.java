package hei.ProjetRoseCorail.entities;

/**
 * Un article est composé d'un identifiant unique,
 * un id de sous catégorie (7 sous catégories différentes),
 * un nom (normalement unique),
 * un numéro de réference (propre à la société),
 * une description: un petit texte donnant plus de détail sur l'article
 * une image qui lui est associé
 * une dimension, géneralement exprimé par Longueur*Largeur lorsque c'est un article rectangulaire, ou par son diamètre si il est rond
 * un prix, qui n'est pas un prix unitaire, mais le prix du lot de vente (ex: les portes couteaux sont vendu par 6, le prix correspond donc au prix de 6 porte-couteaux)
 * un lot de vente: l'article ne peut être vendu que dans un multiple de ce chiffre (ex: les portes couteaux sont vendu par 6)
 * l'attribut actif. Actif signifie qu'il sera affiché sur le site. Un article meme si il est inactif car plus vendu, peut toujours apparaitre dans les statistiques.
 */
public class Article {
    private Integer id_article;
    private Integer id_sous_categorie;
    private String nom_article;
    private String reference;
    private String description;
    private String image;
    private String dimension;
    private double prix;
    private Integer lot_vente;
    private Integer actif;

    public Article(Integer id_article, Integer id_sous_categorie, String nom_article, String reference, String description, String image, String dimension, double prix, Integer lot_vente,Integer actif) {
        this.id_article = id_article;
        this.id_sous_categorie = id_sous_categorie;
        this.nom_article = nom_article;
        this.reference = reference;
        this.description = description;
        this.image = image;
        this.dimension = dimension;
        this.prix = prix;
        this.lot_vente = lot_vente;
        this.actif=actif;
    }

    public Integer getId_article() {
        return id_article;
    }

    public void setId_article(Integer id_article) {
        this.id_article = id_article;
    }

    public Integer getId_sous_categorie() {
        return id_sous_categorie;
    }

    public void setId_sous_categorie(Integer id_sous_categorie) {
        this.id_sous_categorie = id_sous_categorie;
    }

    public String getNom_article() {
        return nom_article;
    }


    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Integer getLot_vente() {
        return lot_vente;
    }

    public void setLot_vente(Integer lot_vente) {
        this.lot_vente = lot_vente;
    }

    public void setNom_article(String nom_article) {
        this.nom_article = nom_article;
    }

    public Integer getActif() {
        return actif;
    }

    public void setActif(Integer actif) {
        this.actif = actif;
    }
}
