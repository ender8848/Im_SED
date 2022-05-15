package ic.doc.templatemethod;

import java.util.Iterator;

public abstract class Sequence implements Iterable<Integer> {

    public int term(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Not defined for indices < 0");
        }
        return positiveTerm(i);
    }

    abstract int positiveTerm(int i);

    public Iterator<Integer> iterator() {
        return new Sequence.SequenceIterator();
    }

    private class SequenceIterator implements Iterator<Integer> {

        private int index = 0;

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public Integer next() {
            return positiveTerm(index++);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove is not implemented");
        }
    }
}
