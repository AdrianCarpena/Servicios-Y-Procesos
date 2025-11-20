package psp.procesos.sumaFicheros;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GenerarFicheroConNumeros {

    final static int numLineas=1000000;
    final static int peso= Integer.MAX_VALUE/100;

    public static void main(String[] args) {
        if(args.length<1){
            System.out.println("Se necesita el nombre del fichero");
            System.exit(1);
        }
        try{
            PrintWriter pw= new PrintWriter(new FileWriter(args[0]));
            for(int x=1;x<=numLineas;x++){
                long aleatorio= (long) (Math.random()*peso);
                pw.println(aleatorio);
            }
        }catch (IOException e){
            throw new RuntimeException();
        }

        System.out.println("Fichero generado");
    }
}
