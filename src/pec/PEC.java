package pec;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PEC {
    private final List<CenasFormigas> PriorQue;
    private Map<String, EventStrategy> EventTypeMap = new HashMap<>();
    private Double eta;
    private Double delta;
    private IColony colonia;

    public PEC(double eta, double delta, IColony colonia) {
        this.PriorQue = new ArrayList<>();
        this.eta = eta;
        this.delta = delta;
        insertEvent("Movimento", new MEventStrategy(delta));
        insertEvent("Evaporação", new EEventStrategy(eta));
        this.colonia = colonia;
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
            if (time <= i.getTempo()) { //Ordena por tempo, parando quando encontra um tempo maior
                break;
            }
            aux++;  //Auxiliar para saber em que posição inserir
        }
        this.PriorQue.add(aux, new CenasFormigas(time, Tipo, id));
    }


    public CenasFormigas getFirstElement() {
        //Obtém o primeiro elemento da lista e retira o da fila
        CenasFormigas Elemento = this.PriorQue.get(0);
        this.PriorQue.remove(0);
        Double tempo = ChooseAndExecuteEventStrat(Elemento.getTipo(), Elemento.getID());
        if (tempo != -1.0) { // Caso em que há decaimento, logo é agenda um novo evento de decaimento
            tempo += Elemento.getTempo(); // Adiciona o tempo de decaimento ao tempo atual, para saber em que altura agendar
            Addevent(tempo, Elemento.getTipo(), Elemento.getID());
        }
        System.out.print(tempo + "  "); //Para apagar depois
        return Elemento;
    }

    public List<CenasFormigas> getPriorQue() {
        return PriorQue;
    }

    public void insertEvent(String str, EventStrategy eventStrategy) {
        this.EventTypeMap.put(str, eventStrategy);
    }

    public Double ChooseAndExecuteEventStrat(String event, Integer id) {
        return this.EventTypeMap.get(event).execute(id);
    }

    public int Tamanho_lista() {
        return this.PriorQue.size();
    }

    public static void main(String[] args) {
        PEC a = new PEC(0.2, 0.5, new Colony());
        a.Addevent(5.3, "Evaporação", 3);
        a.Addevent(14.2, "Evaporação", 7);
        a.Addevent(5.77, "Evaporação", 5);
        a.Addevent(5.34, "Evaporação", 4);
        a.Addevent(5.1, "Evaporação", 2);
        /*for (int i = 0; i < 2; i++) {
            CenasFormigas ola = a.getFirstElement();
            System.out.println(ola.getTipo() + " em " + ola.getTempo() + "seg, com ID " + ola.getID());
        }*/
        a.Addevent(13.9, "Evaporação", 6);
        a.Addevent(4.9, "Evaporação", 1);
        while (a.Tamanho_lista() != 0) {
            CenasFormigas ola = a.getFirstElement();
            System.out.println(ola.getTipo() + " em " + ola.getTempo() + "seg, com ID " + ola.getID());
        }
    }
}
