package Tarea1;

import java.io.*;
import java.util.Scanner;

public class Procesos {
    static Scanner teclado= new Scanner(System.in);
    public static void main(String[] args) {
        Procesos p= new Procesos();
        int opc=0;
        do {
            p.menu();
            opc = teclado.nextInt();
            switch (opc) {
                case 1:
                    p.ping();
                    break;
                case 2:
                    p.listar();
                    break;
                case 3:
                    p.leer_cerrar_Procesos();
                    break;
                case 4:
                    p.navegador();
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion invalida");
            }
        }while (opc!=5);
    }

    public void menu(){
        System.out.println("-------Menu------"+
                "\n 1.Realizar un Ping"+
                "\n 2.Volcar listado de archivos y ficheres en un archivo indicado"+
                "\n 3.Ver los procesos de sistema y cerrar el proceso elegido por PID"+
                "\n 4.Ejecutar un navegador mediante URL introducida"+
                "\n 5.Exit");
    }

    public void ping(){
        System.out.println("Introduce a que ip se hace el ping: ");
        String ip=teclado.next();
        ProcessBuilder pb;
        Process process;
        try {

            if(System.getProperty("os.name").equals("Linux")){
                pb = new ProcessBuilder("bash", "-c", "ping -c 3 "+ip);
                process=pb.start();
                BufferedReader br= new BufferedReader(new InputStreamReader(process.getInputStream()));
                String linea;
                while ((linea=br.readLine())!=null){
                    System.out.println(linea);
                }
            }
            else {
                pb = new ProcessBuilder("cmd", "/c", "ping -n 3 "+ip);
                process=pb.start();
                BufferedReader br= new BufferedReader(new InputStreamReader(process.getInputStream()));
                String linea;
                while ((linea=br.readLine())!=null){
                    System.out.println(linea);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void listar(){
        System.out.println("Introduce el nombre que recibirá el archivo: ");
        String archivo=teclado.next();
        ProcessBuilder pb;
        Process process;
        try {

            if(System.getProperty("os.name").equals("Linux")){
                pb = new ProcessBuilder("bash", "-c", "ls>"+archivo);
                process=pb.start();
            }
            else {
                pb = new ProcessBuilder("cmd", "/c", "dir>"+archivo);
                process=pb.start();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void leer_cerrar_Procesos(){
        ProcessBuilder pb;
        Process process;
        try {

            if(System.getProperty("os.name").equals("Linux")){
                pb = new ProcessBuilder("bash", "-c", "ps -aux");
                process=pb.start();
                BufferedReader br= new BufferedReader(new InputStreamReader(process.getInputStream()));
                String linea;
                while ((linea=br.readLine())!=null){
                    System.out.println(linea);
                }
                System.out.println("Dime el PID del proceso a detener: ");
                String pid=teclado.next();
                pb = new ProcessBuilder("bash", "-c", "kill -9 "+pid);
                process=pb.start();
            }
            else {
                pb = new ProcessBuilder("cmd", "/c", "tasklist");
                process=pb.start();
                BufferedReader br= new BufferedReader(new InputStreamReader(process.getInputStream()));
                String linea;
                while ((linea=br.readLine())!=null){
                    System.out.println(linea);
                }
                System.out.println("Dime el PID del proceso a detener: ");
                String pid=teclado.next();
                pb = new ProcessBuilder("cmd", "/c", "taskkill /PID "+pid+ " /F");
                process=pb.start();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void navegador(){
        System.out.println("Introduce una URL: ");
        String url=teclado.next();
        ProcessBuilder pb;
        Process process;
        try {

            if(System.getProperty("os.name").equals("Linux")){
                pb = new ProcessBuilder("bash", "-c", "/usr/bin/firefox "+url);
                process=pb.start();
            }
            else {
                pb = new ProcessBuilder("cmd", "/c", "start chrome "+url);
                process=pb.start();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}