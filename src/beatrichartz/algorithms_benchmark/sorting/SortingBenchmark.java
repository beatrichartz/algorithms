package beatrichartz.algorithms_benchmark.sorting;

import beatrichartz.algorithms.sorting.*;
import com.google.caliper.BeforeExperiment;
import com.google.caliper.Param;
import com.google.caliper.api.Macrobenchmark;

import java.util.Arrays;

public class SortingBenchmark {
    @Param
    private int size;

    @Param
    private String inputType;

    private Integer[] input;

    @BeforeExperiment
    void setUp() {
        input = new Integer[size];
        for (int i = 0; i < size; i++) {
            input[i] = getElementForPosition(i, inputType);
        }
    }

    protected Integer getElementForPosition(int i, String inputType) {
        switch (inputType) {
            case "random":
                return (int) Math.ceil(Math.random() * 100_000_000) - 100_000;
            case "reverse":
                return size - i;
            case "lowCardinality":
                return i%(size / 10);
            case "nearlySorted":
                if (i%(size / 100) == 0) {
                    return i + (int) Math.ceil(Math.random() * 10) - 10;
                }
            default: throw new UnsupportedOperationException("Input type " + inputType + " is not supported");
        }
    }

    @Macrobenchmark
    void timeSelectionSort() {
        new SelectionSort<Integer>().sort(Arrays.copyOf(input, input.length));
    }

    @Macrobenchmark
    void timeInsertionSort() {
        new InsertionSort<Integer>().sort(Arrays.copyOf(input, input.length));
    }

    @Macrobenchmark
    void timeBubbleSort() {
        new BubbleSort<Integer>().sort(Arrays.copyOf(input, input.length));
    }

    @Macrobenchmark
    void timeQuickSort() {
        new QuickSort<Integer>().sort(Arrays.copyOf(input, input.length));
    }

    @Macrobenchmark
    void timeQuickSort3() {
        new QuickSort3<Integer>().sort(Arrays.copyOf(input, input.length));
    }

    @Macrobenchmark
    void timeHeapSort() {
        new HeapSort<Integer>().sort(Arrays.copyOf(input, input.length));
    }

    @Macrobenchmark
    void timeShellSort() {
        new ShellSort<Integer>().sort(Arrays.copyOf(input, input.length));
    }

    @Macrobenchmark
    void timeTopDownMergeSort() {
        new TopDownMergeSort<Integer>().sort(Arrays.copyOf(input, input.length));
    }

    @Macrobenchmark
    void timeBottomUpMergeSort() {
        new BottomUpMergeSort<Integer>().sort(Arrays.copyOf(input, input.length));
    }
}
