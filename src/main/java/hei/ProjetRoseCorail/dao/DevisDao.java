package hei.ProjetRoseCorail.dao;

import hei.ProjetRoseCorail.entities.Devis;

import java.util.List;

public interface DevisDao {
    public Devis creerUnDevis(Devis devis);
    public List<Devis> listDevisByCompteClient(Integer idCompteClient);
    public List<Devis> listDevis();
    public Devis getDevisByArticle(Integer idArticle);
    public Devis getPanierClient(Integer idcompteclient);
    public Devis getDevisById(Integer idDevis);
    public void dePanieraEnPreparation(Integer idDevis);


}
