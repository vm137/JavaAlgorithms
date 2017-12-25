import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int maxN, pp, qq;
    private int sitesOpen = 0;
    private boolean[][] matrix; // false - closed, true - open
    private final WeightedQuickUnionUF ufstruct;

    public Percolation(int n) {
        if (n <= 0) { throw new IllegalArgumentException(); }

        maxN = n;
        pp = maxN * maxN;
        qq = maxN * maxN + 1;

        ufstruct = new WeightedQuickUnionUF(maxN * maxN + 2);
        matrix = new boolean[maxN + 1][maxN + 1];
    }

    public void open(int row, int col) {
        checkIfValid(row, col);

        if (!isOpen(row, col)) {
            matrix[row][col] = true;
            sitesOpen++;
        }

        // pp
        if (row == 1) {
            ufstruct.union(rowColTo1D(row, col), pp);
        }

        // top
        if (isValid(row - 1, col) && isOpen(row - 1, col)) {
            ufstruct.union(rowColTo1D(row, col), rowColTo1D(row - 1, col));
        }

        // right
        if (isValid(row, col + 1) && isOpen(row, col + 1)) {
            ufstruct.union(rowColTo1D(row, col), rowColTo1D(row, col + 1));
        }

        // bottom
        if (isValid(row + 1, col) && isOpen(row + 1, col)) {
            ufstruct.union(rowColTo1D(row, col), rowColTo1D(row + 1, col));
        }

        // left
        if (isValid(row, col - 1) && isOpen(row, col - 1)) {
            ufstruct.union(rowColTo1D(row, col), rowColTo1D(row, col - 1));
        }

        // qq
        if (row == maxN) {
            if (isOpen(row - 1, col) && isFull(row - 1, col)
                    || (isValid(row, col - 1) && isFull(row, col - 1))
                    || (isValid(row, col + 1) && isFull(row, col + 1))) {
                ufstruct.union(rowColTo1D(row, col), qq);
            }

        }

    }

    private boolean isValid(int row, int col) {
        return (row >= 1 && row <= maxN) && (col >= 1 && col <= maxN);
    }

    private void checkIfValid(int row, int col) {
        if ((row < 1 || row > maxN) || (col < 1 || col > maxN))
            throw new IllegalArgumentException();
    }

    private int rowColTo1D(int row, int col) {
        return (row - 1) * maxN + (col - 1);
    }

    public boolean isOpen(int row, int col) {
        checkIfValid(row, col);
        return matrix[row][col];
    }

    public boolean isFull(int row, int col) {
        checkIfValid(row, col);
        return ufstruct.connected(pp, rowColTo1D(row, col));
    }

    public int numberOfOpenSites() {
        return sitesOpen;
    }

    public boolean percolates() {
        return ufstruct.connected(pp, qq);
    }

    private void visualizeMatrix() {
        for (int row = 1; row <= maxN; row++) {
            for (int col = 1; col <= maxN; col++) {
                System.out.print(matrix[row][col] ? 1 : 0);
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    public static void main(String[] args) {
        int n = 5;
        Percolation p = new Percolation(n);
        System.out.println("Percolation constructor (x" + n + ")\n");

        int row, col;
        while (!p.percolates()) {
            row = StdRandom.uniform(n) + 1;
            col = StdRandom.uniform(n) + 1;

            if (!p.isOpen(row, col)) {
                p.open(row, col);
            }
        }

        p.visualizeMatrix();
        System.out.println("percolates with: " + p.numberOfOpenSites() + " sites of " + n * n + " (" + ((p.numberOfOpenSites()) / (n * n * 1.0)) + ")");
    }

}
