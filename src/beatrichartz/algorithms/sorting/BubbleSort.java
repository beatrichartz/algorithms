package beatrichartz.algorithms.sorting;

public class BubbleSort<T extends Comparable<T>> extends Sort<T> {
    public T[] sort(T[] elements) {
        boolean swapped;
        do {
            swapped = false;
            for (int i = 1; i < elements.length; i++) {
                if (isGreaterThan(elements, i-1, i)) {
                    swap(elements, i-1, i);
                    swapped = true;
                }
            }
        } while (swapped);

        return elements;
    }
}
