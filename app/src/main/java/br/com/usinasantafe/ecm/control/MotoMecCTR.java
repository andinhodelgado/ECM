package br.com.usinasantafe.ecm.control;

import android.app.ProgressDialog;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.ecm.model.bean.estaticas.ColabBean;
import br.com.usinasantafe.ecm.model.bean.estaticas.MotoMecBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.ApontMMBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.BoletimMMBean;
import br.com.usinasantafe.ecm.model.dao.ApontMMDAO;
import br.com.usinasantafe.ecm.model.dao.AtividadeDAO;
import br.com.usinasantafe.ecm.model.dao.BoletimMMDAO;
import br.com.usinasantafe.ecm.model.dao.CarretaDAO;
import br.com.usinasantafe.ecm.model.dao.MotoMecDAO;
import br.com.usinasantafe.ecm.model.dao.OSDAO;
import br.com.usinasantafe.ecm.model.dao.PreCECDAO;
import br.com.usinasantafe.ecm.util.AtualDadosServ;
import br.com.usinasantafe.ecm.util.Tempo;

public class MotoMecCTR {

    private BoletimMMBean boletimMMBean;
    private MotoMecBean motoMecBean;

    public MotoMecCTR() {
        if (boletimMMBean == null)
            boletimMMBean = new BoletimMMBean();
    }

    public boolean verBolAberto(){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        return boletimMMDAO.verBolAberto();
    }

    public void atualApont(){
        ApontMMDAO apontMMDAO = new ApontMMDAO();
        ApontMMBean apontMMBean = apontMMDAO.getApontMMAberto();
        apontMMDAO.updApont(apontMMBean);
    }


    //////////////////////////// SETAR CAMPOS ///////////////////////////////////////////////

    public void setFuncBol(Long matric){
        boletimMMBean.setMatricFuncBolMM(matric);
    }

    public void setEquipBol(){
        ConfigCTR configCTR = new ConfigCTR();
        boletimMMBean.setIdEquipBolMM(configCTR.getEquip().getIdEquip());
    }

    public void setTurnoBol(Long idTurno){
        boletimMMBean.setIdTurnoBolMM(idTurno);
    }

    public void setOSBol(Long os){
            boletimMMBean.setOsBolMM(os);
    }

    public void setAtivBol(Long ativ){
        ConfigCTR configCTR = new ConfigCTR();
        boletimMMBean.setAtivPrincBolMM(ativ);
        boletimMMBean.setStatusConBolMM(configCTR.getConfig().getStatusConConfig());
    }


    public void setHodometroInicialBol(Double horimetroNum, Double longitude, Double latitude){
        boletimMMBean.setHodometroInicialBolMM(horimetroNum);
        boletimMMBean.setHodometroFinalBolMM(0D);
        boletimMMBean.setIdExtBolMM(0L);
        boletimMMBean.setLongitudeBolMM(longitude);
        boletimMMBean.setLatitudeBolMM(latitude);
    }

    public void setHodometroFinalBol(Double horimetroNum){
        boletimMMBean.setHodometroFinalBolMM(horimetroNum);
    }

    public void setMotoMecBean(MotoMecBean motoMecBean) {
        this.motoMecBean = motoMecBean;
    }

