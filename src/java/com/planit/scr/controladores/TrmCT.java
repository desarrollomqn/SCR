/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.controladores;

import static co.com.sc.nexura.superfinanciera.action.generic.services.trm.test.TCRMTestClient.consultarTRM;
import com.planit.scr.clienttrm.action.Util;
import com.planit.scr.dao.TrmDao;
import com.planit.scr.modelos.Trm;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;

/**
 *
 * @author Desarrollo_Planit
 */
public class TrmCT {

    private Trm trm;
    private List<Trm> listatrm;
    private String trmd;
    private String anio;
    private String mes;
    private double promedio;
    private double promedioMes1;
    private double promedioMes2;
    private double promedioMes3;
    private double promedioTrimestreSel = 0.0;
    public TrmCT() {
        trm = new Trm();
        listatrm = new ArrayList<>();
        trmd = "";
        anio = "";
        mes = "";
    }

    @PostConstruct
    public void init() {
        try {
            TrmDao trmDao = new TrmDao();
            try {
                registrarTrm();
                //listatrm = trmDao.consultarTrm();
            } catch (Exception ex) {
                Logger.getLogger(TrmCT.class.getName()).log(Level.SEVERE, null, ex);
            }

            trmd = trmDao.consultarTrmWS();
            
            //obtenerPromedioMes(Integer.parseInt(mes), Integer.parseInt(anio));
            obtenerPromedioMes(0);
                
        } catch (Exception ex) {
            Logger.getLogger(TrmCT.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    } 
    
    
    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    } 
    
    public double getPromedioMes1() {
        return promedioMes1;
    }

    public void setPromedioMes1(double promedioMes1) {
        this.promedioMes1 = promedioMes1;
    } 
    
    public double getPromedioMes2() {
        return promedioMes2;
    }

    public void setPromedioMes2(double promedioMes2) {
        this.promedioMes2 = promedioMes2;
    }    
    
    public double getPromedioMes3() {
        return promedioMes3;
    }

    public void setPromedioMes3(double promedioMes3) {
        this.promedioMes3 = promedioMes3;
    }    
    
    public double getPromedioTrimestreSel() {
        return promedioTrimestreSel;
    }

    public void setPromedioTrimestreSel(double promedioTrimestreSel) {
        this.promedioTrimestreSel = promedioTrimestreSel;
    }    
    
    
    
      
      
    public Trm getTrm() {
        return trm;
    }

    public void setTrm(Trm trm) {
        this.trm = trm;
    }

    public List<Trm> getListatrm() {
        return listatrm;
    }

    public void setListatrm(List<Trm> listatrm) {
        this.listatrm = listatrm;
    }

    public String getTrmd() {
        return trmd;
    }

    public void setTrmd(String trmd) {
        this.trmd = trmd;
    }

    //Metodos
    public void registrarTrm() throws Exception {
        int mes = 0, count = 0;
        Trm nuevotrm = new Trm();
        TrmDao trmDao = new TrmDao();
        Calendar calendar = Calendar.getInstance();
        nuevotrm = trmDao.consultarMaxTrm();
        if (!(nuevotrm.getFecha().toString().equals(Util.GetFechaActualDate().toString()))) {

            long dias = (Util.GetFechaActualDate().getTime() - nuevotrm.getFecha().getTime());

            dias = ((((dias / 1000) / 60) / 60) / 24);

            for (int i = 1; i <= dias; i++) {

                calendar.setTime(nuevotrm.getFecha()); // Configuramos la fecha que se recibe
                calendar.add(Calendar.DAY_OF_MONTH, i);
//                if (calendar.get(Calendar.DAY_OF_MONTH) == calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
//                    mes = calendar.get(Calendar.MONTH) + 1;
//                    count = 1;
//                } else if (count == 1) {
//                    mes = calendar.get(Calendar.MONTH) + 2;
//                    count = 0;
//                }else{
//                 mes = calendar.get(Calendar.MONTH) + 1;
//                }

                int dia = calendar.get(Calendar.DAY_OF_MONTH);
                mes = calendar.get(Calendar.MONTH) + 1;
                int anio = calendar.get(Calendar.YEAR);

                String fec = anio + "-" + mes + "-" + dia;

                double valorTrm = consultarTRM(fec);
//                double valorTrmd = Double.parseDouble(valorTrm);
                nuevotrm.setValor(valorTrm);
                trmDao.registrarTrm(nuevotrm, fec);
            }
            
           
        
        

        }
        

    }
    //Metodo para obtener promedio de un mes
    public void obtenerPromedioMes(int $mesDelTrimestre) throws Exception{
        TrmDao trmDao = new TrmDao();
        if(anio.isEmpty()){
            promedio = 0.0;           
        }else{
            promedio = trmDao.consultarPromedioMensualTrm(Integer.parseInt(mes), Integer.parseInt(anio));         
        }
        
        if($mesDelTrimestre == 1){
             promedioMes1=promedio;
        } else if($mesDelTrimestre == 2) {
            promedioMes2=promedio;
        }else if($mesDelTrimestre == 3){
            promedioMes3=promedio;
        }
    }
    
    
        //Metodo para obtener promedio de los tres mes seleccionados
    public void obtenerPromedioTrimestreSeleccionado() throws Exception{
        TrmDao trmDao = new TrmDao();
        promedioTrimestreSel = trmDao.calcularPromedioDeTresMesesTRM(promedioMes1, promedioMes2, promedioMes3);    
    }
    
    
    
    
    

//    public void registrar() throws Exception {
//        TrmDao trmDao = new TrmDao();
//        trmDao.registrarTrm(trm);
//        trm = new Trm();
//        listatrm = trmDao.consultarTrm();
//    }
    
    //Metodos de busqueda
    public void buscartrm() throws Exception{
        TrmDao trmDao = new TrmDao();
        if(anio.isEmpty() || anio.equals("")){
            //listatrm = trmDao.consultarTrm();
        }else{
            listatrm = trmDao.buscarTrm(anio, mes);
        } 
    }
    
}
