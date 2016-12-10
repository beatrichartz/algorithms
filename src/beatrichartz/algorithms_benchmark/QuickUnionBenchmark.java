package beatrichartz.algorithms_benchmark;

import beatrichartz.algorithms.QuickUnion;
import com.google.caliper.BeforeExperiment;
import com.google.caliper.Benchmark;

public final class QuickUnionBenchmark {
    QuickUnion quickUnion;

    @BeforeExperiment
    void setUp() {
        quickUnion = new QuickUnion(10_000_000);
        for (int i = 0; i < 9_999_999; i++) {
            quickUnion.union(i, i+1);
        }
    }

    @Benchmark
    void timeQuickUnion(int reps) {
        for (int i = 0; i < reps; i++) {
            quickUnion.connected(0, 9_999_999);
        }
    }
}


