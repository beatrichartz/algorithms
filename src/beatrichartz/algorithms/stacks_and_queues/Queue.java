package beatrichartz.algorithms.stacks_and_queues;

public interface Queue<T> {
    boolean isEmpty();
    void enqueue(T element);
    T dequeue();
}
