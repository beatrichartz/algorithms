package beatrichartz.algorithms.sorting;

import edu.princeton.cs.algs4.StdRandom;

public class QuickSort3<T extends Comparable<T>> extends Sort<T> {
    public T[] sort(T[] elements) {
        StdRandom.shuffle(elements);

        sort(elements, 0, elements.length - 1);
        return elements;
    }

    private void sort(T[] elements, int low, int high) {
        if (high <= low) return;
        int lowValue = low;
        int highValue = high;

        T value = elements[low];
        int i = low;

        while (i <= highValue) {
            int comparison = elements[i].compareTo(value);
            if (comparison < 0) swap(elements, lowValue++, i++);
            else if (comparison > 0) swap(elements, i, highValue--);
            else i++;
        }

        sort(elements, low, lowValue - 1);
        sort(elements, highValue + 1, high);
    }
}
