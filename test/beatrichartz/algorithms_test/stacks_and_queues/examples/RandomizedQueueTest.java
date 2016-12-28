package beatrichartz.algorithms_test.stacks_and_queues.examples;

import beatrichartz.algorithms.stacks_and_queues.examples.RandomizedQueue;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Constructor;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static junit.framework.TestCase.assertEquals;

public class RandomizedQueueTest {

    private RandomizedQueue<String> randomizedQueue;

    @Before
    public void setUp() throws Exception {
        StdRandom.setSeed(System.currentTimeMillis());
        randomizedQueue = new RandomizedQueue();
    }

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void enqueueingNullThrows() throws Exception {
        expectedException.expect(NullPointerException.class);
        randomizedQueue.enqueue(null);
    }

    @Test
    public void checkingIfEmpty() throws Exception {
        assertEquals(true, randomizedQueue.isEmpty());

        randomizedQueue.enqueue("A");
        assertEquals(false, randomizedQueue.isEmpty());
    }

    @Test
    public void sizeReturnsCountOfEnqueuedItems() throws Exception {
        assertEquals(0, randomizedQueue.size());

        randomizedQueue.enqueue("A");

        assertEquals(1, randomizedQueue.size());
    }

    @Test
    public void dequeueWithAnEmptyQueueThrows() throws Exception {
        expectedException.expect(NoSuchElementException.class);
        randomizedQueue.dequeue();
    }

    @Test
    public void dequeWithOneItemQueueReturnsItem() throws Exception {
        randomizedQueue.enqueue("A");

        assertEquals("A", randomizedQueue.dequeue());

        expectedException.expect(NoSuchElementException.class);
        randomizedQueue.dequeue();
    }

    @Test
    public void dequeRemovesAndReturnsARandomItemFromAQueue() throws Exception {
        StdRandom.setSeed(98_988);
        randomizedQueue.enqueue("A");
        randomizedQueue.enqueue("B");
        randomizedQueue.enqueue("C");

        assertEquals("B", randomizedQueue.dequeue());
        assertEquals("A", randomizedQueue.dequeue());
        assertEquals("C", randomizedQueue.dequeue());

        expectedException.expect(NoSuchElementException.class);
        randomizedQueue.dequeue();
    }

    @Test
    public void sampleWithEmptyQueueThrows() throws Exception {
        expectedException.expect(NoSuchElementException.class);

        randomizedQueue.sample();
    }

    @Test
    public void sampleShowsARandomItemFromAQueue() throws Exception {
        StdRandom.setSeed(5_000);
        randomizedQueue.enqueue("A");
        randomizedQueue.enqueue("B");
        randomizedQueue.enqueue("C");

        assertEquals("B", randomizedQueue.sample());
        assertEquals("A", randomizedQueue.sample());
        assertEquals("B", randomizedQueue.sample());
        assertEquals("B", randomizedQueue.sample());
        assertEquals("C", randomizedQueue.sample());
    }

    @Test
    public void iteratorIteratesQueueRandomly() throws Exception {
        StdRandom.setSeed(5_000);
        randomizedQueue.enqueue("A");
        randomizedQueue.enqueue("B");
        randomizedQueue.enqueue("C");

        Iterator<String> iterator = randomizedQueue.iterator();
        assertEquals(true, iterator.hasNext());
        assertEquals("B", iterator.next());
        assertEquals(true, iterator.hasNext());
        assertEquals("A", iterator.next());
        assertEquals(true, iterator.hasNext());
        assertEquals("C", iterator.next());
        assertEquals(false, iterator.hasNext());

        expectedException.expect(NoSuchElementException.class);
        iterator.next();
    }

    @Test
    public void iteratorTakesACopyOfElements() throws Exception {
        StdRandom.setSeed(6_000);
        randomizedQueue.enqueue("A");
        randomizedQueue.enqueue("B");
        randomizedQueue.enqueue("C");

        Iterator<String> iterator = randomizedQueue.iterator();
        for (int i = 0; i < 3; i++) {
            iterator.next();
        }

        assertEquals("B", randomizedQueue.dequeue());
        assertEquals("A", randomizedQueue.dequeue());
        assertEquals("C", randomizedQueue.dequeue());

        expectedException.expect(NoSuchElementException.class);
        iterator.next();
    }

    @Test
    public void multipleIteratorsOperateIndependently() throws Exception {
        randomizedQueue.enqueue("A");
        randomizedQueue.enqueue("B");
        randomizedQueue.enqueue("C");

        StdRandom.setSeed(7_000);
        Iterator<String> iteratorA = randomizedQueue.iterator();
        Iterator<String> iteratorB = randomizedQueue.iterator();

        assertEquals(false, iteratorA.next().equals(iteratorB.next()));
        assertEquals(false, iteratorA.next().equals(iteratorB.next()));
        assertEquals(false, iteratorA.next().equals(iteratorB.next()));
    }

    @Test
    public void removeFromIteratorThrows() throws Exception {
        Iterator<String> iterator = randomizedQueue.iterator();

        expectedException.expect(UnsupportedOperationException.class);
        iterator.remove();
    }

    @Test
    public void resizesWhenFull() throws Exception {
        StdRandom.setSeed(8_000);

        /*
         Constructor has to be private for submission,
         so this is needed.
          */
        Constructor<RandomizedQueue> constructor = (Constructor<RandomizedQueue>) RandomizedQueue.class.getDeclaredConstructors()[1];
        constructor.setAccessible(true);
        randomizedQueue = constructor.newInstance(2);

        randomizedQueue.enqueue("A");
        randomizedQueue.enqueue("B");
        randomizedQueue.enqueue("C");
        randomizedQueue.enqueue("D");
        randomizedQueue.enqueue("E");

        assertEquals("C", randomizedQueue.dequeue());
        assertEquals("B", randomizedQueue.dequeue());
        assertEquals("E", randomizedQueue.dequeue());
        assertEquals("A", randomizedQueue.dequeue());
        assertEquals("D", randomizedQueue.dequeue());

        expectedException.expect(NoSuchElementException.class);
        randomizedQueue.dequeue();
    }
}
