package beatrichartz.algorithms_test.sorting.examples.pattern_recognition;

import beatrichartz.algorithms.sorting.examples.pattern_recognition.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.core.Is.isA;

@RunWith(value = Parameterized.class)
public class CollinearPointsTest {
    private final GridTestHelper gridTestHelper = new GridTestHelper();

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object> data() {
        List<Object> collection = new ArrayList();
        collection.add(BruteCollinearPoints.class);
        collection.add(FastCollinearPoints.class);
        return collection;
    }

    private Class collinearPointsClass;

    public CollinearPointsTest(Class collinearPointsClass) {
        this.collinearPointsClass = collinearPointsClass;
    }

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testConstructingWithNullThrows() throws Exception {
        expectedException.expectCause(isA(NullPointerException.class));

        collinearPointsClass.getConstructor(Point[].class).newInstance(new Object[]{null});
    }

    @Test
    public void testConstructingWithNullPointThrows() throws Exception {
        Point[] points = new Point[]{new Point(1,2), null, new Point(2,2)};

        expectedException.expectCause(isA(NullPointerException.class));

        collinearPointsClass.getConstructor(Point[].class).newInstance(new Object[]{points});
    }

    @Test
    public void testConstructingWithRepeatedPointThrows() throws Exception {
        Point[] points = new Point[]{new Point(1,2), new Point(2,2), new Point(-1,-2), new Point(1,2)};

        expectedException.expectCause(isA(IllegalArgumentException.class));

        collinearPointsClass.getConstructor(Point[].class).newInstance(new Object[]{points});
    }

    @Test
    public void testNumberOfSegments() throws Exception {
        assertEquals(4, getCollinearPointsForGrid("7x7").numberOfSegments());
        assertEquals(5, getCollinearPointsForGrid("20x20").numberOfSegments());
    }

    @Test
    public void testConstructorDoesNotMutate() throws Exception {
        Point[] points = gridTestHelper.getPointsForGrid("7x7");
        Point[] backup = points.clone();

        collinearPointsClass.getConstructor(Point[].class).
                    newInstance(new Object[]{points});

        for (int i = 0; i < points.length; i++) {
            assertEquals(0, points[i].compareTo(backup[i]));
        }
    }

    @Test
    public void testSegmentsImmutable() throws Exception {
        CollinearPoints collinearPoints = getCollinearPointsForGrid("7x7");
        LineSegment[] segments = collinearPoints.segments();

        assertEquals(true, segments.length > 0);
        assertEquals(false, segments[1] == null);
        segments[1] = null;

        segments = collinearPoints.segments();
        assertEquals(false, segments[1] == null);
    }

    @Test
    public void testSegmentsWithSmallGrid() throws Exception {
        CollinearPoints collinearPoints = getCollinearPointsForGrid("7x7");
        LineSegment[] segments = getSortedSegments(collinearPoints);

        assertEquals(4, segments.length);

        assertEquals("(4, 6) -> (4, 1)", segments[0].toString());
        assertEquals("(4, 5) -> (0, 1)", segments[1].toString());
        assertEquals("(2, 6) -> (2, 1)", segments[2].toString());
        assertEquals("(2, 3) -> (5, 0)", segments[3].toString());
    }

    @Test
    public void testSegmentsWithLargeGrid() throws Exception {
        CollinearPoints collinearPoints = getCollinearPointsForGrid("20x20");
        LineSegment[] segments = getSortedSegments(collinearPoints);

        assertEquals(5, segments.length);
        assertEquals("(19, 19) -> (19, 1)", segments[0].toString());
        assertEquals("(19, 1) -> (5, 1)", segments[1].toString());
        assertEquals("(18, 17) -> (0, 11)", segments[2].toString());
        assertEquals("(0, 18) -> (18, 9)", segments[3].toString());
        assertEquals("(0, 11) -> (5, 1)", segments[4].toString());
    }

    private LineSegment[] getSortedSegments(CollinearPoints collinearPoints) {
        List<LineSegment> segmentList = Arrays.asList(collinearPoints.segments());
        Collections.sort(segmentList, (a, b) -> b.toString().compareTo(a.toString()));

        return segmentList.toArray(new LineSegment[0]);
    }

    private CollinearPoints getCollinearPoints(Point[] points) {
        try {
            return (CollinearPoints) collinearPointsClass.getConstructor(Point[].class).
                    newInstance(new Object[]{points});
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private CollinearPoints getCollinearPointsForGrid(String gridName) {
        return getCollinearPoints(gridTestHelper.getPointsForGrid(gridName));
    }
}
