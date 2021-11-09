import java.util.Arrays;

public class PercolationUF implements IPercolate{
   private IUnionFind myFinder;
   private boolean[][] myGrid;
   private final int VTOP;
   private final int VBOTTOM;
   private int myOpenCount;
   PercolationUF(IUnionFind finder, int size ){
       finder.initialize(size * size + 2);
       myFinder = finder;
       VTOP = size * size;
       VBOTTOM = size * size + 1;
       myGrid = new boolean[size][size];
       myOpenCount = 0;}

    @Override
    public void open(int row, int col) {
        if (row < 0 || row >= myGrid.length || col < 0 || col >= myGrid.length) {
            throw new IndexOutOfBoundsException(
                    String.format("(%d,%d) not in bounds", row,col));
        }
        if  (!(isOpen(row,col)) ){
            myGrid[row][col] = true;
            myOpenCount += 1;
            if (row == 0){myFinder.union(VTOP, getIndex(row, col));}
            if (row == myGrid.length - 1) {myFinder.union(getIndex(row, col), VBOTTOM);}
            int[][] neighbors = new int[][]{{row + 1, col}, {row - 1, col}, {row, col + 1}, {row, col - 1}};
            for (int[] n: neighbors) {
                if ( n[0] >= 0 && n[0] < myGrid.length && n[1] >= 0 && n[1] < myGrid.length && isOpen(n[0], n[1])) {
                    myFinder.union(getIndex(n[0], n[1]),getIndex(row,col));
                  }
            }
        }

    }

    @Override
    public boolean isOpen(int row, int col) {
        if (row < 0 || row >= myGrid.length || col < 0 || col >= myGrid.length) {
            throw new IndexOutOfBoundsException(
                    String.format("(%d,%d) not in bounds", row,col));
        }
       return myGrid[row][col];
    }

    @Override
    public boolean isFull(int row, int col) {
        if (row < 0 || row >= myGrid.length || col < 0 || col >= myGrid.length) {
            throw new IndexOutOfBoundsException(
                    String.format("(%d,%d) not in bounds", row,col));
        }
        return myFinder.connected(getIndex(row, col), VTOP);
    }

    @Override
    public boolean percolates() {
        return myFinder.connected(VTOP,VBOTTOM);
    }

    @Override
    public int numberOfOpenSites() {
        return myOpenCount;
    }
    private int getIndex(int row, int col){return row*myGrid.length + col; }

}
