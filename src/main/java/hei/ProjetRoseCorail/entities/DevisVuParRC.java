package hei.ProjetRoseCorail.entities;

import java.time.LocalDate;
import java.util.Date;

public class DevisVuParRC {
    private Integer idDevis;
    private String boutique;
    private LocalDate date;
    private Integer nbreArticles;
    private Double prixTotal;
    private String etat;

    public DevisVuParRC(Integer idDevis, String boutique, LocalDate date, Integer nbreArticles, Double prixTotal, String etat) {
        this.idDevis = idDevis;
        this.boutique = boutique;
        this.date = date;
        this.nbreArticles = nbreArticles;
        this.prixTotal = prixTotal;
        this.etat = etat;
    }

    public Integer getIdDevis() {
        return idDevis;
    }

    public void setIdDevis(Integer idDevis) {
        this.idDevis = idDevis;
    }

    public String getBoutique() {
        return boutique;
    }

    public void setBoutique(String boutique) {
        this.boutique = boutique;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getNbreArticles() {
        return nbreArticles;
    }

    public void setNbreArticles(Integer nbreArticles) {
        this.nbreArticles = nbreArticles;
    }

    public Double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(Double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
}
