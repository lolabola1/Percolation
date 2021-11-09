import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PercolationBFS extends PercolationDFSFast{
    /**
     * Initialize a grid so that all cells are blocked.
     *
     * @param n is the size of the simulated (square) grid
     */
    public PercolationBFS(int n) {
        super(n);
    }
    @Override
    protected void dfs(int row, int col) {
        // out of bounds?
        if (! inBounds(row,col)) return;

        // full or NOT open, don't process
        if (isFull(row, col) || !isOpen(row, col))
            return;
        Queue<int[]> q = new LinkedList<int[]>();
        myGrid[row][col] = FULL;
        q.add(new int[]{row, col});
        while (q.size() != 0){
            int[] lol = q.remove();
            int[][] neighbors = {{lol[0] +1, lol[1]},{lol[0] - 1, lol[1]}, {lol[0], lol[1]+1},{lol[0], lol[1]-1}};
            for (int[] l: neighbors){
                if (inBounds(l[0],l[1]) && isOpen(l[0],l[1]) && !(isFull(l[0],l[1]))){
                    myGrid[l[0]][l[1]]= FULL;
                    q.add(new int[]{l[0],l[1]});
                }
            }




        }
}}
