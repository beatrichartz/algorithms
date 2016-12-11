package beatrichartz.algorithms_test.quick_union.examples.percolation;

import beatrichartz.algorithms.quick_union.examples.percolation.Percolation;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class PercolationTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void testDoesNotAcceptNegativeNumbers() {
        exception.expect(IllegalArgumentException.class);
        new Percolation(-1);
    }

    @Test
    public void testDoesNotAcceptZero() {
        exception.expect(IllegalArgumentException.class);
        new Percolation(0);
    }

    @Test
    public void testStartsWithAllSitesBlocked() throws Exception {
        Percolation percolation = new Percolation(2);

        assertEquals(false, percolation.isOpen(1,1));
        assertEquals(false, percolation.isOpen(2,2));
        assertEquals(false, percolation.isOpen(1,2));
        assertEquals(false, percolation.isOpen(2,1));

        assertEquals(false, percolation.isFull(1,1));
        assertEquals(false, percolation.isFull(2,2));
        assertEquals(false, percolation.isFull(2,1));
        assertEquals(false, percolation.isFull(1,2));
    }

    @Test
    public void testCanOpenSites() throws Exception {
        Percolation percolation = new Percolation(2);
        percolation.open(2,2);

        assertEquals(false, percolation.isOpen(1,1));
        assertEquals(true, percolation.isOpen(2,2));
        assertEquals(false, percolation.isOpen(1,2));
        assertEquals(false, percolation.isOpen(2,1));
    }

    @Test
    @Parameters({"3, 2", "2, 3", "1, 0", "0, 1", "-1, 1", "1, -1"})
    public void testOpenThrowsIndexOutOfBoundsWhenGivenInvalidNumbers(int row, int col) throws Exception {
        Percolation percolation = new Percolation(2);
        exception.expect(IndexOutOfBoundsException.class);
        percolation.open(row, col);
    }

    @Test
    @Parameters({"3, 2", "2, 3", "1, 0", "0, 1", "-1, 1", "1, -1"})
    public void testIsOpenThrowsIndexOutOfBoundsWhenGivenInvalidNumbers(int row, int col) throws Exception {
        Percolation percolation = new Percolation(2);
        exception.expect(IndexOutOfBoundsException.class);
        percolation.isOpen(row, col);
    }

    @Test
    @Parameters({"3, 2", "2, 3", "1, 0", "0, 1", "-1, 1", "1, -1"})
    public void testIsFullThrowsIndexOutOfBoundsWhenGivenInvalidNumbers(int row, int col) throws Exception {
        Percolation percolation = new Percolation(2);
        exception.expect(IndexOutOfBoundsException.class);
        percolation.isFull(row, col);
    }

    @Test
    public void testOpeningSitesConnectedToTopFillsSites() throws Exception {
        Percolation percolation = new Percolation(3);
        percolation.open(1,1);

        assertEquals(true, percolation.isFull(1,1));
        assertEquals(false, percolation.isFull(2,2));
        assertEquals(false, percolation.isFull(3,3));
        assertEquals(false, percolation.isFull(1,2));
        assertEquals(false, percolation.isFull(1,3));
        assertEquals(false, percolation.isFull(2,3));
        assertEquals(false, percolation.isFull(3,2));

        percolation.open(1,2);
        percolation.open(2,2);
        percolation.open(3,3);

        assertEquals(true, percolation.isFull(1,1));
        assertEquals(true, percolation.isFull(2,2));
        assertEquals(false, percolation.isFull(3,3));
        assertEquals(true, percolation.isFull(1,2));
        assertEquals(false, percolation.isFull(1,3));
        assertEquals(false, percolation.isFull(2,3));
        assertEquals(false, percolation.isFull(3,2));
    }

    @Test
    public void testPercolatesWhenPathFromTopToBottomExists() throws Exception {
        Percolation percolation = new Percolation(3);
        assertEquals(false, percolation.percolates());

        percolation.open(1,1);
        assertEquals(false, percolation.percolates());

        percolation.open(1,2);
        percolation.open(2,2);
        percolation.open(3,3);
        assertEquals(false, percolation.percolates());

        percolation.open(2,3);
        assertEquals(true, percolation.percolates());
    }
}
