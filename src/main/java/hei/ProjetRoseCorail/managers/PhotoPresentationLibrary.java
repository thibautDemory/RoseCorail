package hei.ProjetRoseCorail.managers;

import hei.ProjetRoseCorail.dao.PhotoPresentationDao;
import hei.ProjetRoseCorail.dao.impl.PhotoPresentationDaoImpl;
import hei.ProjetRoseCorail.entities.PhotosPresentation;

import java.util.List;

public class PhotoPresentationLibrary {
    private static class PhotoPresentationLibraryHolder {
        private final static PhotoPresentationLibrary instance = new PhotoPresentationLibrary();
    }
    public static PhotoPresentationLibrary getInstance(){return PhotoPresentationLibraryHolder.instance;}
    private PhotoPresentationDao photoPresentationDao=new PhotoPresentationDaoImpl();

    public List<PhotosPresentation> listphotopresentation(){return photoPresentationDao.listphotospresentation();}
    public List<PhotosPresentation> listphotoAccueil(){return photoPresentationDao.listphotosparpage("accueil");}
    public List<PhotosPresentation> listphotoCollection(){return photoPresentationDao.listphotosparpage("collection");}
    public PhotosPresentation getPhotoPresentationById(Integer idphoto){return photoPresentationDao.getPhotoPresentationById(idphoto);}
    public PhotosPresentation addPhotoPresentation(PhotosPresentation photosPresentation){
        if (photosPresentation==null){
            throw new IllegalArgumentException("La photo presentation ne peut pas être nulle.");
        }
        return photoPresentationDao.addPhotoPresentation(photosPresentation);
    }
    public void deletePhotoPresentation(Integer idPhoto){photoPresentationDao.deletePhotoPresentation(idPhoto);}
    public void modifierphoto(String adresse, Integer idPhoto){photoPresentationDao.modifierPhoto(adresse,idPhoto);}

}
