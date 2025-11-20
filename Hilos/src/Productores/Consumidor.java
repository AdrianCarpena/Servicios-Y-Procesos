package Productores;

public class Consumidor implements Runnable{

    private final BufferProductos buffer;
    private final int id;


    public  Consumidor(int id,BufferProductos buffer){
        this.id=id;
        this.buffer=buffer;
    }

    @Override
    public void run() {
        int producto;

        while (true){
            try {
                producto=buffer.consumir();
                System.out.println("consumidor "+id+" ha consumido el producto "+producto);
                Thread.sleep((long) (Math.random()*100));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Consumidor "+id+ " interrumpido y finalizado");
                return;
            }
        }
    }
}
