/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.controladores;

import com.planit.scr.dao.DerivadoDao;
import com.planit.scr.dao.PonderadosRefinacionDao;
import com.planit.scr.dao.TrmDao;
import com.planit.scr.modelos.Derivado;
import com.planit.scr.modelos.PonderadoRefinacion;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author VaioDevelopment
 */
public class PonderadoRefinacionCT {

    private PonderadoRefinacion ponderadoRefinacion;
    private List<PonderadoRefinacion> ponderadosRegistro;
    private List<PonderadoRefinacion> ponderados;

    private String nombreOperacion;
    private int operacion;

    private int anio;
    private int trimestremes;

    public PonderadoRefinacionCT() {
        ponderadoRefinacion = new PonderadoRefinacion();
        ponderados = new ArrayList<>();
        ponderadosRegistro = new ArrayList<>();
        
        anio = 2016;
        trimestremes = 0;
        nombreOperacion = "Registrar";
        operacion = 0;
    }

    @PostConstruct
    public void init() {
        PonderadosRefinacionDao ponderadosRefinacionDao = new PonderadosRefinacionDao();
        ponderadosRegistro.clear();
        try {
            ponderados = ponderadosRefinacionDao.ConsultarPonderados();
            cargarDerivados();
        } catch (Exception ex) {
            Logger.getLogger(PonderadoRefinacionCT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public PonderadoRefinacion getPonderadoRefinacion() {
        return ponderadoRefinacion;
    }

    public void setPonderadoRefinacion(PonderadoRefinacion ponderadoRefinacion) {
        this.ponderadoRefinacion = ponderadoRefinacion;
    }

    public List<PonderadoRefinacion> getPonderados() {
        return ponderados;
    }

    public void setPonderados(List<PonderadoRefinacion> ponderados) {
        this.ponderados = ponderados;
    }

    public String getNombreOperacion() {
        return nombreOperacion;
    }

    public void setNombreOperacion(String nombreOperacion) {
        this.nombreOperacion = nombreOperacion;
    }

    public int getOperacion() {
        return operacion;
    }

    public void setOperacion(int operacion) {
        this.operacion = operacion;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getTrimestremes() {
        return trimestremes;
    }

    public void setTrimestremes(int trimestremes) {
        this.trimestremes = trimestremes;
    }

    public List<PonderadoRefinacion> getPonderadosRegistro() {
        return ponderadosRegistro;
    }

    public void setPonderadosRegistro(List<PonderadoRefinacion> ponderadosRegistro) {
        this.ponderadosRegistro = ponderadosRegistro;
    }

    public void registrar() throws Exception {
        PonderadosRefinacionDao ponderadosRefinacionDao = new PonderadosRefinacionDao();
        FacesMessage message = new FacesMessage();
        TrmDao trmDao = new TrmDao();
        double trmPromedio = trmDao.consultarPromedioTrimestralTrm(trimestremes, anio);

        if (trmPromedio != 0) {
            for (int i = 0; i < ponderadosRegistro.size(); i++) {
                ponderadosRegistro.get(i).setRendimiento(ponderadosRegistro.get(i).getProduccion() / obtenerTotalProduccion(ponderadosRegistro));
                ponderadosRegistro.get(i).setPreciomedio(((ponderadosRegistro.get(i).getNalbpd() * ponderadosRegistro.get(i).getPrecional()) + ((ponderadosRegistro.get(i).getExportbpd() * ponderadosRegistro.get(i).getPrecioexpo() * trmPromedio) / 42)) / ponderadosRegistro.get(i).getProduccion());
                ponderadosRegistro.get(i).setMediordto(ponderadosRegistro.get(i).getPreciomedio() * ponderadosRegistro.get(i).getRendimiento());
                ponderadosRegistro.get(i).setAnio(anio);
                ponderadosRegistro.get(i).setTrimestremes(trimestremes);
            }

            int r = ponderadosRefinacionDao.registrarGrupoPonderado(ponderadosRegistro);
            if (r == 1) {
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Los valores de cada derivado fueron registrados correctamente");
            } else if (r == 0) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrio un error al registrar los valores de cada derivado");
            }

        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "No existen valores de TRM registrados para este trimestre/mes y año");
        }

        FacesContext.getCurrentInstance().addMessage(null, message);
        ponderadosRegistro.clear();
        cargarDerivados();
        ponderados = ponderadosRefinacionDao.ConsultarPonderados();
    }

    public void modificar() throws Exception {
        PonderadosRefinacionDao ponderadosRefinacionDao = new PonderadosRefinacionDao();
        FacesMessage message = new FacesMessage();

        TrmDao trmDao = new TrmDao();
        double trmPromedio = trmDao.consultarPromedioTrimestralTrm(ponderadosRegistro.get(0).getTrimestremes(), ponderadosRegistro.get(0).getAnio());
        if (trmPromedio != 0) {
            for (int i = 0; i < ponderadosRegistro.size(); i++) {
                ponderadosRegistro.get(i).setRendimiento(ponderadosRegistro.get(i).getProduccion() / obtenerTotalProduccion(ponderadosRegistro));
                ponderadosRegistro.get(i).setPreciomedio(((ponderadosRegistro.get(i).getNalbpd() * ponderadosRegistro.get(i).getPrecional()) + ((ponderadosRegistro.get(i).getExportbpd() * ponderadosRegistro.get(i).getPrecioexpo() * trmPromedio) / 42)) / ponderadosRegistro.get(i).getProduccion());
                ponderadosRegistro.get(i).setMediordto(ponderadosRegistro.get(i).getPreciomedio() * ponderadosRegistro.get(i).getRendimiento());
            }

            int r = ponderadosRefinacionDao.modificarGrupoPonderado(ponderadosRegistro);
            if (r == 1) {
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Los valores de cada derivado fueron modificados correctamente");
            } else if (r == 0) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrio un error al modificar los valores de cada derivado");
            }
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "No existen valores de TRM registrados para este trimestre/mes y año");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
        cargarDerivados();
        ponderados = ponderadosRefinacionDao.ConsultarPonderados();
    }

    public void eliminar() throws Exception {
        PonderadosRefinacionDao ponderadosRefinacionDao = new PonderadosRefinacionDao();
        FacesMessage message = new FacesMessage();
        int r = ponderadosRefinacionDao.eliminarGrupoPonderado(anio, trimestremes);
        if (r == 1) {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Los valores de cada derivado fueron eliminados correctamente");
        } else if (r == 0) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrio un error al eliminar los valores de cada derivado");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
        ponderadosRegistro.clear();
        ponderados = ponderadosRefinacionDao.ConsultarPonderados();
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
            PonderadosRefinacionDao ponderadosRefinacionDao = new PonderadosRefinacionDao();
            ponderadosRegistro.clear();
            ponderadosRegistro = ponderadosRefinacionDao.ConsultarPonderado(anio, trimestremes);
        }
    }

    public void cancelar() throws Exception {
        PonderadosRefinacionDao ponderadosRefinacionDao = new PonderadosRefinacionDao();
        ponderadoRefinacion = new PonderadoRefinacion();
        ponderados = ponderadosRefinacionDao.ConsultarPonderados();
        anio = 2016;
        trimestremes = 0;
        cargarDerivados();
        nombreOperacion = "Registrar";
    }

    //Otros metodos
    public double obtenerTotalProduccion(List<PonderadoRefinacion> lista) {
        double valor = 0;
        for (int i = 0; i < lista.size(); i++) {
            valor = valor + lista.get(i).getProduccion();
        }
        return valor;
    }

    public void cargarDerivados() throws Exception {
        ponderadosRegistro.clear();
        DerivadoDao derivadoDao = new DerivadoDao();
        List<Derivado> d = derivadoDao.consultarDerivados();
        for (int i = 0; i < derivadoDao.consultarDerivados().size(); i++) {
            PonderadoRefinacion p = new PonderadoRefinacion();
            p.setDerivado(d.get(i));
            ponderadosRegistro.add(p);
        }
    }

}
