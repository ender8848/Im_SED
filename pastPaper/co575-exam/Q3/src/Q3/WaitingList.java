package Q3;

public interface WaitingList {
    void anyoneWaiting(Show show, int num);

    void add(Customer customer, Show show, int num);
}
