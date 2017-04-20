/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.controladores;

import com.planit.scr.conexion.ConexionSQL;
import com.planit.scr.dao.DepartamentosDao;
import com.planit.scr.modelos.Departamento;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;

/**
 *
 * @author Desarrollo_Planit
 */
public class DepartamentoCT {

    private Departamento departamento;
    private List<Departamento> departamentos;
    
    public DepartamentoCT() {
        departamento = new Departamento();
        departamentos = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        DepartamentosDao departamentoDao = new DepartamentosDao();
        try {
            departamentos = departamentoDao.consultarDepartamentos();
        } catch (Exception ex) {
            Logger.getLogger(DepartamentoCT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }

    //Metodos 
    public void registrar() throws Exception {
        DepartamentosDao departamentoDao = new DepartamentosDao();
        departamentoDao.registrarDepartamento(departamento);
        departamento = new Departamento();
        departamentos = departamentoDao.consultarDepartamentos();
    }  
}
