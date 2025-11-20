package Ejemplo3;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ContadorColaborativoLock {

    private static int valor=0;
    private static final int hilosT=10;
    private static final int incrementosporhilo=10000;

    private final Lock lock= new ReentrantLock();

    public void incrementaValor(){

        try {
            if(lock.tryLock(5, TimeUnit.MILLISECONDS)) {

                int valorprevio = valor;
                valor++;
                if (valorprevio != (valor - 1)) {
                    System.out.println("Valor previo: " + valorprevio + "--- Nuevo valor: " + valor);
                }
            }
            else{
                System.out.println("El hilo no ha conseguido el cerrojo");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {

        ContadorColaborativoLock cc= new ContadorColaborativoLock();
        Thread [] hilos= new Thread[hilosT];
        for(int x=0; x< hilosT; x++){
            hilos[x]=new Thread(() -> {
                for(int i=0;i<incrementosporhilo;i++){
                    cc.incrementaValor();
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
        System.out.println("Obtenido: "+valor);

    }

}