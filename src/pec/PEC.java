package pec;

import AntCO.IColony;

import java.util.*;

public class PEC {
    private final PriorityQueue<Eventos> PriorQueue;
    private final Map<String, EventStrategy> EventTypeMap = new HashMap<>();
    Integer[] NumberEvents = new Integer[]{0, 0}; //Number of Move Events,Number of Evaporation Events

    private ArrayList<Integer> TopCycles = new ArrayList<>();

    public PEC(double eta, double delta, IColony colonia, int Nu, Double tau, int n) {
        this.PriorQueue = new PriorityQueue<>(Nu + (n * (n - 1) / 2 - n) + 20, Comparator.comparingDouble(Eventos::getTempo));
        insertEvent("Movimento", new MovementEventStrategy(delta, eta, colonia, this));
        insertEvent("Evaporação", new EvaporationEventStrategy(eta, colonia, this));
        insertEvent("Observacao", new ObservationEventStrategy(tau, this));
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
        this.PriorQueue.add(new Eventos(time, Tipo, id));
    }


    public Eventos getFirstElement() {
        //Obtém o primeiro elemento da lista e retira o da fila
        Eventos Elemento = this.PriorQueue.poll();
        ChooseAndExecuteEventStrat(Elemento.getTipo(), Elemento.getID(), Elemento.getTempo());
        return Elemento;
    }

    public PriorityQueue<Eventos> getPriorQueue() {
        return PriorQueue;
    }

    public void insertEvent(String str, EventStrategy eventStrategy) {
        this.EventTypeMap.put(str, eventStrategy);
    }

    public void ChooseAndExecuteEventStrat(String event, int id, double tempo) {
        this.EventTypeMap.get(event).execute(id, tempo, this.NumberEvents);
    }

    public Double ExponentialTime(Double mean) {
        Random rand = new Random(); //Criar singleton depois
        double u = rand.nextDouble();
        return -(mean) * Math.log(1 - u);

    }

    public boolean isEmpty() {
        return this.PriorQueue.isEmpty();
    }

    public ArrayList<Integer> getTopCycles() {
        return TopCycles;
    }

    public void setTopCycles(ArrayList<Integer> topCycles) {
        for (Integer i : topCycles) {
            if (i == 0) {
                break;
            }
            TopCycles = topCycles;
            break;
        }
    }
}

