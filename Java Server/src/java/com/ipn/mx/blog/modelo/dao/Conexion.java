/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.blog.modelo.dao;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author andres
 */
public abstract class Conexion {

    //Agregarlos a un archivo de propiedades 
    private static final String BASE = "proyecto2";
    private static final String URL = "jdbc:mysql://localhost:3306/" + BASE + "?autoReconnect=true&useSSL=false";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    protected Connection conexion = null;

    protected boolean getConexion() {
        conexion = null;
        try {
            Class.forName(DRIVER);
            conexion = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e);
            return false;
        }
    }

    protected void cerrar(Connection cnn) {
        if (cnn != null) {
            try {
                cnn.close();
            } catch (SQLException e) {
            }
        }
    }

    protected boolean cerrar() {
        if (conexion != null) {
            try {
                conexion.close();
                return true;
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        return false;
    }

    protected void cerrar(PreparedStatement ps) throws SQLException {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
            }
        }
    }

    protected void cerrar(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
            }
        }
    }

    protected void deshacerCambios(Connection cn) {
        try {
            cn.rollback();
        } catch (SQLException e) {
            //Terminar
        }
    }
}

