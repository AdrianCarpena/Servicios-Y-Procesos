package psp.hilos.exameneva1;
//La clase extiende de Thread ya que es un hilo
public class Ordenadores extends Thread{
//Atributos
    AlmacenIps almacen;
    int id;
    final int tiempoMaxEspera=300;

    //Constructor de Ordenadores al que se mete un id para diferenciarlos y el alamcen de ips
    public Ordenadores(int id,AlmacenIps almacen){
        this.almacen=almacen;
        this.id=id;
    }
//Metodo run necesario en hilos, en este caso el hilo(ordenador) intenta cojer una ip, espera unos seg y la devuelve, así sucesivamente hasta
//que sea interrumpido

    @Override
    public void run() {
    String ip;
        while (true){
            try {
                ip=almacen.cojerIP();
                System.out.println("Al ordenador "+id+" se le ha asignado la ip "+ip);
                Thread.sleep((long) (Math.random()*tiempoMaxEspera));
                almacen.devolverIP(ip);
                System.out.println("El ordenador "+id+" ha devuelto la ip "+ip);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Ordenador "+id+ " interrumpido y finalizado");
                return;
            }
        }

    }

}
