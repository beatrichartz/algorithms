package beatrichartz.algorithms_test.sorting.examples.nuts_and_bolts;

import beatrichartz.algorithms.sorting.examples.nuts_and_bolts.Bolt;
import beatrichartz.algorithms.sorting.examples.nuts_and_bolts.Nut;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static junit.framework.TestCase.assertEquals;

public class NutTest {
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

        assertEquals(0, smallNut.compareTo(smallBolt));
        assertEquals(1, mediumNut.compareTo(smallBolt));
        assertEquals(1, bigNut.compareTo(smallBolt));

        assertEquals(-1, smallNut.compareTo(mediumBolt));
        assertEquals(0, mediumNut.compareTo(mediumBolt));
        assertEquals(1, bigNut.compareTo(mediumBolt));

        assertEquals(-1, smallNut.compareTo(bigBolt));
        assertEquals(-1, mediumNut.compareTo(bigBolt));
        assertEquals(0, bigNut.compareTo(bigBolt));
    }

    @Test
    public void testCompareToOtherThanBolt() throws Exception {
        expectedException.expect(RuntimeException.class);

        new Nut(1).compareTo(new Object());
    }
}
