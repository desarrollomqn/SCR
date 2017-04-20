/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.dao;

import com.planit.scr.conexion.Pool;
import com.planit.scr.metodos.Redondear;
import com.planit.scr.modelos.Contrato;
import com.planit.scr.modelos.Pbl;
import com.planit.scr.modelos.Valores;
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
public class PblDao {

    private final Pool pool = new Pool();

    public int registrarPbl(Valores valores) throws Exception {
        int resultado = 0;

        Connection con = null;
        Statement st = null;

        ContratosDao contratosDao = new ContratosDao();
        CalidadCrudoDao calidadCrudoDao = new CalidadCrudoDao();
        PonderadosRefinacionDao ponderadosRefinacionDao = new PonderadosRefinacionDao();
        TrmDao trmDao = new TrmDao();

        List<Contrato> contratos = contratosDao.consultarContratos();
        Pbl pbl = new Pbl();

        double pf = ponderadosRefinacionDao.ConsultarPFPonderado(valores.getAnio(), valores.getTrimestreMes());
        double px = calidadCrudoDao.consultarPrecioReferenciaExportacion(valores.getAnio(), valores.getTrimestreMes());
        if (valores.getAnio() <= 2012) {
            pf = (pf * 42) / trmDao.consultarPromedioTrimestralTrm(valores.getTrimestreMes(), valores.getAnio());
        } else {
            pf = (pf * 42) / trmDao.consultarPromedioMensualTrm(valores.getTrimestreMes(), valores.getAnio());
        }

        try {
            con = pool.dataSource.getConnection();
            st = con.createStatement();
            try {
                for (int i = 0; i < contratos.size(); i++) {
                    pbl = new Pbl();
                    pbl.setCtc((valores.getCtc() * contratos.get(i).getCib()));
                    pbl.setCt1((pbl.getCtc() + valores.getCtmc() + valores.getCtmd() + valores.getCmt() + valores.getCr()));
                    pbl.setCce((valores.getCce() * contratos.get(i).getCov()));
                    pbl.setCt2((pbl.getCce() + valores.getCtme()));
                    pbl.setRefinacion(((pf - pbl.getCt1()) * (valores.getV1() / valores.getVt())));
                    pbl.setExportacion(((px - pbl.getCt2()) * (valores.getV2() / valores.getVt())));
                    pbl.setPrc(pbl.getRefinacion() + pbl.getExportacion());
                    pbl.setContrato(contratos.get(i));
                    pbl.setAnio(valores.getAnio());
                    pbl.setTrimestreMes(valores.getTrimestreMes());

                    String sql = "INSERT INTO public.pbl (ctc, ct1, cce, ct2, trimestre_mes, prc, anio, refinacion, exportacion, idcontrato)"
                            + " VALUES('" + Redondear.redondear(pbl.getCtc(), 2) + "',"
                            + " '" + Redondear.redondear(pbl.getCt1(), 2) + "',"
                            + " '" + Redondear.redondear(pbl.getCce(), 2) + "',"
                            + " '" + Redondear.redondear(pbl.getCt2(), 2) + "',"
                            + " " + pbl.getTrimestreMes() + ","
                            + " '" + Redondear.redondear(pbl.getPrc(), 2) + "',"
                            + " " + pbl.getAnio() + ","
                            + " '" + Redondear.redondear(pbl.getRefinacion(), 2) + "',"
                            + " '" + Redondear.redondear(pbl.getExportacion(), 2) + "',"
                            + " " + pbl.getContrato().getIdcontrato() + ")";
                    st.execute(sql);
                    resultado = 1;
                }
                st.close();
                con.close();
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (con != null & st != null) {
                st.close();
                con.close();
            }
        }
        return resultado;
    }

    public int modificarPbl(Valores valores, int anio, int trimestreMes) throws Exception {
        int resultado = 0;

        Connection con = null;
        Statement st = null;

        ContratosDao contratosDao = new ContratosDao();
        CalidadCrudoDao calidadCrudoDao = new CalidadCrudoDao();
        PonderadosRefinacionDao ponderadosRefinacionDao = new PonderadosRefinacionDao();
        TrmDao trmDao = new TrmDao();

        List<Contrato> contratos = contratosDao.consultarContratos();
        Pbl pbl = new Pbl();

        double pf = ponderadosRefinacionDao.ConsultarPFPonderado(valores.getAnio(), valores.getTrimestreMes());
        double px = calidadCrudoDao.consultarPrecioReferenciaExportacion(valores.getAnio(), valores.getTrimestreMes());
        if (valores.getAnio() <= 2012) {
            pf = (pf * 42) / trmDao.consultarPromedioTrimestralTrm(valores.getTrimestreMes(), valores.getAnio());
        } else {
            pf = (pf * 42) / trmDao.consultarPromedioMensualTrm(valores.getTrimestreMes(), valores.getAnio());
        }

        try {
            con = pool.dataSource.getConnection();
            st = con.createStatement();
            try {
                for (int i = 0; i < contratos.size(); i++) {
                    pbl = new Pbl();
                    pbl.setCtc((valores.getCtc() * contratos.get(i).getCib()));
                    pbl.setCt1((pbl.getCtc() + valores.getCtmc() + valores.getCtmd() + valores.getCmt() + valores.getCr()));
                    pbl.setCce((valores.getCce() * contratos.get(i).getCov()));
                    pbl.setCt2((pbl.getCce() + valores.getCtme()));
                    pbl.setRefinacion(((pf - pbl.getCt1()) * (valores.getV1() / valores.getVt())));
                    pbl.setExportacion(((px - pbl.getCt2()) * (valores.getV2() / valores.getVt())));
                    pbl.setPrc(pbl.getRefinacion() + pbl.getExportacion());
                    pbl.setContrato(contratos.get(i));
                    pbl.setAnio(valores.getAnio());
                    pbl.setTrimestreMes(valores.getTrimestreMes());

                    String sql = "UPDATE public.pbl SET ctc = '" + Redondear.redondear(pbl.getCtc(), 2) + "', "
                            + "ct1 = '" + Redondear.redondear(pbl.getCt1(), 2) + "', "
                            + "cce = '" + Redondear.redondear(pbl.getCce(), 2) + "', "
                            + "ct2 = '" + Redondear.redondear(pbl.getCt2(), 2) + "', "
                            + "trimestre_mes = " + pbl.getTrimestreMes() + ", "
                            + "prc = '" + Redondear.redondear(pbl.getPrc(), 2) + "', "
                            + "anio = " + pbl.getAnio() + ", "
                            + "refinacion = '" + Redondear.redondear(pbl.getRefinacion(), 2) + "', "
                            + "exportacion = '" + Redondear.redondear(pbl.getExportacion(), 2) + "' "
                            + "WHERE idcontrato = " + contratos.get(i).getIdcontrato() + " and  anio = " + anio + " and trimestre_mes = " + trimestreMes + "";
                    st.execute(sql);
                }
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (con != null & st != null) {
                st.close();
                con.close();
            }
        }
        return resultado;
    }

    public int eliminarPbl(int anio, int trimestreMes) throws Exception {
        int resultado = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = pool.dataSource.getConnection();
            st = con.createStatement();
            try {
                String sql = "DELETE FROM public.pbl "
                        + "WHERE anio = " + anio + " and trimestre_mes = " + trimestreMes + "";
                st.execute(sql);
                st.close();
                con.close();
                resultado = 1;
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (con != null & st != null) {
                st.close();
                con.close();
            }
        }
        return resultado;
    }

    public List<Pbl> consultarPblSegunMunicipio(Municipio municipio, Pbl pbl) throws Exception {
        List<Pbl> pbls = new ArrayList<>();
        Connection con = null;
        Statement st = null;
        try {
            con = pool.dataSource.getConnection();
            st = con.createStatement();
            try {
                String sql = "SELECT p.idpbl, p.ctc, p.ct1, p.cce, p.ct2, p.trimestre_mes, p.prc, p.refinacion, p.exportacion, p.idcontrato, "
                        + " p.anio, cr.nombre, cr.idtipo, t.nombre, cr.cib, cr.car, cr.cov "
                        + " FROM public.pbl as p, public.municipios_contratos as mc, public.contratos as cr, public.tipos as t"
                        + " WHERE p.anio = " + pbl.getAnio() + " AND p.trimestre_mes = " + pbl.getTrimestreMes() + " AND"
                        + " p.idcontrato = mc.idcontrato AND mc.idmunicipio = " + municipio.getIdmunicipio() + " AND"
                        + " p.idcontrato = cr.idcontrato AND cr.idtipo = t.idtipo";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    pbls.add(new Pbl(rs.getInt(1),
                            rs.getDouble(2),
                            rs.getDouble(3),
                            rs.getDouble(4),
                            rs.getDouble(5),
                            rs.getInt(6),
                            rs.getDouble(7),
                            rs.getDouble(8),
                            rs.getDouble(9),
                            rs.getInt(11),
                            new Contrato(rs.getInt(10), rs.getString(12), rs.getInt(15), rs.getInt(16), rs.getInt(17), new Tipo(rs.getInt(13), rs.getString(14)))));
                }
                rs.close();
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (con != null & st != null) {
                st.close();
                con.close();
            }
        }
        return pbls;
    }

    public Pbl consultarPblSegunContrato(Contrato contrato, int trimestre_mes, int anio) throws Exception {
        Pbl pbl = new Pbl();
        Connection con = null;
        Statement st = null;
        try {
            con = pool.dataSource.getConnection();
            st = con.createStatement();
            try {
                String sql = "SELECT p.idpbl, p.ctc, p.ct1, p.cce, p.ct2, p.trimestre_mes, p.prc, p.refinacion, p.exportacion, p.idcontrato,"
                        + " p.anio, cr.nombre, cr.idtipo, t.nombre, cr.cib, cr.car, cr.cov"
                        + " FROM public.pbl as p, public.contratos as cr, public.tipos as t"
                        + " WHERE p.anio = " + anio + " AND p.trimestre_mes = " + trimestre_mes + " AND"
                        + " p.idcontrato = " + contrato.getIdcontrato() + " AND p.idcontrato = cr.idcontrato AND cr.idtipo = t.idtipo";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    pbl = new Pbl(rs.getInt(1),
                            rs.getDouble(2),
                            rs.getDouble(3),
                            rs.getDouble(4),
                            rs.getDouble(5),
                            rs.getInt(6),
                            rs.getDouble(7),
                            rs.getDouble(8),
                            rs.getDouble(9),
                            rs.getInt(11),
                            new Contrato(rs.getInt(10), rs.getString(12), rs.getInt(15), rs.getInt(16), rs.getInt(17), new Tipo(rs.getInt(13), rs.getString(14))));
                }
                rs.close();
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (con != null & st != null) {
                st.close();
                con.close();
            }
        }
        return pbl;
    }

    public List<Pbl> consultarPbl(int trimestre_mes, int anio) throws Exception {
        List<Pbl> pbl = new ArrayList<>();
        Connection con = null;
        Statement st = null;
        try {
            con = pool.dataSource.getConnection();
            st = con.createStatement();
            try {
                String sql = "SELECT p.idpbl, p.ctc, p.ct1, p.cce, p.ct2, p.trimestre_mes, p.prc, p.refinacion, p.exportacion, p.idcontrato, p.anio"
                        + " FROM public.pbl as p"
                        + " WHERE p.anio = " + anio + " AND p.trimestre_mes = " + trimestre_mes + "";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    pbl.add(new Pbl(rs.getInt(1),
                            rs.getDouble(2),
                            rs.getDouble(3),
                            rs.getDouble(4),
                            rs.getDouble(5),
                            rs.getInt(6),
                            rs.getDouble(7),
                            rs.getDouble(8),
                            rs.getDouble(9),
                            rs.getInt(11),
                            new Contrato(rs.getInt(10))));
                }
                rs.close();

            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (con != null & st != null) {
                st.close();
                con.close();
            }
        }
        return pbl;
    }

    //Calculos
    public int obtenerTrimestre(int mes, int anio) {
        int valor = 0;
        if (anio <= 2012) {
            if (mes >= 1 && mes <= 3) {
                valor = 1;
            } else if (mes >= 4 && mes <= 6) {
                valor = 2;
            } else if (mes >= 7 && mes <= 9) {
                valor = 3;
            } else if (mes >= 10 && mes <= 12) {
                valor = 4;
            }
        } else {
            valor = mes;
        }
        return valor;
    }
}
