package psp.procesos.a13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

    public class BuscadorTextoCLI {

        /**
         * Busca una palabra en un fichero y muestra cuántas veces aparece
         */
        public void buscarTexto(String idFichero, String opcionBuscar) {
            String nombreFichero = "texto" + idFichero + ".txt";
            String palabraBuscada = opcionBuscar;
            int contador = 0;

            try (BufferedReader bf = new BufferedReader(new FileReader(nombreFichero))) {
                String linea;
                while ((linea = bf.readLine()) != null) {
                    if (linea.equals(palabraBuscada)) {
                        contador++;
                    }
                }
            } catch (IOException e) {
                System.out.println("Error leyendo el fichero: " + nombreFichero);
                return;
            }

            // Muestra resultado en consola
            System.out.println("Valores encontrados: " + contador);
            System.out.println("Opción -> " + opcionBuscar);
            System.out.println("Fichero -> " + idFichero);
        }

        public static void main(String[] args) {
            if (args.length < 2) {
                System.out.println("Uso: java BuscadorTextoCLI <idFichero> <palabra>");
                return;
            }
            BuscadorTextoCLI buscador = new BuscadorTextoCLI();
            buscador.buscarTexto(args[0], args[1]);
        }
    }

