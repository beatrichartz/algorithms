package beatrichartz.algorithms.sorting;

abstract public class Sort<T extends Comparable<T>> {
    public T[] sort(T[] elements) {
        throw new UnsupportedOperationException();
    }

    protected T[] swap(T[] elements, int i, int j) {
        T currentElement = elements[i];
        elements[i] = elements[j];
        elements[j] = currentElement;

        return elements;
    }

    protected boolean isGreaterThan(T[] elements, int i, int j) {
        return compare(elements[i], elements[j]) > 0;
    }

    protected boolean isEqualTo(T[] elements, int i, int j) {
        return compare(elements[i], elements[j]) == 0;
    }

    protected boolean isLessThan(T[] elements, int i, int j) {
        return compare(elements[i], elements[j]) < 0;
    }

    private int compare(T a, T b) {
        return a.compareTo(b);
    }
}
