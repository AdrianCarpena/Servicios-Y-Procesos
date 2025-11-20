package psp.procesos;

import java.io.File;
import java.io.IOException;

public class EjecProcess {
    public static void main(String[] args) {
    String comando="gnome-terminal";
    EjecProcess ej=new EjecProcess();
    ej.ejecutarPB(comando);

    ej.ejecutarRun(args);
    }


    public void ejecutarPB(String comando){
    ProcessBuilder pb;
    Process process;
    try{
       pb=new ProcessBuilder("bash","-c","ip a");
       File log= new File("Salida.log");
       pb.redirectOutput(log);
       pb.redirectError(log);

       process= pb.start();
       int retorno=process.waitFor();
       System.out.println("La ejecución de "+pb.command()+" devuelve "+ retorno);
       System.out.println("Las variables de entorno son "+pb.environment());
    }catch (Exception er){
       throw new RuntimeException(er);
    }
    }


    public void ejecutarRun(String[] comando){
        Runtime runtime;
        Process process;

        try{
            runtime= Runtime.getRuntime();
            process=runtime.exec(comando);
            synchronized (process) {
                process.wait(2000);
            }
            process.destroy();
        }catch (IOException | InterruptedException e){
            throw new RuntimeException(e);
        }
    }

}