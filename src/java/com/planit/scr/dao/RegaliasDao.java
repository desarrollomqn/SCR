/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.dao;

import com.planit.scr.conexion.Pool;
import com.planit.scr.metodos.Redondear;
import com.planit.scr.modelos.Campo;
import com.planit.scr.modelos.Contrato;
import com.planit.scr.modelos.Departamento;
import com.planit.scr.modelos.Regalias;
import com.planit.scr.modelos.Municipio;
import com.planit.scr.modelos.Produccion;
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
public class RegaliasDao {

    private final Pool pool = new Pool();

    public int registrarRegalias(Regalias regalia) throws Exception {
        int resultado = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = pool.dataSource.getConnection();
            st = con.createStatement();
            try {
                String sql = "INSERT INTO public.regalias (iddepartamento, idmunicipio, idcampo, idcontrato,"
                        + " porcmunicipio, porcregalias, depproductor, munproductor, munnoproductor, puertos, anio, mes, precio, regalias, idproduccion, fondonacional)"
                        + " VALUES(" + regalia.getDepartamento().getIddepartamento() + ", "
                        + "'" + regalia.getMunicipio().getIdmunicipio() + "', "
                        + "'" + regalia.getCampo().getIdcampo() + "', "
                        + "'" + regalia.getContrato().getIdcontrato() + "', "
                        + "'" + Redondear.redondear(regalia.getPorcmunicipio(), 3) + "', "
                        + "'" + regalia.getPorcregalias() + "', "
                        + "'" + Redondear.redondear(regalia.getDepproductor(), 3) + "', "
                        + "'" + Redondear.redondear(regalia.getMunproductor(), 3) + "', "
                        + "'" + Redondear.redondear(regalia.getMunnoproductor(), 3) + "', "
                        + "'" + Redondear.redondear(regalia.getPuertos(), 3) + "', "
                        + "'" + regalia.getAnio() + "', "
                        + "'" + regalia.getMes() + "', "
                        + "'" + Redondear.redondear(regalia.getPrecio(), 3) + "', "
                        + "'" + Redondear.redondear(regalia.getRegalias(), 3) + "', "
                        + "'" + regalia.getProduccion().getIdproduccion() + "', "
                        + "'" + Redondear.redondear(regalia.getFondonacional(), 3) + "')";
                st.execute(sql);               
                resultado = 1;
            } catch (SQLException e) {
                System.out.println("Error sql" + e);
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

    public List<Regalias> consultarRegalias(Regalias regalia) throws Exception {
        List<Regalias> regalias = new ArrayList<>();
        Connection con = null;
        Statement st = null;
        try {
            con = pool.dataSource.getConnection();
            st = con.createStatement();
            try {
                String sql = "SELECT r.idregalias, r.iddepartamento, r.idmunicipio, r.idcampo, r.porcmunicipio, r.porcregalias, r.depproductor,"
                        + " r.munproductor, r.munnoproductor, r.puertos, r.anio, r.mes, r.precio, r.regalias, r.idproduccion, r.fondonacional, r.idcontrato,"
                        + " d.nombre, m.nombre, c.nombre, p.produccionhdia, p.producciongdia, p.producciontotaldia, p.producciontotalmes, cr.nombre"
                        + " FROM public.regalias as r,"
                        + " public.campos as c,"
                        + " public.departamentos as d,"
                        + " public.produccion as p,"
                        + " public.municipios as m,"
                        + " public.contratos as cr"
                        + " WHERE r.idmunicipio = " + regalia.getMunicipio().getIdmunicipio() + " AND r.anio = " + regalia.getAnio() + " and r.mes = " + regalia.getMes() + ""
                        + " and r.iddepartamento = d.iddepartamento and m.idmunicipio = r.idmunicipio and c.idcampo = r.idcampo"
                        + " and r.idproduccion = p.idproduccion and r.idcontrato = cr.idcontrato";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    regalias.add(new Regalias(rs.getInt(1),
                            rs.getDouble(5),
                            rs.getDouble(6),
                            rs.getDouble(7),
                            rs.getDouble(8),
                            rs.getDouble(9),
                            rs.getDouble(10),
                            rs.getInt(11),
                            rs.getInt(12),
                            rs.getDouble(13),
                            rs.getDouble(14),
                            new Campo(rs.getInt(4), rs.getString(20)),
                            new Departamento(rs.getInt(2), rs.getString(18)),
                            new Municipio(rs.getInt(3), rs.getString(19)),
                            new Contrato(rs.getInt(17), rs.getString(25)),
                            new Produccion(rs.getInt(15), rs.getDouble(21), rs.getDouble(22), rs.getDouble(23), rs.getDouble(24)),
                            rs.getDouble(16)));
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
        return regalias;
    }

    public boolean verificarCalculoRegalias(Regalias regalia) throws Exception {
        boolean valor = false;
        Connection con = null;
        Statement st = null;
        try {
            con = pool.dataSource.getConnection();
            st = con.createStatement();
            try {
                String sql = "SELECT DISTINCT true FROM public.regalias WHERE exists(SELECT *"
                        + " FROM public.regalias"
                        + " WHERE idmunicipio = " + regalia.getMunicipio().getIdmunicipio() + " AND anio = " + regalia.getAnio() + " and mes = " + regalia.getMes() + ")";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    valor = rs.getBoolean(1);
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
        return valor;
    }
}
