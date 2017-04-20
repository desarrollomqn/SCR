/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.modelos;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Desarrollo_Planit
 */
@XmlRootElement
public class Campo implements Serializable {

    private Integer idcampo;

    private String nombre;
    
    private double porcentaje;

    //Constructores
    public Campo() {       
        idcampo = 0;
    }

    public Campo(Integer idcampo) {
        this.idcampo = idcampo;
    }

    public Campo(Integer idcampo, String nombre) {
        this.idcampo = idcampo;
        this.nombre = nombre;
    }

    public Campo(Integer idcampo, String nombre, double porcentaje) {
        this.idcampo = idcampo;
        this.nombre = nombre;
        this.porcentaje = porcentaje;
    }

    //Getters & Setters
    public Integer getIdcampo() {
        return idcampo;
    }

    public void setIdcampo(Integer idcampo) {
        this.idcampo = idcampo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
   
    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }
    
    
}
