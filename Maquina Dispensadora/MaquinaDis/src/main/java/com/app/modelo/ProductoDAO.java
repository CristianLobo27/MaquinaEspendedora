/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.modelo;

import com.app.conexion.ConexionBD;
import com.app.entidades.Producto;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class ProductoDAO {

    ConexionBD conexion = new ConexionBD();

    //Devuelvo un ResultSet para que en funciones pueda llenar un ArrayList con los priductos
    public ResultSet devolverProductos() {
        ResultSet rs = null;
        try {
            Connection cn = conexion.abrirConexion();
            String sql = "SELECT * FROM productos";
            Statement st = cn.createStatement();
            rs = st.executeQuery(sql);

          
        } catch (SQLException e) {

        }
        return rs;
    }
       public void actualizarProductos(Producto p) {
        int unidadespro=p.getUnidades();
        try (Connection con =conexion.abrirConexion()) {
            String sql = "UPDATE productos SET Unidades = ? where id_producto =?";
            try (PreparedStatement ps2 = con.prepareStatement(sql)) {
                ps2.setInt(1, (unidadespro-1));
                ps2.setInt(2, p.getIdProducto());
                ps2.executeUpdate();
                JOptionPane.showMessageDialog(null, "Se ha retirado el producto");
                ps2.close();
            }
        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(null, "Error al hacer la actualizacion");
            ex.printStackTrace();
        }
    }

}
