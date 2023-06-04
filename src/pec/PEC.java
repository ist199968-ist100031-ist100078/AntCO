package pec;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PEC {
    private final List<CenasFormigas> PriorQue;
    private Map<String, EventStrategy> EventType  = new HashMap<>();
    //private EventStrategy eventStrat;

    public PEC() {
        this.PriorQue = new ArrayList<>();
        insertEvent( "Movimento", new MEventStrategy());
        insertEvent("Evaporação", new EEventStrategy());
    }

    public void Addevent(Double event) {
        int aux = 0;
        for (CenasFormigas i : this.PriorQue) {
            if (event <= i.getTempo()) {
                break;
            }
            aux++;
        }
        this.PriorQue.add(aux, new CenasFormigas(event));
    }


    // PODE SE APAGAR DEPOIS
    public void Addevent(Double time, int a) {
        int aux = 0;
        for (CenasFormigas i : this.PriorQue) {
            if (time <= i.getTempo()) {
                break;
            }
            aux++;
        }
        this.PriorQue.add(aux, new CenasFormigas(time,a));
    }

    public CenasFormigas getFirstElement(){
        CenasFormigas Elemento = this.PriorQue.get(0);
        this.PriorQue.remove(0);
        return Elemento;
    }
    public List<CenasFormigas> getPriorQue() {
        return PriorQue;
    }

    public void insertEvent(String str, EventStrategy eventStrategy){
        this.EventType.put(str,eventStrategy);
    }
    public Double chooseEvent(String event){
        return this.EventType.get(event).execute();
    }

    public static void main(String[] args) {
        PEC a = new PEC();
        a.Addevent(5.3, 1);
        a.Addevent(14.2, 0);
        a.Addevent(5.77, 1);
        a.Addevent(5.34, 1);
        a.Addevent(5.1, 0);
        for (int i=0; i < 2;i++) {
            CenasFormigas ola = a.getFirstElement();
            System.out.println(ola.getTipo() + " at " + ola.getTempo() + "  " + a.chooseEvent(ola.getTipo()));
        }
        a.Addevent(13.9, 1);
        a.Addevent(2.9, 0);
        int tam = a.getPriorQue().size();
        for (int i=0; i < tam;i++) {
            CenasFormigas ola = a.getFirstElement();
            System.out.println(ola.getTipo() + " at " + ola.getTempo() + "  " + a.chooseEvent(ola.getTipo()));
        }
    }
}
