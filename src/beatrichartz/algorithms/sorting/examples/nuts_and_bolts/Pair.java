package beatrichartz.algorithms.sorting.examples.nuts_and_bolts;

public class Pair {
    private Nut nut;
    private Bolt bolt;

    public Pair(Nut nut, Bolt bolt) {
        this.nut = nut;
        this.bolt = bolt;
    }

    public Nut getNut() {
        return nut;
    }

    public Bolt getBolt() {
        return bolt;
    }

    public boolean fits() {
        return nut.compareTo(bolt) == 0;
    }

    @Override
    public String toString() {
        return "Pair(Nut(" + nut.getSize() + "), Bolt(" + bolt.getSize() + "))";
    }
}
