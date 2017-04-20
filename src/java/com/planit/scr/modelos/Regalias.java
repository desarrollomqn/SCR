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
public class Regalias implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idregalias;
    
    private double porcmunicipio;

    private double porcregalias;

    private double depproductor;

    private double munproductor;

    private double munnoproductor;

    private double puertos;
    
    private Integer anio;
    
    private Integer mes;
    
    private double precio;
    
    private double regalias;
    
    private Campo campo;
    
    private Departamento departamento;
    
    private Municipio municipio;
    
    private Contrato contrato;
    
    private Produccion produccion;

    private double fondonacional;
    
    public Regalias() {
        this.campo = new Campo();
        this.departamento = new Departamento();
        this.municipio = new Municipio();
        this.produccion = new Produccion();
        this.contrato = new Contrato();
    }

    public Regalias(Integer idregalias) {
        this.idregalias = idregalias;
    }

    public Regalias(Integer idregalias, double porcmunicipio, double porcregalias, double depproductor, double munproductor, double munnoproductor, double puertos, double fondonacional) {
        this.idregalias = idregalias;
        this.porcmunicipio = porcmunicipio;
        this.porcregalias = porcregalias;
        this.depproductor = depproductor;
        this.munproductor = munproductor;
        this.munnoproductor = munnoproductor;
        this.puertos = puertos;
        this.fondonacional = fondonacional;               
    }

    public Regalias(Integer idregalias, double porcmunicipio, double porcregalias, double depproductor, double munproductor, double munnoproductor, double puertos, Integer anio, Integer mes, Double precio, Double regalias, Campo idcampo, Departamento iddepartamento, Municipio idmunicipio, Contrato idcontrato, Produccion idproduccion, double fondonacional) {
        this.idregalias = idregalias;
        this.porcmunicipio = porcmunicipio;
        this.porcregalias = porcregalias;
        this.depproductor = depproductor;
        this.munproductor = munproductor;
        this.munnoproductor = munnoproductor;
        this.puertos = puertos;
        this.anio = anio;
        this.mes = mes;
        this.precio = precio;
        this.regalias = regalias;
        this.campo = idcampo;
        this.departamento = iddepartamento;
        this.municipio = idmunicipio;
        this.contrato = idcontrato;
        this.produccion = idproduccion;
        this.fondonacional = fondonacional;
    }

    public Regalias(double porcmunicipio, double porcregalias, double depproductor, double munproductor, double munnoproductor, double puertos, Integer anio, Integer mes, Double precio, Double regalias, Campo idcampo, Departamento iddepartamento, Municipio idmunicipio, Contrato idcontrato, Produccion idproduccion, double fondonacional) {
        this.porcmunicipio = porcmunicipio;
        this.porcregalias = porcregalias;
        this.depproductor = depproductor;
        this.munproductor = munproductor;
        this.munnoproductor = munnoproductor;
        this.puertos = puertos;
        this.anio = anio;
        this.mes = mes;
        this.precio = precio;
        this.regalias = regalias;
        this.campo = idcampo;
        this.departamento = iddepartamento;
        this.municipio = idmunicipio;
        this.contrato = idcontrato;
        this.produccion = idproduccion;
        this.fondonacional = fondonacional;
    }

    public Integer getIdregalias() {
        return idregalias;
    }

    public void setIdregalias(Integer idregalias) {
        this.idregalias = idregalias;
    }

    public double getPorcmunicipio() {
        return porcmunicipio;
    }

    public void setPorcmunicipio(double porcmunicipio) {
        this.porcmunicipio = porcmunicipio;
    }

    public double getPorcregalias() {
        return porcregalias;
    }

    public void setPorcregalias(double porcregalias) {
        this.porcregalias = porcregalias;
    }

    public double getDepproductor() {
        return depproductor;
    }

    public void setDepproductor(double depproductor) {
        this.depproductor = depproductor;
    }

    public double getMunproductor() {
        return munproductor;
    }

    public void setMunproductor(double munproductor) {
        this.munproductor = munproductor;
    }

    public double getMunnoproductor() {
        return munnoproductor;
    }

    public void setMunnoproductor(double munnoproductor) {
        this.munnoproductor = munnoproductor;
    }

    public double getPuertos() {
        return puertos;
    }

    public void setPuertos(double puertos) {
        this.puertos = puertos;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getRegalias() {
        return regalias;
    }

    public void setRegalias(double regalias) {
        this.regalias = regalias;
    }

    public Campo getCampo() {
        return campo;
    }

    public void setCampo(Campo campo) {
        this.campo = campo;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public Produccion getProduccion() {
        return produccion;
    }

    public void setProduccion(Produccion produccion) {
        this.produccion = produccion;
    }

    public double getFondonacional() {
        return fondonacional;
    }

    public void setFondonacional(double fondonacional) {
        this.fondonacional = fondonacional;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    } 
      
    @Override
    public String toString() {
        return "com.planit.scr.modelos.Regalias[ idregalias=" + idregalias + " ]";
    }

}
