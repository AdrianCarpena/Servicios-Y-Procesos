package Filosofos;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Filosofo implements Runnable{

    private final Semaphore cubIzquierdo,cubDerecho;

    public Filosofo(Semaphore cubIzquierdo,Semaphore cubDerecho){

        this.cubDerecho=cubDerecho;
        this.cubIzquierdo=cubIzquierdo;
    }

    @Override
    public void run() {

        Random random= new Random();

        while (true){
            try {
                cubIzquierdo.acquire();
                if(cubDerecho.tryAcquire()) {

                    System.out.println("El filosofo " + Thread.currentThread().getName() + " comiendo");
                    cubDerecho.release();
                }
                else{
                    System.out.println("El filosofo "+Thread.currentThread().getName()+" no ha podido cojer el cubierto y suelta el que tenia");
                }
                cubIzquierdo.release();
                System.out.println("El filosofo " + Thread.currentThread().getName() + " pensando...");


                Thread.sleep(random.nextInt(1,5));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}


/*
 while (true){
         try {
         cubIzquierdo.acquire();
                cubDerecho.acquire();
                System.out.println("El filosofo "+Thread.currentThread().getName()+" comiendo");
        Thread.sleep(random.nextInt(10,600));
        System.out.println("El filosofo "+Thread.currentThread().getName()+" pensando...");

        cubIzquierdo.release();
                cubDerecho.release();

                Thread.sleep(random.nextInt(1,50));
        } catch (InterruptedException e) {
        throw new RuntimeException(e);
            }
                    }

 */