package P1_2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LanzadorAleatorio {
    static Scanner teclado= new Scanner(System.in);
    static String filename="numeros.txt";
    /**
     * El programa espera que se le pase un argumento por teclado ya sea un numero,cadena,carcater..., por cada carcater
     * que se le introduzca llamará una vez a aleatorio, clase la cual generará un número aleatorio y lo metera en un fichero.
     * Posteriorrmente el programá leera el archivo una vez se haya llamdo a aleatorio tantas veces como caracteres hayas escrito,
     * lo mostrará y podras introducir más o escribir fin para acabar
     * @param args No requiere argumentos
     */

    public static void main(String[] args) {
        String valores;
        System.out.println("Introduce valores para generar numeros aleatorios, para acabar escribe fin");
        LanzadorAleatorio la = new LanzadorAleatorio();
        boolean sigue=true;
        do {
            //Aqui recojo la entrada/texto introducida por el usuario
            valores = teclado.nextLine();
            /*En caso de que no sea fin, ya sea mayus o minus, hago todo el proceso, de lo contrario no haria nada y el bucle finalizaria
            */
            if(!valores.toLowerCase().equals("fin")) {
                try{
                    File file= new File(filename);
                    file.delete();
                    file.createNewFile();
                }catch (IOException e){

                }
            //Llamo al metodo lanzarAleatorio para que haga el proceso y acabe escribiendo los numeros generados en un fichero
                la.lanzarAleatorio(valores);
            //Aqui leo el fichero y lo imprimo
                try(BufferedReader br=new BufferedReader(new FileReader(filename))){
                    String output="";
                    String linea;
                    while ((linea=br.readLine())!=null){
                        output+=linea;
                    }
                    System.out.println(output);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        if(valores.toLowerCase().equals("fin"))
        sigue=false;

        }while(sigue);

    }

    private void lanzarAleatorio(String valores){
        ProcessBuilder pb;
        Process process;
        //Con las 3 siguientes lineas recojo donde esta ubicada la clase Aleatorios para que la ejecute
        String classname="P1_2.Aleatorios";
        String currentpath=System.getProperty("user.dir");
        String classpath=currentpath+"/out/production/EjecPRocesos";
        pb= new ProcessBuilder("java","-cp",classpath,classname,filename);
        //hago un bucle para llamar a la clase y generar un numero tantas veces como caracteres haya introducido
        for(int x=0;x<valores.length();x++){
            try{

                pb.inheritIO();
                process= pb.start();

            if(x==valores.length()-1) process.waitFor();
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        }

    }

