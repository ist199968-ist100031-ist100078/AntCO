package pec;

import java.util.ArrayList;

public class Colony implements IColony{
    @Override
    public int getCost(int hashedge) {
        return 5;
    }

    @Override
    public ArrayList<Integer> triggerAntMovement(int triggerid) {
        ArrayList<Integer> lista =  new ArrayList<>();
        lista.add(3);
        lista.add(9);
        lista.add(4);
        return lista;
    }
    @Override
    public boolean triggerPheromoneDecay(int a)
    {
        return a == 1;
    }
}
