package beatrichartz.algorithms.elementary_symbol_tables;

import java.util.Iterator;

public class ArraySymbolTable<Key extends Comparable<Key>, Value> implements SymbolTable<Key, Value> {
    private int size;
    private Key[] keys;
    private Value[] values;

    public ArraySymbolTable(int initialCapacity) {
        keys = (Key[]) new Comparable[initialCapacity];
        values = (Value[]) new Object[initialCapacity];
    }

    public ArraySymbolTable() {
        this(10);
    }

    public void put(Key key, Value value) {
        int rank = rank(key);
        if (size == keys.length) resize(size * 2 + 1);
        keys[rank] = key;
        values[rank] = value;
        size++;
    }

    private void resize(int newCapacity) {
        Key[] newKeys = (Key[]) new Comparable[newCapacity];
        Value[] newValues = (Value[]) new Object[newCapacity];

        System.arraycopy(keys, 0, newKeys, 0, size);
        System.arraycopy(values, 0, newValues, 0, size);

        keys = newKeys;
        values = newValues;
    }

    private int rank(Key key) {
        int low = 0;
        int high = size - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int comparison = keys[mid].compareTo(key);
            if (comparison > 0) {
                high = mid - 1;
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                return mid;
            }
        }

        return low;
    }

    public Value get(Key key) {
        int rank = rank(key);
        if (isValidRank(key, rank)) return values[rank];

        return null;
    }

    public void delete(Key key) {
        int rank = rank(key);
        if (isValidRank(key, rank(key))) {
            if (rank + 1 < size) {
                System.arraycopy(keys, rank + 1, keys, rank, size - rank);
                System.arraycopy(values, rank + 1, values, rank, size - rank);
            } else {
                keys[rank] = null;
                values[rank] = null;
            }

            size--;
            if (size <= keys.length / 2) resize(size / 2 + 1);
        }
    }

    private boolean isValidRank(Key key, int rank) {
        return rank < size && keys[rank].equals(key);
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private class KeyIterator implements Iterator<Key> {
        private final Key[] keys;
        private final int size;
        private int index;

        public KeyIterator(Key[] keys, int size) {
            this.keys = keys;
            this.size = size;
        }

        public boolean hasNext() {
            return index < size;
        }

        public Key next() {
            return keys[index++];
        }
    }

    private class KeyIterable implements Iterable<Key> {
        private final Key[] keys;
        private final int size;

        public KeyIterable(Key[] keys, int size) {
            this.keys = keys;
            this.size = size;
        }

        public Iterator<Key> iterator() {
            return new KeyIterator(keys, size);
        }
    }

    public Iterable<Key> keys() {
        return new KeyIterable(keys, size);
    }
}
