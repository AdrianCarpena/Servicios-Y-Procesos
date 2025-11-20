package Ejemplo1;

public class HiloThread extends Thread{

    @Override
    public void run(){
        System.out.println("Ejecutando hilo 1 que hereda de la clase Thread");
        System.out.println("Nombre del hilo actual: "+ getName());
    }
}
