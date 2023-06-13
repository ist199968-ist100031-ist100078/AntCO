package pec;
import  antco.IColony;
import java.util.ArrayList;
/** Movement Event Strategy for PEC simulator
 * @see antco.Ant
 */
public class MovementEventStrategy implements EventStrategy {
    private final Double meanMovimento;
    private final Double meanEvaporacao;
    private final IColony colony;
    private final PEC pec;
/** Public constructor for MovementEventStrategy Class
 * @param meanMovimento arithmetic parameter for scheduling next movement
 * @param meanEvaporacao arithmetic parameter for scheduling next evaporation
 * @param colony colony to which the event refers to
 * @param pec simulator to which the event refers to
 */
    public MovementEventStrategy(Double meanMovimento, Double meanEvaporacao, IColony colony, PEC pec) {
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
    public void execute(int id, double tempo, Integer[] NumberEvents) {
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
