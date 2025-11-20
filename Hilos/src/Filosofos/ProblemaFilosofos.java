package Filosofos;

import java.util.concurrent.Semaphore;

public class ProblemaFilosofos {

    public static void main(String[] args) {

        Semaphore sem1_2= new Semaphore(1);
        Semaphore sem2_3= new Semaphore(1);
        Semaphore sem3_1= new Semaphore(1);

        Filosofo f1= new Filosofo(sem1_2,sem3_1);
        Filosofo f2= new Filosofo(sem2_3,sem1_2);
        Filosofo f3= new Filosofo(sem3_1,sem2_3);

        Thread h1,h2,h3;
        h1= new Thread(f1,"1");
        h2= new Thread(f2,"2");
        h3= new Thread(f3,"3");

        h1.start();
        h2.start();
        h3.start();

        try{
            h1.join();
            h2.join();
            h3.join();
        }catch (InterruptedException e){

        }

    }
}
