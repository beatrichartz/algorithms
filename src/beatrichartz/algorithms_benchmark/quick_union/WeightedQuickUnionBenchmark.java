package beatrichartz.algorithms_benchmark.quick_union;

import beatrichartz.algorithms.quick_union.WeightedQuickUnion;
import com.google.caliper.BeforeExperiment;
import com.google.caliper.Benchmark;

public final class WeightedQuickUnionBenchmark {
    WeightedQuickUnion weightedQuickUnion;

    @BeforeExperiment
    void setUp() {
        weightedQuickUnion = new WeightedQuickUnion(10_000_000);
        for (int i = 0; i < 9_999_999; i++) {
            weightedQuickUnion.union(i, i+1);
        }
    }

    @Benchmark
    void timeWeightedQuickUnion(long reps) {
        for (int i = 0; i < reps; i++) {
            weightedQuickUnion.connected(0, 9_999_999);
        }
    }
}
