package br.com.usinasantafe.ecm.control;

import android.app.ProgressDialog;
import android.content.Context;

import java.util.List;

import br.com.usinasantafe.ecm.model.bean.estaticas.REquipPneuBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.ItemPneuBean;
import br.com.usinasantafe.ecm.model.dao.CabecPneuDAO;
import br.com.usinasantafe.ecm.model.dao.EquipDAO;
import br.com.usinasantafe.ecm.model.dao.ItemPneuDAO;
import br.com.usinasantafe.ecm.model.dao.PneuDAO;

public class PneuCTR {

    private ItemPneuBean itemPneuBean;

    public PneuCTR() {
    }

    public boolean verCalibAberto(){
        CabecPneuDAO cabecPneuDAO = new CabecPneuDAO();
        return cabecPneuDAO.verCabecPneuAberto();
    }

    public List<ItemPneuBean> itemCalibPneuList(){
        CabecPneuDAO cabecPneuDAO = new CabecPneuDAO();
        ItemPneuDAO itemPneuDAO = new ItemPneuDAO();
        return itemPneuDAO.itemPneuList(cabecPneuDAO.getIdCabecAberto());
    }

    public ItemPneuBean getItemPneuBean() {
        return itemPneuBean;
    }

    public void setItemPneuBean(Long idPosConfPneu) {
        CabecPneuDAO cabecPneuDAO = new CabecPneuDAO();
        this.itemPneuBean = new ItemPneuBean();
        this.itemPneuBean.setPosItemPneu(idPosConfPneu);
        this.itemPneuBean.setIdCabecItemPneu(cabecPneuDAO.getIdCabecAberto());
    }

    public void verPneu(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ItemPneuDAO itemPneuDAO = new ItemPneuDAO();
        itemPneuDAO.verPneu(dado, telaAtual, telaProx, progressDialog);
    }

    public void salvarItem(){
        ItemPneuDAO itemPneuDAO = new ItemPneuDAO();
        itemPneuDAO.salvarItem(itemPneuBean);
    }

    public boolean verFechCabec(){
        CabecPneuDAO cabecPneuDAO = new CabecPneuDAO();
        ItemPneuDAO itemPneuDAO = new ItemPneuDAO();
        return cabecPneuDAO.verFechCabec(itemPneuDAO.itemPneuList(cabecPneuDAO.getIdCabecAberto()));
    }

    public List<REquipPneuBean> rEquipPneuList(){
        EquipDAO equipDAO = new EquipDAO();
        return equipDAO.rEquipPneuList();
    }

    public REquipPneuBean getEquipPneu(String posPneu){
        EquipDAO equipDAO = new EquipDAO();
        return equipDAO.getEquipPneu(posPneu);
    }

    public boolean verPneu(String codPneu){
        PneuDAO pneuDAO = new PneuDAO();
        return pneuDAO.verPneu(codPneu);
    }

}
