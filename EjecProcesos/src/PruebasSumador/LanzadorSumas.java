package PruebasSumador;

import java.io.*;

public class LanzadorSumas {

    static String filename="resultado";
    public static void main(String[] args) {

        try{
            File file= new File(filename);
            file.delete();
            file.createNewFile();
        }catch (IOException e){

        }
LanzadorSumas ls= new LanzadorSumas();
ls.lanzarSumas(2,5,1,filename);
ls.lanzarSumas(4,6,2,filename);
ls.lanzarSumas(3,6,3,filename);
ls.lanzarSumas(1,8,4,filename);
ls.lanzarSumas(5,7,5,filename);

try(BufferedReader br=new BufferedReader(new FileReader(filename))){
    String output="";
    String linea;
    while ((linea=br.readLine())!=null){
        output+=linea+"\n";
    }
    System.out.println(output);
    }catch (IOException e){
    e.printStackTrace();
    }
    }

    private void lanzarSumas(Integer n1, Integer n2, Integer proceso,String filename){

        ProcessBuilder pb;
        Process process;
        String classname="PruebasSumador.Sumador";
        String currentpath=System.getProperty("user.dir");
        String classpath=currentpath+"/out/production/EjecPRocesos";

        try{
        pb= new ProcessBuilder("java","-cp",classpath,classname,n1.toString(),n2.toString(),proceso.toString(),filename);
        pb.inheritIO();
        process= pb.start();
        process.waitFor();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
