package beatrichartz.algorithms_test.analysis.examples;

import beatrichartz.algorithms.analysis.examples.EggDrop;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static junit.framework.TestCase.assertEquals;

public class EggDropTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void throwsExceptionWhenTossingWithNoEggs() throws Exception {
        EggDrop eggDrop = new EggDrop(0, 10, 1);

        exception.expect(RuntimeException.class);
        eggDrop.tossFromFloor(1);
    }

    @Test
    public void checkIsRightFloorReturnsTrueOnlyForRightFloor() throws Exception {
        EggDrop eggDrop = new EggDrop(1, 10, 3);
        assertEquals(true, eggDrop.checkIsRightFloor(3));

        for (int i = 0; i < 3; i++) {
            eggDrop = new EggDrop(1, 10, 3);
            assertEquals(false, eggDrop.checkIsRightFloor(i));
        }

        for (int i = 4; i <= 10; i++) {
            eggDrop = new EggDrop(1, 10, 3);
            assertEquals(false, eggDrop.checkIsRightFloor(i));
        }
    }

    @Test
    public void canCheckFloorOnlyOnceWhenOutOfEggs() throws Exception {
        EggDrop eggDrop = new EggDrop(1, 100, 10);
        eggDrop.tossFromFloor(11);
        eggDrop.checkIsRightFloor(11);

        exception.expect(RuntimeException.class);
        eggDrop.checkIsRightFloor(11);
    }

    @Test
    public void breaksAnEggIfTossedFromTooHighUp() throws Exception {
        EggDrop eggDrop = new EggDrop(10, 100, 30);
        assertEquals(10, eggDrop.getNumEggs());

        eggDrop.tossFromFloor(31);
        assertEquals(9, eggDrop.getNumEggs());

        eggDrop.tossFromFloor(87);
        assertEquals(8, eggDrop.getNumEggs());
    }

    @Test
    public void testBreaksNoEggIfTossedFromRightFloorOrBelow() throws Exception {
        EggDrop eggDrop = new EggDrop(10, 100, 20);
        assertEquals(10, eggDrop.getNumEggs());

        eggDrop.tossFromFloor(0);
        eggDrop.tossFromFloor(9);
        eggDrop.tossFromFloor(18);
        eggDrop.tossFromFloor(20);
        assertEquals(10, eggDrop.getNumEggs());
    }

    @Test
    public void throwsExceptionIfTossedFromAboveBuilding() throws Exception {
        EggDrop eggDrop = new EggDrop(10, 100, 10);

        exception.expect(RuntimeException.class);
        eggDrop.tossFromFloor(101);
    }
}
