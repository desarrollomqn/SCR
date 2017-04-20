/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.dao;

import com.planit.scr.conexion.Pool;
import com.planit.scr.modelos.Campo;
import com.planit.scr.modelos.Contrato;
import com.planit.scr.modelos.Municipio;
import com.planit.scr.modelos.Tipo;
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
public class ContratosDao {

    private final Pool pool = new Pool();

    //Metodos 
    public int registrarContrato(Contrato contrato) throws Exception {
        TipoDao tipoDao = new TipoDao();
        int resultado = 0;
        Connection con = null;
        Statement st = null;
        contrato.setTipo(tipoDao.consultarTipo(contrato.getTipo()));
        try {
            con = pool.dataSource.getConnection();
            st = con.createStatement();
            try {
                String sql = "INSERT INTO public.contratos(nombre, idtipo, cib, car, cov)"
                        + " VALUES('" + contrato.getNombre() + "',"
                        + " " + contrato.getTipo().getIdtipo() + ","
                        + " " + contrato.getCib() + ","
                        + " " + contrato.getCar() + ","
                        + " " + contrato.getCov() + ")";
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

    public int modificarContrato(Contrato contrato) throws Exception {

        TipoDao tipoDao = new TipoDao();
        int resultado = 0;
        Connection con = null;
        Statement st = null;
        contrato.setTipo(tipoDao.consultarTipo(contrato.getTipo()));
        try {
            con = pool.dataSource.getConnection();
            st = con.createStatement();
            try {
                String sql = "UPDATE public.contratos SET nombre = '" + contrato.getNombre() + "',"
                        + " idtipo = " + contrato.getTipo().getIdtipo() + ","
                        + " cib = " + contrato.getCib() + ","
                        + " car = " + contrato.getCar() + ", "
                        + " cov = " + contrato.getCov() + ""
                        + " WHERE idcontrato = '" + contrato.getIdcontrato() + "'";
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

    public int eliminarContrato(Contrato contrato) throws Exception {

        TipoDao tipoDao = new TipoDao();
        int resultado = 0;
        Connection con = null;
        Statement st = null;
        contrato.setTipo(tipoDao.consultarTipo(contrato.getTipo()));
        try {
            con = pool.dataSource.getConnection();
            st = con.createStatement();
            try {
                String sql = "DELETE FROM public.contratos WHERE idcontrato = '" + contrato.getIdcontrato() + "'";
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

    public Contrato consultarContrato(Contrato c) throws Exception {

        Contrato nuevocontrato = new Contrato();
        Connection con = null;
        Statement st = null;
        try {
            con = pool.dataSource.getConnection();
            st = con.createStatement();
            try {
                String sql = "SELECT cr.idcontrato, cr.nombre, cr.idtipo, cr.cib, cr.car, cr.cov, t.nombre FROM public.contratos as cr, public.tipos as t"
                        + " WHERE cr.idcontrato = " + c.getIdcontrato() + " or cr.nombre = '" + c.getNombre() + "' "
                        + " and cr.idtipo = t.idtipo ";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    nuevocontrato = new Contrato(rs.getInt(1), rs.getString(2), rs.getInt(4), rs.getInt(5), rs.getInt(6), new Tipo(rs.getInt(3), rs.getString(7)));
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
        return nuevocontrato;
    }

    public Contrato consultarContratoSegunCampo(Campo campo) throws Exception {

        Contrato nuevocontrato = new Contrato();
        Connection con = null;
        Statement st = null;
        try {
            con = pool.dataSource.getConnection();
            st = con.createStatement();
            try {
                String sql = "SELECT ct.idcontrato, ct.nombre, ct.idtipo, ct.cib, ct.car, ct.cov, t.nombre FROM public.contratos as ct, public.campos as c, public.tipos as t"
                        + " WHERE c.idcampo = " + campo.getIdcampo() + " and c.idcontrato = ct.idcontrato and t.idtipo = ct.idtipo";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    nuevocontrato = new Contrato(rs.getInt(1), rs.getString(2), rs.getInt(4), rs.getInt(5), rs.getInt(6), new Tipo(rs.getInt(3), rs.getString(7)));
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
        return nuevocontrato;
    }

    public List<Contrato> consultarContratos() throws Exception {

        List<Contrato> listacontratos = new ArrayList<>();
        Connection con = null;
        Statement st = null;
        try {
            con = pool.dataSource.getConnection();
            st = con.createStatement();
            try {
                String sql = "SELECT cr.idcontrato, cr.nombre, cr.idtipo, cr.cib, cr.car, cr.cov, t.nombre FROM public.contratos as cr, public.tipos as t "
                        + "where cr.idtipo = t.idtipo";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    listacontratos.add(new Contrato(rs.getInt(1), rs.getString(2), rs.getInt(4), rs.getInt(5), rs.getInt(6), new Tipo(rs.getInt(3), rs.getString(7))));
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
        return listacontratos;
    }

    public List<Contrato> consultarContratosSegunCampo(Campo campo) throws Exception {

        List<Contrato> listacontratos = new ArrayList<>();
        Connection con = null;
        Statement st = null;
        try {
            con = pool.dataSource.getConnection();
            st = con.createStatement();
            try {
                String sql = "SELECT cr.idcontrato, cr.nombre, cr.idtipo, cr.cib, cr.car, cr.cov, t.nombre FROM public.contratos as cr, public.campos_contratos as cc, public.tipos as t "
                        + "WHERE cr.idcontrato = cc.idcontrato and cc.idcampo = '" + campo.getIdcampo() + "' and cr.idtipo = t.idtipo";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    listacontratos.add(new Contrato(rs.getInt(1), rs.getString(2), rs.getInt(4), rs.getInt(5), rs.getInt(6), new Tipo(rs.getInt(3), rs.getString(7))));
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
        return listacontratos;
    }

    public List<Contrato> consultarContratosSegunMunicipio(Municipio municipio) throws Exception {

        List<Contrato> listacontratos = new ArrayList<>();
        Connection con = null;
        Statement st = null;
        try {
            con = pool.dataSource.getConnection();
            st = con.createStatement();
            try {
                String sql = "SELECT c.idcontrato, c.nombre, c.idtipo, c.cib, c.car, c.cov, t.nombre FROM public.contratos as c, public.municipios_contratos as mc, public.tipos as t "
                        + "WHERE mc.idmunicipio = " + municipio.getIdmunicipio() + " and mc.idcontrato = c.idcontrato and c.idtipo = t.idtipo ";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    listacontratos.add(new Contrato(rs.getInt(1), rs.getString(2), rs.getInt(4), rs.getInt(5), rs.getInt(6), new Tipo(rs.getInt(3), rs.getString(7))));
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
        return listacontratos;
    }

    public List<Contrato> buscarContratos(String valor) throws Exception {
        List<Contrato> listacontratos = new ArrayList<>();
        Connection con = null;
        Statement st = null;
        try {
            con = pool.dataSource.getConnection();
            st = con.createStatement();
            try {
                String sql = "SELECT c.idcontrato, c.nombre, c.idtipo, c.cib, c.car, c.cov, t.nombre FROM public.tipos as t, public.contratos as c, public.municipios_contratos as mc, public.municipios as m "
                        + "WHERE mc.idmunicipio = m.idmunicipio and mc.idcontrato = c.idcontrato and c.idtipo = t.idtipo and (c.nombre LIKE '%" + valor + "%' or t.nombre LIKE '%" + valor + "%' or m.nombre LIKE '%" + valor + "%')";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    listacontratos.add(new Contrato(rs.getInt(1), rs.getString(2), rs.getInt(4), rs.getInt(5), rs.getInt(6), new Tipo(rs.getInt(3), rs.getString(7))));
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
        return listacontratos;
    }
}
