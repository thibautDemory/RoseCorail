package hei.ProjetRoseCorail.entities;

public class CompteRoseCorail {
    private Integer id_compte_rose_corail;
    private String email;
    private String mdp;
    private String numero_tel;

    public CompteRoseCorail(Integer id_compte_rose_corail, String email, String mdp, String numero_tel) {
        this.id_compte_rose_corail = id_compte_rose_corail;
        this.email = email;
        this.mdp = mdp;
        this.numero_tel = numero_tel;
    }

    public Integer getId_compte_rose_corail() {
        return id_compte_rose_corail;
    }

    public void setId_compte_rose_corail(Integer id_compte_rose_corail) {
        this.id_compte_rose_corail = id_compte_rose_corail;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getNumero_tel() {
        return numero_tel;
    }

    public void setNumero_tel(String numero_tel) {
        this.numero_tel = numero_tel;
    }
}
