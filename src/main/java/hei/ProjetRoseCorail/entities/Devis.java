package hei.ProjetRoseCorail.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Un devis est composé de:
 * Un identifiant unique
 * l'id du compte client, pour savoir quel client a émis le devis
 * La date de création du devis
 * L'état du devis, pour savoir si il est encore à l'état de panier (non envoyé à Rose Corail), ou bien si il est 'en préparation', 'expédié' (cela en fonction de l'avancement du colis)
 * un boolean etatPanier, qui permet de savoir si il est ou non en état panier, cela permet qu'il soit visible ou non par Rose Corail
 */
public class Devis {
    private Integer id_devis;
    private Integer id_compte_client;
    private LocalDate date_creation;

    private String etat;
    private Boolean etatPanier;

    public Devis(Integer id_devis, Integer id_compte_client, LocalDate date_creation, String etat, Boolean etatPanier) {
        this.id_devis = id_devis;
        this.id_compte_client = id_compte_client;
        this.date_creation = date_creation;
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

    public LocalDate getDate() {
        return date_creation;
    }

    public void setDate(LocalDate date) {
        this.date_creation = date_creation;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Boolean getEtatPanier() {
        return etatPanier;
    }

    public void setEtatPanier(Boolean etatPanier) {
        this.etatPanier = etatPanier;
    }
}
