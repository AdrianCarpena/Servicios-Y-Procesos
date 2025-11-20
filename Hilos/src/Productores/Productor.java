package Productores;

public class Productor implements Runnable{

    private final BufferProductos buffer;
    private final int id;
    private final int numProductos;

    public Productor(int id,int numProductos, BufferProductos buffer){
        this.id=id;
        this.numProductos=numProductos;
        this.buffer=buffer;
    }

    @Override
    public void run() {
        for(int i=1;i<=numProductos;i++){
            int producto= id*1000+i;
            try {
                buffer.producir(producto);
                System.out.println("Productor "+id+" produjo "+producto);
                Thread.sleep((long) (Math.random()*100));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
