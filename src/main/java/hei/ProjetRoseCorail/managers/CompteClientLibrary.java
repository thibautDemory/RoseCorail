package hei.ProjetRoseCorail.managers;

import hei.ProjetRoseCorail.dao.CompteClientDao;
import hei.ProjetRoseCorail.dao.impl.CompteClientDaoImpl;
import hei.ProjetRoseCorail.entities.CompteClient;

import java.util.List;

public class CompteClientLibrary {
    private static class CompteClientLibraryHolder {
        private final static CompteClientLibrary instance = new CompteClientLibrary();
    }

    public static CompteClientLibrary getInstance() {
        return CompteClientLibrary.CompteClientLibraryHolder.instance;
    }

    private CompteClientDao compteClientDao = new CompteClientDaoImpl();

    public CompteClient addCompteClient(CompteClient compteClient) {
        if (compteClient == null) {
            throw new IllegalArgumentException("Le compteClient ne peut pas être nul.");
        }
        if (compteClient.getAdresse() == null || "".equals(compteClient.getAdresse())) {
            throw new IllegalArgumentException("L'adresse du compteClient ne peut pas être nul.");
        }
        if (compteClient.getCode_postal() == null || "".equals(compteClient.getCode_postal())) {
            throw new IllegalArgumentException("Le CP du compteClient ne pas être nul.");
        }
        if (compteClient.getDescription_activite() == null || "".equals(compteClient.getDescription_activite())) {
            throw new IllegalArgumentException("La description du compteClient ne pas être nul.");
        }
        if (compteClient.getEmail() == null || "".equals(compteClient.getEmail())) {
            throw new IllegalArgumentException("L'email du compteClient ne peut pas être nul.");
        }
        if (compteClient.getNom_boutique() == null || "".equals(compteClient.getNom_boutique())) {
            throw new IllegalArgumentException("Le nom de la boutique du compteClient ne pas être nul.");
        }
        if (compteClient.getNom_gerant() == null || "".equals(compteClient.getNom_gerant())) {
            throw new IllegalArgumentException("Le nom du gérant de la boutique ne pas être nul.");
        }
        if (compteClient.getNum_tva() == null || "".equals(compteClient.getNum_tva())) {
            throw new IllegalArgumentException("Le numero de TVA du compteClient ne peut pas être nul.");
        }
        if (compteClient.getNumero_tel() == null || "".equals(compteClient.getNumero_tel())) {
            throw new IllegalArgumentException("Le numero de tel du compteClient ne pas être nul.");
        }
        if (compteClient.getPrenom_gerant() == null || "".equals(compteClient.getPrenom_gerant())) {
            throw new IllegalArgumentException("Le prenom du gérant de la boutique ne pas être nul.");
        }
        if (compteClient.getVille() == null || "".equals(compteClient.getVille())) {
            throw new IllegalArgumentException("La ville du compteClient ne pas être nul.");
        }
        return compteClientDao.addCompteClient(compteClient);
    }

    public CompteClient updateCompteClientWithoutPassword(CompteClient compteClient) {
        if (compteClient == null) {
            throw new IllegalArgumentException("Le compteClient ne peut pas être nul.");
        }
        if (compteClient.getAdresse() == null || "".equals(compteClient.getAdresse())) {
            throw new IllegalArgumentException("L'adresse du compteClient ne peut pas être nul.");
        }
        if (compteClient.getCode_postal() == null || "".equals(compteClient.getCode_postal())) {
            throw new IllegalArgumentException("Le CP du compteClient ne pas être nul.");
        }
        if (compteClient.getDescription_activite() == null || "".equals(compteClient.getDescription_activite())) {
            throw new IllegalArgumentException("La description du compteClient ne pas être nul.");
        }
        if (compteClient.getEmail() == null || "".equals(compteClient.getEmail())) {
            throw new IllegalArgumentException("L'email du compteClient ne peut pas être nul.");
        }
        if (compteClient.getNom_boutique() == null || "".equals(compteClient.getNom_boutique())) {
            throw new IllegalArgumentException("Le nom de la boutique du compteClient ne pas être nul.");
        }
        if (compteClient.getNom_gerant() == null || "".equals(compteClient.getNom_gerant())) {
            throw new IllegalArgumentException("Le nom du gérant de la boutique ne pas être nul.");
        }
        if (compteClient.getNum_tva() == null || "".equals(compteClient.getNum_tva())) {
            throw new IllegalArgumentException("Le numero de TVA du compteClient ne peut pas être nul.");
        }
        if (compteClient.getNumero_tel() == null || "".equals(compteClient.getNumero_tel())) {
            throw new IllegalArgumentException("Le numero de tel du compteClient ne pas être nul.");
        }
        if (compteClient.getPrenom_gerant() == null || "".equals(compteClient.getPrenom_gerant())) {
            throw new IllegalArgumentException("Le prenom du gérant de la boutique ne pas être nul.");
        }
        if (compteClient.getVille() == null || "".equals(compteClient.getVille())) {
            throw new IllegalArgumentException("La ville du compteClient ne pas être nul.");
        }
        return compteClientDao.updateCompteClientWithoutPassword(compteClient);
    }

    public CompteClient getCompteClientById(Integer id){ return compteClientDao.getCompteClientById(id);}
    public CompteClient getCompteClientByMail(String mail){ return compteClientDao.getCompteClientByMail(mail);}
    public List<CompteClient> listComptesClients() {return compteClientDao.listComptesClient();}

    public void updatePassword(int id, String password){
        if (password == null || password.equals("")) {
            throw new IllegalArgumentException("Le mot de passe ne peut pas être nul.");
        }else {
            compteClientDao.updatePassword(id, password);
        }
    }


}
