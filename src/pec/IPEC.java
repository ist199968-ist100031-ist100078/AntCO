package pec;

public interface IPEC {
    void Addevent(double time, String Tipo, int id);
    double ExponentialTime(double mean);
    boolean isEmpty();
    void getFirstElement();
}
