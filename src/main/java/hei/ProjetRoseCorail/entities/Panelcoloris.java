package hei.ProjetRoseCorail.entities;

public class Panelcoloris {
    private Integer idPanelColoris;
    private String legende;
    private String image;
    private String saison;

    public Panelcoloris(Integer idPanelColoris, String legende, String image, String saison) {
        this.idPanelColoris = idPanelColoris;
        this.legende = legende;
        this.image = image;
        this.saison=saison;
    }

    public Integer getIdPanelColoris() {
        return idPanelColoris;
    }

    public void setIdPanelColoris(Integer idPanelColoris) {
        this.idPanelColoris = idPanelColoris;
    }

    public String getLegende() {
        return legende;
    }

    public void setLegende(String legende) {
        this.legende = legende;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSaison() {
        return saison;
    }

    public void setSaison(String saison) {
        this.saison = saison;
    }
}
