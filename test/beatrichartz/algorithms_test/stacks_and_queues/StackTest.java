package beatrichartz.algorithms_test.stacks_and_queues;

import beatrichartz.algorithms.stacks_and_queues.LinkedListStack;
import beatrichartz.algorithms.stacks_and_queues.ResizingArrayStack;
import beatrichartz.algorithms.stacks_and_queues.Stack;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EmptyStackException;
import java.util.List;

import static junit.framework.TestCase.assertEquals;


@RunWith(value = Parameterized.class)
public class StackTest {

    @Parameterized.Parameters
    public static Collection data() {
        List<Object> collection = new ArrayList();
        collection.add(LinkedListStack.class);
        collection.add(ResizingArrayStack.class);
        return collection;
    }

    private Class stackClass;
    private Stack<String> stack;

    public StackTest(Class stackClass) {
        this.stackClass = stackClass;
    }

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        this.stack = (Stack) stackClass.newInstance();
    }

    @Test
    public void popThrowsExceptionWithEmptyStack() throws Exception {
        exception.expect(EmptyStackException.class);
        stack.pop();
    }

    @Test
    public void popReturnsLastPushedItem() throws Exception {
        stack.push("A");
        assertEquals("A", stack.pop());

        exception.expect(EmptyStackException.class);
        stack.pop();
    }

    @Test
    public void popReturnsPushedItemsInReverseOrder() throws Exception {
        stack.push("A");
        stack.push("B");
        stack.push("C");

        assertEquals("C", stack.pop());
        assertEquals("B", stack.pop());
        assertEquals("A", stack.pop());

        exception.expect(EmptyStackException.class);
        stack.pop();
    }

    @Test
    public void isLastInFirstOut() throws Exception {
        stack.push("A");
        stack.push("B");
        assertEquals("B", stack.pop());

        stack.push("C");
        assertEquals("C", stack.pop());
        assertEquals("A", stack.pop());

        exception.expect(EmptyStackException.class);
        stack.pop();
    }
}
