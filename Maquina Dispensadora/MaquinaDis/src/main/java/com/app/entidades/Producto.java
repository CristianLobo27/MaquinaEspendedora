/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.entidades;

import java.sql.Blob;

/**
 *
 * @author Usuario
 */
public class Producto {
    private int idProducto;
    private String nombre;
    private int unidades;
    private double precio;
    private Blob imagen;

    public Producto(int idProducto, String nombre, int unidades, double precio) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.unidades = unidades;
        this.precio = precio;
    }

    public Producto(int idProducto, String nombre, int unidades, double precio, Blob imagen) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.unidades = unidades;
        this.precio = precio;
        this.imagen = imagen;
    }

 
    
    

    public Producto(String nombre, int unidades, double precio) {
        this.nombre = nombre;
        this.unidades = unidades;
        this.precio = precio;
    }

    public Producto() {
    }
    
    

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Blob getImagen() {
        return imagen;
    }

    public void setImagen(Blob imagen) {
        this.imagen = imagen;
    }
    

    @Override
    public String toString() {
        return "Producto{" + "nombre=" + nombre + ", unidades=" + unidades + ", precio=" + precio + '}';
    }
    
    
}
