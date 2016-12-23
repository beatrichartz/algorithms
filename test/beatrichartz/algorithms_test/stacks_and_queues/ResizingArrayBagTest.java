package beatrichartz.algorithms_test.stacks_and_queues;

import beatrichartz.algorithms.stacks_and_queues.ResizingArrayBag;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static junit.framework.TestCase.assertEquals;

public class ResizingArrayBagTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();
    private ResizingArrayBag<Integer> resizingArrayBag;

    @Before
    public void setUp() throws Exception {
        resizingArrayBag = new ResizingArrayBag(2);
    }

    @Test
    public void iteratesUpToCapacity() throws Exception {
        resizingArrayBag.add(1);
        resizingArrayBag.add(2);

        Iterator<Integer> iterator = resizingArrayBag.iterator();
        assertEquals(true, iterator.hasNext());
        assertEquals(1, (int) iterator.next());

        assertEquals(true, iterator.hasNext());
        assertEquals(2, (int) iterator.next());

        assertEquals(false, iterator.hasNext());
        exception.expect(NoSuchElementException.class);
        iterator.next();
    }

    @Test
    public void resizesWhenCapacityReached() throws Exception {
        for (int i = 0; i < 20; i++) {
            resizingArrayBag.add(i);
        }

        Integer cursor = 0;
        for (Integer i : resizingArrayBag) {
            assertEquals(cursor++, i);
        }
    }
}
