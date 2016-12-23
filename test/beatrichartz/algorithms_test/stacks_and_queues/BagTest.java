package beatrichartz.algorithms_test.stacks_and_queues;

import beatrichartz.algorithms.stacks_and_queues.Bag;
import beatrichartz.algorithms.stacks_and_queues.LinkedListBag;
import beatrichartz.algorithms.stacks_and_queues.ResizingArrayBag;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

import static junit.framework.TestCase.assertEquals;

@RunWith(value = Parameterized.class)
public class BagTest {

    @Parameterized.Parameters(name = "{0}")
    public static Collection data() {
        List<Object> collection = new ArrayList();
        collection.add(LinkedListBag.class);
        collection.add(ResizingArrayBag.class);
        return collection;
    }

    private Class bagClass;
    private Bag<String> bag;

    public BagTest(Class bagClass) {
        this.bagClass = bagClass;
    }

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        this.bag = (Bag) bagClass.newInstance();
    }

    @Test
    public void sizeReturnsZeroWhenEmpty() throws Exception {
        assertEquals(0, bag.size());
    }

    @Test
    public void addIncreasesSize() throws Exception {
        bag.add("A");
        assertEquals(1, bag.size());

        bag.add("B");
        assertEquals(2, bag.size());
    }

    @Test
    public void iteratorIteratesOverEveryElement() throws Exception {
        bag.add("A");
        bag.add("B");
        bag.add("C");

        Iterator<String> iterator = bag.iterator();
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
}
