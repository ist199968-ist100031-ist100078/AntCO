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

    public void Addevent(Double event, Integer id) {
        int aux = 0;
        for (CenasFormigas i : this.PriorQue) {
            if (event <= i.getTempo()) {
                break;
            }
            aux++;
        }
        this.PriorQue.add(aux, new CenasFormigas(event, id));
    }


    // PODE SE APAGAR DEPOIS
    public void Addevent(Double time, int a, Integer id) {
        int aux = 0;
        for (CenasFormigas i : this.PriorQue) {
            if (time <= i.getTempo()) {
                break;
            }
            aux++;
        }
        this.PriorQue.add(aux, new CenasFormigas(time, a, id));
    }

    public void Addevent(Double time, String Tipo, Integer id) {
        int aux = 0;
        for (CenasFormigas i : this.PriorQue) {
            if (time <= i.getTempo()) {
                break;
            }
            aux++;
        }
        this.PriorQue.add(aux, new CenasFormigas(time, Tipo, id));
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
        a.Addevent(5.3, "Evaporação", 0);
        a.Addevent(14.2, "Movimento", 1);
        a.Addevent(5.77, "Evaporação", 1);
        a.Addevent(5.34, "Evaporação", 2);
        a.Addevent(5.1, "Movimento", 2);
        for (int i = 0; i < 2; i++) {
            CenasFormigas ola = a.getFirstElement();
            System.out.println(ola.getTipo() + " em " + ola.getTempo() + "seg, com ID " + ola.getID() + "  Execute: " + a.chooseEventStrat(ola.getTipo()));
        }
        a.Addevent(13.9, "Movimento",3);
        a.Addevent(2.9, "Movimento",4);
        int tam = a.getPriorQue().size();
        for (int i = 0; i < tam; i++) {
            CenasFormigas ola = a.getFirstElement();
            System.out.println(ola.getTipo() + " em " + ola.getTempo() + "seg, com ID " + ola.getID() + "  Execute:" + a.chooseEventStrat(ola.getTipo()));
        }
    }
}
