/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.vista;

import com.app.controlador.Funciones;
import com.app.entidades.Producto;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author Usuario
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    Producto[][] arrayProductos = new Producto[4][10];

    JLabel codigoAlmacenado = new JLabel();
    double dineroacumu = 0;
    JLabel dineroAcumuladoCantidad = new JLabel();
    Producto pro = new Producto();
    DecimalFormat formato1 = new DecimalFormat("#.####");
    int contadorColumnas = 0;
    int contadorFilas = 0;

    Funciones funciones = new Funciones();

    /**
     * Creates new form VentanaPrincipal
     */
    public VentanaPrincipal() {
        initComponents();

        try {
            setFrame();

        } catch (Exception e) {

        }

    }

    public void setFrame() throws IOException {
        
        //Creo un BorderLayout donde en el centro van a ir todos los productos y sus respectivas descripciones y en el este del BorderLayout
        //Creo diferentes GridLayouts para los botones del dinero y para los botones de seleccion de productos

        this.setLocationRelativeTo(null);
        this.getContentPane().setLayout(new BorderLayout());

        this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        Border bordeMonedas = BorderFactory.createTitledBorder("Introduzca las monedas");
        Border bordeBotones = BorderFactory.createTitledBorder("Introduzca el producto que desee");
        Border bordeDinero = BorderFactory.createTitledBorder("Seleccion del producto");
        Border bordeproductos = BorderFactory.createTitledBorder("Selección de productos");

        JPanel panelgeneral = new JPanel();
        panelgeneral.setLayout(new GridLayout(0, 1));
        JPanel paneldinero = new JPanel();

        JLabel codigoProducto = new JLabel();
        codigoProducto.setText("Artículo en selección..");
        JLabel dineroAcumulado = new JLabel("Dinero acumulado");
        dineroAcumulado.setSize(200, 20);

        dineroAcumuladoCantidad.setText("0");
        dineroAcumuladoCantidad.setSize(200, 20);

        Border bordeEtiqueta = BorderFactory.createLineBorder(Color.BLUE, 2);
        Border bordeEtiqueta2 = BorderFactory.createLineBorder(Color.RED, 2);
        codigoAlmacenado.setBorder(bordeEtiqueta);
        dineroAcumuladoCantidad.setBorder(bordeEtiqueta2);
        paneldinero.setLayout(new GridLayout(0, 2));
        paneldinero.add(codigoProducto);
        paneldinero.add(codigoAlmacenado);

        paneldinero.add(dineroAcumulado);
        paneldinero.add(dineroAcumuladoCantidad);
        JPanel panelProductos = new JPanel();
        panelProductos.setLayout(new GridLayout(0, arrayProductos[0].length));

        JPanel panelMonedas = new JPanel();
        panelMonedas.setLayout(new GridLayout(0, 3));
        JButton boton5 = new JButton("5 cent");
        panelMonedas.add(boton5);
        
        //Cada vez que pulso un botón de una moneda o billete, lo suma al dinero acumulado que se muestra por pantalla
        //Hago un formateo para los decimales
        
        boton5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dineroacumu = dineroacumu + 0.05;
                String redondeado = formato1.format(dineroacumu);
                dineroAcumuladoCantidad.setText(redondeado.replace(",", "."));
            }
        });
        JButton boton10 = new JButton("10 cent");
        panelMonedas.add(boton10);
        boton10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dineroacumu = dineroacumu + 0.1;
                String redondeado = formato1.format(dineroacumu);
                dineroAcumuladoCantidad.setText(redondeado.replace(",", "."));
            }
        });
        JButton boton20 = new JButton("20 cent");
        panelMonedas.add(boton20);
        boton20.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dineroacumu = (dineroacumu + 0.2);
                String redondeado = formato1.format(dineroacumu);
                dineroAcumuladoCantidad.setText(redondeado.replace(",", "."));
            }
        });
        JButton boton50 = new JButton("50 cent");
        panelMonedas.add(boton50);
        boton50.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dineroacumu = (dineroacumu + 0.5);
                String redondeado = formato1.format(dineroacumu);
                dineroAcumuladoCantidad.setText(redondeado.replace(",", "."));
            }
        });
        JButton boton100 = new JButton("1€");
        panelMonedas.add(boton100);
        boton100.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dineroacumu = (dineroacumu + 1);
                String redondeado = formato1.format(dineroacumu);
                dineroAcumuladoCantidad.setText(redondeado.replace(",", "."));
            }
        });
        JButton boton200 = new JButton("2€");
        panelMonedas.add(boton200);
        boton200.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dineroacumu = (dineroacumu + 2);
                String redondeado = formato1.format(dineroacumu);
                dineroAcumuladoCantidad.setText(redondeado.replace(",", "."));
            }
        });
        JButton boton500 = new JButton("5€");
        panelMonedas.add(boton500);
        boton500.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dineroacumu = (dineroacumu + 5);
                String redondeado = formato1.format(dineroacumu);
                dineroAcumuladoCantidad.setText(redondeado.replace(",", "."));
            }
        });
        JButton boton1000 = new JButton("10€");
        panelMonedas.add(boton1000);
        boton1000.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dineroacumu = (dineroacumu + 10);
                String redondeado = formato1.format(dineroacumu);
                dineroAcumuladoCantidad.setText(redondeado.replace(",", "."));

            }
        });
        JButton boton2000 = new JButton("20€");
        panelMonedas.add(boton2000);
        boton2000.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dineroacumu = (dineroacumu + 20);
                String redondeado = formato1.format(dineroacumu);
                dineroAcumuladoCantidad.setText(redondeado.replace(",", "."));
            }
        });

        panelMonedas.setBorder(bordeMonedas);
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.PAGE_AXIS));
        panelBotones.setLayout(new GridLayout(0, 4));

        //creamos los botones
        //panelBotones.add(Box.createVerticalGlue());
        JButton botona = new JButton("A");
        panelBotones.add(botona);
        
        //Al pulsar el botón A, al igual que en los demás botones, obtengo el texto y compruebo la longitud para ver si puedo añadir
        //una letra o numero más. Como el codigo de los productos solo puede estar formado de una letra y un número, solo dejo que pueda estar 
        //formado por una letra y un número
        
        botona.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textoActual = codigoAlmacenado.getText();

                if (textoActual.length() <= 1) {
                    codigoAlmacenado.setText(textoActual + botona.getText());
                }
            }
        });
        JButton botonb = new JButton("B");
        panelBotones.add(botonb);
        botonb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textoActual = codigoAlmacenado.getText();
                if (textoActual.length() <= 1) {
                    codigoAlmacenado.setText(textoActual + botonb.getText());
                }
            }
        });
        JButton botonc = new JButton("C");
        panelBotones.add(botonc);
        botonc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textoActual = codigoAlmacenado.getText();
                if (textoActual.length() <= 1) {
                    codigoAlmacenado.setText(textoActual + botonc.getText());
                }
            }
        });
        JButton botond = new JButton("D");
        panelBotones.add(botond);
        botond.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textoActual = codigoAlmacenado.getText();
                if (textoActual.length() <= 1) {
                    codigoAlmacenado.setText(textoActual + botond.getText());
                }
            }
        });

        JButton botonok = new JButton("OK");
        //En el boton devolucion, recojo las monedas que me han quedado en el dinero acumulado
        JButton botondevolucion = new JButton("Devolución");
        botondevolucion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Recoja sus monedas");
                codigoAlmacenado.setText("");
                dineroacumu = 0;
                dineroAcumuladoCantidad.setText(String.valueOf(dineroacumu));
            }
        });

        //En el boton ok, recojo la informacion que necesito para saber que boton he pulsado
        botonok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String textoActual = codigoAlmacenado.getText().trim();
                //recojo el texto del codigo almacenado sin espacios
                //cojo la letra en una variable
                String letra = textoActual.substring(0, 1);
                //obtengo el número en una variable
                int numero = Integer.parseInt(textoActual.substring(1));
                //Al pulsar la combinación de letras y numeros que deseo, saco por pantalla el producto que he elegido
                //y compruebo si existe o no
                try {
                    if (letra.equals("A")) {

                        pro = arrayProductos[0][numero];

                        JOptionPane.showMessageDialog(null, pro.getNombre());
                    } else if (letra.equals("B")) {
                        pro = arrayProductos[1][numero];
                        JOptionPane.showMessageDialog(null, pro.getNombre());
                    } else if (letra.equals("C")) {
                        pro = arrayProductos[2][numero];
                        JOptionPane.showMessageDialog(null, pro.getNombre());
                    } else if (letra.equals("D")) {
                        pro = arrayProductos[3][numero];
                        JOptionPane.showMessageDialog(null, pro.getNombre());
                    } else {

                        JOptionPane.showMessageDialog(null, "El artículo no existe!");
                    }

                    codigoAlmacenado.setText("");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "El artículo no existe!");
                    codigoAlmacenado.setText("");
                }

                //Comprobaciones de las unidades y el dinero necesario para llevar a cabo la compra del producto
                if (pro.getUnidades() <= 0) {

                    JOptionPane.showMessageDialog(null, "Producto agotado!");
                } else {
                    if (dineroacumu > pro.getPrecio()) {

                        JOptionPane.showMessageDialog(null, "Compra realizada correctamente");
                        //Realizo la devolución del dinero
                        funciones.devolucion(pro.getPrecio(), dineroacumu);
                        double dineroproducto = pro.getPrecio();
                        
                        dineroacumu = dineroacumu - dineroproducto;
                        dineroAcumuladoCantidad.setText(formato1.format(dineroacumu));
                        //Actualizo el producto en la base de datos
                        funciones.actualizarProductos(pro);

                    } else {
                        JOptionPane.showMessageDialog(null, "Dinero insuficiente, introduzca más monedas!");
                    }
                }
            }
        });
        
        //Creación de los botones del 0-9 para poder elegir el producto

        for (int i = 0; i <= 9; i++) {

            JButton boton = new JButton(String.valueOf(i));

            panelBotones.add(boton);
            
            //Introduzco en el codigo almacenado el boton y hago la misma comprobación que en las letras
            boton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    String textoActual = codigoAlmacenado.getText();

                    if (textoActual.length() <= 1) {
                        codigoAlmacenado.setText(textoActual + boton.getText());
                    }
                }
            });

        }

        //Introduccion de los botones, de los bordes y de los paneles en los paneles correspondientes y en el panel general
        panelBotones.add(botonok);
        panelBotones.setBorder(bordeBotones);
        paneldinero.setBorder(bordeDinero);
        panelProductos.setBorder(bordeproductos);
        panelBotones.add(botondevolucion);
        panelgeneral.add(paneldinero);
        panelgeneral.add(paneldinero);
        panelgeneral.add(panelMonedas);
        panelgeneral.add(panelBotones);

        this.getContentPane().add(panelgeneral, BorderLayout.EAST);
        //Obtengo el Producto base de datos       
        //Para sacar los objetos de la base de datos utilizo un array Bidimensional
        //Recupero las imágenes de la base de datos con un blob
        //Cada vez que se introduce un producto en una de las etiquetas se incrementa el contador de las columnas
        //Cuando el contador de las columnas es igual al numero de columas que yo le asigno al principio del programa, 
        //estas se vuelven 0 e incremento el contador de las filas y se vuelve a realizar la misma operacion.
        //Cuando el numero de columnas y el de filas llega al mismo numero de filas y columnas y filas que yo le asigno al principio
        //No se introducen más productos en las etiquetas
        try {

            ArrayList<Producto> listaProductos = funciones.devolverProductos2();

            for (int i = 0; i < listaProductos.size(); i++) {

                Producto producto = new Producto(listaProductos.get(i).getIdProducto(), listaProductos.get(i).getNombre(), listaProductos.get(i).getUnidades(), listaProductos.get(i).getPrecio());
                Blob image = listaProductos.get(i).getImagen();
                if (contadorColumnas == arrayProductos[0].length) {

                    contadorFilas++;
                    contadorColumnas = 0;
                }
                arrayProductos[contadorFilas][contadorColumnas] = producto;
                contadorColumnas++;

                JLabel etiqueta = new JLabel();
                etiqueta.setSize(10, 50);


                Image imagen = javax.imageio.ImageIO.read(image.getBinaryStream());
                ImageIcon icono = new ImageIcon(imagen);
                etiqueta.setIcon(icono);
                //Hago que el texto que introduzco en las etiquetas para saber su descripción salga abajo
                etiqueta.setVerticalTextPosition(JLabel.BOTTOM);
                etiqueta.setHorizontalTextPosition(JLabel.CENTER);
                System.out.println();
                //Hago un método para introducir el boton que debo pulsar para cada producto ej: (A0)
                String letras = letras();
                etiqueta.setText("("+letras + (contadorColumnas - 1)+")" + " " + listaProductos.get(i).getNombre() + " " + listaProductos.get(i).getPrecio() + "€");
                panelProductos.add(etiqueta);

            }


        } catch (SQLException e) {

        }

        this.getContentPane().add(panelProductos, BorderLayout.CENTER);

    }
    
    //Método para introducir la letra del código en el producto

    public String letras() {
        String letras = String.valueOf(contadorFilas);
        switch (contadorFilas) {
            case 0:
                letras = "A";
                break;
            case 1:
                letras = "B";
                break;
            case 2:
                letras = "C";
                break;
            case 3:
                letras = "D";
                break;
            default:
                break;
        }
        return letras;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
