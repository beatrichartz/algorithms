package beatrichartz.algorithms.sorting;

public class InsertionSort<T extends Comparable<T>> extends Sort<T> {
    public T[] sort(T[] elements) {
        for (int i = 0; i < elements.length; i++) {
            for (int j = i; j > 0; j--) {
               if (isGreaterThan(elements, j-1, j))
                   swap(elements, j-1, j);
            }
        }
        return elements;
    }
}
