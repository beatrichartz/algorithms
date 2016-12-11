package beatrichartz.algorithms_test.quick_union.examples.social_network;

import beatrichartz.algorithms.quick_union.examples.social_network.SocialNetwork;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class SocialNetworkTest {
    private SocialNetwork socialNetwork;

    @Before
    public void setUp() throws Exception {
        socialNetwork = new SocialNetwork(4);
    }

    @Test
    public void fullyConnectedIsTrueWhenAllNodesConnected() throws Exception {
        assertEquals(false, socialNetwork.fullyConnected());
        socialNetwork.union(1, 3);
        assertEquals(false, socialNetwork.fullyConnected());
        socialNetwork.union(0, 3);
        assertEquals(false, socialNetwork.fullyConnected());
        socialNetwork.union(2, 3);
        assertEquals(true, socialNetwork.fullyConnected());
    }
}
