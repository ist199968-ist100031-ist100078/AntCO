package pec;

import antco.IColony;
import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Comparator;

public class PEC {
    private PriorityQueue<Eventos> PriorQueue;
    private Map<String, EventStrategy> EventTypeMap = new HashMap<>();
    Integer[] NumberEvents = new Integer[]{0, 0}; //Number of Move Events,Number of Evaporation Events
    private double tau;

    public PEC(double eta, double delta, IColony colonia, int Nu, double tau, int n) {
        this.PriorQueue = new PriorityQueue<>(Nu + (n * (n - 1) / 2 - n) + 20, Comparator.comparingDouble(Eventos::getTempo));
        this.tau = tau;
        insertEvent("Movimento", new MovementEventStrategy(delta, eta, colonia, this));
        insertEvent("Evaporação", new EvaporationEventStrategy(eta, colonia, this));
        insertEvent("Observacao", new ObservationEventStrategy(colonia));
        this.InicializationOfEvents(Nu);
    }

    private void InicializationOfEvents(int Nu) {
        for (int i = 0; i < Nu; i++) {
            this.Addevent(0.0, "Movimento", i);
        }
        for (int i = 1; i <= 20; i++) {
            this.Addevent(i * (this.tau / 20), "Observacao", i);
        }
    }

    public void Addevent(double time, String Tipo, Integer id) {
        if (time < this.tau || Tipo.equals("Observacao")){
            this.PriorQueue.add(new Eventos(time, Tipo, id));
        }
    }

    public PriorityQueue<Eventos> getPriorQueue() {
        return PriorQueue;
    }

    public void getFirstElement() {
        //Obtém o primeiro elemento da lista e retira o da fila
        Eventos Elemento = this.PriorQueue.poll();
        ChooseAndExecuteEventStrat(Elemento.getTipo(), Elemento.getID(), Elemento.getTempo());
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

}

