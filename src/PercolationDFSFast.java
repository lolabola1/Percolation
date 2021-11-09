public class PercolationDFSFast extends PercolationDFS{
    /**
     * Initialize a grid so that all cells are blocked.
     *
     * @param n is the size of the simulated (square) grid
     */
    public PercolationDFSFast(int n) {
        super(n);
    }
    @Override
    protected void updateOnOpen(int row, int col){
    if (row == 0 || isFull(row - 1, col)||col != 0 && isFull(row, col - 1)||(col < myGrid.length - 1 && isFull(row, col + 1)) ||row < myGrid.length - 1 && isFull(row + 1, col)){dfs(row,col);}
    }
}
