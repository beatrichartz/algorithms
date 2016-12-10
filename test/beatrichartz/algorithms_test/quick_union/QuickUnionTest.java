package beatrichartz.algorithms_test.quick_union;

import beatrichartz.algorithms.quick_union.QuickUnion;
import junit.framework.TestCase;

public class QuickUnionTest extends TestCase {
    public void testUnconnectedNodes() throws Exception {
        QuickUnion quickUnion = new QuickUnion(10);
        assertEquals(false, quickUnion.connected(2, 4));
    }

    public void testSimplyConnectedNodes() throws Exception {
        QuickUnion quickUnion = new QuickUnion(10);
        quickUnion.union(2,4);
        assertEquals(true, quickUnion.connected(2,4));
        assertEquals(false, quickUnion.connected(2,5));
    }

    public void testComplexConnectedNodes() throws Exception {
        QuickUnion quickUnion = new QuickUnion(10);
        quickUnion.union(2,4);
        quickUnion.union(5,7);
        quickUnion.union(4,5);
        quickUnion.union(0,9);
        quickUnion.union(1,0);
        assertEquals(true, quickUnion.connected(2,7));
        assertEquals(false, quickUnion.connected(0,8));
    }

    public void testNodesBecomingConnected() throws Exception {
        QuickUnion quickUnion = new QuickUnion(10);
        quickUnion.union(2,4);
        quickUnion.union(0,7);
        quickUnion.union(1,0);
        assertEquals(false, quickUnion.connected(2,7));

        quickUnion.union(0,4);
        assertEquals(true, quickUnion.connected(2,7));
    }
}

