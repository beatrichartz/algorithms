package beatrichartz.algorithms_test.quick_union;

import beatrichartz.algorithms.quick_union.QuickFind;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class QuickFindTest {
    @Test
    public void connectedWithUnconnectedNodes() throws Exception {
        QuickFind quickFind = new QuickFind(10);
        assertEquals(false, quickFind.connected(2, 4));
    }

    @Test
    public void connectedWithSimplyConnectedNodes() throws Exception {
        QuickFind quickFind = new QuickFind(10);
        quickFind.union(2,4);
        assertEquals(true, quickFind.connected(2,4));
        assertEquals(false, quickFind.connected(2,5));
    }

    @Test
    public void connectedWithComplexConnectedNodes() throws Exception {
        QuickFind quickFind = new QuickFind(10);
        quickFind.union(2,4);
        quickFind.union(5,7);
        quickFind.union(4,5);
        quickFind.union(0,9);
        quickFind.union(1,0);
        assertEquals(true, quickFind.connected(2,7));
        assertEquals(false, quickFind.connected(0,8));
    }

    @Test
    public void connectedWithNodesBecomingConnected() throws Exception {
        QuickFind quickFind = new QuickFind(10);
        quickFind.union(2,4);
        quickFind.union(0,7);
        quickFind.union(1,0);
        assertEquals(false, quickFind.connected(2,7));

        quickFind.union(0,4);
        assertEquals(true, quickFind.connected(2,7));
    }
}

