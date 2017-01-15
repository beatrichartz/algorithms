package beatrichartz.algorithms_test.sorting.examples.nuts_and_bolts;

import beatrichartz.algorithms.sorting.examples.nuts_and_bolts.Bolt;
import beatrichartz.algorithms.sorting.examples.nuts_and_bolts.Nut;
import beatrichartz.algorithms.sorting.examples.nuts_and_bolts.Pair;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class PairTest {
    @Test
    public void testFits() throws Exception {
        Nut smallNut = new Nut(1);
        Nut bigNut = new Nut(2);

        Bolt smallBolt = new Bolt(1);
        Bolt bigBolt = new Bolt(2);

        Pair smallPair = new Pair(smallNut, smallBolt);
        Pair bigPair = new Pair(bigNut, bigBolt);

        Pair wrongPair1 = new Pair(smallNut, bigBolt);
        Pair wrongPair2 = new Pair(bigNut, smallBolt);

        assertEquals(true, smallPair.fits());
        assertEquals(true, bigPair.fits());

        assertEquals(false, wrongPair1.fits());
        assertEquals(false, wrongPair2.fits());
    }

    @Test
    public void testToString() throws Exception {
        Pair pair = new Pair(new Nut(11), new Bolt(7));
        assertEquals("Pair(Nut(11), Bolt(7))", pair.toString());
    }
}
