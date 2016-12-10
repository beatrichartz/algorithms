package beatrichartz.algorithms_test.quick_union.examples.social_network;

import beatrichartz.algorithms.quick_union.examples.social_network.SocialNetwork;
import junit.framework.TestCase;
import org.joda.time.LocalDateTime;

public class SocialNetworkTest extends TestCase {

    private SocialNetwork socialNetwork;
    private LocalDateTime now;

    public void setUp() throws Exception {
        socialNetwork = new SocialNetwork(5);
        now = LocalDateTime.now();
    }

    public void testConnected() throws Exception {
        socialNetwork.connectAtTime(1, 2, now);
        assertEquals(true, socialNetwork.connected(1, 2));
        assertEquals(false, socialNetwork.connected(2, 3));
    }

    public void testComplexConnections() throws Exception {
        socialNetwork.connectAtTime(1, 3, now);
        socialNetwork.connectAtTime(2, 3, now);
        socialNetwork.connectAtTime(1, 4, now);
        assertEquals(true, socialNetwork.connected(2, 4));
    }

    public void testLastConnectionMadeAtTimeWhenLastConnected() throws Exception {
        socialNetwork.connectAtTime(1, 4, now);
        assertEquals(now, socialNetwork.getLastConnectionMadeAt());
    }

    public void testLastConnectionMadeAtSetWhenAlreadyConnected() throws Exception {
        socialNetwork.connectAtTime(1, 4, now);
        socialNetwork.connectAtTime(2, 3, now);
        socialNetwork.connectAtTime(3, 4, now);
        socialNetwork.connectAtTime(2, 4, now.plusMinutes(4));
        assertEquals(now.plusMinutes(4), socialNetwork.getLastConnectionMadeAt());
    }

    public void testFullyConnected() throws Exception {
        assertEquals(false, socialNetwork.fullyConnected());
        socialNetwork.connectAtTime(1, 4, now);
        assertEquals(false, socialNetwork.fullyConnected());
        socialNetwork.connectAtTime(0, 4, now);
        assertEquals(false, socialNetwork.fullyConnected());
        socialNetwork.connectAtTime(3, 4, now);
        assertEquals(false, socialNetwork.fullyConnected());
        socialNetwork.connectAtTime(2, 4, now);
        assertEquals(true, socialNetwork.fullyConnected());
    }

    public void testFullyConnectedAt() throws Exception {
        socialNetwork.connectAtTime(1, 4, now.minusMinutes(3));
        socialNetwork.connectAtTime(0, 4, now.minusMinutes(2));
        socialNetwork.connectAtTime(3, 4, now.minusMinutes(1));
        socialNetwork.connectAtTime(2, 4, now);
        assertEquals(now, socialNetwork.getFullyConnectedAt());
    }
}
