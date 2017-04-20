/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.modelos;

/**
 *
 * @author VaioDevelopment
 */
public class Derivado {
    
    private int idderivado;
    private String nombre;

    public Derivado() {
       
    }

    public Derivado(int idderivado) {
        this.idderivado = idderivado;
    }
        
    public Derivado(int idderivado, String nombre) {
        this.idderivado = idderivado;
        this.nombre = nombre;
    }

    public int getIdderivado() {
        return idderivado;
    }

    public void setIdderivado(int idderivado) {
        this.idderivado = idderivado;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Derivado{" + "idderivado=" + idderivado + ", nombre=" + nombre + '}';
    }    
}
