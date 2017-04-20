/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.modelos;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Desarrollo_Planit
 */

@XmlRootElement
public class Trm implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer idtrm;
    private Date fecha;
    private double valor;

    public Trm() {
        fecha = new Date();
    }

    public Trm(Integer idtrm) {
        this.idtrm = idtrm;
    }

    public Trm(Integer idtrm, Date fecha) {
        this.idtrm = idtrm;
        this.fecha = fecha;
    }

    public Trm(Integer idtrm, Date fecha, double valor) {
        this.idtrm = idtrm;
        this.fecha = fecha;
        this.valor = valor;
    }

    public Trm(Date fecha) {
        this.fecha = fecha;
    }

       
    public Integer getIdtrm() {
        return idtrm;
    }

    public void setIdtrm(Integer idtrm) {
        this.idtrm = idtrm;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
   
    
     
    @Override
    public String toString() {
        return "com.planit.scr.modelos.Trm[ idtrm=" + idtrm + " ]";
    }
    
}
