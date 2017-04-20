/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.controladores;

import com.planit.scr.dao.CalidadCrudoDao;
import com.planit.scr.dao.PblDao;
import com.planit.scr.dao.PonderadosRefinacionDao;
import com.planit.scr.dao.ValoresDao;
import com.planit.scr.modelos.CalidadCrudo;
import com.planit.scr.modelos.Valores;
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
public class ValoresCT {

    private Valores valor;
    private List<Valores> valores;

    private int anio;
    private int trimestreMes;
    private int operacion;
    private String nombreOperacion;

    public ValoresCT() {
        valor = new Valores();
        valores = new ArrayList<>();
        System.out.println("" + Thread.currentThread().getContextClassLoader().getResource("/").getPath());
        anio = 0;
        trimestreMes = 0;
        operacion = 0;
        nombreOperacion = "Registrar";
    }

    @PostConstruct
    public void init() {
        ValoresDao valorDao = new ValoresDao();
        try {
            valores = valorDao.consultarValores();
        } catch (Exception ex) {
            Logger.getLogger(ContratoCT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Valores getValor() {
        return valor;
    }

    public void setValor(Valores valor) {
        this.valor = valor;
    }

    public List<Valores> getValores() {
        return valores;
    }

    public void setValores(List<Valores> valores) {
        this.valores = valores;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getTrimestreMes() {
        return trimestreMes;
    }

    public void setTrimestreMes(int trimestreMes) {
        this.trimestreMes = trimestreMes;
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

    public ValoresCT(Valores valor, List<Valores> valores) {
        this.valor = valor;
        this.valores = valores;
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
            anio = valor.getAnio();
            trimestreMes = valor.getTrimestreMes();
        }
    }

    public void registrar() throws Exception {
        ValoresDao valorDao = new ValoresDao();
        valor.setVt(valor.getV1() + valor.getV2());
        int resultado = valorDao.registrarValores(valor);
        if (resultado == 1) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "El registro de valores fue correcto");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else if (resultado == 0) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Ocurrio un error al registrar los valores");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        valor = new Valores();
        valores = valorDao.consultarValores();
    }

    public void modificar() throws Exception {
        ValoresDao valorDao = new ValoresDao();
        PonderadosRefinacionDao ponderadosRefinacionDao = new PonderadosRefinacionDao();
        CalidadCrudoDao calidadCrudoDao = new CalidadCrudoDao();

        valor.setVt(valor.getV1() + valor.getV2());
        int resultado = valorDao.modificarValores(valor);
        if (resultado == 1) {
            PblDao pblDao = new PblDao();
            if (!pblDao.consultarPbl(valor.getTrimestreMes(), valor.getAnio()).isEmpty()) {
                int r = pblDao.modificarPbl(valor, anio, trimestreMes);
                if (r == 1) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "la modificacion de valores pbl fue exitosa");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                } else if (r == 0) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Ocurrio un error al modificar los valores del pbl");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }
            } else {
                if (!ponderadosRefinacionDao.ConsultarPonderado(valor.getAnio(), valor.getTrimestreMes()).isEmpty() && calidadCrudoDao.consultarCalidadCrudo(new CalidadCrudo(valor.getTrimestreMes(), valor.getAnio())).getIdCalidadcrudo() != 0) {
                    int r = pblDao.registrarPbl(valor);
                    if (r == 1) {
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "la modificacion de valores pbl fue exitosa");
                        FacesContext.getCurrentInstance().addMessage(null, message);
                    } else if (r == 0) {
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Ocurrio un error al modificar los valores del pbl");
                        FacesContext.getCurrentInstance().addMessage(null, message);
                    }
                }else{
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No existen precios ponderados de refinacion ni exportacion registrados para este trimestre/mes y año");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }
            }
        } else if (resultado == 0) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Ocurrio un error al modificar los valores y los calculos del pbl");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        valor = new Valores();
        valores = valorDao.consultarValores();
    }

    public void eliminar() throws Exception {
        ValoresDao valorDao = new ValoresDao();
        int resultado = valorDao.eliminarValores(valor);
        if (resultado == 1) {
            PblDao pblDao = new PblDao();
            int r = pblDao.eliminarPbl(valor.getAnio(), valor.getTrimestreMes());
            if (r == 1) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "la eliminacion fue exitosa");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else if (r == 0) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Los valores registrados fueron eliminado pero ocurrio un error al eliminar el valor del pbl");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } else if (resultado == 0) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Ocurrio un error al eliminar los valores registrados y el valor del pbl correspondiente");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        valor = new Valores();
        valores = valorDao.consultarValores();
    }

    public void cancelar() throws Exception {
        ValoresDao valoresDao = new ValoresDao();
        valor = new Valores();
        valores = valoresDao.consultarValores();
        operacion = 0;
        nombreOperacion = "Registrar";
    }
}
