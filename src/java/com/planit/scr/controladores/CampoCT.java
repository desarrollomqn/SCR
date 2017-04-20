/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.controladores;

import com.planit.scr.dao.CamposContratosDao;
import com.planit.scr.dao.CamposDao;
import com.planit.scr.dao.ContratosDao;
import com.planit.scr.dao.MunicipiosDao;
import com.planit.scr.modelos.Campo;
import com.planit.scr.modelos.Contrato;
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
public class CampoCT {

    private Campo campo;
    private List<Campo> campos;
    private List<Contrato> contratos;
    private List<Contrato> contratosRespaldo;

    protected CamposDao camposDao;

    private int operacion;
    private String nombreOperacion;

    private String buscar;

    public CampoCT() {
        campo = new Campo();
        campos = new ArrayList<>();
        contratos = new ArrayList<>();
        contratosRespaldo = new ArrayList<>();
        camposDao = new CamposDao();
        nombreOperacion = "Registrar";
        operacion = 0;
        buscar = "";
        System.out.println("" + Thread.currentThread().getContextClassLoader().getResource("/").getPath());
    }

    @PostConstruct
    public void init() {        ;
        try {
            campos = camposDao.consultarCampos();
        } catch (Exception ex) {
            Logger.getLogger(CampoCT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Campo getCampo() {
        return campo;
    }

    public void setCampo(Campo campo) {
        this.campo = campo;
    }

    public List<Campo> getCampos() {
        return campos;
    }

    public void setCampos(List<Campo> campos) {
        this.campos = campos;
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

    public String getBuscar() {
        return buscar;
    }

    public void setBuscar(String buscar) {
        this.buscar = buscar;
    }

    public List<Contrato> getContratosRespaldo() {
        return contratosRespaldo;
    }

    public void setContratosRespaldo(List<Contrato> contratosRespaldo) {
        this.contratosRespaldo = contratosRespaldo;
    }

    //Metodos     
    public void registrar() throws Exception {
        CamposContratosDao ccDao = new CamposContratosDao();

        int registrar = camposDao.registrarCampo(campo);
        if (registrar == 1) {
            campo = camposDao.consultarCampo(campo);
            if (!contratos.isEmpty()) {
                for (int i = 0; i < contratos.size(); i++) {
                    ccDao.registrarMunicipioContrato(campo, contratos.get(i));
                }
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Campo registrado exitosamente", "");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Campo registrado exitosamente", "No asocio contratos, modifique este contrato posteriormente");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } else if (registrar == 0) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrio un error al momento de registrar el campo", "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        campos = camposDao.consultarCampos();
        campo = new Campo();
        contratos.clear();
        contratosRespaldo.clear();
    }

    public void modificar() throws Exception {
        CamposContratosDao ccDao = new CamposContratosDao();
        //campo.setContrato(contratosDao.consultarContrato(campo.getContrato()));
        int modificar = camposDao.modificarCampo(campo);
        if (modificar == 1) {
            boolean existe = false;
            //Registramos las nuevas asociaciones
            for (int i = 0; i < contratos.size(); i++) {
                for (int j = 0; j < contratosRespaldo.size(); j++) {
                    if (contratos.get(i).getIdcontrato().equals(contratosRespaldo.get(j).getIdcontrato())) {
                        existe = true;
                    }
                }
                if (!existe) {
                    ccDao.registrarMunicipioContrato(campo, contratos.get(i));
                }
                existe = false;
            }

            //Eliminamos la asociaciones desactivadas
            existe = false;
            for (int i = 0; i < contratosRespaldo.size(); i++) {
                for (int j = 0; j < contratos.size(); j++) {
                    if (contratosRespaldo.get(i).getIdcontrato().equals(contratos.get(j).getIdcontrato())) {
                        existe = true;
                    }
                }
                if (!existe) {
                    ccDao.eliminarCamposContrato(campo, contratosRespaldo.get(i));
                }
                existe = false;
            }
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Campo modificado exitosamente", "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else if (modificar == 0) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrio un error al momento de modificar el campo");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        campo = new Campo();
        campos = camposDao.consultarCampos();
        contratos.clear();
        contratosRespaldo.clear();
    }

    public void eliminar() throws Exception {
        int eliminar = camposDao.eliminarCampo(campo);
        if (eliminar == 1) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Se elimino el campo correctamente");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else if (eliminar == 0) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrio un error al momento de eliminar el campo");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        campo = new Campo();
        campos = camposDao.consultarCampos();
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
            ContratosDao contratosDao = new ContratosDao();
            contratos = contratosDao.consultarContratosSegunCampo(campo);
            contratosRespaldo = contratos;
        }
    }

    public void cancelar() throws Exception {

        nombreOperacion = "Registrar";
        operacion = 0;
        campos = camposDao.consultarCampos();
        campo = new Campo();
        contratos.clear();
        contratosRespaldo.clear();
    }

    public void buscarCampo() throws Exception {

        if (buscar.isEmpty()) {
            campos = camposDao.consultarCampos();
        } else {
            campos = camposDao.buscarCampos(buscar);
        }
    }
}
