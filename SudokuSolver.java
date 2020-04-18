/*package whatever //do not write package name here */
import java.util.HashSet;
import java.util.Set;
import java.io.*;
class MyBundle{
    public int row;
    public int col;
    public boolean val;
}
public class SudokuSolver {
    public int grid[][] = new int[9][9];
    public int[][] getGrid(){
        return grid;
    }
    public void setGrid(int grid[][]){
        for(int iterR=0;iterR<9;iterR++)
        {
            System.arraycopy(grid[iterR], 0, this.grid[iterR], 0, 9);
        }
    }
    public boolean inBox(int startRow, int startCol, int num)
    {
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                if (grid[row + startRow][col + startCol] == num)
                    return true;
        return false;
    }
    public boolean inCol(int col, int num)
    {
        for (int row = 0; row < 9; row++)
            if (grid[row][col] == num)
                return true;
        return false;
    }
    public boolean inRow(int row, int num)
    {
        for (int col = 0; col < 9; col++)
            if (grid[row][col] == num)
                return true;
        return false;
    }
    public boolean isSafe(int row, int col, int num)
    {
        return !inBox(row - row % 3, col - col % 3, num) && !inCol(col, num) && !inRow(row, num);
    }
    public MyBundle findUnAssignedGrid(int SearchRow,int SearchCol)
    {
        MyBundle mb = new MyBundle();
        for (SearchRow=0; SearchRow < 9; SearchRow++)
            for (SearchCol=0; SearchCol < 9; SearchCol++)
                if (grid[SearchRow][SearchCol] == 0)
                    {
                        mb.row=SearchRow;
                        mb.col = SearchCol;
                        mb.val = true;
                        return mb;
                    }
        mb.row=-1;
        mb.col=-1;
        mb.val=false;
        return mb;
    }
    public boolean sudokusolver()
    {
        int row=0,col=0;
        MyBundle mb = findUnAssignedGrid(row,col);
        if (!mb.val)
        {
            return true;
        }
        for (int num = 1; num < 10; num++)
        {
            if (isSafe(mb.row,mb.col, num))
            {
                grid[mb.row][mb.col] = num;
                if (sudokusolver())
                {
                    return true;
                }
                grid[mb.row][mb.col] = 0;
            }
        }
        return false;
    }
    public boolean isValidRow(int row)
    {
        Set<Integer> s = new HashSet<>();
        for (int col = 0; col < 9; col++)
        {
            if (grid[row][col] != 0)
            {
                if (!s.add(grid[row][col]))
                {
                    return false;
                }
            }
        }
        return true;
    }
    public boolean isValidCol(int col)
    {
        Set<Integer> s = new HashSet<>();
        for (int row = 0; row < 9; row++)
        {
            if (grid[row][col] != 0)
            {
                if (!s.add(grid[row][col]))
                {
                    return false;
                }
            }
        }
        return true;
    }
    public boolean isValidSubSquare()
    {
        for (int row = 0; row < 9; row = row + 3)
        {
            for (int col = 0; col < 9; col = col + 3)
            {
                System.out.printf("R:%d C:%d\n",row,col);
                Set<Integer> s = new HashSet<>();
                for (int r = row; r < row + 3; r++)
                {
                    for (int c = col; c < col + 3; c++)
                    {
                        System.out.printf("R_check:%d C_check:%d\t",r,c);
                        if (grid[r][c] != 0)
                        {
                            if (!s.add(grid[r][c]))
                            {
                                System.out.println(s);
                                System.out.println(grid[r][c]);
                                return false;
                            }
                        }
                    }
                    System.out.println();
                }
            }
        }
        return true;
    }
    public boolean isProper()
    {
        for (int iter = 0; iter < 9; iter++)
        {
            if (!isValidRow(iter) || !isValidCol(iter))
            {
                System.out.println("IM FALSE HERE");
                return false;
            }
        }
        return isValidSubSquare();
    }
    public void printGrid(){
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++)
            {
                System.out.printf("%d ", grid[i][j]);
            }
            System.out.println();
        }
    }

}
class GFG {
	public static void main (String[] args) {
		SudokuSolver ss = new SudokuSolver();
		int [][]grid = {{5, 3, 0, 0, 7, 0, 0, 0, 0},
                      {6, 0, 0, 1, 9, 5, 0, 0, 0},
                      {0, 9, 8, 0, 0, 0, 0, 6, 0},
                      {8, 0, 0, 0, 6, 0, 0, 0, 3},
                      {4, 0, 0, 8, 0, 3, 0, 0, 1},
                      {7, 0, 0, 0, 2, 0, 0, 0, 6},
                      {0, 6, 0, 0, 0, 0, 2, 8, 0},
                      {0, 0, 0, 4, 1, 9, 0, 0, 5},
                      {0, 0, 0, 0, 8, 0, 0, 7, 9}};
		ss.setGrid(grid);
		System.out.println(ss.isProper());
		ss.sudokusolver();
		ss.printGrid();
	}
}