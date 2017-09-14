import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
/*
    public PercolationStats(int n, int trials) {}    // perform trials independent experiments on an n-by-n grid
    public double mean() {}                         // sample mean of percolation threshold
    public double stddev() {}                       // sample standard deviation of percolation threshold
    public double confidenceLo() {}                 // low  endpoint of 95% confidence interval
    public double confidenceHi() {}                 // high endpoint of 95% confidence interval
*/
    public static void main(String[] args) {
        System.out.println("stats: ... " );

        int n = 8;
        Percolation p = new Percolation(n);
        p.visualizeMatrix();
        System.out.println("percolates with: " + p.numberOfOpenSites() + " sites of " + n * n + " (" + ((n * n - p.numberOfOpenSites()) / (n * n * 1.0)) + ")");
    }
}
