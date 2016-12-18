package beatrichartz.algorithms_test.analysis.examples;

import beatrichartz.algorithms.analysis.examples.BitonicArray;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class BitonicArrayTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void throwsArgumentExeptionIfNotGivenAtLeast3Elements() throws Exception {
        exception.expect(IllegalArgumentException.class);
        new BitonicArray(3,2);
    }

    @Test
    public void throwsArgumentExeptionIfNotGivenBitonicElementsAtStart() throws Exception {
        exception.expect(IllegalArgumentException.class);
        new BitonicArray(4,3,2);
    }

    @Test
    public void throwsArgumentExeptionIfNotGivenBitonicElementsAtEnd() throws Exception {
        exception.expect(IllegalArgumentException.class);
        new BitonicArray(2,3,4);
    }

    @Test
    public void throwsArgumentExeptionIfNotGivenBitonicElements() throws Exception {
        exception.expect(IllegalArgumentException.class);
        new BitonicArray(1,4,3,5);
    }

    @Test
    @Parameters({"0", "1", "-1", "5", "1000"})
    public void doesNotContainAnythingWhenEmpty(int i) throws Exception {
        BitonicArray bitonicArray = new BitonicArray();
        assertEquals(false, bitonicArray.contains(i));
    }

    @Test
    @Parameters({"-989", "1", "-1", "5", "1000"})
    public void doesNotContainElementsNotInArray(int i) throws Exception {
        BitonicArray bitonicArray = new BitonicArray(2, 3, 0);
        assertEquals(false, bitonicArray.contains(i));
    }

    @Test
    public void doesContainElementsInArray() throws Exception {
        BitonicArray bitonicArray = new BitonicArray(-5, 1, 2, 3, 0, -1);
        assertEquals(true, bitonicArray.contains(-5));
        assertEquals(true, bitonicArray.contains(1));
        assertEquals(true, bitonicArray.contains(2));
        assertEquals(true, bitonicArray.contains(3));
        assertEquals(true, bitonicArray.contains(0));
        assertEquals(true, bitonicArray.contains(-1));
    }

    @Test
    @Parameters({"0", "1", "-1", "5", "1000"})
    public void doesNotLinearContainAnythingWhenEmpty(int i) throws Exception {
        BitonicArray bitonicArray = new BitonicArray();
        assertEquals(false, bitonicArray.linearContains(i));
    }

    @Test
    @Parameters({"-989", "1", "-1", "5", "1000"})
    public void doesNotLinearContainElementsNotInArray(int i) throws Exception {
        BitonicArray bitonicArray = new BitonicArray(2, 3, 0);
        assertEquals(false, bitonicArray.linearContains(i));
    }

    @Test
    public void doesLinearContainElementsInArray() throws Exception {
        BitonicArray bitonicArray = new BitonicArray(-5, 1, 2, 3, 0, -1);
        assertEquals(true, bitonicArray.linearContains(-5));
        assertEquals(true, bitonicArray.linearContains(1));
        assertEquals(true, bitonicArray.linearContains(2));
        assertEquals(true, bitonicArray.linearContains(3));
        assertEquals(true, bitonicArray.linearContains(0));
        assertEquals(true, bitonicArray.linearContains(-1));
    }
}
