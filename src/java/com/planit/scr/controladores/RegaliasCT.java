/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.controladores;

import com.planit.scr.dao.CamposDao;
import com.planit.scr.dao.ContratosDao;
import com.planit.scr.dao.DepartamentosDao;
import com.planit.scr.dao.MunicipiosDao;
import com.planit.scr.dao.PblDao;
import com.planit.scr.dao.ProduccionDao;
import com.planit.scr.dao.RegaliasDao;
import com.planit.scr.dao.TrmDao;
import com.planit.scr.modelos.Campo;
import com.planit.scr.modelos.CampoCompleto;
import com.planit.scr.modelos.Municipio;
import com.planit.scr.modelos.Pbl;
import com.planit.scr.modelos.Regalias;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Desarrollo_Planit
 */
public class RegaliasCT {

    private Pbl pbl;
    private Municipio municipio;
    private Regalias regalia;

    private List<Regalias> regalias;
    private int mes;
    private int anio;

    DepartamentosDao departamentosDao = new DepartamentosDao();
    MunicipiosDao municipiosDao = new MunicipiosDao();
    CamposDao campoDao = new CamposDao();
    ProduccionDao produccionDao = new ProduccionDao();
    ContratosDao contratosDao = new ContratosDao();
    RegaliasDao regaliasDao = new RegaliasDao();

    public RegaliasCT() {

        pbl = new Pbl();
        municipio = new Municipio();
        regalias = new ArrayList<>();
        regalia = new Regalias();
    }

    public Pbl getPbl() {
        return pbl;
    }

