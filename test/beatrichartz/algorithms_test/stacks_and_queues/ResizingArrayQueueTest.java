package beatrichartz.algorithms_test.stacks_and_queues;

import beatrichartz.algorithms.stacks_and_queues.ResizingArrayQueue;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class ResizingArrayQueueTest {
    @Test
    public void resizesQueueWhenEnqueuingBeyondCapacity() throws Exception {
        ResizingArrayQueue<Integer> resizingArrayQueue = new ResizingArrayQueue(2);
        for (int i = 0; i < 20; i++) {
            resizingArrayQueue.enqueue(i);
        }

        for (int i = 0; i < 20; i++) {
            assertEquals(i, (int) resizingArrayQueue.dequeue());
        }

        assertEquals(null, resizingArrayQueue.dequeue());
    }

    @Test
    public void resizesQueueToOptimalCapacityWithElementsBeingDequeuedContinuously() throws Exception {
        ResizingArrayQueue<Integer> resizingArrayQueue = new ResizingArrayQueue(2);
        int cursor = 0;
        for (int i = 0; i < 100; i++) {
            resizingArrayQueue.enqueue(i);
            if (i%2 == 0) {
                assertEquals(cursor, (int) resizingArrayQueue.dequeue());
                cursor++;
            }
        }

        for (int i = 50; i < 100; i++) {
            assertEquals(i, (int) resizingArrayQueue.dequeue());
        }

        assertEquals(null, resizingArrayQueue.dequeue());
    }
}
