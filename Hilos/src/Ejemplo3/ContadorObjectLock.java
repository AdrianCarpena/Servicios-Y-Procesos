package Ejemplo3;

public class ContadorObjectLock {

    public int contador=0;
    public int contador2=0;
    private static final int hilosT=10;
    private static final int incrementosporhilo=10000;

    private final Object lock= new Object();
    private final Object lock2= new Object();

    public void incrementa1(){
        synchronized (lock) {
            contador++;
        }
    }
    public void incrementa2(){
        synchronized (lock2) {
            contador2++;
        }
    }

    public static void main(String[] args) {

        ContadorObjectLock col= new ContadorObjectLock();
        Thread [] hilos= new Thread[hilosT];
        for(int x=0; x< hilosT; x++){
            hilos[x]=new Thread(() -> {
                for(int i=0;i<incrementosporhilo;i++){
                    col.incrementa1();
                    col.incrementa2();
                }
            });
            hilos[x].start();
        }

        for(Thread t:hilos){
            try {
                t.join();
            }catch (InterruptedException e){
                throw new RuntimeException();
            }
        }
        System.out.println("Esperado: "+(hilosT*incrementosporhilo));
        System.out.println("Obtenido contador1: "+col.contador);
        System.out.println("Obtenido contador1: "+col.contador2);

    }


}
