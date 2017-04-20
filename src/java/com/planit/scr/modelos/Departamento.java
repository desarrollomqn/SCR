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
public class Departamento implements Serializable {
   
    private Integer iddepartamento;
   
    private String nombre;
  
    public Departamento() {
        iddepartamento = 0;
    }

    public Departamento(Integer iddepartamento) {
        this.iddepartamento = iddepartamento;
    }

    public Departamento(Integer iddepartamento, String nombre) {
        this.iddepartamento = iddepartamento;
        this.nombre = nombre;
    }

    public Integer getIddepartamento() {
        return iddepartamento;
    }

    public void setIddepartamento(Integer iddepartamento) {
        this.iddepartamento = iddepartamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }      
}
