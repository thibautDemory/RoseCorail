package hei.ProjetRoseCorail.entities;

/**
 * Une actualité comprend un identifiant, un titre qui est affiché en haut du panel, un contenu qui est le texte parlant de l'actualité
 * et une image qui lui est associé (afficher à gauche du texte)
 */
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

    public Integer getIdActualite() {
        return id_actualite;
    }

    public void setIdActualite(Integer id_actualite) {
        this.id_actualite = id_actualite;
    }

    public String getTitreActualite() {
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

    public String getImageActualite() {
        return image_actualite;
    }

    public void setImageActualite(String image_actualite) {
        this.image_actualite = image_actualite;
    }
}
