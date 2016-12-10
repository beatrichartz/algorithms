package beatrichartz.algorithms_test.quick_union.examples.social_network;

import beatrichartz.algorithms.quick_union.examples.social_network.SocialNetwork;
import junit.framework.TestCase;

public class SocialNetworkTest extends TestCase {

    private SocialNetwork socialNetwork;

    public void setUp() throws Exception {
        socialNetwork = new SocialNetwork(4);
    }

    public void testFullyConnected() throws Exception {
        assertEquals(false, socialNetwork.fullyConnected());
        socialNetwork.union(1, 3);
        assertEquals(false, socialNetwork.fullyConnected());
        socialNetwork.union(0, 3);
        assertEquals(false, socialNetwork.fullyConnected());
        socialNetwork.union(2, 3);
        assertEquals(true, socialNetwork.fullyConnected());
    }
}
