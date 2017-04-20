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
import com.planit.scr.modelos.Municipio;
import com.planit.scr.modelos.Pbl;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Desarrollo_Planit
 */
public class PblCT {

    private Pbl pbl;
    private List<Pbl> pbls;
    private Municipio municipio;
    private Valores valores;
    private int mes;

    private String nombreOperacion;
    private int operacion;

    public PblCT() {
        pbl = new Pbl();
        pbls = new ArrayList<>();
        municipio = new Municipio();
        valores = new Valores();

        nombreOperacion = "Registrar";
        operacion = 0;
    }

    public Pbl getPbl() {
        return pbl;
    }

    public void setPbl(Pbl pbl) {
        this.pbl = pbl;
    }

    public List<Pbl> getPbls() {
        return pbls;
    }

    public void setPbls(List<Pbl> pbls) {
        this.pbls = pbls;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public Valores getValores() {
        return valores;
    }

    public void setValores(Valores valores) {
        this.valores = valores;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
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

    //Metodos   
    public void consultarPbl() throws Exception {
        PblDao pblDao = new PblDao();
        ValoresDao valoresDao = new ValoresDao();
        PonderadosRefinacionDao ponderadosRefinacionDao = new PonderadosRefinacionDao();
        CalidadCrudoDao calidadCrudoDao = new CalidadCrudoDao();

        //Verifica el año de consulta para determinar la forma en que se registra el pbl
        if (pbl.getAnio() < 2012) {
            pbl.setTrimestreMes(pblDao.obtenerTrimestre(mes, pbl.getAnio()));
        }
        valores = valoresDao.consultarValores(pbl.getTrimestreMes(), pbl.getAnio());
        if (valores.getIdvalores() != 0) {
            //Trae los datos del pbl calculado
            pbls = pblDao.consultarPblSegunMunicipio(municipio, pbl);
            //Si no encuentra datos registrados los calcula
            if (pbls.isEmpty()) {
                if (!ponderadosRefinacionDao.ConsultarPonderado(pbl.getAnio(), pbl.getTrimestreMes()).isEmpty() && calidadCrudoDao.consultarCalidadCrudo(new CalidadCrudo(pbl.getTrimestreMes(), pbl.getAnio())).getIdCalidadcrudo() != 0) {
                    int r = pblDao.registrarPbl(valores);
                    if (r == 1) {
                        pbls = pblDao.consultarPblSegunMunicipio(municipio, pbl);
                    } else if (r == 0) {
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrio un error al momento de calcular el pbl consultado");
                        FacesContext.getCurrentInstance().addMessage(null, message);
                    }
                }else{
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No existen precios ponderados de refinacion ni exportacion registrados para este trimestre/mes y año");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }
            }
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "No existen deducibles de exportacion o refinacion registrados para calcular el pbl consultado");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }        
    }
}
