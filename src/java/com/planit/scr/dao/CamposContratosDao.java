/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.dao;

import com.planit.scr.conexion.Pool;
import com.planit.scr.modelos.Campo;
import com.planit.scr.modelos.Contrato;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author VaioDevelopment
 */
public class CamposContratosDao {

    private final Pool pool = new Pool();

    public int registrarMunicipioContrato(Campo campo, Contrato contrato) throws Exception {

        int resultado = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = pool.dataSource.getConnection();
            st = con.createStatement();
            try {
                String sql = "INSERT INTO public.campos_contratos (idcampo, idcontrato) "
                        + "VALUES('" + campo.getIdcampo() + "', '" + contrato.getIdcontrato() + "')";
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

    public int eliminarCamposContrato(Campo campo, Contrato contrato) throws Exception {

        int resultado = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = pool.dataSource.getConnection();
            st = con.createStatement();
            try {
                String sql = "DELETE FROM public.campos_contratos "
                        + "WHERE idcampo = '" + campo.getIdcampo() + "' and idcontrato = '" + contrato.getIdcontrato() + "'";
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

}
