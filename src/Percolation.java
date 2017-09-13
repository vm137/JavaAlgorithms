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
        P = MaxN * MaxN + 1;
        Q = MaxN * MaxN + 2;
        UFstruct = new QuickFindUF(MaxN * MaxN + 2);
        Matrix = new int[MaxN + 1][MaxN + 1];

        System.out.println("inital counts: " + (UFstruct.count() - 2));

        ///////////////////////////

        int row, col, c = 0; // TODO: c - temp

        while (c++ < 30) { // while (!percolates())
            row = StdRandom.uniform(MaxN) + 1;
            col = StdRandom.uniform(MaxN) + 1;

            if (!isOpen(row, col)) {
                open(row, col);
            };

            // check if cite is full
            if (row == 1) Matrix[row][col] = 2;
        }
    }

    public void open(int row, int col) {

        Matrix[row][col] = 1;

        // checking for nearby cites - then .union() with them

        if (isValid(row - 1, col))

        // checking if full ...
        // if connected with other full - and then look for spreading


        UFstruct.union(5, 6);
        UFstruct.union(5, 7);

        // find if row == 0 then full
        // then check if connected to this elements

        // UFstruct.union(int p, int q)/
        // .connected(int p, int q)
        //

//        System.out.println(UFstruct.connected(5,6));
//        System.out.println(UFstruct.connected(5,8));

        // if top row - .union() with P
        // if bottom row - .union() with Q

    }

    private boolean isValid (int row, int col) {
        return (row >= 1 && row <= MaxN) && (col >= 1 && col <= MaxN);
    }

    private void flooding() {
        // going from top to bottom row
        // left and right each row
        // making ajacent elements - full (=2)
    }

    private int rowColTo1D (int row, int col) {
        return (row -1) * MaxN + (col -1 );
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

        Percolation p = new Percolation(6);

        p.visualizeMatrix();
        System.out.println("open sites when percolates: " + p.numberOfOpenSites());
    }

}
