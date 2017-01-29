package beatrichartz.algorithms.elementary_symbol_tables;

public interface OrderedSymbolTable<Key extends Comparable<Key>, Value> extends SymbolTable<Key, Value> {
    Key min();
    Key max();
    Key floor(Key key);
    Key ceiling(Key key);
    int rank(Key key);
    Key select(int rank);
    void deleteMin();
    void deleteMax();
    int size(Key low, Key high);
    Iterable<Key> keys(Key low, Key high);
}
