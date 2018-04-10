package hei.ProjetRoseCorail.entities;

/**
 * La classe définir sert à faire le lien entre un devis et une statistique. Elle contient:
 * Un identifiant unique
 * l'identifiant du devis en question
 * l'identifiant de la statistique en question
 */
public class Definir {
    private Integer id_definir;
    private Integer id_devis;
    private Integer id_stat;

    public Definir(Integer id_definir, Integer id_devis, Integer id_stat) {
        this.id_definir=id_definir;
        this.id_devis = id_devis;
        this.id_stat = id_stat;
    }

    public Integer getId_devis() {
        return id_devis;
    }

    public void setId_devis(Integer id_devis) {
        this.id_devis = id_devis;
    }

    public Integer getId_stat() {
        return id_stat;
    }

    public void setId_stat(Integer id_stat) {
        this.id_stat = id_stat;
    }

    public Integer getId_definir() {
        return id_definir;
    }

    public void setId_definir(Integer id_definir) {
        this.id_definir = id_definir;
    }

}
