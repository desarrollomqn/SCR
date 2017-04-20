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
public class Municipio implements Serializable {
    
    private Integer idmunicipio;
    
    private String nombre;
    
    private Departamento departamento;
   

    public Municipio() {
        idmunicipio = 0;
        departamento = new Departamento();
    }

    public Municipio(Integer idmunicipio) {
        this.idmunicipio = idmunicipio;
    }

    public Municipio(Integer idmunicipio, String nombre) {
        this.idmunicipio = idmunicipio;
        this.nombre = nombre;
    }

    public Municipio(Integer idmunicipio, String nombre, Departamento departamento) {
        this.idmunicipio = idmunicipio;
        this.nombre = nombre;
        this.departamento = departamento;
    }   
    
    public Integer getIdmunicipio() {
        return idmunicipio;
    }

    public void setIdmunicipio(Integer idmunicipio) {
        this.idmunicipio = idmunicipio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }    
}
