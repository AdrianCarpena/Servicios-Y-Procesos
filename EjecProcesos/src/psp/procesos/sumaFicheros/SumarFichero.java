package psp.procesos.sumaFicheros;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SumarFichero {

    public static void main(String[] args) {

        if(args.length<1){
            System.out.println("Se necesita el nombre del fichero");
            System.exit(1);
        }
        String filename=args[0];
        long total=0;

        try(BufferedReader br= new BufferedReader(new FileReader(filename))){
        String linea;
        while ((linea=br.readLine())!=null){
            total+=Long.parseLong(linea);
        }

        }catch (IOException e){
            throw new RuntimeException();
        }

        System.out.println(total);
    }
}
