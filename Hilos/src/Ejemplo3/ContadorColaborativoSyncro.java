package Ejemplo3;

public class ContadorColaborativoSyncro {

    private static int valor=0;
    private static final int hilosT=10;
    private static final int incrementosporhilo=10000;

    public static void main(String[] args) {

        ContadorColaborativoSyncro cc= new ContadorColaborativoSyncro();
        Thread [] hilos= new Thread[hilosT];
        for(int x=0; x< hilosT; x++){
            hilos[x]=new Thread(() -> {
                for(int i=0;i<incrementosporhilo;i++){
                    cc.incrementaValor();
                }
            });
            hilos[x].start();
        }
        for(Thread t:hilos){
            try {
                t.join();
            }catch (InterruptedException e){
                throw new RuntimeException();
            }
        }
        System.out.println("Esperado: "+(hilosT*incrementosporhilo));
        System.out.println("Obtenido: "+valor);

    }

    synchronized public void incrementaValor(){
        int valorprevio=valor;
        valor++;
        if (valorprevio!= (valor-1))
            System.out.println("Valor previo: "+valorprevio+"--- Nuevo valor: "+valor);
    }

}
