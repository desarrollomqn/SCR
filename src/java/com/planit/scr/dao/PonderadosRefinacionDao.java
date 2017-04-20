/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.dao;

import com.planit.scr.conexion.Pool;
import com.planit.scr.modelos.Derivado;
import com.planit.scr.modelos.PonderadoRefinacion;
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
public class PonderadosRefinacionDao {

    private final Pool pool = new Pool();

    public int registrarGrupoPonderado(List<PonderadoRefinacion> ponderadoRefinacion) throws Exception {
        int resultado = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = pool.dataSource.getConnection();
            st = con.createStatement();
            try {
                for (int i = 0; i < ponderadoRefinacion.size(); i++) {
                    String sql = "INSERT INTO public.ponderados_refinacion "
                            + "(idderivado, produccion,"
                            + " rendimiento, nal_bpd,"
                            + " export_bpd, precio_nal,"
                            + " precio_expo, precio_medio,"
                            + " medio_rdto, trimestre_mes, anio)"
                            + " VALUES('" + ponderadoRefinacion.get(i).getDerivado().getIdderivado() + "', "
                            + "'" + ponderadoRefinacion.get(i).getProduccion() + "', "
                            + "'" + ponderadoRefinacion.get(i).getRendimiento() + "', "
                            + "'" + ponderadoRefinacion.get(i).getNalbpd() + "', "
                            + "'" + ponderadoRefinacion.get(i).getExportbpd() + "', "
                            + "'" + ponderadoRefinacion.get(i).getPrecional() + "', "
                            + "'" + ponderadoRefinacion.get(i).getPrecioexpo() + "', "
                            + "'" + ponderadoRefinacion.get(i).getPreciomedio() + "', "
                            + "'" + ponderadoRefinacion.get(i).getMediordto() + "', "
                            + "'" + ponderadoRefinacion.get(i).getTrimestremes() + "', "
                            + "'" + ponderadoRefinacion.get(i).getAnio() + "')";
                    st.execute(sql);
                }
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

    public int modificarGrupoPonderado(List<PonderadoRefinacion> ponderadoRefinacion) throws Exception {
        int resultado = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = pool.dataSource.getConnection();
            st = con.createStatement();
            try {
                for (int i = 0; i < ponderadoRefinacion.size(); i++) {
                    String sql = "UPDATE public.ponderados_refinacion SET  "
                            + "idderivado = '" + ponderadoRefinacion.get(i).getDerivado().getIdderivado() + "',"
                            + "produccion = '" + ponderadoRefinacion.get(i).getProduccion() + "', "
                            + "rendimiento = '" + ponderadoRefinacion.get(i).getRendimiento() + "', "
                            + "nal_bpd = '" + ponderadoRefinacion.get(i).getNalbpd() + "', "
                            + "export_bpd = '" + ponderadoRefinacion.get(i).getExportbpd() + "', "
                            + "precio_nal = '" + ponderadoRefinacion.get(i).getPrecional() + "', "
                            + "precio_expo = '" + ponderadoRefinacion.get(i).getPrecioexpo() + "', "
                            + "precio_medio = '" + ponderadoRefinacion.get(i).getPreciomedio() + "', "
                            + "medio_rdto = '" + ponderadoRefinacion.get(i).getMediordto() + "', "
                            + "trimestre_mes = '" + ponderadoRefinacion.get(i).getTrimestremes() + "', "
                            + "anio = '" + ponderadoRefinacion.get(i).getAnio() + "' "
                            + "WHERE idponderado = '" + ponderadoRefinacion.get(i).getIdponderado() + "'";
                    st.execute(sql);
                }
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

    public int eliminarGrupoPonderado(int anio, int trimestremes) throws Exception {
        int resultado = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = pool.dataSource.getConnection();
            st = con.createStatement();
            try {
                String sql = "DELETE FROM public.ponderados_refinacion WHERE anio = '" + anio + "' AND trimestre_mes = '" + trimestremes + "'";
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

    public List<PonderadoRefinacion> ConsultarPonderados() throws Exception {
        List<PonderadoRefinacion> resultado = new ArrayList<>();
        Connection con = null;
        Statement st = null;
        try {
            con = pool.dataSource.getConnection();
            st = con.createStatement();
            try {
                String sql = "SELECT pr.idponderado, pr.idderivado, pr.produccion,"
                        + " pr.rendimiento, pr.nal_bpd,"
                        + " pr.export_bpd, pr.precio_nal,"
                        + " pr.precio_expo, pr.precio_medio,"
                        + " pr.medio_rdto, pr.trimestre_mes, pr.anio, d.nombre"
                        + " FROM public.ponderados_refinacion as pr, public.derivados as d"
                        + " WHERE pr.idderivado = d.idderivado";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    resultado.add(new PonderadoRefinacion(rs.getInt(1),
                            new Derivado(rs.getInt(2), rs.getString(13)),
                            rs.getDouble(3),
                            rs.getDouble(4),
                            rs.getDouble(5),
                            rs.getDouble(6),
                            rs.getDouble(7),
                            rs.getDouble(8),
                            rs.getDouble(9),
                            rs.getDouble(10),
                            rs.getInt(11),
                            rs.getInt(12)));
                }
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

    public List<PonderadoRefinacion> ConsultarPonderado(int anio, int trimestremes) throws Exception {
        List<PonderadoRefinacion> resultado = new ArrayList<>();
        Connection con = null;
        Statement st = null;
        try {
            con = pool.dataSource.getConnection();
            st = con.createStatement();
            try {
                String sql = "SELECT pr.idponderado, pr.idderivado, pr.produccion,"
                        + " pr.rendimiento, pr.nal_bpd,"
                        + " pr.export_bpd, pr.precio_nal,"
                        + " pr.precio_expo, pr.precio_medio,"
                        + " pr.medio_rdto, pr.trimestre_mes, pr.anio, d.nombre"
                        + " FROM public.ponderados_refinacion as pr, public.derivados as d"
                        + " WHERE pr.trimestre_mes = '" + trimestremes + "' AND pr.anio = '" + anio + "' AND pr.idderivado = d.idderivado";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    resultado.add(new PonderadoRefinacion(rs.getInt(1),
                            new Derivado(rs.getInt(2), rs.getString(13)),
                            rs.getDouble(3),
                            rs.getDouble(4),
                            rs.getDouble(5),
                            rs.getDouble(6),
                            rs.getDouble(7),
                            rs.getDouble(8),
                            rs.getDouble(9),
                            rs.getDouble(10),
                            rs.getInt(11),
                            rs.getInt(12)));
                }
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

    public double ConsultarPFPonderado(int anio, int trimestremes) throws Exception {
        List<PonderadoRefinacion> resultado = new ArrayList<>();
        Connection con = null;
        Statement st = null;
        double valor = 0;
        try {
            con = pool.dataSource.getConnection();
            st = con.createStatement();
            try {
                String sql = "SELECT pr.idponderado, pr.idderivado, pr.produccion,"
                        + " pr.rendimiento, pr.nal_bpd,"
                        + " pr.export_bpd, pr.precio_nal,"
                        + " pr.precio_expo, pr.precio_medio,"
                        + " pr.medio_rdto, pr.trimestre_mes, pr.anio, d.nombre FROM public.ponderados_refinacion as pr, public.derivados as d"
                        + " WHERE pr.trimestre_mes = '" + trimestremes + "' AND pr.anio = '" + anio + "' AND pr.idderivado = d.idderivado";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    resultado.add(new PonderadoRefinacion(rs.getInt(1),
                            new Derivado(rs.getInt(2), rs.getString(13)),
                            rs.getDouble(3),
                            rs.getDouble(4),
                            rs.getDouble(5),
                            rs.getDouble(6),
                            rs.getDouble(7),
                            rs.getDouble(8),
                            rs.getDouble(9),
                            rs.getDouble(10),
                            rs.getInt(11),
                            rs.getInt(12)));
                }

                for (int i = 0; i < resultado.size(); i++) {
                    valor = valor + resultado.get(i).getMediordto();
                }
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
        return valor;
    }
}
