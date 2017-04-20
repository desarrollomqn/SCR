/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.modelos;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author VaioDevelopment
 */
@XmlRootElement
public class Pbl implements Serializable {

    private Integer idpbl;

    private double ctc;

    private double ct1;

    private double cce;

    private double ct2;

    private int trimestreMes;
    
    private Double prc;
    
    private Double refinacion;
    
    private Double exportacion;

    private Integer anio;
   
    private Contrato contrato;

    public Pbl() {
        this.idpbl = 0;
    }

    public Pbl(Integer idpbl) {
        this.idpbl = idpbl;
    }

    public Pbl(Integer idpbl, double ctc, double ct1, double cce, double ct2, int trimestreMes) {
        this.idpbl = idpbl;
        this.ctc = ctc;
        this.ct1 = ct1;
        this.cce = cce;
        this.ct2 = ct2;
        this.trimestreMes = trimestreMes;
    }

    public Pbl(Integer idpbl, double ctc, double ct1, double cce, double ct2, int trimestreMes, Double prc, Double refinacion, Double exportacion, Integer anio, Contrato contrato) {
        this.idpbl = idpbl;
        this.ctc = ctc;
        this.ct1 = ct1;
        this.cce = cce;
        this.ct2 = ct2;
        this.trimestreMes = trimestreMes;
        this.prc = prc;
        this.refinacion = refinacion;
        this.exportacion = exportacion;
        this.anio = anio;
        this.contrato = contrato;
    }

    public Pbl(double ctc, double ct1, double cce, double ct2, int trimestreMes, Double prc, Double refinacion, Double exportacion, Integer anio, Contrato contrato) {
        this.ctc = ctc;
        this.ct1 = ct1;
        this.cce = cce;
        this.ct2 = ct2;
        this.trimestreMes = trimestreMes;
        this.prc = prc;
        this.refinacion = refinacion;
        this.exportacion = exportacion;
        this.anio = anio;
        this.contrato = contrato;
    }

    public Integer getIdpbl() {
        return idpbl;
    }

    public void setIdpbl(Integer idpbl) {
        this.idpbl = idpbl;
    }

    public double getCtc() {
        return ctc;
    }

    public void setCtc(double ctc) {
        this.ctc = ctc;
    }

    public double getCt1() {
        return ct1;
    }

    public void setCt1(double ct1) {
        this.ct1 = ct1;
    }

    public double getCce() {
        return cce;
    }

    public void setCce(double cce) {
        this.cce = cce;
    }

    public double getCt2() {
        return ct2;
    }

    public void setCt2(double ct2) {
        this.ct2 = ct2;
    }

    public int getTrimestreMes() {
        return trimestreMes;
    }

    public void setTrimestreMes(int trimestreMes) {
        this.trimestreMes = trimestreMes;
    }

    public Double getPrc() {
        return prc;
    }

    public void setPrc(Double prc) {
        this.prc = prc;
    }

    public Double getRefinacion() {
        return refinacion;
    }

    public void setRefinacion(Double refinacion) {
        this.refinacion = refinacion;
    }

    public Double getExportacion() {
        return exportacion;
    }

    public void setExportacion(Double exportacion) {
        this.exportacion = exportacion;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    
}
