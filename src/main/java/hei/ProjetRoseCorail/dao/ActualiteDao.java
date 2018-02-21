package hei.ProjetRoseCorail.dao;

import hei.ProjetRoseCorail.entities.Actualite;
import hei.ProjetRoseCorail.entities.Couleur;

import java.util.List;

public interface ActualiteDao {
    public List<Actualite> listActualites();

    public Actualite getActualiteByID(Integer id);

    public Actualite addActualite(Actualite actualite);

    public void deleteActualite(Integer idActualite);
}
