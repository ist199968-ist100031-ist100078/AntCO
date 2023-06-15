package pec;
/**Interface for acessing PEC simulator
 * @see pec.PEC
 * @since 04-Jun-2023
 * */
public interface IPEC {
    /** Push an Event into the PEC Queue
     * @param time current "time" of event
     * @param Tipo type of event
     * @param id ID of target of event
     */
    void Addevent(double time, String Tipo, int id);
    /** Generate a timestamp based on a exponential distribution
     * @param mean center value to determine exponential distribution from
     * @return timestamp
     */
    double ExponentialTime(double mean);
    /** See if PEC Queue is empty
     * @return boolean value of the statement "Is the PEC Queue empty"
     */
    boolean isEmpty();
    /**Popper for PEC highest priority event in Queue*/
    void getFirstElement();
}
