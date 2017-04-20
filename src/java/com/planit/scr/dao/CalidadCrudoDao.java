/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.dao;

import com.planit.scr.conexion.Pool;
import com.planit.scr.modelos.CalidadCrudo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author VaioDevelopment
 */
public class CalidadCrudoDao {

    private final Pool pool = new Pool();

    public int registrarCalidadCrudo(CalidadCrudo calidadCrudo) throws Exception {
        int resultado = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = pool.dataSource.getConnection();
            st = con.createStatement();
            try {
                String sql = "INSERT INTO public.calidad_crudos (cal_ref_api_exportacion, cal_ref_api_refinado, "
                        + "cal_ref_azufre_exportacion, cal_ref_azufre_refinado, "
                        + "corr_ref_api_exportacion, corr_ref_api_refinado, "
                        + "corr_ref_azufre_exportacion, corr_ref_azufre_refinado, "
                        + "precio_referencia_exportacion, precio_referencia_refinado, "
                        + "trimestre_mes, anio) "
                        + "VALUES ('" + calidadCrudo.getCalRefApiExportacion() + "',"
                        + "'" + calidadCrudo.getCalRefApiRefinado() + "',"
                        + "'" + calidadCrudo.getCalRefAzufreExportacion() + "',"
                        + "'" + calidadCrudo.getCalRefAzufreRefinado() + "',"
                        + "'" + calidadCrudo.getCorrRefApiExportacion() + "',"
                        + "'" + calidadCrudo.getCorrRefApiRefinado() + "',"
                        + "'" + calidadCrudo.getCorrRefAzufreExportacion() + "',"
                        + "'" + calidadCrudo.getCorrRefAzufreRefinado() + "',"
                        + "'" + calidadCrudo.getPrecioReferenciaExportacion() + "',"
                        + "'" + calidadCrudo.getPrecioReferenciaRefinado() + "',"
                        + "'" + calidadCrudo.getTrimestre_mes() + "',"
                        + "'" + calidadCrudo.getAnio() + "')";
                st.execute(sql);
                resultado = 1;
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (con != null && st != null) {
                st.close();
                con.close();
            }
        }
        return resultado;
    }

    public int modificarCalidadCrudo(CalidadCrudo calidadCrudo) throws Exception {
        int resultado = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = pool.dataSource.getConnection();
            st = con.createStatement();
            try {
                String sql = "UPDATE public.calidad_crudos SET "
                        + "cal_ref_api_exportacion = '" + calidadCrudo.getCalRefApiExportacion() + "',"
                        + "cal_ref_api_refinado = '" + calidadCrudo.getCalRefApiRefinado() + "',"
                        + "cal_ref_azufre_exportacion = '" + calidadCrudo.getCalRefAzufreExportacion() + "',"
                        + "cal_ref_azufre_refinado = '" + calidadCrudo.getCalRefAzufreRefinado() + "',"
                        + "corr_ref_api_exportacion = '" + calidadCrudo.getCorrRefApiExportacion() + "',"
                        + "corr_ref_api_refinado = '" + calidadCrudo.getCorrRefApiRefinado() + "',"
                        + "corr_ref_azufre_exportacion = '" + calidadCrudo.getCorrRefAzufreExportacion() + "',"
                        + "corr_ref_azufre_refinado = '" + calidadCrudo.getCorrRefAzufreRefinado() + "',"
                        + "precio_referencia_exportacion = '" + calidadCrudo.getPrecioReferenciaExportacion() + "',"
                        + "precio_referencia_refinado = '" + calidadCrudo.getPrecioReferenciaRefinado() + "',"
                        + "trimestre_mes = '" + calidadCrudo.getTrimestre_mes() + "',"
                        + "anio = '" + calidadCrudo.getAnio() + "' "
                        + "WHERE idcalidadcrudo = '" + calidadCrudo.getIdCalidadcrudo() + "'";
                st.execute(sql);
                resultado = 1;
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (con != null && st != null) {
                st.close();
                con.close();
            }
        }
        return resultado;
    }

    public int eliminarCalidadCrudo(CalidadCrudo calidadCrudo) throws Exception {
        int resultado = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = pool.dataSource.getConnection();
            st = con.createStatement();
            try {
                String sql = "DELETE FROM public.calidad_crudos WHERE idcalidadcrudo = '" + calidadCrudo.getIdCalidadcrudo() + "'";
                st.execute(sql);
                resultado = 1;
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (con != null && st != null) {
                st.close();
                con.close();
            }
        }
        return resultado;
    }

