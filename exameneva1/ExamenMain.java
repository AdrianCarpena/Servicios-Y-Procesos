package psp.hilos.exameneva1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ExamenMain {

    private static Scanner scanner = new Scanner(System.in);

    private static void escanearSubred() {
        //Hago un array de procesos que usare para ver si estan vivos o no y ver si devuelven 0
        Process [] procesos= new Process[254];
        //String que almacenará la subred que se intoduzca
        String subred;
        //Booleano para pedir segun sea true o false de nuevo la subred
        boolean valida;
        do{
                System.out.println("Introduce una subred de 24 bits(X.X.X): ");
                subred=scanner.nextLine();
                if(validarSubred(subred)){
                    valida=true;
                }
                else{
                    valida=false;
                }
        }while (!valida);

        ProcessBuilder pb;
        Process process;
        //Hago un bucle que recorra las 254 direcciones ip
        for(int x=1;x<255;x++) {
            //Le añado a la subred el ultimo byte segun la x
            String ip=subred+"."+x;

            try {
                //Distinguiendo si es linux o windows con if else hago un processbuilder para hacer los ping
                if (System.getProperty("os.name").equals("Linux")) {
                    pb = new ProcessBuilder("bash", "-c", "ping -c 3 " + ip);
                    procesos[x-1]=pb.start();
                    process = pb.start();
                    System.out.println("Se ha lanzado el escaneo a la ip "+ip);

                } else {
                    pb = new ProcessBuilder("cmd", "/c", "ping -n 3 " + ip);
                    process = pb.start();
                    procesos[x-1]=pb.start();
                    System.out.println("Se ha lanzado el escaneo a la ip "+ip);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Para ver si han acabado los procesos hago un bucle que acabe cuando todos esten terminados
        boolean vivos=true;
        while (vivos==true){
            vivos=false;
            for(int i=0;i< procesos.length;i++){
                if(procesos[i].isAlive()){
                    vivos=true;
                }
            }
        }
        //Con este bucle miro si devolvio un 0 o no, depende el caso diré que está arriba o abajo
        for(int i=0;i< procesos.length;i++){
            if(procesos[i].exitValue()==0){
                System.out.println("IP "+subred+"."+i+" Arriba");
            }
            else {
                System.out.println("IP "+subred+"."+i+" Abajo");
            }
        }

    }


    public static void asignarIPs () {
        String subred;
        boolean valida;
        //Uso do while cada vez que se introduce un valor para que si introduce algo erroneo lo vuelva a pedir
        do{
            System.out.println("Introduce una subred de 24 bits(X.X.X): ");
            subred=scanner.nextLine();
            if(validarSubred(subred)){
                valida=true;
            }
            else{
                valida=false;
            }
        }while (!valida);

        int num_ips=0;
        do{
            valida=true;
            try {
                System.out.println("Diga el numero de IPs disponibles: ");
                num_ips = scanner.nextInt();
                if (0 > num_ips || num_ips > 255) {
                    valida = false;
                    scanner.nextLine();
                }
            }catch (InputMismatchException e){
                valida=false;
                scanner.nextLine();
            }
        }while (!valida);

        int ordenadores=0;
        do{
            valida=true;
            try {
                System.out.println("Diga el numero de Ordenadores disponibles: ");
                ordenadores = scanner.nextInt();
                if (ordenadores<num_ips) {
                    valida = false;
                    scanner.nextLine();
                }
            }catch (InputMismatchException e){
                valida=false;
                scanner.nextLine();
            }
        }while (!valida);
//Hago un array de ordenadores para posteriormente poder interrumpirlos al pulsar una tecla
        Ordenadores[] listaOrdenadores= new Ordenadores[ordenadores];
        //Inicializo el almacen de ip
        AlmacenIps almacen= new AlmacenIps(num_ips,subred);
        //En este bucle creo los ordenadores, los inicio y los guardo en el array
        for(int z=0;z<ordenadores;z++){
            Ordenadores pc= new Ordenadores(z,almacen);
            pc.start();
            listaOrdenadores[z]=pc;
        }
        scanner.next();
        //Al pulsar una tecla o meter algo se ejecutara lo de abjo, interrumpiendo todos los ordenadores.
        System.out.println("Pulsa una tecla para detener las asignaciones");
        String tecla=scanner.nextLine();
        for(Ordenadores c:listaOrdenadores){
            c.interrupt();
            try {
                c.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }



    }


    public static void main(String[] args) {

        int opcion;
        do {
            System.out.println("=== MENÚ DE OPCIONES ===");
            System.out.println("1. Escaneo de subred (PROCESOS)");
            System.out.println("2. Asignación de IPs (HILOS)");
            System.out.println("3. Salir");
            System.out.print("Elige una opción (1-3): ");

            // leer input
            try {
                opcion = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Debe introducir un número entre 1 y 3");
                break;
            }
            finally {
                scanner.nextLine();
            }
            switch (opcion) {
                case 1:
                    escanearSubred();
                    break;
                case 2:
                    asignarIPs();
                    break;
                case 3:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }

            System.out.println(); // línea en blanco
        } while (opcion != 3);

        scanner.close();
    }

    /**
     * Método proporcionado por el profesor para validar la dirección de una subred de 24 bits (/24) a partir de una
     * String. Para ser considerada válida, la dirección de red debería cumplir el formato X.X.X dónde cada X puede
     * tomar un valor entre 0 y 255 (p.e.: 192.168.0).
     * @param ip La dirección de la subred /24 (X.X.X) a validar en formato texto (NO INCLUYE LA PARTE DE DIRECCIÓN DEL
     *          HOST).
     * @return true en caso de que se valide que el identificador de la subred cumple los criterios exigidos y false
     *          en caso contrario.
     */
    public static boolean validarSubred(String ip) {
        // Step 1: Separate the given string into an array of strings using the dot as delimiter
        String[] parts = ip.split("\\.");

        // Step 2: Check if there are exactly 3 parts
        if (parts.length != 3) {
            return false;
        }

        // Step 3: Check each part for valid number
        for (String part : parts) {
            try {
                // Step 4: Convert each part into a number
                int num = Integer.parseInt(part);

                // Step 5: Check whether the number lies in between 0 to 255
                if (num < 0 || num > 255) {
                    return false;
                }
            } catch (NumberFormatException e) {
                // If parsing fails, it's not a valid number
                return false;
            }
        }

        // If all checks passed, return true
        return true;
    }
}