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
public class CampoCompleto {
    
    private Integer idcampo;

    private String nombre;
    
    private Contrato contrato;
    
    private double porcentaje;

    //Constructores
    public CampoCompleto() {       
        idcampo = 0;
        contrato = new Contrato();
    }

    public CampoCompleto(Integer idcampo) {
        this.idcampo = idcampo;
    }

    public CampoCompleto(Integer idcampo, String nombre) {
        this.idcampo = idcampo;
        this.nombre = nombre;
    }

    public CampoCompleto(Integer idcampo, String nombre, Contrato contrato , double porcentaje) {
        this.idcampo = idcampo;
        this.nombre = nombre;
        this.contrato = contrato;
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

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }
    
    
    
}
