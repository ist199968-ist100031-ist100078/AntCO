package pec;
/** Interface to acess Event executer/scheduler
 * @see pec.EvaporationEventStrategy
 * @see pec.MovementEventStrategy
 * @see pec.ObservationEventStrategy
 * @since 04-Jun-2023
 */
public interface EventStrategy {
   /**Public executer for generic Event
    * @param id id of a event characteristic
    * @param tempo current "time"
    * @param NumberEvents number of events is pec queue
    */
   void execute(int id, double tempo, int[] NumberEvents);
}
