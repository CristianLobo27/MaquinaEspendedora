/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Usuario
 */
public class ConexionBD {

    private String rutaDB = "jdbc:mysql://localhost:3306/maquinadispensadora";
    private String login = "root";
    private String passwd = "";

    public Connection abrirConexion() throws SQLException {

        return DriverManager.getConnection(rutaDB, login, passwd);
    }
}
