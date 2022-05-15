package ic.doc;

import java.util.ArrayList;
import java.util.List;

public class RecentlyUsedList<E> {
    private List<E> items;

    public RecentlyUsedList() {
        items = new ArrayList<>();
    }

    public Boolean isEmpty() {
        return items.isEmpty();
    }


    public void add(E item) {
        if (items.contains(item)) {
            items.remove(item);
        }
        items.add(0, item);
    }

    public E retrieve(int i) {
        return items.get(i);
    }

    public int size() {
        return items.size();
    }
}
