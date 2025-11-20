package Productores;

public class ProductorConsumidor {

    public static void main(String[] args) throws InterruptedException{
        int capacidadBuffer=5;
        int numproductores=2;
        int numconsumidores=3;
        int productos_por_Productor=5;

        BufferProductos buffer=new BufferProductos(capacidadBuffer);
        Thread[] productores= new Thread[numproductores];
        Thread[] consumidores= new Thread[numconsumidores];

        for(int i=0;i<numproductores;i++){
            productores[i]=new Thread(new Productor(i+1,productos_por_Productor,buffer));
            productores[i].start();
        }

        for(int c=0;c<numconsumidores;c++){
            consumidores[c]=new Thread(new Consumidor(c+1,buffer));
            consumidores[c].start();
        }

        for(Thread p : productores){
                p.join();
        }

        while (!buffer.estaVacio()){
            Thread.sleep(100);
        }

        for(Thread c:consumidores){
            c.interrupt();
            c.join();
        }

        System.out.println("Programa finalizado");

    }
}
