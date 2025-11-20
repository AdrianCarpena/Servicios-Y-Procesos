package psp.hilos.exameneva1;
//
public class AlmacenIps {
    private String[] buffer;
    private int siguiente;
    private int capacidad;

    private boolean estavacio;
//Constructor del almacen que mete las ips conformo a la subred y la cantidad
    public AlmacenIps(int capacidad,String subred){
        this.capacidad=capacidad;
        buffer= new String[capacidad];
        for(int x=0;x<capacidad;x++){
            buffer[x]=subred+"."+(x+1);
        }
        siguiente=0;
        estavacio=false;
    }
    //Metodo con el que se coje una ip, si esta vacio el buffer espera y cuando pilla una notifica
    synchronized public String cojerIP() throws InterruptedException {
        while (estavacio){
            wait();
        }
        String ip=buffer[siguiente];
        siguiente++;
        if (siguiente==capacidad){
            estavacio=true;
        }
        notifyAll();
        return ip;
    }
//Metodo para devolver una ip,lo notifica
    synchronized public void devolverIP(String ip)throws InterruptedException{
        buffer[siguiente-1]=ip;
        siguiente--;
        estavacio=false;
        notifyAll();

    }
}
