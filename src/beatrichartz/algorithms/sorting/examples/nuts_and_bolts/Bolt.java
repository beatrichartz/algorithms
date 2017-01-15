package beatrichartz.algorithms.sorting.examples.nuts_and_bolts;

public class Bolt implements Comparable {
    private int size;

    public Bolt(int size) {
       this.size = size;
    }

    public int getSize() {
        return size;
    }

    public int compareTo(Object other) {
        return Integer.compare(size, ((Nut) other).getSize());
    }
}
