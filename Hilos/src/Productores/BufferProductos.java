package Productores;

public class BufferProductos {

    private static final int max_productos=10;

    private int[] buffer;
    private int siguiente;

    private boolean estavacio;
    private boolean estaLleno;

    public BufferProductos(int capacidad){
        buffer= new int[capacidad];
        siguiente=0;
        estavacio=true;
        estaLleno=false;
    }
    public BufferProductos(){
        this(max_productos);
    }
    synchronized public int consumir() throws InterruptedException {
        while (estavacio){
            System.out.println("Buffer Vacio");
            wait();
        }
        siguiente--;
        System.out.println("Se ha consumido el producto "+buffer[siguiente]);
        if (siguiente==0){
            estavacio=true;
        }
        estaLleno=false;
        notifyAll();
        return (buffer[siguiente]);
    }

    synchronized public void producir(int producto) throws InterruptedException{
        while (estaLleno){
            System.out.println("Buffer lleno");
            wait();
        }
        buffer[siguiente]=producto;
        siguiente++;
        System.out.println("Se ha producido el producto "+producto);
        if(siguiente == buffer.length){
            estaLleno=true;
        }
        estavacio=false;
        notifyAll();
    }

    public boolean estaVacio(){
        return estavacio;
    }
}
