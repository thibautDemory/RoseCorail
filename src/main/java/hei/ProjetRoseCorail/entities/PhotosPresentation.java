package hei.ProjetRoseCorail.entities;

public class PhotosPresentation {
    private Integer id_photo;
    private String page;
    private String adresse;

    public PhotosPresentation(Integer id_photo, String page, String adresse) {
        this.id_photo = id_photo;
        this.page = page;
        this.adresse = adresse;
    }

    public Integer getId_photo() {
        return id_photo;
    }

    public void setId_photo(Integer id_photo) {
        this.id_photo = id_photo;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
