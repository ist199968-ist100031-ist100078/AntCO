package pec;
/** Interface to acess Event executer/scheduler
 * @see pec.EvaporationEventStrategy
 * @see pec.MovementEventStrategy
 * @see pec.ObservationEventStrategy
 */
public interface EventStrategy {
   void execute(int id, double tempo, Integer[] NumberEvents);
}
