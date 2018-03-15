package hei.ProjetRoseCorail.dao;

import hei.ProjetRoseCorail.entities.Panelcoloris;

import java.util.List;

public interface PanelColorisDao {
    public List<Panelcoloris> listPanelColoris();
    public List<Panelcoloris> listPanelColorisParSaison(String saison);


    public Panelcoloris getPanelColorisById(Integer id);

    public Panelcoloris addPanelColoris(Panelcoloris panelcoloris);

    public void deletePanelColoris(Integer idPanelColoris);
}
