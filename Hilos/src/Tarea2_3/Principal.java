package Tarea2_3;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Principal {
    public static void main(String[] args) {
        Scanner teclado= new Scanner(System.in);
        int cantidad=0;
        int num_empleados=0;
        boolean valido=true;

        Semaphore ordenadores,mesas;
        do {
            try {
                valido=true;
                System.out.print("Cuantos ordenadores y mesas quiere que haya: ");
                cantidad = teclado.nextInt();
                if(cantidad<1){
                    valido=false;
                    System.out.println("Debe haber al menos 1 ordenador y una mesa");
                    teclado.nextLine();
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes introducir un número entero");
                valido = false; teclado.nextLine();
            }
        }while (!valido);

        do {
            try {
                valido=true;
                System.out.print("Cuantos empleados habrá?, debe haber más empleados que mesas y ordenadores: ");
                num_empleados = teclado.nextInt();
                if(num_empleados<=cantidad){
                    valido=false;
                    System.out.println("Debe introducir más empleados que mesas y ordenadores");
                    teclado.nextLine();
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes introducir un número entero");
                valido = false;
                teclado.nextLine();
            }
        }while (!valido);

        ordenadores=new Semaphore(cantidad);
        mesas= new Semaphore(cantidad);
        for (int x=0;x<num_empleados;x++){
            Empleado emp= new Empleado(ordenadores,mesas);
            emp.setName(String.valueOf(x+1));
            emp.start();
        }
    }
}