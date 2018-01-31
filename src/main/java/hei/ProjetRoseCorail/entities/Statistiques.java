package hei.ProjetRoseCorail.entities;

public class Statistiques {
    private Integer id_stat;
    private String nom_boutique_num1;
    private String nom_boutique_num2;
    private String nom_boutique_num3;
    private String ref_article_vu_num1;
    private String ref_article_vu_num2;
    private String ref_article_vu_num3;
    private String ref_article_commandes_num1;
    private String ref_article_commandes_num2;
    private String ref_article_commandes_num3;
    private String nom_couleur1;
    private String nom_couleur2;
    private String nom_couleur3;
    private String mois;
    private String annee;

    public Statistiques(Integer id_stat, String nom_boutique_num1, String nom_boutique_num2, String nom_boutique_num3, String ref_article_vu_num1, String ref_article_vu_num2, String ref_article_vu_num3, String ref_article_commandes_num1, String ref_article_commandes_num2, String ref_article_commandes_num3, String nom_couleur1, String nom_couleur2, String nom_couleur3, String mois, String annee) {
        this.id_stat = id_stat;
        this.nom_boutique_num1 = nom_boutique_num1;
        this.nom_boutique_num2 = nom_boutique_num2;
        this.nom_boutique_num3 = nom_boutique_num3;
        this.ref_article_vu_num1 = ref_article_vu_num1;
        this.ref_article_vu_num2 = ref_article_vu_num2;
        this.ref_article_vu_num3 = ref_article_vu_num3;
        this.ref_article_commandes_num1 = ref_article_commandes_num1;
        this.ref_article_commandes_num2 = ref_article_commandes_num2;
        this.ref_article_commandes_num3 = ref_article_commandes_num3;
        this.nom_couleur1 = nom_couleur1;
        this.nom_couleur2 = nom_couleur2;
        this.nom_couleur3 = nom_couleur3;
        this.mois = mois;
        this.annee = annee;
    }

    public Integer getId_stat() {
        return id_stat;
    }

    public void setId_stat(Integer id_stat) {
        this.id_stat = id_stat;
    }

    public String getNom_boutique_num1() {
        return nom_boutique_num1;
    }

    public void setNom_boutique_num1(String nom_boutique_num1) {
        this.nom_boutique_num1 = nom_boutique_num1;
    }

    public String getNom_boutique_num2() {
        return nom_boutique_num2;
    }

    public void setNom_boutique_num2(String nom_boutique_num2) {
        this.nom_boutique_num2 = nom_boutique_num2;
    }

    public String getNom_boutique_num3() {
        return nom_boutique_num3;
    }

    public void setNom_boutique_num3(String nom_boutique_num3) {
        this.nom_boutique_num3 = nom_boutique_num3;
    }

    public String getRef_article_vu_num1() {
        return ref_article_vu_num1;
    }

    public void setRef_article_vu_num1(String ref_article_vu_num1) {
        this.ref_article_vu_num1 = ref_article_vu_num1;
    }

    public String getRef_article_vu_num2() {
        return ref_article_vu_num2;
    }

    public void setRef_article_vu_num2(String ref_article_vu_num2) {
        this.ref_article_vu_num2 = ref_article_vu_num2;
    }

    public String getRef_article_vu_num3() {
        return ref_article_vu_num3;
    }

    public void setRef_article_vu_num3(String ref_article_vu_num3) {
        this.ref_article_vu_num3 = ref_article_vu_num3;
    }

    public String getRef_article_commandes_num1() {
        return ref_article_commandes_num1;
    }

    public void setRef_article_commandes_num1(String ref_article_commandes_num1) {
        this.ref_article_commandes_num1 = ref_article_commandes_num1;
    }

    public String getRef_article_commandes_num2() {
        return ref_article_commandes_num2;
    }

    public void setRef_article_commandes_num2(String ref_article_commandes_num2) {
        this.ref_article_commandes_num2 = ref_article_commandes_num2;
    }

    public String getRef_article_commandes_num3() {
        return ref_article_commandes_num3;
    }

    public void setRef_article_commandes_num3(String ref_article_commandes_num3) {
        this.ref_article_commandes_num3 = ref_article_commandes_num3;
    }

    public String getNom_couleur1() {
        return nom_couleur1;
    }

    public void setNom_couleur1(String nom_couleur1) {
        this.nom_couleur1 = nom_couleur1;
    }

    public String getNom_couleur2() {
        return nom_couleur2;
    }

    public void setNom_couleur2(String nom_couleur2) {
        this.nom_couleur2 = nom_couleur2;
    }

    public String getNom_couleur3() {
        return nom_couleur3;
    }

    public void setNom_couleur3(String nom_couleur3) {
        this.nom_couleur3 = nom_couleur3;
    }

    public String getMois() {
        return mois;
    }

    public void setMois(String mois) {
        this.mois = mois;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }
}
