package pec;

import antco.IColony;
import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Comparator;
/**
 * PEC simulator class for Ant Colony Optimization simulation
 */
public class PEC implements IPEC{
    /**Priority Queue of events to be executed and popped*/
    private PriorityQueue<Eventos> PriorQueue;
    /**Type of Event HashMap*/
    private Map<String, EventStrategy> EventTypeMap = new HashMap<>();
    /**Number of move events in index 0, number of evaporation events in index 1*/
    Integer[] NumberEvents = new Integer[]{0, 0}; //Number of Move Events,Number of Evaporation Events
    /**final instant of simulation aka determiner of simulation duration*/
    private double tau;
	/**Public constructor for PEC simulator
	 * @param eta arithmethic parameter for pheromone evaporation event scheduling
	 * @param delta arithmethic parameter for ant move event scheduling
	 * @param colonia Ant Colony to which the PEC simulation refers
	 * @param Nu Size of Colony (Ant population)
	 * @param tau final instant for simulation, gives duration for simulation
	 * @param n number of nodes in graph
	 */
    public PEC(double eta, double delta, IColony colonia, int Nu, double tau, int n) {
        this.PriorQueue = new PriorityQueue<>(Nu + (n * (n - 1) / 2 - n) + 20, Comparator.comparingDouble(Eventos::getTempo));
        this.tau = tau;
        insertEvent("Movimento", new MovementEventStrategy(delta, eta, colonia, this));
        insertEvent("Evaporação", new EvaporationEventStrategy(eta, colonia, this));
        insertEvent("Observacao", new ObservationEventStrategy(colonia));
        this.InicializationOfEvents(Nu);
    }
	/** Push all starting Movement and Observation events into Queue
	 * @param Nu Size of Colony (Ant population)
	 */
    private void InicializationOfEvents(int Nu) {
        for (int i = 0; i < Nu; i++) {
            this.Addevent(0.0, "Movimento", i);
        }
        for (int i = 1; i <= 20; i++) {
            this.Addevent(i * (this.tau / 20), "Observacao", i);
        }
    }
	/** Push an Event into the PEC Queue
	 * @param time current "time" of event
	 * @param Tipo type of event can be a Movement, a Evaporation or a Observation event
	 * @param id ID of Ant to move, Edge to evaporate pheromone from or frame to observe
	 */
    public void Addevent(double time, String Tipo, Integer id) {
        if (time < this.tau || Tipo.equals("Observacao")){
            this.PriorQueue.add(new Eventos(time, Tipo, id));
        }
    }
	/** Public getter for PEC Queue
	 *@return PEC Priority Queue
	 */
    public PriorityQueue<Eventos> getPriorQueue() {
        return PriorQueue;
    }
	/**Public popper for PEC highest priority event in Queue*/
    public void getFirstElement() {
        //Obtém o primeiro elemento da lista e retira o da fila
        Eventos Elemento = this.PriorQueue.poll();
        ChooseAndExecuteEventStrat(Elemento.getTipo(), Elemento.getID(), Elemento.getTempo());
    }
	/** Insert event into Hash Map to choose strategy
	 * @param str event type
	 * @param eventStrategy type of strategy to use for event
	*/
    public void insertEvent(String str, EventStrategy eventStrategy) {
        this.EventTypeMap.put(str, eventStrategy);
    }
	/** Choose a strategy and excute event accordingly
	 * @param event event type can be a Movement, a Pheromone Decay or Observaion
	 * @param id Identifier of Ant to move, Edge to decay pheromone from or Frame to observe
	 * @param tempo current time
	 */
    public void ChooseAndExecuteEventStrat(String event, int id, double tempo) {
        this.EventTypeMap.get(event).execute(id, tempo, this.NumberEvents);
    }
	/** Generate a timestamp based on a exponential distribution
	 * @param mean center value to determine exponential distribution from
	 * @return timestamp
	 */
    public Double ExponentialTime(Double mean) {
        Random rand = new Random(); //Criar singleton depois
        double u = rand.nextDouble();
        return -(mean) * Math.log(1 - u);

    }
	/** See if PEC Queue is empty
	 * @return boolean value of the statement "Is the PEC Queue empty"
	 */
    public boolean isEmpty() {
        return this.PriorQueue.isEmpty();
    }

}

