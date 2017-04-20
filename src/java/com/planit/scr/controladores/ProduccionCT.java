/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.controladores;

import com.planit.scr.dao.ProduccionDao;
import com.planit.scr.modelos.Produccion;
import javax.annotation.PostConstruct;

/**
 *
 * @author Desarrollo_Planit
 */
public class ProduccionCT {

    private Produccion produccion;

    public ProduccionCT() {
        produccion = new Produccion();
    }

    @PostConstruct
    public void init() {

    }

    public Produccion getProduccion() {
        return produccion;
    }

    public void setProduccion(Produccion produccion) {
        this.produccion = produccion;
    }
    
    

    //Metodos
    public void registrar(Produccion p) throws Exception {     
            
        ProduccionDao produccionDao = new ProduccionDao();
        produccionDao.registrarProduccion(p);
    }
}
