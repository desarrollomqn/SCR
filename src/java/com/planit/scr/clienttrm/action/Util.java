/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.clienttrm.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Jose
 */
public class Util {

//    Devuelve hora y minuto actual
    public static int[] GetHoraMinutoActual() {

        int horaMinuto[] = new int[2];
        Calendar calendario = new GregorianCalendar();

        horaMinuto[0] = calendario.get(Calendar.HOUR_OF_DAY);
        horaMinuto[1] = calendario.get(Calendar.MINUTE);

        return horaMinuto;
    }

    public static String GetFechaActual()  {

        String fechaActual = "";

        Calendar calendario = new GregorianCalendar();

        int dia = calendario.get(Calendar.DAY_OF_MONTH);
        int mes = calendario.get(Calendar.MONTH) + 1;
        int anio = calendario.get(Calendar.YEAR);

        fechaActual = anio + "-" + mes + "-" + dia;

        return fechaActual;
    }
    
        public static Date GetFechaActualDate() throws ParseException {

        String fechaActual = "";

        Calendar calendario = new GregorianCalendar();

        int dia = calendario.get(Calendar.DAY_OF_MONTH);
        int mes = calendario.get(Calendar.MONTH) + 1;
        int anio = calendario.get(Calendar.YEAR);

        fechaActual = anio + "-" + mes + "-" + dia;

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = (Date) formatter.parse(fechaActual);
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);

        return date;
    }

}
