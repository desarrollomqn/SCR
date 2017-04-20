/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.dao;

import static co.com.sc.nexura.superfinanciera.action.generic.services.trm.test.TCRMTestClient.consultarTRM;
import com.planit.scr.conexion.Pool;
import static com.planit.scr.metodos.Redondear.redondear;
import com.planit.scr.modelos.Trm;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author VaioDevelopment
 */
public class TrmDao {

    private Pool pool = new Pool();

    //Metodos
    public void registrarTrm(Trm trm, String fecha) throws Exception {
        Connection con = null;
        Statement st = null;
        String valor = redondear(trm.getValor(), 2);
        try {
            con = pool.dataSource.getConnection();
            st = con.createStatement();
            try {
                String sql = "INSERT INTO public.trm(fecha, valor) "
                        + "VALUES('" + fecha + "','" + valor + "')";
                st.execute(sql);
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (st != null && con != null) {
                st.close();
                con.close();
            }
        }
    }

    public List<Trm> consultarTrm() throws Exception {
        Connection con = null;
        Statement st = null;
        List<Trm> lista = new ArrayList<>();
        try {
            con = pool.dataSource.getConnection();
            st = con.createStatement();
            try {
                String sql = "SELECT idtrm, fecha, valor FROM public.trm";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    lista.add(new Trm(rs.getInt(1), rs.getDate(2), rs.getInt(3)));
                }
                rs.close();
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (st != null && con != null) {
                st.close();
                con.close();
            }
        }
        return lista;
    }

    public Trm consultarTrm(Trm tr) throws Exception {
        Trm nuevotrm = new Trm();
        Connection con = null;
        Statement st = null;
        try {
            con = pool.dataSource.getConnection();
            st = con.createStatement();
            try {
                String sql = "SELECT idtrm, fecha, valor FROM public.trm "
                        + "WHERE idtrm = " + tr.getIdtrm() + " or fecha = '" + tr.getFecha() + "'";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    nuevotrm = new Trm(rs.getInt(1), rs.getDate(2), rs.getDouble(3));
                }
                rs.close();
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (st != null && con != null) {
                st.close();
                con.close();
            }
        }
        return nuevotrm;
    }

    public List<Trm> buscarTrm(String anio, String mes) throws Exception {
        Connection con = null;
        Statement st = null;
        List<Trm> nuevotrm = new ArrayList<>();
        try {
            con = pool.dataSource.getConnection();
            st = con.createStatement();
            try {
                String sql = "";
                if (!mes.isEmpty()) {
                    int m = Integer.parseInt(mes) - 1;
                    Calendar cal = new GregorianCalendar(Integer.parseInt(anio), m, 1);

                    sql = "SELECT idtrm, fecha, valor FROM public.trm "
                            + "WHERE fecha between '" + anio + "-" + mes + "-01' AND '" + anio + "-" + mes + "-" + cal.getActualMaximum(Calendar.DAY_OF_MONTH) + "'";
                } else {

                    sql = "SELECT idtrm, fecha, valor FROM public.trm "
                            + "WHERE fecha between '" + anio + "-01-01' AND '" + anio + "-12-31' ";
                }
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    nuevotrm.add(new Trm(rs.getInt(1), rs.getDate(2), rs.getDouble(3)));
                }
                rs.close();
            } catch (SQLException e) {
                throw e;
            }
        } catch (SQLException | NumberFormatException e) {
            throw e;
        } finally {
            if (st != null && con != null) {
                st.close();
                con.close();
            }
        }
        return nuevotrm;
    }

    public Trm consultarMaxTrm() throws Exception {
        Trm nuevotrm = new Trm();
        Connection con = null;
        Statement st = null;
        try {
            con = pool.dataSource.getConnection();
            st = con.createStatement();
            try {
                String sql = "SELECT MAX(fecha) as fechaFROM from trm ;";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    nuevotrm = new Trm(rs.getDate(1));
                }
                rs.close();
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (st != null && con != null) {
                st.close();
                con.close();
            }
        }
        System.out.println("fecha trm max--------" + nuevotrm);
        return nuevotrm;
    }

    public double consultarPromedioMensualTrm(int mes, int anio) throws Exception {

        List<Trm> nuevotrm = new ArrayList<>();
        Connection con = null;
        Statement st = null;

        Calendar cal = new GregorianCalendar(anio, mes - 1, 1);
        int diasMes = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        String fecha1 = "" + anio + "-" + mes + "-01";
        String fecha2 = "" + anio + "-" + mes + "-" + diasMes + "";

        try {
            con = pool.dataSource.getConnection();
            st = con.createStatement();
            try {
                String sql = "SELECT idtrm, fecha, valor FROM public.trm "
                        + "WHERE fecha between '" + fecha1 + "' AND '" + fecha2 + "'";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    nuevotrm.add(new Trm(rs.getInt(1), rs.getDate(2), rs.getDouble(3)));
                }
                rs.close();
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (st != null && con != null) {
                st.close();
                con.close();
            }
        }
        double sumatoria = 0;
        double resultado = 0;
        for (int i = 0; i < nuevotrm.size(); i++) {
            sumatoria = sumatoria + nuevotrm.get(i).getValor();
        }
        resultado = (sumatoria / diasMes);
        return resultado;
    }

