package beatrichartz.algorithms_test.sorting.examples.pattern_recognition;

import beatrichartz.algorithms.sorting.examples.pattern_recognition.BruteCollinearPoints;
import beatrichartz.algorithms.sorting.examples.pattern_recognition.CollinearPoints;
import beatrichartz.algorithms.sorting.examples.pattern_recognition.LineSegment;
import beatrichartz.algorithms.sorting.examples.pattern_recognition.Point;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.core.Is.isA;

@RunWith(value = Parameterized.class)
public class CollinearPointsTest {
    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object> data() {
        List<Object> collection = new ArrayList();
        collection.add(BruteCollinearPoints.class);
        return collection;
    }

    private Class collinearPointsClass;

    public CollinearPointsTest(Class collinearPointsClass) {
        this.collinearPointsClass = collinearPointsClass;
    }

    private int countChar(String pointsString, String character) {
        return pointsString.length() - pointsString.replace(character, "").length();
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
    public void testSegmentsWithSmallGrid() throws Exception {
        CollinearPoints collinearPoints = getCollinearPointsForGrid("7x7");
        LineSegment[] segments = collinearPoints.segments();

        assertEquals(4, segments.length);
        assertEquals("(2, 6) -> (2, 1)", segments[0].toString());
        assertEquals("(4, 6) -> (4, 1)", segments[1].toString());
        assertEquals("(4, 5) -> (0, 1)", segments[2].toString());
        assertEquals("(2, 3) -> (5, 0)", segments[3].toString());
    }

    @Test
    public void testSegmentsWithLargeGrid() throws Exception {
        CollinearPoints collinearPoints = getCollinearPointsForGrid("20x20");
        LineSegment[] segments = collinearPoints.segments();

        assertEquals(5, segments.length);
        assertEquals("(19, 19) -> (19, 1)", segments[0].toString());
        assertEquals("(0, 18) -> (18, 9)", segments[1].toString());
        assertEquals("(18, 17) -> (0, 11)", segments[2].toString());
        assertEquals("(0, 11) -> (5, 1)", segments[3].toString());
        assertEquals("(19, 1) -> (5, 1)", segments[4].toString());
    }

    private Point[] getPointsForGrid(String gridName) {
        List<String> grid = new ArrayList<>();
        try {
            grid = Files.readAllLines(Paths.get("test/resources/fixtures/pattern_recognition", gridName + ".txt"));
        } catch (IOException e) { System.out.println(e); }

        Integer capacity = grid.stream().map(line -> countChar(line, "x")).reduce(0, (x, y) -> x + y);
        Point[] points = new Point[capacity];
        int index = 0;
        for (int y = grid.size() - 1; y >= 0; y--) {
            String line = grid.get(grid.size() - y - 1);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == 'x') {
                    points[index++] = new Point(x, y);
                }
            }
        }
        return points;
    }

    private CollinearPoints getCollinearPoints(Point[] points) {
        try {
            return (CollinearPoints) collinearPointsClass.getConstructor(Point[].class).
                    newInstance(new Object[]{points});
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    private CollinearPoints getCollinearPointsForGrid(String gridName) {
        return getCollinearPoints(getPointsForGrid(gridName));
    }
}
