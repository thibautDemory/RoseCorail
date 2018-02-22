package hei.ProjetRoseCorail.dao;

import hei.ProjetRoseCorail.entities.Actualite;
import hei.ProjetRoseCorail.entities.Couleur;

import java.util.List;

public interface CouleurDao {
    public List<Couleur> listCouleurs();

    public Couleur getCouleurByID(Integer id);
    public Couleur getCouleurByName(String nom);

    public Couleur addCouleur(Couleur couleur);

    public void deleteCouleur(Integer idCouleur);
}

