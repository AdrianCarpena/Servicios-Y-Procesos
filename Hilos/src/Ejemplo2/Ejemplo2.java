package Ejemplo2;


public class Ejemplo2 {
    public static void main(String[] args) throws InterruptedException {

       Hilo hilo1=new Hilo();
       hilo1.setName("Hilo1");
       Hilo hilo2=new Hilo();
       hilo2.setName("Hilo2");
       Hilo hilo3=new Hilo();
       hilo3.setName("Hilo3");

       hilo1.setPriority(Thread.MIN_PRIORITY);
       hilo2.setPriority(Thread.MAX_PRIORITY);
       hilo3.setPriority(Thread.NORM_PRIORITY);

       hilo1.start();
       hilo2.start();
       hilo3.start();
       hilo1.join();
       hilo2.join();
       hilo3.join();

       System.out.println("Han terminado los hilos");


    }
}
