package beatrichartz.algorithms.stacks_and_queues;

import java.util.EmptyStackException;

public class ResizingArrayStack<T> implements Stack<T> {
   private static final int BOTTOM = -1;
   private T[] elements;
   private int cursor = BOTTOM;

   public ResizingArrayStack() {
      this(10);
   }

   public ResizingArrayStack(int initialCapacity) {
      this.elements = (T[]) new Object[initialCapacity];
   }

   public T pop() {
      if (cursor == BOTTOM) throw new EmptyStackException();
      T element = elements[cursor];
      elements[cursor] = null;
      cursor--;

      return element;
   }

   public void push(T elem) {
      if (elements.length == cursor + 1) {
         T[] newElements = (T[]) new Object[elements.length * 2];
         System.arraycopy(elements, 0, newElements, 0, elements.length);
         this.elements = newElements;
      }

      elements[++cursor] = elem;
   }
}
