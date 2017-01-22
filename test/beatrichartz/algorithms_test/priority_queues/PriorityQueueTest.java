package beatrichartz.algorithms_test.priority_queues;

import beatrichartz.algorithms.priority_queues.PriorityQueue;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.NoSuchElementException;

import static junit.framework.TestCase.assertEquals;

public class PriorityQueueTest {
    PriorityQueue<Integer> empty = new PriorityQueue<>();
    PriorityQueue<Integer> one = new PriorityQueue<>();
    PriorityQueue<Integer> many = new PriorityQueue<>();

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        one.insert(1);
        many.insert(1);
        many.insert(3);
        many.insert(2);
    }

    @Test
    public void returnsEmptiness() throws Exception {
        assertEquals(true, empty.isEmpty());
        assertEquals(false, one.isEmpty());
        assertEquals(false, many.isEmpty());
    }

    @Test
    public void returnsSize() throws Exception {
        assertEquals(0, empty.size());
        assertEquals(1, one.size());
        assertEquals(true, many.size() > 1);
    }

    @Test
    public void deletesMaximumElement() throws Exception {
        assertEquals(3, (int) many.delMax());
        many.insert(-4);

        assertEquals(2, (int) many.delMax());
        assertEquals(1, (int) many.delMax());
        assertEquals(-4, (int) many.delMax());

        expectedException.expect(NoSuchElementException.class);

        many.delMax();
    }

    @Test
    public void growsWhenFull() throws Exception {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(1);
        priorityQueue.insert(3);
        priorityQueue.insert(4);

        assertEquals(2, priorityQueue.size());
    }
}
