package psp.procesos.a13;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestorBusquedaCLI {

    private static final String paquetePath = "psp.procesos.a13";
    private static Process[] activos = new Process[5];
    private static final List<String> opciones = List.of("CASA", "PERRO", "COCHE");
    private static final List<String> ficheros = List.of("1", "2", "3", "4", "5");

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean sigue = true;

        System.out.println("=== BUSCADOR DE TEXTO EN FICHEROS ===");
        System.out.println("Opciones disponibles: CASA, PERRO, COCHE");
        System.out.println("Ficheros disponibles: 1 a 5");
        System.out.println("Para cancelar procesos activos, escribe: cancelar");
        System.out.println("Para salir, escribe: fin");

        while (sigue) {
            System.out.print("\nIntroduce opción a buscar: ");
            String opcion = sc.nextLine().toUpperCase();

            if (opcion.equals("FIN")) {
                sigue = false;
                break;
            } else if (opcion.equals("CANCELAR")) {
                cancelarProcesos();
                continue;
            }

            System.out.print("Introduce número de fichero: ");
            String fichero = sc.nextLine();

            if (!opciones.contains(opcion) && ficheros.contains(fichero)) {
                System.out.println("Introduciste una opción inválida");
                continue;
            } else if (opciones.contains(opcion) && !ficheros.contains(fichero)) {
                System.out.println("Introduciste un fichero inválido");
                continue;
            } else if (!opciones.contains(opcion) && !ficheros.contains(fichero)) {
                System.out.println("Opción y fichero inválidos");
                continue;
            }

            int indice = Integer.parseInt(fichero) - 1;

            if (activos[indice] != null && activos[indice].isAlive()) {
                System.out.println("Ya se está realizando una búsqueda en ese fichero");
            } else {
                if (activos[indice] != null) activos[indice] = null;
                lanzarProceso(fichero, opcion);
            }
        }

        cancelarProcesos();
        System.out.println("Programa finalizado.");
    }

    private static void lanzarProceso(String fichero, String opc) {
        ProcessBuilder pb;
        Process process = null;

        try {
            pb = new ProcessBuilder(
                    "java",
                    "-cp",
                    System.getProperty("java.class.path"),
                    paquetePath + ".BuscadorTextoCLI",
                    fichero,
                    opc
            );
            pb.inheritIO(); // para ver la salida en la consola
            process = pb.start();
            activos[Integer.parseInt(fichero) - 1] = process;
        } catch (IOException e) {
            System.out.println("Error lanzando el proceso.");
            e.printStackTrace();
        }
    }

    private static void cancelarProcesos() {
        for (int i = 0; i < activos.length; i++) {
            Process p = activos[i];
            if (p != null && p.isAlive()) {
                p.destroy();
                System.out.println("Proceso en fichero " + (i + 1) + " cancelado.");
                activos[i] = null;
            }
        }
    }
}