    public List<CalidadCrudo> consultarCalidadCrudos() throws Exception {
        List<CalidadCrudo> resultado = new ArrayList<>();
        Connection con = null;
        Statement st = null;
        try {
            con = pool.dataSource.getConnection();
            st = con.createStatement();
            try {
                String sql = "SELECT idcalidadcrudo, cal_ref_api_exportacion, cal_ref_api_refinado, "
                        + "cal_ref_azufre_exportacion, cal_ref_azufre_refinado, "
                        + "corr_ref_api_exportacion, corr_ref_api_refinado, "
                        + "corr_ref_azufre_exportacion, corr_ref_azufre_refinado, "
                        + "precio_referencia_exportacion, precio_referencia_refinado, "
                        + "trimestre_mes, anio FROM public.calidad_crudos";

                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    resultado.add(new CalidadCrudo(rs.getInt(1),
                            rs.getDouble(2),
                            rs.getDouble(3),
                            rs.getDouble(4),
                            rs.getDouble(5),
                            rs.getDouble(6),
                            rs.getDouble(7),
                            rs.getDouble(8),
                            rs.getDouble(9),
                            rs.getDouble(10),
                            rs.getDouble(11),
                            rs.getInt(12),
                            rs.getInt(13)));
                }
                st.execute(sql);
                rs.close();
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (con != null && st != null) {
                st.close();
                con.close();
            }
        }
        return resultado;
    }

    public CalidadCrudo consultarCalidadCrudo(CalidadCrudo calidadCrudo) throws Exception {
        CalidadCrudo resultado = new CalidadCrudo();
        Connection con = null;
        Statement st = null;
        try {
            con = pool.dataSource.getConnection();
            st = con.createStatement();
            try {
                String sql = "SELECT idcalidadcrudo, cal_ref_api_exportacion, cal_ref_api_refinado, "
                        + "cal_ref_azufre_exportacion, cal_ref_azufre_refinado, "
                        + "corr_ref_api_exportacion, corr_ref_api_refinado, "
                        + "corr_ref_azufre_exportacion, corr_ref_azufre_refinado, "
                        + "precio_referencia_exportacion, precio_referencia_refinado, "
                        + "trimestre_mes, anio FROM public.calidad_crudos "
                        + "WHERE anio = '" + calidadCrudo.getAnio() + "' AND trimestre_mes = '" + calidadCrudo.getTrimestre_mes() + "' or idcalidadCrudo = '" + calidadCrudo.getIdCalidadcrudo() + "'";

                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    resultado = new CalidadCrudo(rs.getInt(1),
                            rs.getDouble(2),
                            rs.getDouble(3),
                            rs.getDouble(4),
                            rs.getDouble(5),
                            rs.getDouble(6),
                            rs.getDouble(7),
                            rs.getDouble(8),
                            rs.getDouble(9),
                            rs.getDouble(10),
                            rs.getDouble(11),
                            rs.getInt(12),
                            rs.getInt(13));
                }
                st.execute(sql);
                rs.close();
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (con != null && st != null) {
                st.close();
                con.close();
            }
        }
        return resultado;
    }

    public double consultarPrecioReferenciaExportacion(int anio, int trimestre_mes) throws Exception {
        double valor = 0;
        CalidadCrudo resultado = new CalidadCrudo();
        Connection con = null;
        Statement st = null;
        try {
            con = pool.dataSource.getConnection();
            st = con.createStatement();
            try {
                String sql = "SELECT idcalidadcrudo, cal_ref_api_exportacion, cal_ref_api_refinado, "
                        + "cal_ref_azufre_exportacion, cal_ref_azufre_refinado, "
                        + "corr_ref_api_exportacion, corr_ref_api_refinado, "
                        + "corr_ref_azufre_exportacion, corr_ref_azufre_refinado, "
                        + "precio_referencia_exportacion, precio_referencia_refinado, "
                        + "trimestre_mes, anio FROM public.calidad_crudos "
                        + "WHERE anio = '" + anio + "' AND trimestre_mes = '" + trimestre_mes + "'";

                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    resultado = new CalidadCrudo(rs.getInt(1),
                            rs.getDouble(2),
                            rs.getDouble(3),
                            rs.getDouble(4),
                            rs.getDouble(5),
                            rs.getDouble(6),
                            rs.getDouble(7),
                            rs.getDouble(8),
                            rs.getDouble(9),
                            rs.getDouble(10),
                            rs.getDouble(11),
                            rs.getInt(12),
                            rs.getInt(13));
                }
                st.execute(sql);
                rs.close();
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (con != null && st != null) {
                st.close();
                con.close();
            }
        }
        valor = resultado.getPrecioReferenciaExportacion();
        return valor;
    }

}