    public double consultarPromedioTrimestralTrm(int trimestre, int anio) throws Exception {
        Connection con = null;
        Statement st = null;
        
        double resultado = 0;
        List<Trm> Lista1 = new ArrayList<>();
        List<Trm> Lista2 = new ArrayList<>();
        List<Trm> Lista3 = new ArrayList<>();

        int mes1 = 0;
        int mes2 = 0;
        int mes3 = 0;

        switch (trimestre) {
            case 1:
                mes1 = 1;
                mes2 = 2;
                mes3 = 3;
                break;
            case 2:
                mes1 = 4;
                mes2 = 5;
                mes3 = 6;
                break;
            case 3:
                mes1 = 7;
                mes2 = 8;
                mes3 = 9;
                break;
            case 4:
                mes1 = 10;
                mes2 = 11;
                mes3 = 12;
                break;
        }

        Calendar cal = new GregorianCalendar(anio, mes1 - 1, 1);
        int diasMes1 = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        cal = new GregorianCalendar(anio, mes2 - 1, 1);
        int diasMes2 = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        cal = new GregorianCalendar(anio, mes3 - 1, 1);
        int diasMes3 = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        String fecha1m1 = "" + anio + "-" + mes1 + "-01";
        String fecha2m1 = "" + anio + "-" + mes1 + "-" + diasMes1 + "";

        String fecha1m2 = "" + anio + "-" + mes2 + "-01";
        String fecha2m2 = "" + anio + "-" + mes2 + "-" + diasMes2 + "";

        String fecha1m3 = "" + anio + "-" + mes3 + "-01";
        String fecha2m3 = "" + anio + "-" + mes3 + "-" + diasMes3 + "";

        try {
            con = pool.dataSource.getConnection();
            st = con.createStatement();
            try {
                String sql1 = "SELECT idtrm, fecha, valor FROM public.trm "
                        + "WHERE fecha between '" + fecha1m1 + "' AND '" + fecha2m1 + "'";
                String sql2 = "SELECT idtrm, fecha, valor FROM public.trm "
                        + "WHERE fecha between '" + fecha1m2 + "' AND '" + fecha2m2 + "'";
                String sql3 = "SELECT idtrm, fecha, valor FROM public.trm "
                        + "WHERE fecha between '" + fecha1m3 + "' AND '" + fecha2m3 + "'";

                ResultSet rs = st.executeQuery(sql1);
                while (rs.next()) {
                    Lista1.add(new Trm(rs.getInt(1), rs.getDate(2), rs.getDouble(3)));
                }

                rs = st.executeQuery(sql2);
                while (rs.next()) {
                    Lista2.add(new Trm(rs.getInt(1), rs.getDate(2), rs.getDouble(3)));
                }

                rs = st.executeQuery(sql3);
                while (rs.next()) {
                    Lista3.add(new Trm(rs.getInt(1), rs.getDate(2), rs.getDouble(3)));
                }

                double sumatoria1 = 0;
                double sumatoria2 = 0;
                double sumatoria3 = 0;
                for (int i = 0; i < Lista1.size(); i++) {
                    sumatoria1 = sumatoria1 + Lista1.get(i).getValor();
                }

                for (int i = 0; i < Lista2.size(); i++) {
                    sumatoria2 = sumatoria2 + Lista2.get(i).getValor();
                }

                for (int i = 0; i < Lista3.size(); i++) {
                    sumatoria3 = sumatoria3 + Lista3.get(i).getValor();
                }

                resultado = ((sumatoria1 / diasMes1) + (sumatoria2 / diasMes2) + (sumatoria3 / diasMes3)) / 3;                
                rs.close();               
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (st != null && con != null) {
                st.close();
                con.close();
            }
        }
        return resultado;
    }

    public double calcularPromedioDeTresMesesTRM(double mes1, double mes2, double mes3){
        double promedio_trimestre = 0;
        
        promedio_trimestre = (mes1 + mes2 + mes3)/3;
        
        return promedio_trimestre;
    }
    
    public String consultarTrmWS() throws Exception {

        String TRM = "";

        TRM = consultarTRM();

        System.out.println("Funciona: " + TRM);

        return TRM;
    }

}
