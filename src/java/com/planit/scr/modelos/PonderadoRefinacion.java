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
public class PonderadoRefinacion {
    
    private int idponderado;
    
    private Derivado derivado;
    
    private double produccion;
    
    private double rendimiento;
    
    private double nalbpd;
    
    private double exportbpd;
    
    private double precional;
    
    private double precioexpo;
    
    private double preciomedio;
    
    private double mediordto;
    
    private int trimestremes;
    
    private int anio;

    public PonderadoRefinacion() {
    }

    public PonderadoRefinacion(int idponderado, Derivado derivado, double produccion, double rendimiento, double nalbpd, double exportbpd, double precional, double precioexpo, double preciomedio, double mediordto, int trimestremes, int anio) {
        this.idponderado = idponderado;
        this.derivado = derivado;
        this.produccion = produccion;
        this.rendimiento = rendimiento;
        this.nalbpd = nalbpd;
        this.exportbpd = exportbpd;
        this.precional = precional;
        this.precioexpo = precioexpo;
        this.preciomedio = preciomedio;
        this.mediordto = mediordto;
        this.trimestremes = trimestremes;
        this.anio = anio;
    }

    public int getIdponderado() {
        return idponderado;
    }

    public void setIdponderado(int idponderado) {
        this.idponderado = idponderado;
    }

    public Derivado getDerivado() {
        return derivado;
    }

    public void setDerivado(Derivado derivado) {
        this.derivado = derivado;
    }

    public double getProduccion() {
        return produccion;
    }

    public void setProduccion(double produccion) {
        this.produccion = produccion;
    }

    public double getRendimiento() {
        return rendimiento;
    }

    public void setRendimiento(double rendimiento) {
        this.rendimiento = rendimiento;
    }

    public double getNalbpd() {
        return nalbpd;
    }

    public void setNalbpd(double nalbpd) {
        this.nalbpd = nalbpd;
    }

    public double getExportbpd() {
        return exportbpd;
    }

    public void setExportbpd(double exportbpd) {
        this.exportbpd = exportbpd;
    }

    public double getPrecional() {
        return precional;
    }

    public void setPrecional(double precional) {
        this.precional = precional;
    }

    public double getPrecioexpo() {
        return precioexpo;
    }

    public void setPrecioexpo(double precioexpo) {
        this.precioexpo = precioexpo;
    }

    public double getPreciomedio() {
        return preciomedio;
    }

    public void setPreciomedio(double preciomedio) {
        this.preciomedio = preciomedio;
    }

    public double getMediordto() {
        return mediordto;
    }

    public void setMediordto(double mediordto) {
        this.mediordto = mediordto;
    }

    public int getTrimestremes() {
        return trimestremes;
    }

    public void setTrimestremes(int trimestremes) {
        this.trimestremes = trimestremes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    @Override
    public String toString() {
        return "PonderadoRefinacion{" + "idponderado=" + idponderado + ", derivado=" + derivado + ", produccion=" + produccion + ", rendimiento=" + rendimiento + ", nalbpd=" + nalbpd + ", exportbpd=" + exportbpd + ", precional=" + precional + ", precioexpo=" + precioexpo + ", preciomedio=" + preciomedio + ", mediordto=" + mediordto + ", trimestremes=" + trimestremes + ", anio=" + anio + '}';
    }
    
    
    
}
