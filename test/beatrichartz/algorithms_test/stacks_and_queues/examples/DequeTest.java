package beatrichartz.algorithms_test.stacks_and_queues.examples;

import beatrichartz.algorithms.stacks_and_queues.examples.Deque;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static junit.framework.TestCase.assertEquals;

public class DequeTest {
    private Deque<String> deque;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        deque = new Deque();
    }

    @Test
    public void checkingIfEmpty() throws Exception {
        assertEquals(true, deque.isEmpty());

        deque.addFirst("A");
        assertEquals(false, deque.isEmpty());

        deque.removeFirst();
        assertEquals(true, deque.isEmpty());

        deque.addLast("A");
        assertEquals(false, deque.isEmpty());

        deque.removeLast();
        assertEquals(true, deque.isEmpty());
    }

    @Test
    public void gettingSize() throws Exception {
        assertEquals(0, deque.size());

        deque.addFirst("A");
        assertEquals(1, deque.size());

        deque.addLast("B");
        assertEquals(2, deque.size());

        deque.removeFirst();
        assertEquals(1, deque.size());

        deque.removeLast();
        assertEquals(0, deque.size());
    }

    @Test
    public void removingFirstElementFromEmptyQueueThrows() throws Exception {
        exception.expect(NoSuchElementException.class);
        deque.removeFirst();
    }

    @Test
    public void removingLastElementFromEmptyQueueThrows() throws Exception {
        exception.expect(NoSuchElementException.class);
        deque.removeLast();
    }

    @Test
    public void addingNullElementsFirstThrows() throws Exception {
        exception.expect(NullPointerException.class);
        deque.addFirst(null);
    }

    @Test
    public void addingNullElementsLastThrows() throws Exception {
        exception.expect(NullPointerException.class);
        deque.addLast(null);
    }

    @Test
    public void addingAndRemovingFirstElementsBehavesLikeAStack() throws Exception {
        deque.addFirst("A");

        assertEquals("A", deque.removeFirst());

        deque.addFirst("B");
        deque.addFirst("C");

        assertEquals("C", deque.removeFirst());
        assertEquals("B", deque.removeFirst());

        exception.expect(NoSuchElementException.class);
        deque.removeFirst();
    }

    @Test
    public void addingAndRemovingLastElementsBehavesLikeAStack() throws Exception {
        deque.addLast("A");
        assertEquals("A", deque.removeLast());

        deque.addLast("B");
        deque.addLast("C");

        assertEquals("C", deque.removeLast());
        assertEquals("B", deque.removeLast());

        exception.expect(NoSuchElementException.class);
        deque.removeLast();
    }

    @Test
    public void addingFirstAndRemovingLastBehavesLikeAQueue() throws Exception {
        deque.addFirst("A");
        assertEquals("A", deque.removeLast());

        deque.addFirst("B");
        deque.addFirst("C");

        assertEquals("B", deque.removeLast());
        assertEquals("C", deque.removeLast());

        exception.expect(NoSuchElementException.class);
        deque.removeLast();
    }

    @Test
    public void addingLastAndRemovingFirstBehavesLikeAQueue() throws Exception {
        deque.addLast("A");
        assertEquals("A", deque.removeFirst());

        deque.addLast("B");
        deque.addLast("C");

        assertEquals("B", deque.removeFirst());
        assertEquals("C", deque.removeFirst());
    }

    @Test
    public void intermixedAddAndRemoveOperationsReturnRightResults() throws Exception {
        deque.addLast("A");
        deque.addFirst("B");
        assertEquals("B", deque.removeFirst());
        assertEquals("A", deque.removeLast());

        deque.addLast("C");
        assertEquals("C", deque.removeFirst());
    }

    @Test
    public void iteratorIteratesFromFirstToLastElement() throws Exception {
        deque.addFirst("A");
        deque.addLast("B");
        deque.addLast("C");

        Iterator<String> iterator = deque.iterator();
        assertEquals(true, iterator.hasNext());
        assertEquals("A", iterator.next());

        assertEquals(true, iterator.hasNext());
        assertEquals("B", iterator.next());

        assertEquals(true, iterator.hasNext());
        assertEquals("C", iterator.next());

        assertEquals(false, iterator.hasNext());
        exception.expect(NoSuchElementException.class);
        iterator.next();
    }

    @Test
    public void iteratorRemoveThrows() throws Exception {
        Iterator<String> iterator = deque.iterator();

        exception.expect(UnsupportedOperationException.class);
        iterator.remove();
    }
}
