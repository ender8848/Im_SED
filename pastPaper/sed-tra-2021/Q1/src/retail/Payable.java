package retail;

public interface Payable<T extends Comparable<T> > {
    T process();
}
