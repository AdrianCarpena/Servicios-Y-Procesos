package psp.procesos.a13;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class GestorBusqueda extends JFrame implements ActionListener {

    private static final String paquetePath = "psp.procesos.a13";
    private static Process[] activos = new Process[5];


    private JLabel etiqueta1;
    private JLabel etiqueta2;
    private JLabel etiqueta3;
    private JLabel etiqueta4;
    private JLabel etiqueta5;

    private JTextField campo;
    private JTextField campo2;
    private JButton boton;
    private JButton boton2;

    private static List<String> opciones= List.of("CASA","PERRO","COCHE");
    private static List<String> ficheros = List.of("1", "2", "3", "4", "5");

        public GestorBusqueda(){
            super();
            configurarGUI();
            iniciarComponentes();

        }

        private void configurarGUI(){
            this.setTitle("Gestor Busqueda");
            this.setSize(500,400);
            this.setLocationRelativeTo(null);
            this.setLayout(null);
            this.setResizable(false);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setVisible(true);
        }

        private void iniciarComponentes(){
            etiqueta1 = new JLabel();
            etiqueta2 = new JLabel();
            etiqueta3 = new JLabel();
            etiqueta4 = new JLabel();
            etiqueta5 = new JLabel();
            campo  = new JTextField();
            campo2  = new JTextField();
            boton = new JButton();
            boton2 = new JButton();

            etiqueta1.setText("<html><h3>BUSCAR TEXTO EN FICHERO</h3></html>");
            etiqueta1.setBounds(150 , 50, 200, 20);
            etiqueta2.setText("<html><h4>Opciones Busqueda: CASA PERRO COCHE</h4></html>");
            etiqueta2.setBounds(110 , 80, 300, 30);
            etiqueta3.setText("<html><h4>Ficheros Busqueda: 1 a 5</h4></html>");
            etiqueta3.setBounds(110 , 100, 300, 30);
            etiqueta4.setText("<html><h4>Introduzca opción:</h4></html>");
            etiqueta4.setBounds(110 , 160, 300, 30);
            etiqueta5.setText("<html><h4>Introduzca Fichero:</h4></html>");
            etiqueta5.setBounds(110 , 200, 200, 30);

            campo.setBounds(250,160,110,30);
            campo2.setBounds(250,200,110,30);

            boton.setText("Buscar");
            boton.setBounds(120,260,100,30);
            boton.addActionListener(this);
            boton.setActionCommand("Buscar");
            boton2.setText("Cancelar");
            boton2.setBounds(250,260,100,30);
            boton2.addActionListener(this);
            boton2.setActionCommand("Cancelar");

            this.add(etiqueta1);
            this.add(etiqueta2);
            this.add(etiqueta3);
            this.add(etiqueta4);
            this.add(etiqueta5);
            this.add(campo);
            this.add(campo2);
            this.add(boton);
            this.add(boton2);

            JPanel panel = (JPanel) this.getContentPane();
            panel.revalidate();
            panel.repaint();

        }

        @Override
        public void actionPerformed(ActionEvent evento) {
            String comando=evento.getActionCommand();



            if(comando.equals("Buscar")){
                String opc=campo.getText();
                String fichero=campo2.getText();
                    if(activos[(Integer.parseInt(fichero))]!=null){
                    if(activos[(Integer.parseInt(fichero))].isAlive())
                        JOptionPane.showMessageDialog(this, "Ya se está haciendo una busqueda en ese fichero");
                    else {
                        activos[(Integer.parseInt(fichero))]=null;
                        LanzarProceso(fichero,opc);
                    }
                    }
                    else {
                        if(opciones.contains(opc) && ficheros.contains(fichero)) {
                           LanzarProceso(fichero,opc);
                        }

                        else if (!opciones.contains(opc) && ficheros.contains(fichero)) {
                            JOptionPane.showMessageDialog(this, "Introduciste una opcion invalida");
                        } else if (opciones.contains(opc) && !ficheros.contains(fichero)) {
                            JOptionPane.showMessageDialog(this, "Introduciste un fichero invalido");
                        } else {
                            JOptionPane.showMessageDialog(this, "Introduciste opción y fichero inválidos");
                        }
                    }

            }
            else if(comando.equals("Cancelar")){
                for(Process p:activos){
                    if(p!=null)
                        if (p.isAlive())
                            p.destroy();
                }
            }
        }

        public static void LanzarProceso(String fichero,String opc){
            ProcessBuilder pb = null;
            Process process = null;
                try {
                    pb = new ProcessBuilder("java", "-cp", System.getProperty("java.class.path"), paquetePath + ".BuscadorTexto", fichero, opc);
                    process = pb.start();
                    activos[Integer.parseInt(fichero)]=process;

                } catch (IOException e) {

                }
        }

        public static void main(String[] args) {
            GestorBusqueda gb = new GestorBusqueda();

        }

    }


