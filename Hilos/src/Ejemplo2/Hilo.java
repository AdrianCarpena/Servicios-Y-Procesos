package Ejemplo2;

public class Hilo extends Thread{

    @Override
    public void run(){
        for(int x=1;x<=105;x++) {
            System.out.println("Hilo "+getName()+" contador: "+x);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
