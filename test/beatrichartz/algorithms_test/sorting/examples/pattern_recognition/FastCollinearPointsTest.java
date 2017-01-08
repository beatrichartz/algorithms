package beatrichartz.algorithms_test.sorting.examples.pattern_recognition;

import beatrichartz.algorithms.sorting.examples.pattern_recognition.CollinearPoints;
import beatrichartz.algorithms.sorting.examples.pattern_recognition.FastCollinearPoints;
import beatrichartz.algorithms.sorting.examples.pattern_recognition.LineSegment;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class FastCollinearPointsTest {
    private GridTestHelper gridTestHelper = new GridTestHelper();

    @Test
    public void testGridsWithBiggerSegments() throws Exception {
        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(gridTestHelper.getPointsForGrid("24x24"));

        LineSegment[] segments = getSortedSegments(fastCollinearPoints);

        assertEquals(5, segments.length);
        assertEquals("(22, 5) -> (5, 5)", segments[0].toString());
        assertEquals("(19, 23) -> (19, 1)", segments[1].toString());
        assertEquals("(18, 21) -> (0, 15)", segments[2].toString());
        assertEquals("(0, 22) -> (18, 13)", segments[3].toString());
        assertEquals("(0, 15) -> (7, 1)", segments[4].toString());
    }

    private LineSegment[] getSortedSegments(CollinearPoints collinearPoints) {
        List<LineSegment> segmentList = Arrays.asList(collinearPoints.segments());
        Collections.sort(segmentList, (a, b) -> b.toString().compareTo(a.toString()));

        return segmentList.toArray(new LineSegment[0]);
    }
}
