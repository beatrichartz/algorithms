package beatrichartz.algorithms_benchmark.sorting;

import beatrichartz.algorithms.sorting.*;
import com.google.caliper.BeforeExperiment;
import com.google.caliper.Benchmark;

public class SortingBenchmark {
    private Integer[] input;

    @BeforeExperiment
    void setUp() {
        input = new Integer[100];
        for (int i = 0; i < 100; i++) {
            input[i] = (int) Math.ceil(Math.random() * 1_000);
        }
    }

    @Benchmark
    void timeSelectionSort(int reps) {
        for (int i = 0; i < reps; i++) {
            new SelectionSort<Integer>().sort(input);
        }
    }

    @Benchmark
    void timeInsertionSort(int reps) {
        for (int i = 0; i < reps; i++) {
            new InsertionSort<Integer>().sort(input);
        }
    }

    @Benchmark
    void timeShellSort(int reps) {
        for (int i = 0; i < reps; i++) {
            new ShellSort<Integer>().sort(input);
        }
    }

    @Benchmark
    void timeTopDownMergeSort(int reps) {
        for (int i = 0; i < reps; i++) {
            new TopDownMergeSort<Integer>().sort(input);
        }
    }

    @Benchmark
    void timeBottomUpMergeSort(int reps) {
        for (int i = 0; i < reps; i++) {
            new BottomUpMergeSort<Integer>().sort(input);
        }
    }
}
