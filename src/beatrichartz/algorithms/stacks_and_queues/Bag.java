package beatrichartz.algorithms.stacks_and_queues;

public interface Bag<T> extends Iterable<T> {
    void add(T element);
    int size();
}
