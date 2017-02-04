package beatrichartz.algorithms_test.kd_trees;

import beatrichartz.algorithms.kd_trees.APointSet;
import beatrichartz.algorithms.kd_trees.KdTree;
import beatrichartz.algorithms.kd_trees.PointSET;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

import static junit.framework.TestCase.assertEquals;

@RunWith(value = Parameterized.class)
public class PointSetTest {
    @Parameterized.Parameters(name = "{0}")
    public static Collection data() {
        List<Object> collection = new ArrayList();
        collection.add(PointSET.class);
        collection.add(KdTree.class);
        return collection;
    }

    private Class pointSetClass;
    private APointSet empty;
    private APointSet one;
    private APointSet many;

    public PointSetTest(Class pointSetClass) {
        this.pointSetClass = pointSetClass;
    }

    @Before
    public void setUp() throws Exception {
        this.empty = (APointSet) pointSetClass.newInstance();
        this.one = (APointSet) pointSetClass.newInstance();
        this.many = (APointSet) pointSetClass.newInstance();

        one.insert(new Point2D(.1, .2));
        many.insert(new Point2D(.1, .2));
        many.insert(new Point2D(.2, .3));
    }

    @Test
    public void empty() throws Exception {
        assertEquals(true, empty.isEmpty());
        assertEquals(false, one.isEmpty());
        assertEquals(false, many.isEmpty());
    }

    @Test
    public void size() throws Exception {
        assertEquals(0, empty.size());
        assertEquals(1, one.size());
        assertEquals(true, many.size() > 1);

    }

    @Test
    public void sizeDoesNotCountDuplicates() throws Exception {
        APointSet pointSet = (APointSet) pointSetClass.newInstance();
        pointSet.insert(new Point2D(.1, .2));
        pointSet.insert(new Point2D(.2, .3));
        pointSet.insert(new Point2D(.1, .2));
        assertEquals(2, pointSet.size());
    }

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void insertNull() throws Exception {
        expectedException.expect(NullPointerException.class);
        empty.insert(null);
    }

    @Test
    public void contains() throws Exception {
        Point2D point1 = new Point2D(.1, .2);
        Point2D point2 = new Point2D(.2, .3);

        assertEquals(false, empty.contains(point1));
        assertEquals(false, empty.contains(point2));
        assertEquals(true, one.contains(point1));
        assertEquals(false, one.contains(point2));
        assertEquals(true, many.contains(point1));
        assertEquals(true, many.contains(point2));

        expectedException.expect(NullPointerException.class);
        many.contains(null);
    }

    @Test
    public void range() throws Exception {
        APointSet pointSet = (APointSet) pointSetClass.newInstance();
        pointSet.insert(new Point2D(0, 0));
        pointSet.insert(new Point2D(.1, .2));
        pointSet.insert(new Point2D(.2, .2));
        pointSet.insert(new Point2D(.2, .3));
        pointSet.insert(new Point2D(.3, .3));
        pointSet.insert(new Point2D(.3, .4));
        pointSet.insert(new Point2D(.9, .7));
        pointSet.insert(new Point2D(.9, .9));

        RectHV containsNone = new RectHV(.0001, .00001, .001, .0001);
        RectHV containsSome = new RectHV(.1, .1, .3, .3);
        RectHV containsAll = new RectHV(0, 0, .9, .9);

        Iterator<Point2D> none = pointSet.range(containsNone).iterator();
        assertEquals(false, none.hasNext());

        Iterator<Point2D> some = pointSet.range(containsSome).iterator();
        assertEquals(true, some.hasNext());
        assertEquals(new Point2D(.1, .2), some.next());
        assertEquals(true, some.hasNext());
        assertEquals(new Point2D(.2, .2), some.next());
        assertEquals(true, some.hasNext());
        assertEquals(new Point2D(.2, .3), some.next());
        assertEquals(true, some.hasNext());
        assertEquals(new Point2D(.3, .3), some.next());
        assertEquals(false, some.hasNext());

        Iterator<Point2D> all = pointSet.range(containsAll).iterator();
        assertEquals(true, all.hasNext());
        assertEquals(new Point2D(0, 0), all.next());
        assertEquals(true, all.hasNext());
        assertEquals(new Point2D(.1, .2), all.next());
        assertEquals(true, all.hasNext());
        assertEquals(new Point2D(.2, .2), all.next());
        assertEquals(true, all.hasNext());
        assertEquals(new Point2D(.2, .3), all.next());
        assertEquals(true, all.hasNext());
        assertEquals(new Point2D(.3, .3), all.next());
        assertEquals(true, all.hasNext());
        assertEquals(new Point2D(.3, .4), all.next());
        assertEquals(true, all.hasNext());
        assertEquals(new Point2D(.9, .7), all.next());
        assertEquals(true, all.hasNext());
        assertEquals(new Point2D(.9, .9), all.next());
        assertEquals(false, all.hasNext());

        expectedException.expect(NoSuchElementException.class);
        all.next();
    }

    @Test
    public void nearest() throws Exception {
        APointSet pointSet = (APointSet) pointSetClass.newInstance();
        assertEquals(null, pointSet.nearest(new Point2D(.1, .1)));

        pointSet.insert(new Point2D(.1, .2));
        pointSet.insert(new Point2D(.2, .3));
        pointSet.insert(new Point2D(.3, .4));
        pointSet.insert(new Point2D(.9, .7));
        pointSet.insert(new Point2D(.6, .4));

        assertEquals(new Point2D(.1, .2), pointSet.nearest(new Point2D(.0001, .1)));
        assertEquals(new Point2D(.2, .3), pointSet.nearest(new Point2D(.3, .3)));
        assertEquals(new Point2D(.9, .7), pointSet.nearest(new Point2D(.999, .991)));
        assertEquals(new Point2D(.9, .7), pointSet.nearest(new Point2D(.75, .55)));
        assertEquals(new Point2D(.6, .4), pointSet.nearest(new Point2D(.75, .54)));

        expectedException.expect(NullPointerException.class);
        pointSet.nearest(null);
    }
}
