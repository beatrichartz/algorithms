package beatrichartz.algorithms.sorting.examples.nuts_and_bolts;

import edu.princeton.cs.algs4.StdRandom;

public class PairFinder {
    public static Pair[] find(Nut[] nuts, Bolt[] bolts) {
        StdRandom.shuffle(nuts);
        StdRandom.shuffle(bolts);

        pairSort(nuts, bolts, 0, nuts.length - 1);

        Pair[] pairs = new Pair[nuts.length];
        for (int i = 0; i < nuts.length; i++) {
            pairs[i] = new Pair(nuts[i], bolts[i]);
        }

        return pairs;
    }

    private static void pairSort(Comparable[] nuts, Comparable[] bolts, int low, int high) {
        if (high <= low) return;

        int pivot = partition(nuts, low, high, bolts[high]);
        partition(bolts, low, high, nuts[pivot]);

        pairSort(nuts, bolts, low, pivot - 1);
        pairSort(nuts, bolts, pivot + 1, high);
    }

    private static int partition(Comparable[] elements, int low, int high, Comparable value) {
        int i = low;
        for (int j = low; j < high; j++) {
            int comparison = elements[j].compareTo(value);
            if (comparison < 0) swap(elements, i++, j);
            else if (comparison == 0) swap(elements, j--, high);
        }

        swap(elements, i, high);

        return i;
    }

    private static Comparable[] swap(Comparable[] elements, int i, int j) {
        Comparable currentElement = elements[i];
        elements[i] = elements[j];
        elements[j] = currentElement;

        return elements;
    }

}
