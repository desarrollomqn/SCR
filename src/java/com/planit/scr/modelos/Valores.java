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
public class Valores implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer idvalores;
    private Double v1;
    private Double v2;
    private Double vt;
    private Double ctmd;
    private Double cmt;
    private Double ctmc;
    private Double cr;
    private Double cce;
    private Double ctme;
    private Double ctc;
    private Integer trimestreMes;
    private Integer anio;

    public Valores() {
        this.idvalores = 0;
    }

    public Valores(Integer idvalores) {
        this.idvalores = idvalores;
    }

    public Valores(Integer idvalores, Double v1, Double v2, Double vt, Double ctmd, Double cmt, Double ctmc, Double cr, Double cce, Double ctme, Double ctc, Integer trimestreMes, Integer anio) {
        this.idvalores = idvalores;       
        this.v1 = v1;
        this.v2 = v2;
        this.vt = vt;
        this.ctmd = ctmd;
        this.cmt = cmt;
        this.ctmc = ctmc;
        this.cr = cr;
        this.cce = cce;
        this.ctme = ctme;
        this.ctc = ctc;
        this.trimestreMes = trimestreMes;
        this.anio = anio;
    }

    public Valores(Double v1, Double v2, Double vt, Double ctmd, Double cmt, Double ctmc, Double cr, Double cce, Double ctme, Double ctc, Integer trimestreMes, Integer anio) {
        this.v1 = v1;
        this.v2 = v2;
        this.vt = vt;
        this.ctmd = ctmd;
        this.cmt = cmt;
        this.ctmc = ctmc;
        this.cr = cr;
        this.cce = cce;
        this.ctme = ctme;
        this.ctc = ctc;
        this.trimestreMes = trimestreMes;
        this.anio = anio;
    }
        
    public Integer getIdvalores() {
        return idvalores;
    }

    public void setIdvalores(Integer idvalores) {
        this.idvalores = idvalores;
    } 

    public Double getV1() {
        return v1;
    }

    public void setV1(Double v1) {
        this.v1 = v1;
    }

    public Double getV2() {
        return v2;
    }

    public void setV2(Double v2) {
        this.v2 = v2;
    }

    public Double getVt() {
        return vt;
    }

    public void setVt(Double vt) {
        this.vt = vt;
    }

    public Double getCtmd() {
        return ctmd;
    }

    public void setCtmd(Double ctmd) {
        this.ctmd = ctmd;
    }

    public Double getCmt() {
        return cmt;
    }

    public void setCmt(Double cmt) {
        this.cmt = cmt;
    }

    public Double getCtmc() {
        return ctmc;
    }

    public void setCtmc(Double ctmc) {
        this.ctmc = ctmc;
    }

    public Double getCr() {
        return cr;
    }

    public void setCr(Double cr) {
        this.cr = cr;
    }

    public Double getCce() {
        return cce;
    }

    public void setCce(Double cce) {
        this.cce = cce;
    }

    public Double getCtme() {
        return ctme;
    }

    public void setCtme(Double ctme) {
        this.ctme = ctme;
    }

    public Double getCtc() {
        return ctc;
    }

    public void setCtc(Double ctc) {
        this.ctc = ctc;
    }

    public Integer getTrimestreMes() {
        return trimestreMes;
    }

    public void setTrimestreMes(Integer trimestreMes) {
        this.trimestreMes = trimestreMes;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    @Override
    public String toString() {
        return "com.planit.scr.modelos.Valores[ idvalores=" + idvalores + " ]";
    }
    
}
