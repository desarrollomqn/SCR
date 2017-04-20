/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.dao;

import com.planit.scr.conexion.Pool;
import com.planit.scr.modelos.Contrato;
import com.planit.scr.modelos.Municipio;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author VaioDevelopment
 */
public class MunicipiosContratosDao {

    private final Pool pool = new Pool();

    public int registrarMunicipioContrato(Municipio municipío, Contrato contrato) throws Exception {

        int resultado = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = pool.dataSource.getConnection();
            st = con.createStatement();
            try {
                String sql = "INSERT INTO public.municipios_contratos (idmunicipio, idcontrato) "
                        + "VALUES('" + municipío.getIdmunicipio() + "', '" + contrato.getIdcontrato() + "')";
                st.execute(sql);
                st.close();
                con.close();
                resultado = 1;
            } catch (SQLException e) {
                System.out.println("Error sql" + e);
                throw e;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if(con != null && st != null){
                st.close();
                con.close();
            }
        }
        return resultado;
    }

    public int eliminarMunicipioContrato(Municipio municipío, Contrato contrato) throws Exception {

        int resultado = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = pool.dataSource.getConnection();
            st = con.createStatement();
            try {
                String sql = "DELETE FROM public.municipios_contratos "
                        + "WHERE idmunicipio = '" + municipío.getIdmunicipio() + "' and idcontrato = '" + contrato.getIdcontrato() + "'";
                st.execute(sql);               
                resultado = 1;
            } catch (SQLException e) {
                System.out.println("Error sql" + e);
                throw e;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if(con != null && st != null){
                st.close();
                con.close();
            }
        }
        return resultado;
    }
}
