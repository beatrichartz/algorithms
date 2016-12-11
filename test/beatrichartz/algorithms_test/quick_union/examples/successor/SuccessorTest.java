package beatrichartz.algorithms_test.quick_union.examples.successor;

import beatrichartz.algorithms.quick_union.examples.successor.Successor;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class SuccessorTest {

    @Test
    public void returnsItselfWithoutDeletedEntries() throws Exception {
        Successor successor = new Successor(10);
        assertEquals(1, successor.find(1));
    }

    @Test
    public void returnsSuccessorWhenDeletingEntries() throws Exception {
        Successor successor = new Successor(10);
        successor.delete(2);
        assertEquals(3, successor.find(2));

        successor.delete(3);
        assertEquals(4, successor.find(2));

        successor.delete(7);
        successor.delete(5);
        successor.delete(6);
        successor.delete(4);
        successor.delete(0);
        successor.delete(1);
        assertEquals(8, successor.find(0));
        assertEquals(8, successor.find(2));
        assertEquals(8, successor.find(4));
    }
}
