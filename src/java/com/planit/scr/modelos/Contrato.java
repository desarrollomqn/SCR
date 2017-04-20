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
 * @author VaioDevelopment
 */
@XmlRootElement
public class Contrato implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idcontrato;

    private String nombre;

    private Integer cib;

    private Integer car;

    private Integer cov;    

    private Tipo tipo;

    //Constructores
    public Contrato() {
        this.tipo = new Tipo();
        this.idcontrato = 0;
    }

    public Contrato(Integer idcontrato) {
        this.idcontrato = idcontrato;
    }

    public Contrato(Integer idcontrato, String nombre) {
        this.idcontrato = idcontrato;
        this.nombre = nombre;
    }

    public Contrato(Integer idcontrato, String nombre, Integer cib, Integer car, Integer cov, Tipo tipo) {
        this.idcontrato = idcontrato;
        this.nombre = nombre;
        this.cib = cib;
        this.car = car;
        this.cov = cov;        
        this.tipo = tipo;
    }

    //Getters & setters
    public Integer getIdcontrato() {
        return idcontrato;
    }

    public void setIdcontrato(Integer idcontrato) {
        this.idcontrato = idcontrato;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCib() {
        return cib;
    }

    public void setCib(Integer cib) {
        this.cib = cib;
    }

    public Integer getCar() {
        return car;
    }

    public void setCar(Integer car) {
        this.car = car;
    }

    public Integer getCov() {
        return cov;
    }

    public void setCov(Integer cov) {
        this.cov = cov;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

}
