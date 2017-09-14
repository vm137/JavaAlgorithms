import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] thresholds;
    private double mean, dev;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) { throw new IllegalArgumentException(); }
        // System.out.printf("%dx%d %d times%n%n", n, n, trials);

        thresholds = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            thresholds[i] = (double)p.numberOfOpenSites() / (n * n);
        }

        System.out.printf("mean = %g %n", mean());
        System.out.printf("stddev = %g %n", stddev());
        System.out.printf("95%% confidence interval = [%g, %g]", confidenceLo(), confidenceHi());
    }

    public double mean() {
        mean = StdStats.mean(thresholds);
        return mean;
    }

    public double stddev() {
        dev = StdStats.stddev(thresholds);
        return dev;
    }

    public double confidenceLo() {
        return mean - .95 * dev;
    }

    public double confidenceHi() {
        return mean + .95 * dev;
    }

    public static void main(String[] args) {
        if (args.length != 2) { throw new IllegalArgumentException("incorrect input arguments for main()"); }
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);

        new PercolationStats(n, t);
    }
}
