package beatrichartz.algorithms_test.sorting;

import beatrichartz.algorithms.sorting.InsertionSort;
import beatrichartz.algorithms.sorting.SelectionSort;
import beatrichartz.algorithms.sorting.ShellSort;
import beatrichartz.algorithms.sorting.Sort;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

@RunWith(value = Parameterized.class)
public class SortTest {
    @Parameterized.Parameters(name = "{0}")
    public static Collection data() {
        List<Object> collection = new ArrayList();
        collection.add(SelectionSort.class);
        collection.add(InsertionSort.class);
        collection.add(ShellSort.class);
        return collection;
    }

    private Class sortClass;
    private Sort<Integer> sort;

    public SortTest(Class sortClass) {
        this.sortClass = sortClass;
    }

    @Before
    public void setUp() throws Exception {
        this.sort = (Sort) sortClass.newInstance();
    }

    @Test
    public void sortsUnsortedInputDescending() throws Exception {
        Integer[] input = new Integer[]{4,5,100_000,4,2,-432,0,-1,12};
        Integer[] actual = sort.sort(input);
        Integer[] expected = new Integer[]{-432,-1,0,2,4,4,5,12,100_000};

        for (int i = 0; i < expected.length; i++) {
            assertEquals("Unexpected object in position " + i,
                    expected[i], actual[i]);
        }
    }
}
