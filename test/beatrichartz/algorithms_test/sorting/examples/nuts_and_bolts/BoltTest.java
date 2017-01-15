package beatrichartz.algorithms_test.sorting.examples.nuts_and_bolts;

import beatrichartz.algorithms.sorting.examples.nuts_and_bolts.Bolt;
import beatrichartz.algorithms.sorting.examples.nuts_and_bolts.Nut;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static junit.framework.TestCase.assertEquals;

public class BoltTest {
    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testCompareToBolt() throws Exception {
        Nut smallNut = new Nut(1);
        Nut mediumNut = new Nut(2);
        Nut bigNut = new Nut(3);

        Bolt smallBolt = new Bolt(1);
        Bolt mediumBolt = new Bolt(2);
        Bolt bigBolt = new Bolt(3);

        assertEquals(0, smallBolt.compareTo(smallNut));
        assertEquals(1, mediumBolt.compareTo(smallNut));
        assertEquals(1, bigBolt.compareTo(smallNut));

        assertEquals(-1, smallBolt.compareTo(mediumNut));
        assertEquals(0, mediumBolt.compareTo(mediumNut));
        assertEquals(1, bigBolt.compareTo(mediumNut));

        assertEquals(-1, smallBolt.compareTo(bigNut));
        assertEquals(-1, mediumBolt.compareTo(bigNut));
        assertEquals(0, bigBolt.compareTo(bigNut));
    }

    @Test
    public void testCompareToOtherThanNut() throws Exception {
        expectedException.expect(RuntimeException.class);

        new Bolt(1).compareTo(new Object());
    }
}
