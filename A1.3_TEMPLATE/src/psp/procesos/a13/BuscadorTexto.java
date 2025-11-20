package psp.procesos.a13;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

public class BuscadorTexto extends JFrame {

    public BuscadorTexto() {
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    public void buscarTexto(String idFichero, String opcionBucar) throws IOException {

        String nombreFichero = "texto" + idFichero + ".txt";
        String palabraBuscada = opcionBucar;
        int contador = 0;

        try (BufferedReader bf= new BufferedReader(new FileReader(nombreFichero))) {
            String linea;
            while ((linea = bf.readLine()) != null) {
                if (linea.equals(palabraBuscada)) {
                    contador++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(this,"Valores encontrados: "+contador+
                "\nOpción -> "+opcionBucar+
                "\nFichero -> "+idFichero);
        System.out.println(contador);
        System.exit(0);
    }


    public static void main(String[] args) throws IOException {
    BuscadorTexto buscadorTexto = new BuscadorTexto();
    buscadorTexto.buscarTexto(args[0],args[1]);
    }

}
