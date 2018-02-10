package hei.ProjetRoseCorail.dao;

import hei.ProjetRoseCorail.entities.CompteRoseCorail;

public interface CompteRoseCorailDao {
    public CompteRoseCorail getCompteRoseCorailByMail(String mail);
    public CompteRoseCorail getCompteRoseCorailById(Integer id);
    public void updatePassword(Integer id, String pwd);
}
