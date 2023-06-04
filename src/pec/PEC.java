package pec;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PEC {
    private final List<CenasFormigas> PriorQue;
    private Map<String, EventStrategy> EventTypeMap = new HashMap<>();
    //private EventStrategy eventStrat;

    public PEC() {
        this.PriorQue = new ArrayList<>();
        insertEvent("Movimento", new MEventStrategy());
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
        this.PriorQue.add(aux, new CenasFormigas(time, a));
    }

    public void Addevent(Double time, String Tipo) {
        int aux = 0;
        for (CenasFormigas i : this.PriorQue) {
            if (time <= i.getTempo()) {
                break;
            }
            aux++;
        }
        this.PriorQue.add(aux, new CenasFormigas(time, Tipo));
    }


    public CenasFormigas getFirstElement() {
        CenasFormigas Elemento = this.PriorQue.get(0);
        this.PriorQue.remove(0);
        return Elemento;
    }

    public List<CenasFormigas> getPriorQue() {
        return PriorQue;
    }

    public void insertEvent(String str, EventStrategy eventStrategy) {
        this.EventTypeMap.put(str, eventStrategy);
    }

    public Double chooseEventStrat(String event) {
        return this.EventTypeMap.get(event).execute();
    }

    public static void main(String[] args) {
        PEC a = new PEC();
        a.Addevent(5.3, "Evaporação");
        a.Addevent(14.2, "Movimento");
        a.Addevent(5.77, "Evaporação");
        a.Addevent(5.34, "Evaporação");
        a.Addevent(5.1, "Movimento");
        for (int i = 0; i < 2; i++) {
            CenasFormigas ola = a.getFirstElement();
            System.out.println(ola.getTipo() + " at " + ola.getTempo() + "  " + a.chooseEventStrat(ola.getTipo()));
        }
        a.Addevent(13.9, "Movimento");
        a.Addevent(2.9, "Movimento");
        int tam = a.getPriorQue().size();
        for (int i = 0; i < tam; i++) {
            CenasFormigas ola = a.getFirstElement();
            System.out.println(ola.getTipo() + " at " + ola.getTempo() + "  " + a.chooseEventStrat(ola.getTipo()));
        }
    }
}
