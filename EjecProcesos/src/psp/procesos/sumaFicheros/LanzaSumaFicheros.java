package psp.procesos.sumaFicheros;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LanzaSumaFicheros {

    private static final String[] nombresfich = {
            "cuenta1.txt", "cuenta2.txt", "cuenta3.txt", "cuenta4.txt", "cuenta5.txt",
            "cuenta6.txt", "cuenta7.txt", "cuenta8.txt", "cuenta9.txt", "cuenta10.txt"};
    private static final String paquetePath = "psp.procesos.sumaFicheros";

    public static void main(String[] args) throws IOException, InterruptedException {
        ProcessBuilder pb = null;
        File file;
        List<Process> listaFicherosGenerados = new ArrayList<>();
        for (String filename : nombresfich) {
            file = new File(filename);
            if (!file.exists()) {
                pb = new ProcessBuilder("java", "-cp", System.getProperty("java.class.path"), paquetePath + ".GenerarFicheroConNumeros", filename);
                listaFicherosGenerados.add(pb.start());
            }
        }

        while (!listaFicherosGenerados.isEmpty()) {
            Process processfichero;
            Iterator<Process> iterator = listaFicherosGenerados.iterator();
            while (iterator.hasNext()) {
                processfichero = iterator.next();
                if (!processfichero.isAlive()) {
                    iterator.remove();
                }
            }
        }
        LanzaSumaFicheros lsf= new LanzaSumaFicheros();
        lsf.sumaSerie();
        lsf.sumaParalelo();

    }

    public void sumaParalelo() throws IOException,InterruptedException{
        long total=0;
        Process[] procesos= new Process[nombresfich.length];
        InputStream[] entradas= new InputStream[nombresfich.length];

        ProcessBuilder pb=null;
        long tiempoi=System.currentTimeMillis();

        for (int pos=0;pos<nombresfich.length;pos++) {
            pb = new ProcessBuilder("java", "-cp", System.getProperty("java.class.path"), paquetePath + ".SumarFichero", nombresfich[pos]);
            procesos[pos]= pb.start();
            entradas[pos] = procesos[pos].getInputStream();
        }
        boolean vivos=true;
        while (vivos){
            vivos=false;

            for(int pos=0;pos<procesos.length;pos++) {
                if(procesos[pos]==null) continue;
                if (entradas[pos].available()>0) {
                    String linea = new String(entradas[pos].readAllBytes()).trim();
                    long valor=Long.parseLong(linea);
                    procesos[pos]=null;
                    entradas[pos].close();
                    total+=valor;
                }
                else if(procesos[pos].isAlive()){
                    vivos=true;
                }
            }

            Thread.sleep(10);
        }
        long tiempof=System.currentTimeMillis();
        System.out.printf("En total es: %20d\n",total);
        System.out.printf("El tiempo eempleado fue de %d milisegundos\n",tiempof-tiempoi);
    }




    public void sumaSerie() throws IOException, InterruptedException {
        long total = 0;

        Process process;
        InputStream entrada;

        ProcessBuilder pb = null;
        long tiempoi=System.currentTimeMillis();
        for (String filename : nombresfich) {
            pb = new ProcessBuilder("java", "-cp", System.getProperty("java.class.path"), paquetePath + ".SumarFichero", filename);
            process=pb.start();
            process.waitFor();
            entrada=process.getInputStream();
        String linea= new String(entrada.readAllBytes()).trim();
        entrada.close();
        long valor=Long.parseLong(linea);
        total+=valor;

        }
        long tiempof=System.currentTimeMillis();
        System.out.printf("En total es: %20d\n",total);
        System.out.printf("El tiempo eempleado fue de %d milisegundos\n",tiempof-tiempoi);
    }
}