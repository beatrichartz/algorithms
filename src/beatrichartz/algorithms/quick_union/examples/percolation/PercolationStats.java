package beatrichartz.algorithms.quick_union.examples.percolation;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

import static java.lang.Math.sqrt;

public class PercolationStats {
    private int numTrials;
    private double[] percolationThresholds;

    public static void main(String[] args) {
        if (args.length != 2) { throw new IllegalArgumentException(); }

        int sideLength = Integer.decode(args[0]);
        int numTrials = Integer.decode(args[1]);
        PercolationStats stats = new PercolationStats(sideLength, numTrials);

        System.out.println("mean\t\t\t\t\t= " + stats.mean());
        System.out.println("stddev\t\t\t\t\t= " + stats.stddev());
        System.out.println(
                "95% confidence interval\t= " +
                stats.confidenceLo() + ", " +
                stats.confidenceHi());
    }

    public PercolationStats(int sideLength, int numTrials) {
        if (sideLength <= 0 || numTrials <= 0) { throw new IllegalArgumentException(); }

        this.numTrials = numTrials;
        percolationThresholds = new double[numTrials];
        for (int i = 0; i < numTrials; i++) {
            Percolation percolation = new Percolation(sideLength);
            percolationThresholds[i] = getPercolationThreshold(sideLength, percolation);
        }
    }

    private double getPercolationThreshold(int sideLength, Percolation percolation) {
        int maxNumOpens = sideLength * sideLength;
        for (int i = 1; i <= maxNumOpens; i++) {
            int row = StdRandom.uniform(1, sideLength + 1);
            int col = StdRandom.uniform(1, sideLength + 1);

            while (percolation.isOpen(row, col)) {
                row = StdRandom.uniform(1, sideLength + 1);
                col = StdRandom.uniform(1, sideLength + 1);
            }

            percolation.open(row, col);

            if (percolation.percolates()) {
                return ((double) i) / ((double) maxNumOpens);
            }
        }

        return ((double) maxNumOpens);
    }

    public double mean() {
        return StdStats.mean(percolationThresholds);
    }

    public double stddev() {
        return StdStats.stddev(percolationThresholds);
    }

    public double confidenceLo() {
        return mean() - confidenceInterval();
    }

    public double confidenceHi() {
        return mean() + confidenceInterval();
    }

    private double confidenceInterval() {
        return 1.96 * stddev() / sqrt(numTrials);
    }
}
