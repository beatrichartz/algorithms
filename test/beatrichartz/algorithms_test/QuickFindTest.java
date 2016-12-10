package beatrichartz.algorithms_test;

import beatrichartz.algorithms.QuickFind;
import junit.framework.TestCase;

public class QuickFindTest extends TestCase {
    public void testUnconnectedNodes() throws Exception {
        QuickFind quickFind = new QuickFind(10);
        assertEquals(false, quickFind.connected(2, 4));
    }

    public void testSimplyConnectedNodes() throws Exception {
        QuickFind quickFind = new QuickFind(10);
        quickFind.union(2,4);
        assertEquals(false, quickFind.connected(2,4));
        assertEquals(true, quickFind.connected(2,5));
    }

    public void testComplexConnectedNodes() throws Exception {
        QuickFind quickFind = new QuickFind(10);
        quickFind.union(2,4);
        quickFind.union(5,7);
        quickFind.union(4,5);
        quickFind.union(0,9);
        quickFind.union(1,0);
        assertEquals(true, quickFind.connected(2,7));
        assertEquals(false, quickFind.connected(0,8));
    }

    public void testNodesBecomingConnected() throws Exception {
        QuickFind quickFind = new QuickFind(10);
        quickFind.union(2,4);
        quickFind.union(0,7);
        quickFind.union(1,0);
        assertEquals(false, quickFind.connected(2,7));

        quickFind.union(0,4);
        assertEquals(true, quickFind.connected(2,7));
    }
}

