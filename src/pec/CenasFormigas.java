package pec;

import java.util.Random;

public class CenasFormigas {
    private final Double Tempo;
    private String Tipo;
    private final Integer ID;
    CenasFormigas(Double tempo, Integer id){
        Random rand = new Random();
        int a = rand.nextInt(0,2);
        if (a == 0){
            this.Tipo = "Movimento";
        }
        if(a == 1){
            this.Tipo = "Evaporação";
        }
        this.Tempo = tempo;
        this.ID = id;
    }
    //PARA mudar e receber diretamente o tipo
    CenasFormigas(Double tempo, int Tipo, Integer id){
        Random rand = new Random();
        if (Tipo == 0){
            this.Tipo = "Movimento";
        }
        if(Tipo == 1){
            this.Tipo = "Evaporação";
        }
        this.Tempo = tempo;
        this.ID = id;
    }

    CenasFormigas(Double tempo, String type, Integer id ){
        this.Tempo = tempo;
        this.Tipo = type;
        this.ID = id;
    }

    public Double getTempo() {
        return Tempo;
    }

    public String getTipo() {
        return Tipo;
    }

    public Integer getID() {
        return ID;
    }
}
