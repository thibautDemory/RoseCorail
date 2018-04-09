package hei.ProjetRoseCorail.dao;

import hei.ProjetRoseCorail.entities.PhotosPresentation;

import java.util.List;

public interface PhotoPresentationDao {
    public List<PhotosPresentation> listphotospresentation();
    public List<PhotosPresentation> listphotosparpage(String page);
    public PhotosPresentation addPhotoPresentation(PhotosPresentation photosPresentation);
    public PhotosPresentation getPhotoPresentationById(Integer idPhoto);
    public void deletePhotoPresentation(Integer idPhoto);
    public void modifierPhoto (String adresse,Integer idPhoto);
}
