package hei.ProjetRoseCorail.dao;

import hei.ProjetRoseCorail.entities.LigneDevis;

import java.util.List;

public interface LigneDevisDao {
    public LigneDevis addLigneDevis(LigneDevis ligneDevis);
    public List<LigneDevis> listAllLigneDevis();
    public List<LigneDevis> listLignesDevisPourUneCouleur(Integer idCouleur);
    public List<LigneDevis> listLignesDevisPourUnArticle(Integer idArticle);
    public List<LigneDevis> listLignesDevisPourUnDevis(Integer idDevis);
    public void modifierQuantiteLigneDevis(Integer idLigneDevis,Integer quantite);
    public void deleteLigneDevis(Integer idLigneDevis);
    public void deleteLigneDevisForOneCouleur(Integer idCouleur);
}
