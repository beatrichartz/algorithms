package beatrichartz.algorithms.sorting.examples.nuts_and_bolts;

public class Nut implements Comparable {
    private int size;
    public Nut(int size) {
        this.size = size;
    }

    public int compareTo(Object other) {
        return Integer.compare(size, ((Bolt) other).getSize());
    }

    public int getSize() {
        return size;
    }
}
