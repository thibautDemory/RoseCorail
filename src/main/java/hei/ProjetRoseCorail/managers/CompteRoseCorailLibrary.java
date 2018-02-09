package hei.ProjetRoseCorail.managers;

import hei.ProjetRoseCorail.dao.CompteClientDao;
import hei.ProjetRoseCorail.dao.CompteRoseCorailDao;
import hei.ProjetRoseCorail.dao.impl.CompteClientDaoImpl;
import hei.ProjetRoseCorail.dao.impl.CompteRoseCorailDaoImpl;
import hei.ProjetRoseCorail.entities.CompteClient;
import hei.ProjetRoseCorail.entities.CompteRoseCorail;

import java.util.List;




import java.util.List;

public class CompteRoseCorailLibrary {
    private static class CompteRoseCorailLibraryHolder {
        private final static CompteRoseCorailLibrary instance = new CompteRoseCorailLibrary();
    }

    public static CompteRoseCorailLibrary getInstance() {
        return CompteRoseCorailLibrary.CompteRoseCorailLibraryHolder.instance;
    }

    private CompteRoseCorailDao compteRoseCorailDao = new CompteRoseCorailDaoImpl();



    public CompteRoseCorail getCompteRoseCorailById(Integer id){ return compteRoseCorailDao.getCompteRoseCorailById(id);}
    public CompteRoseCorail getCompteRoseCorailByMail(String mail){ return compteRoseCorailDao.getCompteRoseCorailByMail(mail);}



}
