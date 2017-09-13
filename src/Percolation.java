import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.QuickFindUF;

public class Percolation {
    private int MaxN;
    private int[][] Matrix;


    public Percolation(int n) {
        System.out.println("Percolation constructor " + n);
        MaxN = n;
        Matrix = new int[MaxN][MaxN];


        int row, col, c = 0; // TODO: c - temp

        do {
            row = StdRandom.uniform(MaxN);
            col = StdRandom.uniform(MaxN);

            if (!isOpen(row, col)) {
                open(row,col);
            }

        } while (c++ < 20);

    }

    public void open(int row, int col) { Matrix[row][col] = 1; }
    public boolean isOpen(int row, int col) { return (boolean)(Matrix[row][col] >= 1); } // is site (row, col) open?
    public boolean isFull(int row, int col) { return (boolean)(Matrix[row][col] == 2); } // is site (row, col) full?

    public int numberOfOpenSites() {
        int count = 0;
        for (int row = 0; row < MaxN; row++) {
            for (int col = 0; col < MaxN; col++) {
                if (Matrix[row][col] >= 1) count++;
            }
        }
        return count;
    }

    public boolean percolates() {
        // HERE



        return true;
    }

    private void visualizeMatrix() {
        for (int row = 0; row < MaxN; row++) {
            for (int col = 0; col < MaxN; col++) {
                System.out.print(Matrix[row][col]);
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    public static void main(String[] args) {

        Percolation p = new Percolation(6);
        p.visualizeMatrix();

        System.out.println("open sites: " + p.numberOfOpenSites());
    }

}
