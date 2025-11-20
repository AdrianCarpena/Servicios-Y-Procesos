package Tarea2_3;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Empleado extends Thread{

    private final Semaphore ordenadores,mesas;
    private final int tiempoMaximoTrabajo=3000;
    private final int tiempoMaximoDescanse=2000;

    public Empleado(Semaphore ordenadores,Semaphore mesas){
        this.mesas=mesas;
        this.ordenadores=ordenadores;
    }

    @Override public void run() {
        Random random= new Random();
        while (true){
            int reserva=random.nextInt(0,2);
            if (reserva==0) {
                try {
                    ordenadores.acquire();
                    if (mesas.tryAcquire()) {
                        System.out.println("El empleado " + getName() + " esta trabajando en una mesa con un ordenador");
                        Thread.sleep(random.nextInt(1, tiempoMaximoTrabajo));
                        mesas.release();
                    }
                    else {
                        System.out.println("El empleado " + getName() + " no ha podido cojer una mesa asi que deja el ordenador");
                    }
                    ordenadores.release();
                    System.out.println("El empleado " + getName() + " va a tomar un descanso...");
                    Thread.sleep(random.nextInt(1, tiempoMaximoDescanse));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            else{
                try {
                    mesas.acquire();
                    if (ordenadores.tryAcquire()) {
                        System.out.println("El empleado " + getName() + " esta trabajando en una mesa con un ordenador");
                        Thread.sleep(random.nextInt(1, 500));
                        ordenadores.release();
                    }
                    else {
                        System.out.println("El empleado " + getName() + " no ha podido cojer un ordenador asi que deja la mesa");
                    }
                    mesas.release();
                    System.out.println("El empleado " + getName() + " va a tomar un descanso...");
                    Thread.sleep(random.nextInt(1, 500));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
