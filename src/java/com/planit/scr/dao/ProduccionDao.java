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
import com.planit.scr.modelos.Municipio;
import com.planit.scr.modelos.Produccion;
import com.planit.scr.modelos.Tipo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author VaioDevelopment
 */
public class ProduccionDao {

    private final Pool pool = new Pool();

    //Calculos
    public double convertirABarrilesEquivalentes(double produccionGas) {
        double barrilesequivalentes;
        barrilesequivalentes = (produccionGas / (double) 5700);
        return barrilesequivalentes;
    }

    //Metodos
    public int registrarProduccion(Produccion p) throws Exception {
        int resultado = 0;
        try {
            Connection con = pool.dataSource.getConnection();
            Statement st = con.createStatement();
            try {
                String sql = "INSERT INTO public.produccion (idcampo, produccionhdia, produccionhmes, producciongdia, producciongmes, producciontotaldia, producciontotalmes, mes, anio, idmunicipio, idcontrato)"
                        + " VALUES('" + p.getCampo().getIdcampo() + "', "
                        + "'" + p.getProduccionhdia() + "', "
                        + "'" + p.getProduccionhmes() + "', "
                        + "'" + p.getProducciongdia() + "', "
                        + "'" + p.getProducciongmes() + "', "
                        + "'" + Redondear.redondear(p.getProducciontotaldia(), 3) + "', "
                        + "'" + Redondear.redondear(p.getProducciontotalmes(), 3) + "', "
                        + "'" + p.getMes() + "', "
                        + "'" + p.getAnio() + "',"
                        + "'" + p.getMunicipio().getIdmunicipio() + "',"
                        + "'" + p.getContrato().getIdcontrato() + "')";
                st.execute(sql);
                resultado = 1;
                st.close();
                con.close();
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

    public Produccion consultarProduccionCampoContrato(Produccion p) throws Exception {

        Produccion produccion = new Produccion();
        try {
            Connection con = pool.dataSource.getConnection();
            Statement st = con.createStatement();
            try {
                String sql = "SELECT p.idproduccion, p.idcampo, p.produccionhdia, p.produccionhmes, p.producciongdia, "
                        + "p.producciongmes, p.producciontotaldia, p.producciontotalmes, p.mes, p.anio, p.idmunicipio, p.idcontrato, "
                        + "c.nombre, cr.nombre, cr.idtipo, cr.cib, cr.car, cr.cov, m.nombre "
                        + "FROM public.produccion as p "
                        + "JOIN public.campos as c "
                        + "ON p.idcampo = c.idcampo "
                        + "JOIN public.contratos as cr "
                        + "ON p.idcontrato = cr.idcontrato "
                        + "JOIN public.municipios as m "
                        + "ON p.idmunicipio = m.idmunicipio "
                        + "WHERE "
                        + "(p.anio = '" + p.getAnio() + "' AND "
                        + "p.mes = '" + p.getMes() + "' AND "
                        + "p.idcampo = '" + p.getCampo().getIdcampo() + "' AND "
                        + "p.idcontrato = '" + p.getContrato().getIdcontrato() + "' AND "
                        + "p.idmunicipio = '" + p.getMunicipio().getIdmunicipio() + "') "
                        + "OR p.idproduccion = '" + p.getIdproduccion() + "'";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    produccion = new Produccion(rs.getInt(1),
                            rs.getDouble(3),
                            rs.getDouble(4),
                            rs.getDouble(5),
                            rs.getDouble(6),
                            rs.getDouble(7),
                            rs.getInt(9),
                            rs.getInt(10),
                            rs.getDouble(8),
                            new Campo(rs.getInt(2), rs.getString(13)),
                            new Contrato(rs.getInt(12), rs.getString(14), rs.getInt(16), rs.getInt(17), rs.getInt(18), new Tipo(rs.getInt(15))),
                            new Municipio(rs.getInt(11), rs.getString(19)));
                }
                rs.close();
                st.close();
                con.close();
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        }
        return produccion;
    }

    public boolean verificarRegistroProduccionMunicipio(Municipio municipio, int anio, int mes) throws Exception {

        boolean valor = false;
        try {
            Connection con = pool.dataSource.getConnection();
            Statement st = con.createStatement();
            try {
                String sql = "SELECT Distinct true FROM public.produccion where exists(select * from public.produccion where idmunicipio = '" + municipio.getIdmunicipio() + "' AND anio = '" + anio + "' AND mes = '" + mes + "')";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    valor = rs.getBoolean(1);
                }
                rs.close();
                st.close();
                con.close();
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        }
        return valor;
    }
}
