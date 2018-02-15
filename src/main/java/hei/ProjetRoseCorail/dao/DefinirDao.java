package hei.ProjetRoseCorail.dao;

import hei.ProjetRoseCorail.entities.Definir;

import java.util.List;

public interface DefinirDao {
    List<Definir> listDefinirPourUnDevis(Integer id_devis);
    public Definir addDefinir(Definir definir);
    public Definir modifierDefinir(Definir definir);

}
