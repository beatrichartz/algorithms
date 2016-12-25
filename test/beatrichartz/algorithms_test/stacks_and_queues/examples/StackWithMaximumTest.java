package beatrichartz.algorithms_test.stacks_and_queues.examples;

import beatrichartz.algorithms.stacks_and_queues.examples.StackWithMaximum;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class StackWithMaximumTest {

    private StackWithMaximum<Integer> stackWithMaximum;

    @Before
    public void setUp() throws Exception {
        stackWithMaximum = new StackWithMaximum();
    }

    @Test
    public void maximumIsNullWhenEmpty() throws Exception {
        assertEquals(null, stackWithMaximum.max());
        stackWithMaximum.push(2);
        stackWithMaximum.pop();
        assertEquals(null, stackWithMaximum.max());
    }

    @Test
    public void maximumReturnsMaximumAddedElement() throws Exception {
        stackWithMaximum.push(2);
        stackWithMaximum.push(1);
        stackWithMaximum.push(3);

        assertEquals(3, (int) stackWithMaximum.max());
        stackWithMaximum.pop();

        assertEquals(2, (int) stackWithMaximum.max());

        stackWithMaximum.push(4);
        assertEquals(4, (int) stackWithMaximum.max());

        stackWithMaximum.pop();
        stackWithMaximum.pop();
        assertEquals(2, (int) stackWithMaximum.max());
    }

    @Test
    public void maximumReturnsRightValueWhenPoppingOneOfTwoMaxValuesOffStack() throws Exception {
        stackWithMaximum.push(3);
        stackWithMaximum.push(1);
        stackWithMaximum.push(2);
        stackWithMaximum.push(3);

        assertEquals(3, (int) stackWithMaximum.max());
        stackWithMaximum.pop();

        assertEquals(3, (int) stackWithMaximum.max());
    }
}
