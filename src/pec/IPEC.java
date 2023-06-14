package pec;

public interface IPEC {
    public void Addevent(double time, String Tipo, Integer id);
    public Double ExponentialTime(Double mean);
    public boolean isEmpty();
    public void getFirstElement();
}
