package beatrichartz.algorithms_benchmark.analysis;

import beatrichartz.algorithms.analysis.examples.BitonicArray;
import com.google.caliper.BeforeExperiment;
import com.google.caliper.Benchmark;

public class BitonicArrayBenchmark {
    BitonicArray bitonicArray;
    int[] containsInputArray;

    @BeforeExperiment
    void setUp() {
        int maxIndex = randomIntBetween(2, 1_000_000);
        int endIndex = maxIndex + randomIntBetween(2, 1_000_000);
        int[] input = new int[endIndex];
        int value = randomIntBetween(-100_000, 100_000);
        for (int i = 0; i < maxIndex; i++) {
            input[i] = value;
            value += randomIntBetween(1, 100);
        }

        for (int i = maxIndex; i < endIndex; i++) {
            input[i] = value;
            value -= randomIntBetween(1, 100);
        }

        containsInputArray = new int[10];
        for (int i = 0; i < 10; i++) {
            containsInputArray[i] = randomIntBetween(-10_000, 10_000);
        }

        bitonicArray = new BitonicArray(input);
    }

    @Benchmark
    void timeContains(int reps) {
        for (int i = 0; i < reps; i++) {
            bitonicArray.contains(randomIntBetween(-1_000_000, 1_000_000));
        }
    }

    @Benchmark
    void timeLinearContains(int reps) {
        for (int i = 0; i < reps; i++) {
            bitonicArray.linearContains(randomIntBetween(-1_000_000, 1_000_000));
        }
    }

    private int randomIntBetween(int start, int end) {
        return start + (int)(Math.random() * Math.abs(end - start));
    }

}

