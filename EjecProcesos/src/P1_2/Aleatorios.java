package P1_2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Aleatorios {
    /**
     * Esta clase recibe como argumneto el nombre del fichero en el que va a escribir
     * @param args
     */

    public static void main(String[] args) {
            int ale = (int) (Math.random() * 10);
            //hago un BufferedWritter para escribir en fichero el numero generado
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(args[0],true));){

            bw.write(Integer.toString(ale));

        }catch (IOException e){

        }
    }
}
