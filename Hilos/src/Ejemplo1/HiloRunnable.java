package Ejemplo1;

public class HiloRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println("Ejecutando hilo 2 que implementa la clase Runnable");
        System.out.println("Nombre del hilo actual: "+ Thread.currentThread().getName());
    }
}
