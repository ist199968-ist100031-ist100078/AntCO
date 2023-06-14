package pec;
/**Event Class for PEC simulator
 */
public class Eventos {
    /**Time to which the event is scheduled to execute*/
    private final Double Tempo;
    /**Type of event*/
    private final String Tipo;
    /**Identifier of target of event*/
    private final Integer ID;

	/**Public Constructor for Eventos Class
	 * @param tempo a time value
	 * @param type type of event can be "Evaporação" (EvaporationEvent), "Observacao"(ObservationEvent) or "Movimento" (MovementEvent)
	 * @param id ID of Ant to move or edge to evaporate
	 * @see pec.EvaporationEventStrategy
	 * @see pec.MovementEventStrategy
	 * @see pec.ObservationEventStrategy
	 */
    Eventos(Double tempo, String type, Integer id) {
        this.Tempo = tempo;
        this.Tipo = type;
        this.ID = id;

    }
	/** getter for Tempo attribute
	 *@return time of event
	 */
    public Double getTempo() {
        return Tempo;
    }
	/**getter for Tipo attribute
	 * @return type of event
	 */
    public String getTipo() {
        return Tipo;
    }
	/**getter for ID attribute
	 * @return ID of Ant or Edge
	 */
    public Integer getID() {
        return ID;
    }

}
