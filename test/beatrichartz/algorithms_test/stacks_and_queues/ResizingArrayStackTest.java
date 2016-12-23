package beatrichartz.algorithms_test.stacks_and_queues;

import beatrichartz.algorithms.stacks_and_queues.ResizingArrayStack;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class ResizingArrayStackTest {
    @Test
    public void resizesWhenCapacityReached() throws Exception {
        ResizingArrayStack<Integer> resizingArrayStack = new ResizingArrayStack<>(2);
        for (int i = 0; i < 10; i++) {
            resizingArrayStack.push(i);
        }

        for (int i = 9; i > 0; i--) {
            assertEquals(i, (int) resizingArrayStack.pop());
        }
    }
}
