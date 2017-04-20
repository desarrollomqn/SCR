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
public class CalidadCrudo {
     
    private int idCalidadcrudo;
    private double calRefApiExportacion;
    private double calRefApiRefinado;
    private double calRefAzufreExportacion;
    private double calRefAzufreRefinado;
    private double corrRefApiExportacion;
    private double corrRefApiRefinado;
    private double corrRefAzufreExportacion;
    private double corrRefAzufreRefinado;
    private double precioReferenciaExportacion;
    private double precioReferenciaRefinado;
    private int trimestre_mes;
    private int anio;

    public CalidadCrudo() {
        idCalidadcrudo = 0;
    }

    public CalidadCrudo(int trimestre_mes, int anio) {
        this.trimestre_mes = trimestre_mes;
        this.anio = anio;
        this.idCalidadcrudo = 0;
    }
    
    

    public CalidadCrudo(int idCalidadcrudo, double calRefApiExportacion, double calRefApiRefinado, double calRefAzufreExportacion, double calRefAzufreRefinado, double corrRefApiExportacion, double corrRefApiRefinado, double corrRefAzufreExportacion, double corrRefAzufreRefinado, double precioReferenciaExportacion, double precioReferenciaRefinado, int trimestre_mes, int anio) {
        this.idCalidadcrudo = idCalidadcrudo;
        this.calRefApiExportacion = calRefApiExportacion;
        this.calRefApiRefinado = calRefApiRefinado;
        this.calRefAzufreExportacion = calRefAzufreExportacion;
        this.calRefAzufreRefinado = calRefAzufreRefinado;
        this.corrRefApiExportacion = corrRefApiExportacion;
        this.corrRefApiRefinado = corrRefApiRefinado;
        this.corrRefAzufreExportacion = corrRefAzufreExportacion;
        this.corrRefAzufreRefinado = corrRefAzufreRefinado;
        this.precioReferenciaExportacion = precioReferenciaExportacion;
        this.precioReferenciaRefinado = precioReferenciaRefinado;
        this.trimestre_mes = trimestre_mes;
        this.anio = anio;
    }

    public int getIdCalidadcrudo() {
        return idCalidadcrudo;
    }

    public void setIdCalidadcrudo(int idCalidadcrudo) {
        this.idCalidadcrudo = idCalidadcrudo;
    }

    public double getCalRefApiExportacion() {
        return calRefApiExportacion;
    }

    public void setCalRefApiExportacion(double calRefApiExportacion) {
        this.calRefApiExportacion = calRefApiExportacion;
    }

    public double getCalRefApiRefinado() {
        return calRefApiRefinado;
    }

    public void setCalRefApiRefinado(double calRefApiRefinado) {
        this.calRefApiRefinado = calRefApiRefinado;
    }

    public double getCalRefAzufreExportacion() {
        return calRefAzufreExportacion;
    }

    public void setCalRefAzufreExportacion(double calRefAzufreExportacion) {
        this.calRefAzufreExportacion = calRefAzufreExportacion;
    }

    public double getCalRefAzufreRefinado() {
        return calRefAzufreRefinado;
    }

    public void setCalRefAzufreRefinado(double calRefAzufreRefinado) {
        this.calRefAzufreRefinado = calRefAzufreRefinado;
    }

    public double getCorrRefApiExportacion() {
        return corrRefApiExportacion;
    }

    public void setCorrRefApiExportacion(double corrRefApiExportacion) {
        this.corrRefApiExportacion = corrRefApiExportacion;
    }

    public double getCorrRefApiRefinado() {
        return corrRefApiRefinado;
    }

    public void setCorrRefApiRefinado(double corrRefApiRefinado) {
        this.corrRefApiRefinado = corrRefApiRefinado;
    }

    public double getCorrRefAzufreExportacion() {
        return corrRefAzufreExportacion;
    }

    public void setCorrRefAzufreExportacion(double corrRefAzufreExportacion) {
        this.corrRefAzufreExportacion = corrRefAzufreExportacion;
    }

    public double getCorrRefAzufreRefinado() {
        return corrRefAzufreRefinado;
    }

    public void setCorrRefAzufreRefinado(double corrRefAzufreRefinado) {
        this.corrRefAzufreRefinado = corrRefAzufreRefinado;
    }

    public double getPrecioReferenciaExportacion() {
        return precioReferenciaExportacion;
    }

    public void setPrecioReferenciaExportacion(double precioReferenciaExportacion) {
        this.precioReferenciaExportacion = precioReferenciaExportacion;
    }

    public double getPrecioReferenciaRefinado() {
        return precioReferenciaRefinado;
    }

    public void setPrecioReferenciaRefinado(double precioReferenciaRefinado) {
        this.precioReferenciaRefinado = precioReferenciaRefinado;
    }

    public int getTrimestre_mes() {
        return trimestre_mes;
    }

    public void setTrimestre_mes(int trimestre_mes) {
        this.trimestre_mes = trimestre_mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    @Override
    public String toString() {
        return "CalidadCrudo{" + "idCalidadcrudo=" + idCalidadcrudo + ", calRefApiExportacion=" + calRefApiExportacion + ", calRefApiRefinado=" + calRefApiRefinado + ", calRefAzufreExportacion=" + calRefAzufreExportacion + ", calRefAzufreRefinado=" + calRefAzufreRefinado + ", corrRefApiExportacion=" + corrRefApiExportacion + ", corrRefApiRefinado=" + corrRefApiRefinado + ", corrRefAzufreExportacion=" + corrRefAzufreExportacion + ", corrRefAzufreRefinado=" + corrRefAzufreRefinado + ", precioReferenciaExportacion=" + precioReferenciaExportacion + ", precioReferenciaRefinado=" + precioReferenciaRefinado + ", trimestre_mes=" + trimestre_mes + ", anio=" + anio + '}';
    }
    
    
}
