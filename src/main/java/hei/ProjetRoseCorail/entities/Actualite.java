package hei.ProjetRoseCorail.entities;

public class Actualite {
    private Integer id_actualite;
    private String titre;
    private String contenu;
    private String image_actualite;

    public Actualite(Integer id_actualite, String titre, String contenu, String image_actualite) {
        this.id_actualite = id_actualite;
        this.titre = titre;
        this.contenu = contenu;
        this.image_actualite = image_actualite;
    }

    public Integer getId_actualite() {
        return id_actualite;
    }

    public void setId_actualite(Integer id_actualite) {
        this.id_actualite = id_actualite;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getImage_actualite() {
        return image_actualite;
    }

    public void setImage_actualite(String image_actualite) {
        this.image_actualite = image_actualite;
    }
}
