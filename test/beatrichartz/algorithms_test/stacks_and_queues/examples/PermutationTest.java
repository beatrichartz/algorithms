package beatrichartz.algorithms_test.stacks_and_queues.examples;

import beatrichartz.algorithms.stacks_and_queues.examples.Permutation;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static junit.framework.TestCase.assertEquals;

public class PermutationTest {
    @Test
    public void readsStdInAndReturnsRandomSubsetUpToK() throws Exception {
        StdRandom.setSeed(1_000);
        String input = "A B C D E F G";

        InputStream oldSystemIn = System.in;
        PrintStream oldSystemOut = System.out;

        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setIn(inContent);
        System.setOut(new PrintStream(outContent));

        Permutation.main(new String[]{"3"});
        assertEquals("B\nG\nF\n", outContent.toString());

        System.setIn(oldSystemIn);
        System.setOut(oldSystemOut);
    }
}
