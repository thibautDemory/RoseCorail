package hei.ProjetRoseCorail.managers;

import hei.ProjetRoseCorail.dao.PanelColorisDao;
import hei.ProjetRoseCorail.dao.impl.PanelColorisDaoImpl;
import hei.ProjetRoseCorail.entities.Panelcoloris;


import java.util.List;

public class PanelColorisLibrary {
    private static class PanelColorisLibraryHolder {
        private final static PanelColorisLibrary instance= new PanelColorisLibrary();
    }

    public static PanelColorisLibrary getInstance() {
        return PanelColorisLibraryHolder.instance;
    }

    private PanelColorisDao panelColorisDao = new PanelColorisDaoImpl();

    public List<Panelcoloris> listPanelColoris(){
        return panelColorisDao.listPanelColoris();
    }

    public Panelcoloris getPanelColorisById(int id){
        return panelColorisDao.getPanelColorisById(id);
    }

    public Panelcoloris addPanelColoris(Panelcoloris panelcoloris) {
        if (panelcoloris == null) {
            throw new IllegalArgumentException("Le panel coloris ne peut pas être nulle.");
        }
        if (panelcoloris.getLegende() == null || "".equals(panelcoloris.getLegende())) {
            throw new IllegalArgumentException("La légende du panel coloris ne peut pas être nul.");
        }
        if (panelcoloris.getImage() == null || "".equals(panelcoloris.getImage())) {
            throw new IllegalArgumentException("L'image du panel coloris ne pas être nul.");
        }
        if (panelcoloris.getSaison() == null) {
            throw new IllegalArgumentException("La saison ne pas être nul.");
        }
        return panelColorisDao.addPanelColoris(panelcoloris);
    }
    public List<Panelcoloris> listpanelcolorisparsaison(String saison){ return panelColorisDao.listPanelColorisParSaison(saison);}

    public void deletePanelColoris(Integer idPanelColoris){
        panelColorisDao.deletePanelColoris(idPanelColoris);
    }
}
