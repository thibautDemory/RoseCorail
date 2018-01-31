package hei.ProjetRoseCorail.entities;

public class Definir {
    private Integer id_devis;
    private Integer id_stat;

    public Definir(Integer id_devis, Integer id_stat) {
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
}
