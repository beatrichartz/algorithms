package beatrichartz.algorithms_test.sorting.examples.pattern_recognition;

import beatrichartz.algorithms.sorting.examples.pattern_recognition.Point;
import org.junit.Test;

import java.util.Comparator;

import static junit.framework.TestCase.assertEquals;

public class PointTest {
    @Test
    public void testRepresentsAsString() throws Exception {
        Point point = new Point(1, 2);
        assertEquals("(1, 2)", point.toString());
    }

    @Test
    public void testCompare() throws Exception {
        Point topLeft = new Point(-1, 1);
        Point topRight = new Point(1, 1);
        Point bottomRight = new Point(1, -1);

        assertEquals(0, topLeft.compareTo(topLeft));
        assertEquals(0, topRight.compareTo(topRight));
        assertEquals(0, bottomRight.compareTo(bottomRight));

        assertEquals(true, topLeft.compareTo(topRight) < 0);
        assertEquals(true, topRight.compareTo(topLeft) > 0);

        assertEquals(true, topLeft.compareTo(bottomRight) > 0);
        assertEquals(true, bottomRight.compareTo(topLeft) < 0);

        assertEquals(true, topRight.compareTo(bottomRight) > 0);
        assertEquals(true, bottomRight.compareTo(topRight) < 0);
    }

    @Test
    public void testSlope() throws Exception {
        Point zero = new Point(0, 0);
        Point topRight = new Point(1, 4);
        Point bottomLeft = new Point(-4, -2);

        assertEquals(4.0, zero.slopeTo(topRight));
        assertEquals(4.0, topRight.slopeTo(zero));

        assertEquals(0.5, zero.slopeTo(bottomLeft));
        assertEquals(0.5, bottomLeft.slopeTo(zero));

        assertEquals(6.0/5.0, bottomLeft.slopeTo(topRight));
        assertEquals(6.0/5.0, topRight.slopeTo(bottomLeft));
    }

    @Test
    public void testSlopeSpecialCases() throws Exception {
        Point zero = new Point(0, 0);
        Point top = new Point(0, 2);
        Point zeroRight = new Point(2, 0);

        assertEquals(0.0, zero.slopeTo(zeroRight));
        assertEquals(Double.POSITIVE_INFINITY, zero.slopeTo(top));
        assertEquals(Double.NEGATIVE_INFINITY, zero.slopeTo(zero));
    }

    @Test
    public void testSlopeOrder() throws Exception {
        Point zero = new Point(0, 0);
        Point top = new Point(1, 2);
        Point bottom = new Point(1, -2);

        Comparator<Point> zeroComparator = zero.slopeOrder();
        assertEquals(0, zeroComparator.compare(top, top));
        assertEquals(0, zeroComparator.compare(bottom, bottom));

        assertEquals(true, zeroComparator.compare(top, bottom) > 0);
        assertEquals(true, zeroComparator.compare(bottom, top) < 0);
    }
}
