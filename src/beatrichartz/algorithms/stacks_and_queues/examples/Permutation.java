package beatrichartz.algorithms.stacks_and_queues.examples;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        String[] input = StdIn.readAllStrings();
        int inputSize = input.length;

        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();
        for (int i = 0; i < k; i++) {
            int randomIndex = StdRandom.uniform(inputSize);
            String in = input[randomIndex];
            input[randomIndex] = input[--inputSize];
            input[inputSize] = null;
            randomizedQueue.enqueue(in);
        }
        for (int i = 0; i < k; i++) {
            System.out.println(randomizedQueue.dequeue());
        }
    }
}