    public void setPbl(Pbl pbl) {
        this.pbl = pbl;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public List<Regalias> getRegalias() {
        return regalias;
    }

    public void setRegalias(List<Regalias> regalias) {
        this.regalias = regalias;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public Regalias getRegalia() {
        return regalia;
    }

    public void setRegalia(Regalias regalia) {
        this.regalia = regalia;
    }

    //metodos 
    public void consultarCamposSegunMunicipio() throws Exception {
        CamposDao camposDao = new CamposDao();
        List<CampoCompleto> campos = new ArrayList<>();
        regalias = new ArrayList<>();
        if (!produccionDao.verificarRegistroProduccionMunicipio(municipio, anio, mes)) {            
            campos = camposDao.consultarCamposSegunMunicipio(municipio);
            if (!campos.isEmpty()) {
                for (int i = 0; i < campos.size(); i++) {
                    Campo c = new Campo(campos.get(i).getIdcampo(), campos.get(i).getNombre(), campos.get(i).getPorcentaje());

                    Regalias reg = new Regalias();
                    reg.setContrato(campos.get(i).getContrato());
                    reg.setCampo(c);
                    reg.setMunicipio(municipio);
                    reg.setDepartamento(municipio.getDepartamento());
                    reg.setAnio(anio);
                    reg.setMes(mes);
                    reg.getProduccion().setAnio(anio);
                    reg.getProduccion().setMes(mes);
                    reg.getProduccion().setCampo(c);
                    reg.getProduccion().setMunicipio(municipio);
                    reg.getProduccion().setContrato(campos.get(i).getContrato());
                    regalias.add(reg);
                }
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "No existen campos registrados para el municipio");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } else {
            //calculoRegaliasRapido();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Ya ha sido registrada la produccion para este municipio en este mes y año");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public String calcularRegalias() throws Exception {
        String ruta = "";
        FacesMessage message = new FacesMessage();

        Calendar cal = new GregorianCalendar(anio, mes - 1, 1);
        int diasMes = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int i = 0; i < regalias.size(); i++) {
            //Calculamos todos los valores relacionados a la produccion
            regalias.get(i).getProduccion().setProduccionhmes(regalias.get(i).getProduccion().getProduccionhdia() * diasMes);
            regalias.get(i).getProduccion().setProducciongmes(regalias.get(i).getProduccion().getProducciongdia() * diasMes);
            regalias.get(i).getProduccion().setProducciontotaldia(regalias.get(i).getProduccion().getProduccionhdia() + produccionDao.convertirABarrilesEquivalentes(regalias.get(i).getProduccion().getProducciongdia()));
            regalias.get(i).getProduccion().setProducciontotalmes(regalias.get(i).getProduccion().getProducciontotaldia() * diasMes);
            produccionDao.registrarProduccion(regalias.get(i).getProduccion());
        }
       
        for (int i = 0; i < regalias.size(); i++) {
            regalias.get(i).setProduccion(produccionDao.consultarProduccionCampoContrato(regalias.get(i).getProduccion()));
        }        
         
        double bpdm = promedioDiarioMensual(regalias);
        TrmDao trmDao = new TrmDao();
        double trm = trmDao.consultarPromedioMensualTrm(mes, anio);
        
        for (int i = 0; i < regalias.size(); i++) {
            //Obtenemos el porcentaje correspondiente de regalias segun el tipo de contrato
            double porcentaje = regalias.get(i).getCampo().getPorcentaje();
            regalias.get(i).setPorcregalias(porcentaje);

            //Obtenemos el porcentaje al que equivale la produccion del campo con respecto al total del municipio
            double total = totalProdMesMunicipio(regalias);
            regalias.get(i).setPorcmunicipio((regalias.get(i).getProduccion().getProducciontotalmes() * 100) / total);

            //Obtenemos pbl correspondiente al mes seleccionado
            PblDao pblDao = new PblDao();
            pbl = pblDao.consultarPblSegunContrato(regalias.get(i).getContrato(),
                    pblDao.obtenerTrimestre(regalias.get(i).getMes(), regalias.get(i).getAnio()), regalias.get(i).getAnio());

            if (pbl.getIdpbl() != 0) {
                regalias.get(i).setPrecio(pbl.getPrc());//Calculamos 

                if (trm != 0) {
                    regalias.get(i).setRegalias(regalias.get(i).getProduccion().getProducciontotalmes() * regalias.get(i).getPrecio() * regalias.get(i).getPorcregalias() * trm);

                    //Hacemos division de regalias
                    if (bpdm <= 10000) {
                        Double valor = (regalias.get(i).getRegalias() * 52) / 100;
                        regalias.get(i).setDepproductor(valor);
                        valor = (regalias.get(i).getRegalias() * 32) / 100;
                        regalias.get(i).setMunproductor(valor);
                        valor = (regalias.get(i).getRegalias() * 8) / 100;
                        regalias.get(i).setPuertos(valor);
                        regalias.get(i).setFondonacional(valor);
                        regalias.get(i).setMunnoproductor(0.0);

                    } else if (bpdm > 10000 && bpdm <= 20000) {

                        double porcentaje1 = ((10000 * 100) / bpdm) / 100;

                        double valorA = (((regalias.get(i).getRegalias() * porcentaje1) * 52) / 100);
                        double valorB = (((regalias.get(i).getRegalias() - (regalias.get(i).getRegalias() * porcentaje1)) * 47.5) / 100);
                        regalias.get(i).setDepproductor(valorA + valorB);

                        valorA = (((regalias.get(i).getRegalias() * porcentaje1) * 32) / 100);
                        valorB = (((regalias.get(i).getRegalias() - (regalias.get(i).getRegalias() * porcentaje1)) * 25) / 100);
                        regalias.get(i).setMunproductor(valorA + valorB);

                        //Calculo de participacion del municipio
//                            double excedente = (valorA + valorB) - (valorA + (valorB * (10 / 100)));                            
//                            double fnr = (excedente) * (60 / 100);
//                            regalias.get(i).setMunnoproductor(excedente - fnr);
//                            //fin panticipacion                  
                        valorA = (((regalias.get(i).getRegalias() * porcentaje1) * 8) / 100);
                        valorB = (((regalias.get(i).getRegalias() - (regalias.get(i).getRegalias() * porcentaje1)) * 8) / 100);
                        regalias.get(i).setPuertos(valorA + valorB);

                        valorB = (((regalias.get(i).getRegalias() - (regalias.get(i).getRegalias() * porcentaje1)) * 19.5) / 100);
                        regalias.get(i).setFondonacional(valorA + valorB);
                        regalias.get(i).setMunnoproductor(0.0);

                    } else if (bpdm > 20000 && bpdm <= 50000) {

                        double porcentaje1 = ((10000 * 100) / bpdm) / 100;
                        double porcentaje2 = ((20000 * 100) / bpdm) / 100;
                        double excedente = 0;

                        double valorA = (((regalias.get(i).getRegalias() * porcentaje1) * 52) / 100);
                        double valorB = ((((regalias.get(i).getRegalias() * porcentaje2) - (regalias.get(i).getRegalias() * porcentaje1)) * 47.5) / 100);
                        double valorC = (((regalias.get(i).getRegalias() - (regalias.get(i).getRegalias() * porcentaje2)) * 47.5) / 100);
                        regalias.get(i).setDepproductor(valorA + valorB + valorC);

                        valorA = (((regalias.get(i).getRegalias() * porcentaje1) * 32) / 100);
                        valorB = ((((regalias.get(i).getRegalias() * porcentaje2) - (regalias.get(i).getRegalias() * porcentaje1)) * 25) / 100);
                        valorC = (((regalias.get(i).getRegalias() - (regalias.get(i).getRegalias() * porcentaje2)) * 12.5) / 100);
                        regalias.get(i).setMunproductor(valorA + valorB + valorC);
                            //Calculo de participacion del municipio
//                            excedente = (valorA + valorB + valorC) - (valorA + ((valorB + valorC) * (10 / 100)));
//                            
//                            double fnr = excedente * (60 / 100);
//                            regalias.get(i).setMunnoproductor(excedente - fnr);
                        //fin panticipacion                        

                        valorA = (((regalias.get(i).getRegalias() * porcentaje1) * 8) / 100);
                        valorB = ((((regalias.get(i).getRegalias() * porcentaje2) - (regalias.get(i).getRegalias() * porcentaje1)) * 8) / 100);
                        valorC = (((regalias.get(i).getRegalias() - (regalias.get(i).getRegalias() * porcentaje2)) * 8) / 100);
                        regalias.get(i).setPuertos(valorA + valorB + valorC);

                        valorB = ((((regalias.get(i).getRegalias() * porcentaje2) - (regalias.get(i).getRegalias() * porcentaje1)) * 19.5) / 100);
                        valorC = (((regalias.get(i).getRegalias() - (regalias.get(i).getRegalias() * porcentaje2)) * 32) / 100);
                        regalias.get(i).setFondonacional(valorA + valorB + valorC);
                        regalias.get(i).setMunnoproductor(0.0);
                    }
                    if (regalias.get(i).getProduccion().getIdproduccion() != 0) {
                        int r = regaliasDao.registrarRegalias(regalias.get(i));
                        if (r == 0) {
                            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Ocurrio un error al registrar la distribucion de regalias");
                        }
                    }
                } else {
                    message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Ocurrio un error al consultar el trm del mes respectivo");
                }
            } else {
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "No existen valores registrados de pbl para el mes y año seleccionado");
            }
        }
        consultarRegalias();
        FacesContext.getCurrentInstance().addMessage(null, message);
        return ruta;
    }

    public void calculoRegaliasRapido() throws Exception {
        List<CampoCompleto> campos = new ArrayList<>();
        campos = new ArrayList<>();
        campos = campoDao.consultarCamposSegunMunicipio(municipio);
        FacesMessage message = new FacesMessage();

        if (!campos.isEmpty()) {
            for (int i = 0; i < campos.size(); i++) {
                Campo c = new Campo(campos.get(i).getIdcampo(), campos.get(i).getNombre(), campos.get(i).getPorcentaje());

                Regalias reg = new Regalias();
                reg.setContrato(campos.get(i).getContrato());
                reg.setCampo(c);
                reg.setMunicipio(municipio);
                reg.setDepartamento(municipio.getDepartamento());
                reg.setAnio(anio);
                reg.setMes(mes);
                reg.getProduccion().setAnio(anio);
                reg.getProduccion().setMes(mes);
                reg.getProduccion().setCampo(c);
                reg.getProduccion().setMunicipio(municipio);
                reg.getProduccion().setContrato(campos.get(i).getContrato());
                regalias.add(reg);
            }

            double bpdm = promedioDiarioMensual(regalias);
            TrmDao trmDao = new TrmDao();
            double trm = trmDao.consultarPromedioMensualTrm(mes, anio);

            for (int i = 0; i < regalias.size(); i++) {
                regalias.get(i).setProduccion(produccionDao.consultarProduccionCampoContrato(regalias.get(i).getProduccion()));
            }

            for (int i = 0; i < regalias.size(); i++) {
                //Obtenemos el porcentaje correspondiente de regalias segun el tipo de contrato
                double porcentaje = regalias.get(i).getCampo().getPorcentaje();
                regalias.get(i).setPorcregalias(porcentaje);

                //Obtenemos el porcentaje al que equivale la produccion del campo con respecto al total del municipio
                double total = totalProdMesMunicipio(regalias);
                regalias.get(i).setPorcmunicipio((regalias.get(i).getProduccion().getProducciontotalmes() * 100) / total);

                //Obtenemos pbl correspondiente al mes seleccionado
                PblDao pblDao = new PblDao();
                pbl = pblDao.consultarPblSegunContrato(regalias.get(i).getContrato(),
                        pblDao.obtenerTrimestre(regalias.get(i).getMes(), regalias.get(i).getAnio()), regalias.get(i).getAnio());

                if (pbl.getIdpbl() != 0) {
                    regalias.get(i).setPrecio(pbl.getPrc());//Calculamos 

                    if (trm != 0) {
                        regalias.get(i).setRegalias(regalias.get(i).getProduccion().getProducciontotalmes() * regalias.get(i).getPrecio() * regalias.get(i).getPorcregalias() * trm);

                        //Hacemos division de regalias
                        if (bpdm <= 10000) {
                            Double valor = (regalias.get(i).getRegalias() * 52) / 100;
                            regalias.get(i).setDepproductor(valor);
                            valor = (regalias.get(i).getRegalias() * 32) / 100;
                            regalias.get(i).setMunproductor(valor);
                            valor = (regalias.get(i).getRegalias() * 8) / 100;
                            regalias.get(i).setPuertos(valor);
                            regalias.get(i).setFondonacional(valor);
                            regalias.get(i).setMunnoproductor(0.0);

                        } else if (bpdm > 10000 && bpdm <= 20000) {

                            double porcentaje1 = ((10000 * 100) / bpdm) / 100;

                            double valorA = (((regalias.get(i).getRegalias() * porcentaje1) * 52) / 100);
                            double valorB = (((regalias.get(i).getRegalias() - (regalias.get(i).getRegalias() * porcentaje1)) * 47.5) / 100);
                            regalias.get(i).setDepproductor(valorA + valorB);

                            valorA = (((regalias.get(i).getRegalias() * porcentaje1) * 32) / 100);
                            valorB = (((regalias.get(i).getRegalias() - (regalias.get(i).getRegalias() * porcentaje1)) * 25) / 100);
                            regalias.get(i).setMunproductor(valorA + valorB);

                            //Calculo de participacion del municipio
//                            double excedente = (valorA + valorB) - (valorA + (valorB * (10 / 100)));                            
//                            double fnr = (excedente) * (60 / 100);
//                            regalias.get(i).setMunnoproductor(excedente - fnr);
//                            //fin panticipacion                  
                            valorA = (((regalias.get(i).getRegalias() * porcentaje1) * 8) / 100);
                            valorB = (((regalias.get(i).getRegalias() - (regalias.get(i).getRegalias() * porcentaje1)) * 8) / 100);
                            regalias.get(i).setPuertos(valorA + valorB);

                            valorB = (((regalias.get(i).getRegalias() - (regalias.get(i).getRegalias() * porcentaje1)) * 19.5) / 100);
                            regalias.get(i).setFondonacional(valorA + valorB);
                            regalias.get(i).setMunnoproductor(0.0);

                        } else if (bpdm > 20000 && bpdm <= 50000) {

                            double porcentaje1 = ((10000 * 100) / bpdm) / 100;
                            double porcentaje2 = ((20000 * 100) / bpdm) / 100;
                            double excedente = 0;

                            double valorA = (((regalias.get(i).getRegalias() * porcentaje1) * 52) / 100);
                            double valorB = ((((regalias.get(i).getRegalias() * porcentaje2) - (regalias.get(i).getRegalias() * porcentaje1)) * 47.5) / 100);
                            double valorC = (((regalias.get(i).getRegalias() - (regalias.get(i).getRegalias() * porcentaje2)) * 47.5) / 100);
                            regalias.get(i).setDepproductor(valorA + valorB + valorC);

                            valorA = (((regalias.get(i).getRegalias() * porcentaje1) * 32) / 100);
                            valorB = ((((regalias.get(i).getRegalias() * porcentaje2) - (regalias.get(i).getRegalias() * porcentaje1)) * 25) / 100);
                            valorC = (((regalias.get(i).getRegalias() - (regalias.get(i).getRegalias() * porcentaje2)) * 12.5) / 100);
                            regalias.get(i).setMunproductor(valorA + valorB + valorC);
                            //Calculo de participacion del municipio
//                            excedente = (valorA + valorB + valorC) - (valorA + ((valorB + valorC) * (10 / 100)));
//                            
//                            double fnr = excedente * (60 / 100);
//                            regalias.get(i).setMunnoproductor(excedente - fnr);
                            //fin panticipacion                        

                            valorA = (((regalias.get(i).getRegalias() * porcentaje1) * 8) / 100);
                            valorB = ((((regalias.get(i).getRegalias() * porcentaje2) - (regalias.get(i).getRegalias() * porcentaje1)) * 8) / 100);
                            valorC = (((regalias.get(i).getRegalias() - (regalias.get(i).getRegalias() * porcentaje2)) * 8) / 100);
                            regalias.get(i).setPuertos(valorA + valorB + valorC);

                            valorB = ((((regalias.get(i).getRegalias() * porcentaje2) - (regalias.get(i).getRegalias() * porcentaje1)) * 19.5) / 100);
                            valorC = (((regalias.get(i).getRegalias() - (regalias.get(i).getRegalias() * porcentaje2)) * 32) / 100);
                            regalias.get(i).setFondonacional(valorA + valorB + valorC);
                            regalias.get(i).setMunnoproductor(0.0);
                        }
                        if (regalias.get(i).getProduccion().getIdproduccion() != 0) {
                            int r = regaliasDao.registrarRegalias(regalias.get(i));
                            if (r == 0) {
                                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Ocurrio un error al registrar la distribucion de regalias");
                            }
                        }
                    } else {
                        message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Ocurrio un error al consultar el trm del mes respectivo");
                    }
                } else {
                    message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "No existen valores registrados de pbl para el mes y año seleccionado");
                }
            }
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "no existen campos registrados para este municipio");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public double totalProdDiaMunicipio(List<Regalias> rg) {
        double total = 0;
        for (int i = 0; i < rg.size(); i++) {
            total = total + (rg.get(i).getProduccion().getProducciontotaldia());
        }
        return total;
    }

    public double totalProdMesMunicipio(List<Regalias> rg) {
        double total = 0;
        for (int i = 0; i < rg.size(); i++) {
            total = total + (rg.get(i).getProduccion().getProducciontotalmes());
        }
        return total;
    }

    public double promedioDiarioMensual(List<Regalias> rg) {
        double bpdm = 0;
        double sumatoria = 0;
        for (int i = 0; i < rg.size(); i++) {
            sumatoria = sumatoria + rg.get(i).getProduccion().getProducciontotalmes();
        }
        Calendar cal = new GregorianCalendar(anio, mes - 1, 1);
        int diasMes = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        bpdm = sumatoria / diasMes;
        return bpdm;
    }

    //Metodos   
    public void consultarRegalias() throws Exception {

        municipio = municipiosDao.consultarMunicipio(municipio);
        regalia.setMunicipio(municipio);
        regalias = new ArrayList<>();
        boolean valor = regaliasDao.verificarCalculoRegalias(regalia);

        if (!valor) {
            if (produccionDao.verificarRegistroProduccionMunicipio(municipio, regalia.getAnio(), regalia.getMes())) {
                anio = regalia.getAnio();
                mes = regalia.getMes();
                calculoRegaliasRapido();
                regalias = regaliasDao.consultarRegalias(regalia);
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No hay produccion registrada para este municipio en ese mes y año");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } else {
            regalias = regaliasDao.consultarRegalias(regalia);
        }
        municipio = new Municipio();
        regalia = new Regalias();
        anio = 0;
        mes = 0;
    }

    public String redondear(double valor, int digitos) {
        String val = valor + "";
        BigDecimal big = new BigDecimal(val);
        big = big.setScale(digitos, RoundingMode.HALF_UP);
        return big.toString();
    }

}
