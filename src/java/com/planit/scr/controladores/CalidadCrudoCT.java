/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.controladores;

import com.planit.scr.dao.CalidadCrudoDao;
import com.planit.scr.dao.CamposDao;
import com.planit.scr.modelos.CalidadCrudo;
import com.planit.scr.modelos.Campo;
import java.util.ArrayList;
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
public class CalidadCrudoCT {

    private CalidadCrudo calidadCrudo;
    private List<CalidadCrudo> calidadCrudos;
    private String nombreOperacion;
    private int operacion;
    private String buscar;
    
    
    public CalidadCrudoCT() {
        calidadCrudo = new CalidadCrudo();
        calidadCrudos = new ArrayList<>();
        nombreOperacion = "Registrar";
        operacion = 0;
        buscar = null;
    }
    
    @PostConstruct
    public void init(){
        try {
            CalidadCrudoDao calidadCrudoDao = new CalidadCrudoDao();
            calidadCrudos = calidadCrudoDao.consultarCalidadCrudos();
        } catch (Exception ex) {
            Logger.getLogger(CalidadCrudoCT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public CalidadCrudo getCalidadCrudo() {
        return calidadCrudo;
    }

    public void setCalidadCrudo(CalidadCrudo calidadCrudo) {
        this.calidadCrudo = calidadCrudo;
    }

    public List<CalidadCrudo> getCalidadCrudos() {
        return calidadCrudos;
    }

    public void setCalidadCrudos(List<CalidadCrudo> calidadCrudos) {
        this.calidadCrudos = calidadCrudos;
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

    public String getBuscar() {
        return buscar;
    }

    public void setBuscar(String buscar) {
        this.buscar = buscar;
    }    
        
    public void registrar() throws Exception{
        CalidadCrudoDao calidadCrudoDao = new CalidadCrudoDao();
        int r = calidadCrudoDao.registrarCalidadCrudo(calidadCrudo);
        FacesMessage message = new FacesMessage();
        if(r == 1){
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Valores registrados exitosamente");
        }else if(r == 0){
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrio un error al registrar los valores de la calidad de crudo");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
        calidadCrudo = new CalidadCrudo();
        calidadCrudos = calidadCrudoDao.consultarCalidadCrudos();
    }
    
    public void modificar() throws Exception{
        CalidadCrudoDao calidadCrudoDao = new CalidadCrudoDao();
        int r = calidadCrudoDao.modificarCalidadCrudo(calidadCrudo);
        FacesMessage message = new FacesMessage();
        if(r == 1){
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Valores modificados exitosamente");
        }else if(r == 0){
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrio un error al modificar los valores de la calidad de crudo");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
        calidadCrudo = new CalidadCrudo();
        calidadCrudos = calidadCrudoDao.consultarCalidadCrudos();
    }
    
    public void eliminar() throws Exception{
        CalidadCrudoDao calidadCrudoDao = new CalidadCrudoDao();
        int r = calidadCrudoDao.eliminarCalidadCrudo(calidadCrudo);
        FacesMessage message = new FacesMessage();
        if(r == 1){
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Valores eliminados exitosamente");
        }else if(r == 0){
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrio un error al eliminar los valores de la calidad de crudo");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
        calidadCrudo = new CalidadCrudo();
        calidadCrudos = calidadCrudoDao.consultarCalidadCrudos();
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
        }
    }
    
    public void cancelar() throws Exception {
        nombreOperacion = "Registrar";
        operacion = 0;
        CalidadCrudoDao calidadCrudoDao = new CalidadCrudoDao();
        calidadCrudo = new CalidadCrudo();
        calidadCrudos = calidadCrudoDao.consultarCalidadCrudos();    
    }
}
