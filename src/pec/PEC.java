package pec;

import java.util.*;

public class PEC {
    private final List<Eventos> PriorQue;
    private final Map<String, EventStrategy> EventTypeMap = new HashMap<>();
    Integer[] NumberEvents = new Integer[]{0, 0}; //Number of Move Events,Number of Evaporation Events
    private IColony colonia;

    public PEC(double eta, double delta, IColony colonia, int Nu, Double tau) {
        this.PriorQue = new ArrayList<>();
        insertEvent("Movimento", new MovementEventStrategy(delta, eta, colonia, this));
        insertEvent("Evaporação", new EvaporationEventStrategy(eta, colonia, this));
        insertEvent("Observacao", new ObservationEventStrategy(tau));
        this.colonia = colonia;
        this.InicializationOfEvents(Nu, tau);
    }

    private void InicializationOfEvents(int Nu, Double tau) {
        for (int i = 1; i <= Nu; i++) {
            this.Addevent(0.0, "Movimento", i);
        }
        for (int i = 1; i <= 20; i++) {
            this.Addevent(i * (tau / 20), "Observacao", 0);
        }
    }

    public void Addevent(Double time, String Tipo, Integer id) {
        int aux = 0;
        for (Eventos i : this.PriorQue) {
            if (time <= i.getTempo()) { //Ordena por tempo, parando quando encontra um tempo maior
                break;
            }
            aux++;  //Auxiliar para saber em que posição inserir
        }
        this.PriorQue.add(aux, new Eventos(time, Tipo, id));
    }


    public Eventos getFirstElement() {
        //Obtém o primeiro elemento da lista e retira o da fila
        Eventos Elemento = this.PriorQue.get(0);
        this.PriorQue.remove(0);
        Double tempo;
        if (Elemento.getID() == null) {
            tempo = ChooseAndExecuteEventStrat(Elemento.getTipo(), Elemento.getTempo());
        } else {
            tempo = ChooseAndExecuteEventStrat(Elemento.getTipo(), Double.valueOf(Elemento.getID()));
        }
        if (tempo == -2.0) {
            this.PriorQue.clear();
        } else if (tempo != -1.0) { // Caso em que há decaimento ou movimento das formigas, logo é agenda um novo evento
            tempo += Elemento.getTempo(); // Adiciona o tempo da distribuição exponencial ao tempo atual, para saber em que altura agendar
            Addevent(tempo, Elemento.getTipo(), Elemento.getID());
        }
        return Elemento;
    }

    public List<Eventos> getPriorQue() {
        return PriorQue;
    }

    public void insertEvent(String str, EventStrategy eventStrategy) {
        this.EventTypeMap.put(str, eventStrategy);
    }

    public Double ChooseAndExecuteEventStrat(String event, Double id) {

        return this.EventTypeMap.get(event).execute(id, this.NumberEvents);
    }
    public Double ExponentialTime(Double mean) {
        Random rand = new Random(); //Criar singleton depois
        double u = rand.nextDouble();
        return -(mean) * Math.log(1 - u);
    }
    public int Tamanho_lista() {
        return this.PriorQue.size();
    }

    public static void main(String[] args) {
        int Nu = 5;
        Double tau = 20.0;
        PEC a = new PEC(2.0, 0.2, new Colony(), Nu, tau);
        while (a.Tamanho_lista() != 0) {
            Eventos ola = a.getFirstElement();
            /*if (!ola.getTipo().equals("Observacao")) {
                 System.out.println(ola.getTipo() + " em " + ola.getTempo() + "seg, com ID " + ola.getID());*/
        }
    }
}

