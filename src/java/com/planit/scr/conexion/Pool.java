/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.conexion;

/**
 *
 * @author VaioDevelopment
 */
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;

public class Pool {
    public DataSource dataSource;
    
    //public String db = "planitco_scr";
    public String db = "planitco_scrdb";
    //public String db = "SCR";

    //public String user = "postgres";
    public String user = "planitco_scruser";

    //public String clave = "123456";
    public String clave = "yU7eywfXILoZjtaD";

    //public String url = "jdbc:postgresql://localhost:5432/" + db + "";
    //public String url = "jdbc:postgresql://70.38.109.121:5432/" + db + "";
    public String url = "jdbc:postgresql://190.146.144.80:5432/" + db + "";

    public Pool() {
        inicializaDataSource();
    }

    private void inicializaDataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("org.postgresql.Driver");
        basicDataSource.setUsername(user);
        basicDataSource.setPassword(clave);
        basicDataSource.setUrl(url);
        basicDataSource.setMaxActive(50);
        basicDataSource.setMaxIdle(50);
        dataSource = basicDataSource;
    }
}
