package hei.ProjetRoseCorail.entities;

import java.util.Date;

public class Devis {
    private Integer id_devis;
    private Integer id_compte_client;
    private Date date;
    private String etat;
    private String etatPanier;

    public Devis(Integer id_devis, Integer id_compte_client, Date date, String etat, String etatPanier) {
        this.id_devis = id_devis;
        this.id_compte_client = id_compte_client;
        this.date = date;
        this.etat = etat;
        this.etatPanier = etatPanier;
    }

    public Integer getId_devis() {
        return id_devis;
    }

    public void setId_devis(Integer id_devis) {
        this.id_devis = id_devis;
    }

    public Integer getId_compte_client() {
        return id_compte_client;
    }

    public void setId_compte_client(Integer id_compte_client) {
        this.id_compte_client = id_compte_client;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getEtatPanier() {
        return etatPanier;
    }

    public void setEtatPanier(String etatPanier) {
        this.etatPanier = etatPanier;
    }
}