    public void setAtivMM(Long ativ) {
        this.motoMecBean.setIdMotoMec(ativ);
        this.motoMecBean.setFuncaoOperMotoMec(1L);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////// GET DE CAMPOS ///////////////////////////////////////////

    public Long getAtiv(){
        return boletimMMBean.getAtivPrincBolMM();
    }

    public Long getTurno(){
        return boletimMMBean.getIdTurnoBolMM();
    }

    public Long getFunc(){
        return boletimMMBean.getMatricFuncBolMM();
    }

    public String getDescrCarreta(){
        CarretaDAO carretaDAO = new CarretaDAO();
        return carretaDAO.getDescrCarreta();
    }

    public ColabBean getMatricNomeFunc(){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        return boletimMMDAO.getMatricNomeFunc();
    }

    public Long getIdExtBol(){
        return boletimMMBean.getIdExtBolMM();
    }

    public Long getStatusConBol(){
        return boletimMMBean.getStatusConBolMM();
    }

    public Long getOS() {
        return boletimMMBean.getOsBolMM();
    }

    public Long getIdBol(){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        return boletimMMDAO.getIdBolAberto();
    }

    public Double getLongitude(){
        return boletimMMBean.getLongitudeBolMM();
    }

    public Double getLatitude(){
        return boletimMMBean.getLatitudeBolMM();
    }

    public String getDataSaidaUlt(){
        PreCECDAO preCECDAO = new PreCECDAO();
        return preCECDAO.getDataSaidaUlt();
    }

    public List getMotoMecList() {
        MotoMecDAO motoMecDAO = new MotoMecDAO();
        return motoMecDAO.getMotoMecList();
    }

    public List getParadaList() {
        MotoMecDAO motoMecDAO = new MotoMecDAO();
        return motoMecDAO.getParadaList();
    }

    public Long getOpCorSaidaUsina(){
        MotoMecDAO motoMecDAO = new MotoMecDAO();
        return motoMecDAO.getOpCorSaidaUsina();
    }

    public MotoMecBean getMotoMecBean() {
        return motoMecBean;
    }

    public int qtdeCarreta(){
        CarretaDAO carretaDAO = new CarretaDAO();
        return carretaDAO.getQtdeCarreta();
    }

    /////////////////////////////////////////////////////////////////////////////////////

    //////////////////////// MANIPULAR DADOS MOTOMEC BOLETIM //////////////////////////////////////

    ///////////// SALVAR DADOS ///////////////

    public void salvarBolAbertoMM(){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        boletimMMDAO.salvarBolAberto(boletimMMBean);
    }

    public void salvarBolFechadoMM(){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        boletimMMDAO.salvarBolFechado(boletimMMBean);
    }

    ////////// VERIFICAÇÃO PRA ENVIO ///////////////

    public boolean verEnvioBolAbertoMM(){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        return boletimMMDAO.bolAbertoSemEnvioList().size() > 0;
    }

    public boolean verEnvioBolFechMM() {
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        return boletimMMDAO.bolFechadoList().size() > 0;
    }

    ////////// DADOS PRA ENVIO ///////////////

    public String dadosEnvioBolAbertoMM(){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        return boletimMMDAO.dadosEnvioBolAberto();
    }

    public String dadosEnvioBolFechadoMM(){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        return boletimMMDAO.dadosEnvioBolFechado();
    }

    ////////// MANIPULAÇÃO RETORNO DE ENVIO ///////////////

    public void updBolAbertoMM(String retorno){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        boletimMMDAO.updateBolAberto(retorno);
    }

    public void delBolFechadoMM(String retorno) {
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        boletimMMDAO.deleteBolFechado(retorno);
    }

    //////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////// MANIPULAR APONT DADOS MOTOMEC ////////////////////////////////////

    ////////// VERIFICAÇÃO PRA ENVIO ///////////////

    public Boolean verEnvioDadosApont(){
        ApontMMDAO apontMMDAO = new ApontMMDAO();
        return (apontMMDAO.getListApontEnvio().size() > 0);
    }

    ////////// DADOS PRA ENVIO ///////////////

    public String dadosEnvioApontBolMM(Long idBol){
        ApontMMDAO apontMMDAO = new ApontMMDAO();
        return apontMMDAO.dadosEnvioApontMM(apontMMDAO.getListApontEnvio(idBol));
    }

    public String dadosEnvioApontMM(){
        ApontMMDAO apontMMDAO = new ApontMMDAO();
        return apontMMDAO.dadosEnvioApontMM(apontMMDAO.getListApontEnvio());
    }

    ////////// MANIPULAÇÃO RETORNO DE ENVIO ///////////////

    public void updateApontMM(String retorno) {
        ApontMMDAO apontMMDAO = new ApontMMDAO();
        apontMMDAO.updateApont(retorno);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////// ATUALIZAÇÃO DE DADOS POR CLASSE /////////////////////////////////////

    public void atualDadosMotorista(Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ArrayList operadorArrayList = new ArrayList();
        operadorArrayList.add("MotoristaBean");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, operadorArrayList);
    }

    public void atualDadosTurno(Context telaAtual, Class telaProx, ProgressDialog progressDialog) {
        ArrayList turnoArrayList = new ArrayList();
        turnoArrayList.add("TurnoBean");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, turnoArrayList);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////// VERIFICAÇÃO E ATUALIZAÇÃO DE DADOS ////////////////////////////

    public void verOS(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        OSDAO osDAO = new OSDAO();
        osDAO.verOS(dado, telaAtual, telaProx, progressDialog);
    }

    public void verAtiv(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ConfigCTR configCTR = new ConfigCTR();
        AtividadeDAO atividadeDAO = new AtividadeDAO();
        atividadeDAO.verAtiv(dado  + "_" + configCTR.getEquip().getNroEquip(), telaAtual, telaProx, progressDialog);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////// RETORNO DE LISTA DAS ATIVIDADES DA OS /////////////////////////////

    public ArrayList getAtivArrayList(Long nroOS){
        ConfigCTR configCTR = new ConfigCTR();
        AtividadeDAO atividadeDAO = new AtividadeDAO();
        return atividadeDAO.retAtivArrayList(configCTR.getEquip().getIdEquip(), nroOS);
    }

    //////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////// CRIAR E ATUALIZAR APONTAMENTO ////////////////////////////////////

    public void insApontMM(Double longitude, Double latitude, Long statusCon){

        ApontMMDAO apontMMDAO = new ApontMMDAO();
        ConfigCTR configCTR = new ConfigCTR();
        apontMMDAO.salvarApont(motoMecBean, configCTR.getConfig(), longitude, latitude, statusCon);

        atualQtdeApontBol();

        configCTR.setDtUltApontConfig(Tempo.getInstance().dataComHora().getDataHora());

//        if(status == 0L){
//            CabecPneuDAO cabecPneuDAO = new CabecPneuDAO();
//            cabecPneuDAO.salvarDados(func, equip, getIdApont());
//        }

    }


    public void insParadaCheckList(Double longitude, Double latitude, Long statusCon){

        ApontMMDAO apontMMDAO = new ApontMMDAO();
        MotoMecDAO motoMecDAO = new MotoMecDAO();
        ConfigCTR configCTR = new ConfigCTR();
        apontMMDAO.salvarApont(motoMecDAO.getCheckList(), configCTR.getConfig(), longitude, latitude, statusCon);

        atualQtdeApontBol();

        configCTR.setDtUltApontConfig(Tempo.getInstance().dataComHora().getDataHora());
    }

    public void insSaídaCampo(Double longitude, Double latitude, Long statusCon){
        ApontMMDAO apontMMDAO = new ApontMMDAO();
        MotoMecDAO motoMecDAO = new MotoMecDAO();
        ConfigCTR configCTR = new ConfigCTR();
        apontMMDAO.salvarApont(motoMecDAO.getSaidaCampo(), configCTR.getConfig(), longitude, latitude, statusCon);

        atualQtdeApontBol();

        configCTR.setDtUltApontConfig(Tempo.getInstance().dataComHora().getDataHora());
    }

    public void insVoltaTrab(Double longitude, Double latitude, Long statusCon){
        ApontMMDAO apontMMDAO = new ApontMMDAO();
        MotoMecDAO motoMecDAO = new MotoMecDAO();
        ConfigCTR configCTR = new ConfigCTR();
        apontMMDAO.salvarApont(motoMecDAO.getVoltaTrabalho(), configCTR.getConfig(), longitude, latitude, statusCon);

        atualQtdeApontBol();

        configCTR.setDtUltApontConfig(Tempo.getInstance().dataComHora().getDataHora());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////// ATUALIZAR QTDE DE APONTAMENTO DO BOLETIM ///////////////////////////

    public void atualQtdeApontBol(){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        boletimMMDAO.atualQtdeApontBol();
    }

    ////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////// CARRETA /////////////////////////////////////////////

    public void delCarreta(){
        CarretaDAO carretaDAO = new CarretaDAO();
        carretaDAO.delCarreta();
    }

    public int verCarr(Long nroCarreta){
        CarretaDAO carretaDAO = new CarretaDAO();
        return carretaDAO.verCarr(nroCarreta);
    }

    public void insCarreta(Long nroCarreta){
        CarretaDAO carretaDAO = new CarretaDAO();
        carretaDAO.insCarreta(nroCarreta);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

}
