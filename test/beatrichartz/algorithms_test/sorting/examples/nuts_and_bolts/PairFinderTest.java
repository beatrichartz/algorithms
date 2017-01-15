package beatrichartz.algorithms_test.sorting.examples.nuts_and_bolts;

import beatrichartz.algorithms.sorting.examples.nuts_and_bolts.Bolt;
import beatrichartz.algorithms.sorting.examples.nuts_and_bolts.Nut;
import beatrichartz.algorithms.sorting.examples.nuts_and_bolts.Pair;
import beatrichartz.algorithms.sorting.examples.nuts_and_bolts.PairFinder;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class PairFinderTest {
    @Test
    public void testFindPairs() throws Exception {
        Nut[] nuts = { new Nut(2), new Nut(3), new Nut(1), new Nut(4), new Nut(5) };
        Bolt[] bolts = { new Bolt(1), new Bolt(3), new Bolt(2), new Bolt(5), new Bolt(4) };

        Pair[] pairs = PairFinder.find(nuts, bolts);
        assertEquals(5, pairs.length);
        for (Pair pair : pairs) {
            assertEquals("Expected " + pair + " to fit", true, pair.fits());
        }
    }
}
