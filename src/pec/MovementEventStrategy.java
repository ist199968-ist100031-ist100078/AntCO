package pec;
import  antco.IColony;
import java.util.ArrayList;
/** Movement Event Strategy for PEC simulator
 * @see antco.Ant
 * @since 04-Jun-2023
 */
public class MovementEventStrategy implements EventStrategy {
    /**Mean value to determine exponential distribution of movement event scheduling*/
    private final double meanMovimento;
    
    /**Mean value to determine exponential distribution of evaporation event scheduling*/
    private final double meanEvaporacao;

    /**Interface to acess Colony to which the event refers to*/
    private final IColony colony;
    
    /**PEC simulator to which the event belongs to*/
    private final IPEC pec;

/** Public constructor for MovementEventStrategy Class
 * @param meanMovimento arithmetic parameter for scheduling next movement
 * @param meanEvaporacao arithmetic parameter for scheduling next evaporation
 * @param colony colony to which the event refers to
 * @param pec simulator to which the event refers to
 */
    public MovementEventStrategy(double meanMovimento, double meanEvaporacao, IColony colony, IPEC pec) {
        this.meanMovimento = meanMovimento;
        this.meanEvaporacao = meanEvaporacao;
        this.colony = colony;
        this.pec = pec;
    }
/** Executer of Ant Movement trigger and Scheduler for next Movement and Evaporation
 * @param id ID of Ant to trigger movement of
 * @param tempo current "time"
 * @param NumberEvents current number of events in pec queue
 * @see antco.Colony#triggerAntMovement
 */
    @Override
    public void execute(int id, double tempo, int[] NumberEvents) {
        NumberEvents[0]++;
        ArrayList<Integer> list = this.colony.triggerAntMovement(id);
        double time = this.pec.ExponentialTime( this.meanMovimento * colony.getCost(colony.getIdEdge(id)));
        for (Integer i : list) {
            if(i != 0){
                this.pec.Addevent(tempo + time + this.pec.ExponentialTime(this.meanEvaporacao), "Evaporação", i);
            }
        }
        this.pec.Addevent(tempo + time, "Movimento", id);
    }
}
