/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.controladores;

import com.planit.scr.dao.MunicipiosDao;
import com.planit.scr.modelos.Departamento;
import com.planit.scr.modelos.Municipio;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;

/**
 *
 * @author Desarrollo_Planit
 */
public class MunicipioCT {

    private Municipio municipio;
    private List<Municipio> municipios;
    private List<Municipio> municipiosFiltrados;
   
    public MunicipioCT() {
        municipio = new Municipio();
        municipios = new ArrayList<>();
        municipiosFiltrados = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        MunicipiosDao municipioDao = new MunicipiosDao();
        try {
            municipios = municipioDao.consultarMunicipios();
        } catch (Exception ex) {
            Logger.getLogger(MunicipioCT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public List<Municipio> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(List<Municipio> municipios) {
        this.municipios = municipios;
    }

    public List<Municipio> getMunicipiosFiltrados() {
        return municipiosFiltrados;
    }

    public void setMunicipiosFiltrados(List<Municipio> municipiosFiltrados) {
        this.municipiosFiltrados = municipiosFiltrados;
    }

    //Metodos 
    public void registrar() throws Exception {
        MunicipiosDao municipioDao = new MunicipiosDao();
        municipioDao.registrarMunicipio(municipio);
        municipio = new Municipio();
        municipios = municipioDao.consultarMunicipios();
    }

    public void cambioDepartamentos(Departamento departamento) throws Exception {
        MunicipiosDao municipioDao = new MunicipiosDao();
        if (departamento.getIddepartamento() != 0) {
            municipiosFiltrados = municipioDao.consultarMunicipiosSegunDepartamento(departamento);
        } else {
            municipiosFiltrados = new ArrayList<>();
        }
    }

}
