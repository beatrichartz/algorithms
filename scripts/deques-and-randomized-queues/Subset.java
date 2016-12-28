

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class Subset {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        String[] input = StdIn.readAllStrings();
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();
        for (int i = 0; i < k; i++) {
            randomizedQueue.enqueue(
                    input[StdRandom.uniform(input.length)]
            );
        }
        for (int i = 0; i < k; i++) {
            System.out.println(randomizedQueue.dequeue());
        }
    }
}
