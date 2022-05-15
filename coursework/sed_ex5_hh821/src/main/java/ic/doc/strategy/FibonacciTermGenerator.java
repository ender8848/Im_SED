package ic.doc.strategy;

public class FibonacciTermGenerator implements TermGenerator{

    @Override
    public int positiveTerm(int i) {
        if (i < 2) {
            return 1;
        }
        return positiveTerm(i - 1) + positiveTerm(i - 2);
    }
}
