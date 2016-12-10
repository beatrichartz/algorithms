package beatrichartz.algorithms_test.quick_union;

import beatrichartz.algorithms.quick_union.WeightedQuickUnion;
import junit.framework.TestCase;

public class WeightedQuickUnionTest extends TestCase {
    public void testUnconnectedNodes() throws Exception {
        WeightedQuickUnion weightedQuickUnion = new WeightedQuickUnion(10);
        assertEquals(false, weightedQuickUnion.connected(2, 4));
    }

    public void testSimplyConnectedNodes() throws Exception {
        WeightedQuickUnion weightedQuickUnion = new WeightedQuickUnion(10);
        weightedQuickUnion.union(2,4);
        assertEquals(true, weightedQuickUnion.connected(2,4));
        assertEquals(false, weightedQuickUnion.connected(2,5));
    }

    public void testComplexConnectedNodes() throws Exception {
        WeightedQuickUnion weightedQuickUnion = new WeightedQuickUnion(10);
        weightedQuickUnion.union(2,4);
        weightedQuickUnion.union(5,7);
        weightedQuickUnion.union(4,5);
        weightedQuickUnion.union(0,9);
        weightedQuickUnion.union(1,0);
        assertEquals(true, weightedQuickUnion.connected(2,7));
        assertEquals(false, weightedQuickUnion.connected(0,8));
    }

    public void testNodesBecomingConnected() throws Exception {
        WeightedQuickUnion weightedQuickUnion = new WeightedQuickUnion(10);
        weightedQuickUnion.union(2,4);
        weightedQuickUnion.union(0,7);
        weightedQuickUnion.union(1,0);
        assertEquals(false, weightedQuickUnion.connected(2,7));

        weightedQuickUnion.union(0,4);
        assertEquals(true, weightedQuickUnion.connected(2,7));
    }


}

