package br.com.usinasantafe.ecm.control;

import br.com.usinasantafe.ecm.model.bean.estaticas.ItemCheckListBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.CabecCLBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.RespItemCLBean;
import br.com.usinasantafe.ecm.model.dao.CabecCheckListDAO;
import br.com.usinasantafe.ecm.model.dao.ItemCheckListDAO;
import br.com.usinasantafe.ecm.model.dao.RespCheckListDAO;

public class CheckListCTR {

    public CheckListCTR() {
    }

    public boolean verCabecAberto(){
        CabecCheckListDAO cabecCheckListDAO = new CabecCheckListDAO();
        return cabecCheckListDAO.verCabecAberto();
    }

    public void clearRespCabecAberto(){
        CabecCheckListDAO cabecCheckListDAO = new CabecCheckListDAO();
        CabecCLBean cabecCLTO = cabecCheckListDAO.getCabecAberto();
        RespCheckListDAO respItemCLDAO = new RespCheckListDAO();
        respItemCLDAO.clearRespItem(cabecCLTO.getIdCabCL());
    }

    public void createCabecAberto(MotoMecCTR motoMecCTR){
        CabecCheckListDAO cabecCheckListDAO = new CabecCheckListDAO();
        cabecCheckListDAO.createCabecAberto(motoMecCTR);
    }

    public boolean verAberturaCheckList(Long turno){
        CabecCheckListDAO cabecCheckListDAO = new CabecCheckListDAO();
        return cabecCheckListDAO.verAberturaCheckList(turno);
    }

    public ItemCheckListBean getItemCheckList(int pos){
        ConfigCTR configCTR = new ConfigCTR();
        RespCheckListDAO respCheckListDAO = new RespCheckListDAO();
        return respCheckListDAO.getItemCheckList(pos, configCTR.getEquip());
    }

    public int qtdeItemCheckList(){
        ConfigCTR configCTR = new ConfigCTR();
        ItemCheckListDAO itemCheckListDAO = new ItemCheckListDAO();
        return itemCheckListDAO.qtdeItem(configCTR.getEquip().getIdCheckList());
    }

    public void insResp(RespItemCLBean respItemCLBean){
        CabecCheckListDAO cabecCheckListDAO = new CabecCheckListDAO();
        RespCheckListDAO respCheckListDAO = new RespCheckListDAO();
        respCheckListDAO.salvarRespCheckList(cabecCheckListDAO.getIdCabecAberto(), respItemCLBean);
    }

    public void fechaCabec(){
        CabecCheckListDAO cabecCheckListDAO = new CabecCheckListDAO();
        cabecCheckListDAO.fechaCabec();
    }

    public void recDadosCheckList(String result) {
        ItemCheckListDAO itemCheckListDAO = new ItemCheckListDAO();
        itemCheckListDAO.recDadosCheckList(result);
    }

}
