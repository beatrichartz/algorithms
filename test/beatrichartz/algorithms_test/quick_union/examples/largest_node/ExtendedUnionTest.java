package beatrichartz.algorithms_test.quick_union.examples.largest_node;

import beatrichartz.algorithms.quick_union.examples.largest_node.ExtendedQuickUnion;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class ExtendedUnionTest {

    @Test
    public void findingLargestNode() throws Exception {
        ExtendedQuickUnion extendedQuickUnion = new ExtendedQuickUnion(10);
        extendedQuickUnion.union(1, 2);
        assertEquals(2, extendedQuickUnion.findLargestNodeInComponent(1));
        assertEquals(4, extendedQuickUnion.findLargestNodeInComponent(4));

        extendedQuickUnion.union(1, 3);
        assertEquals(3, extendedQuickUnion.findLargestNodeInComponent(1));

        extendedQuickUnion.union(9, 0);
        extendedQuickUnion.union(2, 0);
        assertEquals(9, extendedQuickUnion.findLargestNodeInComponent(1));
    }
}
