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
public class Produccion implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idproduccion;

    private Double produccionhdia;

    private Double produccionhmes;

    private Double producciongdia;

    private Double producciongmes;

    private Double producciontotaldia;

    private Integer mes;

    private Integer anio;

    private Double producciontotalmes;

    private Campo campo;

    private Contrato contrato;

    private Municipio municipio;

    public Produccion() {
        this.idproduccion = 0;
        this.produccionhdia = 0.0;
        this.produccionhmes = 0.0;
        this.producciongdia = 0.0;
        this.producciongmes = 0.0;
        this.producciontotaldia = 0.0;
        this.mes = 0;
        this.anio = 0;
        this.producciontotalmes = 0.0;
        this.campo = new Campo();
        this.contrato = new Contrato();
        this.municipio = new Municipio();
    }

    public Produccion(Integer idproduccion) {
        this.idproduccion = idproduccion;
        this.produccionhdia = 0.0;
        this.produccionhmes = 0.0;
        this.producciongdia = 0.0;
        this.producciongmes = 0.0;
        this.producciontotaldia = 0.0;
        this.mes = 0;
        this.anio = 0;
        this.producciontotalmes = 0.0;
        this.campo = new Campo();
        this.contrato = new Contrato();
        this.municipio = new Municipio();
    }

    public Produccion(Integer idproduccion, Double produccionhdia, Double produccionhmes, Double producciongdia, Double producciongmes, Double producciontotaldia, Integer mes, Integer anio, Double producciontotalmes, Campo campo, Contrato contrato, Municipio municipio) {
        this.idproduccion = idproduccion;
        this.produccionhdia = produccionhdia;
        this.produccionhmes = produccionhmes;
        this.producciongdia = producciongdia;
        this.producciongmes = producciongmes;
        this.producciontotaldia = producciontotaldia;
        this.mes = mes;
        this.anio = anio;
        this.producciontotalmes = producciontotalmes;
        this.campo = campo;
        this.contrato = contrato;
        this.municipio = municipio;
    }

    public Produccion(Integer idproduccion, Double produccionhdia, Double producciongdia, Double producciontotaldia, Double producciontotalmes) {
        this.idproduccion = idproduccion;
        this.produccionhdia = produccionhdia;
        this.producciongdia = producciongdia;
        this.producciontotaldia = producciontotaldia;
        this.producciontotalmes = producciontotalmes;
    }
    
    

    public Produccion(Double produccionhdia, Double produccionhmes, Double producciongdia, Double producciongmes, Double producciontotaldia, Integer mes, Integer anio, Double producciontotalmes, Campo campo, Contrato contrato, Municipio municipio) {
        this.produccionhdia = produccionhdia;
        this.produccionhmes = produccionhmes;
        this.producciongdia = producciongdia;
        this.producciongmes = producciongmes;
        this.producciontotaldia = producciontotaldia;
        this.mes = mes;
        this.anio = anio;
        this.producciontotalmes = producciontotalmes;
        this.campo = campo;
        this.contrato = contrato;
        this.municipio = municipio;

    }

    public Integer getIdproduccion() {
        return idproduccion;
    }

    public void setIdproduccion(Integer idproduccion) {
        this.idproduccion = idproduccion;
    }

    public Double getProduccionhdia() {
        return produccionhdia;
    }

    public void setProduccionhdia(Double produccionhdia) {
        this.produccionhdia = produccionhdia;
    }

    public Double getProduccionhmes() {
        return produccionhmes;
    }

    public void setProduccionhmes(Double produccionhmes) {
        this.produccionhmes = produccionhmes;
    }

    public Double getProducciongdia() {
        return producciongdia;
    }

    public void setProducciongdia(Double producciongdia) {
        this.producciongdia = producciongdia;
    }

    public Double getProducciongmes() {
        return producciongmes;
    }

    public void setProducciongmes(Double producciongmes) {
        this.producciongmes = producciongmes;
    }

    public Double getProducciontotaldia() {
        return producciontotaldia;
    }

    public void setProducciontotaldia(Double producciontotaldia) {
        this.producciontotaldia = producciontotaldia;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Double getProducciontotalmes() {
        return producciontotalmes;
    }

    public void setProducciontotalmes(Double producciontotalmes) {
        this.producciontotalmes = producciontotalmes;
    }

    public Campo getCampo() {
        return campo;
    }

    public void setCampo(Campo campo) {
        this.campo = campo;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    @Override
    public String toString() {
        return "com.planit.scr.modelos.Produccion[ idproduccion=" + idproduccion + " ]";
    }

}
