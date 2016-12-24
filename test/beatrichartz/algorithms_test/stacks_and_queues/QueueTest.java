package beatrichartz.algorithms_test.stacks_and_queues;

import beatrichartz.algorithms.stacks_and_queues.LinkedListQueue;
import beatrichartz.algorithms.stacks_and_queues.Queue;
import beatrichartz.algorithms.stacks_and_queues.ResizingArrayQueue;
import beatrichartz.algorithms.stacks_and_queues.examples.QueueOfStacks;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

@RunWith(value = Parameterized.class)
public class QueueTest {

    @Parameterized.Parameters(name = "{0}")
    public static Collection data() {
        List<Object> collection = new ArrayList();
        collection.add(LinkedListQueue.class);
        collection.add(ResizingArrayQueue.class);
        collection.add(QueueOfStacks.class);
        return collection;
    }

    private Class queueClass;
    private Queue<String> queue;

    public QueueTest(Class queueClass) {
        this.queueClass = queueClass;
    }

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        this.queue = (Queue) queueClass.newInstance();
    }

    @Test
    public void dequeueReturnsNullWithEmptyQueue() throws Exception {
        assertEquals(null, queue.dequeue());
    }

    @Test
    public void enqueuedObjectsAreReturnedWhenDequeued() throws Exception {
        queue.enqueue("A");
        assertEquals("A", queue.dequeue());
        assertEquals(null, queue.dequeue());
    }

    @Test
    public void firstEnqueuedObjectsAreReturnedFirst() throws Exception {
        queue.enqueue("A");
        queue.enqueue("B");
        assertEquals("A", queue.dequeue());

        queue.enqueue("C");
        assertEquals("B", queue.dequeue());
        assertEquals("C", queue.dequeue());
        assertEquals(null, queue.dequeue());
    }

    @Test
    public void canBeRefilled() throws Exception {
        queue.enqueue("A");
        queue.enqueue("B");
        queue.dequeue();
        queue.dequeue();

        assertEquals(null, queue.dequeue());

        queue.enqueue("A");
        queue.enqueue("B");
        assertEquals("A", queue.dequeue());

        queue.enqueue("C");
        assertEquals("B", queue.dequeue());
        assertEquals("C", queue.dequeue());
        assertEquals(null, queue.dequeue());
    }
}
