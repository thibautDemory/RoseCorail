package hei.ProjetRoseCorail.dao;

import hei.ProjetRoseCorail.entities.Actualite;

import java.util.List;

public interface ActualiteDao {
    public List<Actualite> listActualites();

    public Actualite addActualite(Actualite actualite);

    public void deleteActualite(Integer idActualite);
}
