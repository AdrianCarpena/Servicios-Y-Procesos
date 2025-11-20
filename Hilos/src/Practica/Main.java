package Practica;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner teclado= new Scanner(System.in);
        int cont=1;
        int cantidad=0;
        String opc="";
        List<Hilo> hilos=new ArrayList<Hilo>();
        boolean valido=true;
        do {
            try {
                valido=true;
                System.out.print("Cuantos triángulos quieres calcular? ");
                cantidad = teclado.nextInt();
                if(cantidad<0) {
                    valido = false;
                    System.out.println("No puede ser menor a 0");
                }
            } catch (InputMismatchException e) {
                valido=false;
                System.out.println("Cantidad invalida");
                teclado.nextLine();
            }
        }while (!valido);
        do {
                valido=true;
                System.out.print("Quiere asignar prioridad al cálculo de los triangulos? (S o N) ");
                opc = teclado.next().toLowerCase();
                if (!opc.equals("s") && !opc.equals("n")) {
                    valido = false;
                    System.out.println("Opción inválida");
                }

        }while (!valido);

        while (cont<=cantidad){
            float base=0;
            float altura=0;
            int prioridad=0;
            System.out.println("Triangulo "+cont+":");
            do {
                try {
                    valido=true;
                    System.out.print("Introduzca la base: ");
                    base = teclado.nextFloat();
                    if(base<0) {
                        valido = false;
                        System.out.println("La base no puede ser negativa");
                    }
                } catch (InputMismatchException e) {
                    valido=false;
                    System.out.println("Base invalida");
                    teclado.nextLine();
                }
            }while (!valido);

            do {
                try {
                    valido=true;
                    System.out.print("Introduzca la altura: ");
                    altura = teclado.nextFloat();
                    if(altura<0) {
                        valido = false;
                        System.out.println("La altura no puede ser negativa");
                    }
                } catch (InputMismatchException e) {
                    valido=false;
                    System.out.println("Altura invalida");
                    teclado.nextLine();
                }
            }while (!valido);

            Hilo hilo= new Hilo(base,altura);
            hilo.setName(""+(cont));
            if(opc.equals("s")){
                do {
                    try {
                        valido = true;
                        System.out.print("Introduzca la prioridad del 1 al 10: ");
                        prioridad = teclado.nextInt();
                        if (prioridad < 1 || prioridad > 10){
                            valido = false;
                        teclado.nextLine();
                        }
                    } catch (InputMismatchException e) {
                        valido=false;
                        System.out.println("Prioridad invalida");
                        teclado.nextLine();
                    }

                }while (!valido);
                hilo.setPriority(prioridad);
            }
            hilos.add(hilo);
            cont++;

        }
        for(Hilo h:hilos){
            h.start();
        }

    }
}
