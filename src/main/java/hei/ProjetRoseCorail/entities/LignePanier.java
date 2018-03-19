package hei.ProjetRoseCorail.entities;

public class LignePanier {
    private String photoArticle;
    private String numeroArticle;
    private String nomArticle;
    private String dimensionArticle;
    private String photoCouleur;
    private Integer quantite;
    private Double prixunitaire;
    private Integer idLigneDevis;
    private Integer vendupar;


    public LignePanier(String photoArticle, String numeroArticle, String nomArticle, String dimensionArticle, String photoCouleur, Integer quantite,Integer vendupar,Double prixunitaire, Integer idLigneDevis) {
        this.photoArticle = photoArticle;
        this.numeroArticle = numeroArticle;
        this.nomArticle = nomArticle;
        this.dimensionArticle = dimensionArticle;
        this.photoCouleur = photoCouleur;
        this.quantite = quantite;
        this.vendupar=vendupar;
        this.prixunitaire=prixunitaire;
        this.idLigneDevis=idLigneDevis;
    }

    public String getPhotoArticle() {
        return photoArticle;
    }

    public void setPhotoArticle(String photoArticle) {
        this.photoArticle = photoArticle;
    }

    public String getNumeroArticle() {
        return numeroArticle;
    }

    public void setNumeroArticle(String numeroArticle) {
        this.numeroArticle = numeroArticle;
    }

    public String getNomArticle() {
        return nomArticle;
    }

    public void setNomArticle(String nomArticle) {
        this.nomArticle = nomArticle;
    }

    public String getDimensionArticle() {
        return dimensionArticle;
    }

    public void setDimensionArticle(String dimensionArticle) {
        this.dimensionArticle = dimensionArticle;
    }

    public String getPhotoCouleur() {
        return photoCouleur;
    }

    public void setPhotoCouleur(String photoCouleur) {
        this.photoCouleur = photoCouleur;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }
    public Integer getIdLigneDevis() {
        return idLigneDevis;
    }

    public void setIdLigneDevis(Integer idLigneDevis) {
        this.idLigneDevis = idLigneDevis;
    }

    public Double getPrixunitaire() {
        return prixunitaire;
    }

    public void setPrixunitaire(Double prixunitaire) {
        this.prixunitaire = prixunitaire;
    }

    public Integer getVendupar() {
        return vendupar;
    }

    public void setVendupar(Integer vendupar) {
        this.vendupar = vendupar;
    }
}
