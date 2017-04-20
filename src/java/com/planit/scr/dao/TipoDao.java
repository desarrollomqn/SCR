/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.dao;

import com.planit.scr.conexion.Pool;
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
public class TipoDao {

    private final Pool pool = new Pool();

    //Metodos

    public Tipo consultarTipo(Tipo t) throws Exception {
        Tipo nuevotipo = new Tipo();
        Connection con = null;
        Statement st = null;
        try {
            con = pool.dataSource.getConnection();
            st = con.createStatement();
            try {
                String sql = "SELECT idtipo, nombre FROM public.tipos "
                        + "WHERE idtipo = " + t.getIdtipo() + " or nombre = '" + t.getNombre() + "'";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    nuevotipo = new Tipo(rs.getInt(1), rs.getString(2));
                }
                rs.close();                
            } catch (SQLException e) {
                nuevotipo = new Tipo();
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
        return nuevotipo;
    }

    public List<Tipo> consultarTipos() throws Exception {
        List<Tipo> listatipos = new ArrayList<>();
        Connection con = null;
        Statement st = null;
        try {
            con = pool.dataSource.getConnection();
            st = con.createStatement();
            try {
                String sql = "SELECT idtipo, nombre FROM public.tipos";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    listatipos.add(new Tipo(rs.getInt(1), rs.getString(2)));
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
        return listatipos;
    }
}
