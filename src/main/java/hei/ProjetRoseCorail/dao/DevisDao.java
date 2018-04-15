package hei.ProjetRoseCorail.dao;

import hei.ProjetRoseCorail.entities.Devis;

import java.util.List;

public interface DevisDao {
    public Devis creerUnDevis(Devis devis);
    public List<Devis> listDevisByCompteClient(Integer idCompteClient);
    public List<Devis> listDevis();
    public Devis getPanierClient(Integer idcompteclient);
    public Devis getDevisById(Integer idDevis);
    public void changerDateDevis(Integer idDevis);
    public void dePanieraEnPreparation(Integer idDevis);
    public void annulerDevis(Integer idDevis);
    public void changerEtatDevis(Integer idDevis, String etat);


}
