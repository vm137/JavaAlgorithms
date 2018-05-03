import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double[] thresholds;
    private double mean, devt;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) { throw new IllegalArgumentException(); }

        int row, col;
        thresholds = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);

            while (!p.percolates()) {
                row = StdRandom.uniform(n) + 1;
                col = StdRandom.uniform(n) + 1;

                if (!p.isOpen(row, col)) {
                    p.open(row, col);
                }
            }

            thresholds[i] = p.numberOfOpenSites() / (n * n * 1.0);
        }
    }

    public double mean() {
        mean = StdStats.mean(thresholds);
        return mean;
    }

    public double stddev() {
        devt = StdStats.stddev(thresholds);
        return devt;
    }

    public double confidenceLo() {
        return mean - (1.96 * devt / Math.sqrt(thresholds.length));
    }

    public double confidenceHi() {
        return mean + (1.96 * devt / Math.sqrt(thresholds.length));
    }

    public static void main(String[] args) {
        if (args.length != 2) { throw new IllegalArgumentException("incorrect input arguments for main()"); }

        PercolationStats ps = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));



        System.out.printf("mean = %g %n", ps.mean());
        System.out.printf("stddev = %g %n", ps.stddev());
        System.out.printf("95%% confidence interval = [%g, %g]", ps.confidenceLo(), ps.confidenceHi());
    }
}
