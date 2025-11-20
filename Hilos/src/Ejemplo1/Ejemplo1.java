package Ejemplo1;

public class Ejemplo1 {

    public static void main(String[] args) {
        HiloThread ht= new HiloThread();

        System.out.println("CLASE PRINCCIPAL CORRIENDO, LANZAMIENTO DE HILOS...");
        System.out.println("HILO DE LA CLASE Ejemplo1: "+Thread.currentThread().getName());
        ht.start();
        new Thread(new HiloRunnable(),"HiloRunnable").start();
    }
}
