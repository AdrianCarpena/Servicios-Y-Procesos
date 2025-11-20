package PruebasSumador;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Sumador {

    public static void main(String[] args) {

        Sumador sumador=new Sumador();
        String filename=args[3];
        int n1=Integer.parseInt(args[0]);
        int n2=Integer.parseInt(args[1]);
        int proceso=Integer.parseInt(args[2]);
        sumador.sum2(n1,n2,proceso,filename);
    }

    public int sum(int n1,int n2, int proceso){
        int res=0;
        for(int x=n1;x<=n2;x++)
            res+=x;
        System.out.println("Ejecutado por el proceso: "+proceso);
        System.out.println("El resultado del sumatorio de "+n1+" y "+n2+" = "+res);
        return res;
    }

    public int sum2(int n1,int n2, int proceso,String filename){
        int res=0;
        for(int x=n1;x<=n2;x++)
            res+=x;
        try ( BufferedWriter bw = new BufferedWriter(new FileWriter(filename,true));){

            bw.write("\nEjecutado por el proceso: "+proceso);
            bw.write("\nEl resultado del sumatorio de "+n1+" y "+n2+" = "+res);

        }catch (IOException e){

        }
        return res;
    }
}
