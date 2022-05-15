package ic.doc.strategy;

public class TriangularTermGenerator implements TermGenerator{

    @Override
    public int positiveTerm(int i) {
        if (i < 1) {
            return 1;
        }
        return (i+1)*(i+2)/2;
    }
}
