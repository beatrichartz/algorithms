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
        int lowerThan = low;
        int greaterThan = high;

        T value = elements[low];
        int i = low;

        while (i <= greaterThan) {
            int comparison = elements[i].compareTo(value);
            if (comparison < 0) swap(elements, lowerThan++, i++);
            else if (comparison > 0) swap(elements, i, greaterThan--);
            else i++;
        }

        sort(elements, low, lowerThan - 1);
        sort(elements, greaterThan + 1, high);
    }
}
