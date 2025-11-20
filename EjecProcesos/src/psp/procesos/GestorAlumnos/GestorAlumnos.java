package psp.procesos.GestorAlumnos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestorAlumnos extends JFrame implements ActionListener {

    private JLabel etiqueta;
    private JTextField campo;
    private JButton boton;

    public GestorAlumnos(){
        super();
        configurarGUI();
        iniciarComponentes();

    }

    private void configurarGUI(){
        this.setTitle("Gestor Alumnos");
        this.setSize(600,500);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void iniciarComponentes(){
        etiqueta = new JLabel();
        campo  = new JTextField();
        boton = new JButton();

        etiqueta.setText("<html><B><I>Añadir alumno</I></B></html");
        etiqueta.setBounds(50 , 100, 200, 20);

        campo.setBounds(50,200,200,20);

        boton.setText("Pulsar");
        boton.setBounds(100,300,80,80);
        boton.addActionListener(this);
        boton.setActionCommand("Buscar");

        this.add(etiqueta);
        this.add(campo);
        this.add(boton);

        JPanel panel = (JPanel) this.getContentPane();
        panel.revalidate();
        panel.repaint();

    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        String comando=evento.getActionCommand();
        if(comando.equals("Buscar")){
            String texto=campo.getText();
            JOptionPane.showConfirmDialog(this,"Selecciona");
            JOptionPane.showMessageDialog(this,"Botón pulsado, texto: "+texto);
        }
    }

    public static void main(String[] args) {
        GestorAlumnos ga= new GestorAlumnos();

    }

}
