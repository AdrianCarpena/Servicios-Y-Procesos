package Practica;

public class Hilo extends Thread{

    float base;
    int alturaentera;
    float alturadecimal;
    float altura;

    public Hilo(float base, float altura){
        this.base=base;
        this.altura=altura;
        this.alturaentera=(int)altura;
        this.alturadecimal = (float) (Math.round((altura - (int)altura) * 1000.0) / 1000.0);
    }

    @Override
    public void run(){
    float area=0;
    for(int x=1;x<=alturaentera;x++){
        area+=base;
    }
    area+=(base*alturadecimal);
    area=area/2;
        System.out.println("Triangulo "+getName()+":"+
        "\nBase: "+base+"     Altura: "+altura+
        "\nEl area es: "+area+"\n");


    }
}
