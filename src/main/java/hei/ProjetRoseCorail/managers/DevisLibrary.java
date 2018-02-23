package hei.ProjetRoseCorail.managers;

import hei.ProjetRoseCorail.dao.DevisDao;
import hei.ProjetRoseCorail.dao.impl.DevisDaoImpl;
import hei.ProjetRoseCorail.entities.Devis;

public class DevisLibrary {
    private static class DevisLibraryHolder {
        private final static DevisLibrary instance= new DevisLibrary();
    }
    public static DevisLibrary getInstance() {return DevisLibrary.DevisLibraryHolder.instance;}

    private DevisDao devisDao=new DevisDaoImpl();

    public Devis creerundevis(Devis devis){return devisDao.creerUnDevis(devis); }
    public Devis getPanierClient(Integer idClient){return devisDao.getPanierClient(idClient);}
    public Devis getDevisByiD(Integer iddevis){return devisDao.getDevisById(iddevis);}
    public void passerdePanierAPreparation(Integer iddevis){devisDao.dePanieraEnPreparation(iddevis);}
}
