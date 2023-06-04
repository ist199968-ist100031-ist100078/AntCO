package pec;

import java.util.Random;

public class CenasFormigas {
    private final Double Tempo;
    private String Tipo;

    CenasFormigas(Double tempo){
        Random rand = new Random();
        int a = rand.nextInt(0,2);
        if (a == 0){
            this.Tipo = "Movimento";
        }
        if(a == 1){
            this.Tipo = "Evaporação";
        }
        this.Tempo = tempo;
    }
    //PARA mudar e receber diretamente o tipo
    CenasFormigas(Double tempo, int a){
        Random rand = new Random();
        if (a == 0){
            this.Tipo = "Movimento";
        }
        if(a == 1){
            this.Tipo = "Evaporação";
        }
        this.Tempo = tempo;
    }

    CenasFormigas(Double tempo, String type){
        this.Tempo = tempo;
        this.Tipo = type;
    }

    public Double getTempo() {
        return Tempo;
    }

    public String getTipo() {
        return Tipo;
    }
}
