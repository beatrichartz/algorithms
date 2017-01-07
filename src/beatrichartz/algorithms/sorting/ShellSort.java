package beatrichartz.algorithms.sorting;

public class ShellSort<T extends Comparable<T>> extends Sort<T> {
    private int factor = 100;
    public T[] sort(T[] elements) {
        int h = 1;
        while (h < elements.length / factor) h = h * factor + 1;
        while (h >= 1) {
            for (int i = 0; i < elements.length; i++) {
                for (int j = i; j >= h; j -= h) {
                    if (isLessThan(elements, j, j-h))
                        swap(elements, j, j-h);
                }
            }
            h /= factor;
        }
        return elements;
    }
}
