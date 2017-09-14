import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.QuickFindUF;

public class Percolation {
    private int MaxN, P, Q;
    private int[][] Matrix; // 0 - closed, 1 - open, 2 - full
    private QuickFindUF UFstruct;

    public Percolation(int n) {
        System.out.println("Percolation constructor (x" + n + ")");
        MaxN = n;
        P = MaxN * MaxN;
        Q = MaxN * MaxN + 1;
        UFstruct = new QuickFindUF(MaxN * MaxN + 2);
        Matrix = new int[MaxN + 1][MaxN + 1];

        System.out.println("initial counts: " + (UFstruct.count() - 2));

        int row, col;

        while (!percolates()) {
            row = StdRandom.uniform(MaxN) + 1;
            col = StdRandom.uniform(MaxN) + 1;

            if (!isOpen(row, col)) {
                open(row, col);
            }
        }
    }

    public void open(int row, int col) {

        Matrix[row][col] = 1;

        // P
        if (row == 1) {
            UFstruct.union(rowColTo1D(row, col), P);
        }

        // top
        if (isValid(row - 1, col) && isOpen(row - 1, col)) {
            UFstruct.union(rowColTo1D(row, col), rowColTo1D(row - 1, col));
        }

        // right
        if (isValid(row, col + 1) && isOpen(row, col + 1)) {
            UFstruct.union(rowColTo1D(row, col), rowColTo1D(row, col + 1));
        }

        // bottom
        if (isValid(row + 1, col) && isOpen(row + 1, col)) {
            UFstruct.union(rowColTo1D(row, col), rowColTo1D(row + 1, col));
        }

        // left
        if (isValid(row, col - 1) && isOpen(row, col - 1)) {
            UFstruct.union(rowColTo1D(row, col), rowColTo1D(row, col - 1));
        }

        // Q
        if (row == MaxN) {
            UFstruct.union(rowColTo1D(row, col), Q);
        }

        makeFull(row, col);
    }

    private void makeFull(int row, int col) {

        if (row == 1
                || isValid(row - 1, col) && isFull(row - 1, col)
                || isValid(row, col - 1) && isFull(row, col - 1)
                || isValid(row + 1, col) && isFull(row + 1, col)
                || isValid(row, col + 1) && isFull(row, col + 1)) {

            Matrix[row][col] = 2;

            if (isValid(row - 1, col) && isOpen(row - 1, col) && !isFull(row - 1, col)) {
                makeFull(row - 1, col);
            }

            if (isValid(row, col - 1) && isOpen(row, col - 1) && !isFull(row, col - 1)) {
                makeFull(row, col - 1);
            }

            if (isValid(row + 1, col) && isOpen(row + 1, col) && !isFull(row + 1, col)) {
                makeFull(row + 1, col);
            }

            if (isValid(row, col + 1) && isOpen(row, col + 1) && !isFull(row, col + 1)) {
                makeFull(row, col + 1);
            }
        }
    }

    private boolean isValid (int row, int col) {
        return (row >= 1 && row <= MaxN) && (col >= 1 && col <= MaxN);
    }

    private int rowColTo1D (int row, int col) {
        return (row - 1) * MaxN + (col - 1 );
    }

    public boolean isOpen(int row, int col) { return (Matrix[row][col] >= 1); }

    public boolean isFull(int row, int col) { return (Matrix[row][col] == 2); }

    public int numberOfOpenSites() {
        return UFstruct.count() - 2;
    }

    public boolean percolates() {
        return UFstruct.connected(P, Q);
    }

    private void visualizeMatrix() {
        for (int row = 1; row <= MaxN; row++) {
            for (int col = 1; col <= MaxN; col++) {
                System.out.print(Matrix[row][col]);
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    public static void main(String[] args) {

        Percolation p = new Percolation(10);

        p.visualizeMatrix();
        System.out.println("percolates with : " + p.numberOfOpenSites() + " sites");
    }

}
