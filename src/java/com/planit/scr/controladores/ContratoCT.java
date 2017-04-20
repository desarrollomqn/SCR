/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.controladores;

import com.planit.scr.dao.ContratosDao;
import com.planit.scr.dao.MunicipiosContratosDao;
import com.planit.scr.dao.MunicipiosDao;
import com.planit.scr.modelos.Campo;
import com.planit.scr.modelos.Contrato;
import com.planit.scr.modelos.Municipio;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Desarrollo_Planit
 */
public class ContratoCT {

    private Contrato contrato;
    private List<Contrato> contratos;
    private List<Municipio> municipios;
    private List<Municipio> municipiosRespaldo;

    private int operacion;
    private String nombreOperacion;

    private String buscar;

    public ContratoCT() {
        contrato = new Contrato();
        contratos = new ArrayList<>();
        municipios = new ArrayList<>();
        municipiosRespaldo = new ArrayList<>();

        operacion = 0;
        nombreOperacion = "Registrar";

        buscar = "";
    }

    @PostConstruct
    public void init() {
        ContratosDao contratosDao = new ContratosDao();
        try {
            contratos = contratosDao.consultarContratos();
        } catch (Exception ex) {
            Logger.getLogger(ContratoCT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Getter & Setter
    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public List<Contrato> getContratos() {
        return contratos;
    }

    public void setContratos(List<Contrato> contratos) {
        this.contratos = contratos;
    }

    public int getOperacion() {
        return operacion;
    }

    public void setOperacion(int operacion) {
        this.operacion = operacion;
    }

    public String getNombreOperacion() {
        return nombreOperacion;
    }

    public void setNombreOperacion(String nombreOperacion) {
        this.nombreOperacion = nombreOperacion;
    }

    public List<Municipio> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(List<Municipio> municipios) {
        this.municipios = municipios;
    }

    public List<Municipio> getMunicipiosRespaldo() {
        return municipiosRespaldo;
    }

    public void setMunicipiosRespaldo(List<Municipio> municipiosRespaldo) {
        this.municipiosRespaldo = municipiosRespaldo;
    }

    public String getBuscar() {
        return buscar;
    }

    public void setBuscar(String buscar) {
        this.buscar = buscar;
    }

    public void buscarContratos() throws Exception {
        ContratosDao contratosDao = new ContratosDao();
        if (buscar.isEmpty()) {
            contratos = contratosDao.consultarContratos();
        } else {
            contratos = contratosDao.buscarContratos(buscar);
        }
    }

    public void metodo() throws Exception {
        if (operacion == 0) {
            registrar();
        } else if (operacion == 1) {
            modificar();
            //Reiniciamos banderas
            nombreOperacion = "Registrar";
            operacion = 0;
        }
    }

    public void seleccionarCRUD(int i) throws Exception {
        operacion = i;
        if (operacion == 1) {
            nombreOperacion = "Modificar";
            MunicipiosDao municipiosDao = new MunicipiosDao();
            municipios = municipiosDao.consultarMunicipiosSegunContrato(contrato);
            municipiosRespaldo = municipios;
        }
    }

    public void cancelar() throws Exception {
        ContratosDao contratosDao = new ContratosDao();
        contrato = new Contrato();
        contratos = contratosDao.consultarContratos();
        operacion = 0;
        nombreOperacion = "Registrar";
        municipios.clear();
        municipiosRespaldo.clear();
    }

    //Metodos 
    public void registrar() throws Exception {
        ContratosDao contratoDao = new ContratosDao();
        MunicipiosContratosDao mcDao = new MunicipiosContratosDao();
        int resultado = contratoDao.registrarContrato(contrato);
        contrato = contratoDao.consultarContrato(contrato);
        if (resultado == 1) {
            if (!municipios.isEmpty()) {
                for (int i = 0; i < municipios.size(); i++) {
                    mcDao.registrarMunicipioContrato(municipios.get(i), contrato);
                }
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Contrato registrado exitosamente", "");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Contrato registrado exitosamente", "No asocio municipios, modifique este contrato posteriormente");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrio un error al momento de registrar el contrato", "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        contrato = new Contrato();
        contratos = contratoDao.consultarContratos();
        municipios.clear();
        municipiosRespaldo.clear();
    }

    public void modificar() throws Exception {
        ContratosDao contratoDao = new ContratosDao();
        MunicipiosContratosDao mcDao = new MunicipiosContratosDao();
        int resultado = contratoDao.modificarContrato(contrato);
        if (resultado == 1) {
            boolean existe = false;
            //Registramos las nuevas asociaciones
            for (int i = 0; i < municipios.size(); i++) {
                for (int j = 0; j < municipiosRespaldo.size(); j++) {
                    if (municipios.get(i).getIdmunicipio().equals(municipiosRespaldo.get(j).getIdmunicipio())) {
                        existe = true;
                    }
                }
                if (!existe) {
                    mcDao.registrarMunicipioContrato(municipios.get(i), contrato);
                }
                existe = false;
            }

            //Eliminamos la asociaciones desactivadas
            existe = false;
            for (int i = 0; i < municipiosRespaldo.size(); i++) {
                for (int j = 0; j < municipios.size(); j++) {
                    if (municipiosRespaldo.get(i).getIdmunicipio().equals(municipios.get(j).getIdmunicipio())) {
                        existe = true;
                    }
                }
                if (!existe) {
                    mcDao.eliminarMunicipioContrato(municipiosRespaldo.get(i), contrato);
                }
                existe = false;
            }

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Contrato modificado exitosamente", "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrio un error al momento de modificar el contrato", "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        contrato = new Contrato();
        contratos = contratoDao.consultarContratos();
        municipios.clear();
        municipiosRespaldo.clear();
    }

    public void eliminar() throws Exception {
        ContratosDao contratoDao = new ContratosDao();
        int resultado = contratoDao.eliminarContrato(contrato);
        if (resultado == 1) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Contrato eliminado exitosamente", "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrio un error al momento de eliminar el contrato", "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        contrato = new Contrato();
        contratos = contratoDao.consultarContratos();
    }

    public Contrato consultarContratoSegunCampo(Campo campo) throws Exception {
        ContratosDao contratosDao = new ContratosDao();
        return contratosDao.consultarContratoSegunCampo(campo);
    }
}
