package AntCO;
import java.util.ArrayList;

public interface IColony {
    public int getCost(int hashedge);
    public int getIdEdge(int id);
    public ArrayList<Integer> triggerAntMovement(int triggerid);
    public boolean triggerPheromoneDecay(int hashededge);
}