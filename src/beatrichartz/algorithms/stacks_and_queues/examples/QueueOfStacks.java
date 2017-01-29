package beatrichartz.algorithms.stacks_and_queues.examples;

import beatrichartz.algorithms.stacks_and_queues.Queue;
import beatrichartz.algorithms.stacks_and_queues.ResizingArrayStack;
import beatrichartz.algorithms.stacks_and_queues.Stack;

public class QueueOfStacks<T> implements Queue<T> {
    private final Stack<T> topStack;
    private final Stack<T> bottomStack;

    public QueueOfStacks() {
        this(new ResizingArrayStack<T>(), new ResizingArrayStack<T>());
    }

    public QueueOfStacks(Stack<T> topStack, Stack<T> bottomStack) {
        this.topStack = topStack;
        this.bottomStack = bottomStack;
    }

    public void enqueue(T element) {
        topStack.push(element);
    }

    public T dequeue() {
        if (isEmpty()) return null;
        else if (bottomStack.isEmpty()) refillBottomStack();

        return bottomStack.pop();
    }

    public boolean isEmpty() {
        return topStack.isEmpty() && bottomStack.isEmpty();
    }

    private void refillBottomStack() {
        while (!topStack.isEmpty()) {
            bottomStack.push(topStack.pop());
        }
    }
}
