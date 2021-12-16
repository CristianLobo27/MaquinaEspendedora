/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.controlador;

import com.app.entidades.Producto;
import com.app.modelo.ProductoDAO;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class Funciones {
    
    //Introduzco todas las monedas que hay y las monedas que puedo utilizar

    private DecimalFormat df = new DecimalFormat("#.####");
    ProductoDAO consultas = new ProductoDAO();
    private int billetes20 = 30;
    private int billetes10 = 30;
    private int billetes5 = 30;
    private int monedas2 = 30;
    private int monedas1 = 30;
    private int monedas50 = 30;
    private int monedas20 = 30;
    private int monedas10 = 30;
    private int monedas5 = 30;
    private double monedas[] = {20, 10, 5, 2, 1, 0.5, 0.2, 0.1, 0.05};

    public void devolucion(double importeProducto, double pago) {
        //Devuelvo la moneda más alta que puedo devolver con el resto de el importe a devolver y las monedas en curso que puedo utilizar
        double importeADevolver = pago - importeProducto;
        String mensajeDevolucion = "";
        for (int i = 0; i < monedas.length && importeADevolver > 0; i++) {

            int division = (int) (importeADevolver / monedas[i]);

            if (division > 0) {

                switch (i) {

                    case 0:

                        if (billetes20 >= division) {
                            billetes20 -= division;
                            mensajeDevolucion += "Billetes de 20 €: " + division + "\n";
                            importeADevolver -= monedas[i] * division;

                            String redondeado = df.format(importeADevolver);
                            importeADevolver = Double.parseDouble(redondeado.replace(",", "."));
                            billetes20++;
                        }
                        break;

                    case 1:

                        if (billetes10 >= division) {
                            billetes10 -= division;
                            mensajeDevolucion += "Billetes de 10 €: " + division + "\n";
                            importeADevolver -= monedas[i] * division;

                            String redondeado = df.format(importeADevolver);
                            importeADevolver = Double.parseDouble(redondeado.replace(",", "."));
                            billetes10++;
                        }

                        break;

                    case 2:
                        if (billetes5 >= division) {
                            billetes5 -= division;
                            mensajeDevolucion += "Billetes de 5 €: " + division + "\n";
                            importeADevolver -= monedas[i] * division;

                            String redondeado = df.format(importeADevolver);
                            importeADevolver = Double.parseDouble(redondeado.replace(",", "."));
                            billetes5++;
                        }

                        break;

                    case 3:
                        if (monedas2 >= division) {
                            monedas2 -= division;
                            mensajeDevolucion += "Monedas de 2€: " + division + "\n";
                            importeADevolver -= monedas[i] * division;

                            String redondeado = df.format(importeADevolver);
                            importeADevolver = Double.parseDouble(redondeado.replace(",", "."));
                            monedas2++;
                        }

                        break;

                    case 4:
                        if (monedas1 >= division) {
                            monedas1 -= division;
                            mensajeDevolucion += "Monedas de 1€: " + division + "\n";
                            importeADevolver -= monedas[i] * division;

                            String redondeado = df.format(importeADevolver);
                            importeADevolver = Double.parseDouble(redondeado.replace(",", "."));
                            monedas1++;
                        }

                        break;

                    case 5:
                        if (monedas50 >= division) {
                            monedas50 -= division;
                            mensajeDevolucion += "Monedas de 50 céntimos: " + division + "\n";
                            importeADevolver -= monedas[i] * division;

                            String redondeado = df.format(importeADevolver);
                            importeADevolver = Double.parseDouble(redondeado.replace(",", "."));
                            monedas50++;
                        }

                        break;

                    case 6:
                        if (monedas20 >= division) {
                            monedas20 -= division;
                            mensajeDevolucion += "Monedas de 20 céntimos: " + division + "\n";
                            importeADevolver -= monedas[i] * division;

                            String redondeado = df.format(importeADevolver);
                            importeADevolver = Double.parseDouble(redondeado.replace(",", "."));
                            monedas20++;
                        }

                        break;
                    case 7:
                        if (monedas10 >= division) {
                            monedas10 -= division;
                            mensajeDevolucion += "Monedas de 10 céntimos: " + division + "\n";
                            importeADevolver -= monedas[i] * division;

                            String redondeado = df.format(importeADevolver);
                            importeADevolver = Double.parseDouble(redondeado.replace(",", "."));
                            monedas10++;
                        }

                        break;

                    case 8:
                        if (monedas5 >= division) {
                            monedas5 -= division;
                            mensajeDevolucion += "Monedas de 5 céntimos: " + division + "\n";
                            importeADevolver -= monedas[i] * division;

                            String redondeado = df.format(importeADevolver);
                            importeADevolver = Double.parseDouble(redondeado.replace(",", "."));
                            monedas5++;
                        }

                        break;

                }

            }

        }

        JOptionPane.showMessageDialog(null, "El cambio a devolver del producto cuyo precio es " + importeProducto + "€ y el pago ha sido de " + df.format(pago) + "€ es: \n" + mensajeDevolucion);

    }
    
       
   public ArrayList<Producto> devolverProductos2(){
     
       ArrayList <Producto> listaProductos = new ArrayList();
       ResultSet rs=null;
     try{
      
         rs=consultas.devolverProductos();
         
         
         while (rs.next()){
             
             Producto producto = new Producto(rs.getInt("id_Producto"), rs.getString("Nombre"), rs.getInt("Unidades"), rs.getDouble("Precio"), rs.getBlob("Imagen"));

             listaProductos.add(producto);
         }
         
     }catch(Exception e){
         
     }
       
     return listaProductos;
   } 
   
   
    
   public void actualizarProductos(Producto p){
       consultas.actualizarProductos(p);
   }
    
}
