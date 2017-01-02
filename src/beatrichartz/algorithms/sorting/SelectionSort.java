package beatrichartz.algorithms.sorting;

public class SelectionSort<T extends Comparable<T>> extends Sort<T> {
    public T[] sort(T[] elements) {
        for (int i = 0; i < elements.length; i++) {
            int smallestIndex = i;
            for (int j = i; j < elements.length; j++) {
               if (isLessThan(elements, j, smallestIndex))
                   smallestIndex = j;
            }

            swap(elements, smallestIndex, i);
        }

        return elements;
    }
}
