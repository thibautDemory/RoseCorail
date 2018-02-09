package hei.ProjetRoseCorail.dao;

import hei.ProjetRoseCorail.entities.Actualite;
import hei.ProjetRoseCorail.entities.CompteClient;

import java.util.List;

public interface CompteClientDao {
    public List<CompteClient> listComptesClient();

    public CompteClient getCompteClientById(Integer id_compte_client);

    public CompteClient getCompteClientByMail(String mail);

    public CompteClient addCompteClient(CompteClient compteClient);

    public void deleteCompteClient(Integer idCompteClient);
}