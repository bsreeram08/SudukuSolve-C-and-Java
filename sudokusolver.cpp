#include <bits/stdc++.h>
#define N 9
using namespace std;
bool inBox(int grid[N][N], int startRow, int startCol, int num)
{
    for (int row = 0; row < 3; row++)
        for (int col = 0; col < 3; col++)
            if (grid[row + startRow][col + startCol] == num)
                return true;
    return false;
}
bool inCol(int grid[N][N], int col, int num)
{
    for (int row = 0; row < N; row++)
        if (grid[row][col] == num)
            return true;
    return false;
}
bool inRow(int grid[N][N], int row, int num)
{
    for (int col = 0; col < N; col++)
        if (grid[row][col] == num)
            return true;
    return false;
}
bool isSafe(int grid[N][N], int row, int col, int num)
{
    if (inBox(grid, row - row % 3, col - col % 3, num) || inCol(grid, col, num) || inRow(grid, row, num))
    {
        return false;
    }
    return true;
}
bool findUnAssignedGrid(int grid[N][N], int &row, int &col)
{
    for (row = 0; row < N; row++)
        for (col = 0; col < N; col++)
            if (grid[row][col] == 0)
                return true;
    return false;
}
bool sudokusolver(int grid[N][N])
{
    int row, col;
    if (findUnAssignedGrid(grid, row, col) == false)
    {
        return true;
    }
    for (int num = 1; num < 10; num++)
    {
        if (isSafe(grid, row, col, num))
        {
            grid[row][col] = num;
            if (sudokusolver(grid))
            {
                return true;
            }
            grid[row][col] = 0;
        }
    }
    return false;
}
void printGrid(int grid[N][N])
{
    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < N; j++)
        {
            cout << grid[i][j] << " ";
        }
        cout << endl;
    }
}
bool isValidRow(int grid[N][N], int row)
{
    set<int> s;
    for (int col = 0; col < 9; col++)
    {
        if (grid[row][col] != 0)
        {
            if (s.insert(grid[row][col]).second == false)
            {
                return false;
            }
        }
    }
    return true;
}
bool isValidCol(int grid[N][N], int col)
{
    set<int> s;
    for (int row = 0; row < 9; row++)
    {
        if (grid[row][col] != 0)
        {
            if (s.insert(grid[row][col]).second == false)
            {
                return false;
            }
        }
    }
    return true;
}
bool isValidSubSquare(int grid[N][N])
{
    for (int row = 0; row < 9; row = row + 3)
    {
        for (int col = 0; col < 9; col = col + 3)
        {
            set<int> s;
            for (int r = row; r < row + 3; r++)
            {
                for (int c = col; c < col + 3; c++)
                {
                    if (grid[r][c] != 0)
                    {
                        if (s.insert(grid[r][c]).second == false)
                        {
                            return false;
                        }
                    }
                }
            }
        }
    }
    return true;
}
bool isProper(int grid[N][N])
{
    for (int iter = 0; iter < 9; iter++)
    {
        if (!isValidRow(grid, iter) || !isValidCol(grid, iter))
        {
            return false;
        }
    }
    if (!isValidSubSquare(grid))
    {
        return false;
    }
    return true;
}
int main()
{
    /*int grid[N][N] = {{3, 0, 6, 5, 0, 8, 4, 0, 0},
                      {5, 2, 0, 0, 0, 0, 0, 0, 0},
                      {0, 8, 7, 0, 0, 0, 0, 3, 1},
                      {0, 0, 3, 0, 1, 0, 0, 8, 0},
                      {9, 0, 0, 8, 6, 3, 0, 0, 5},
                      {0, 5, 0, 0, 9, 0, 6, 0, 0},
                      {1, 3, 0, 0, 0, 0, 2, 5, 0},
                      {0, 0, 0, 0, 0, 0, 0, 7, 4},
                      {0, 0, 5, 2, 0, 6, 3, 0, 0}};
*/
    int grid[N][N] = {{5, 3, 0, 0, 7, 0, 0, 0, 0},
                      {6, 0, 0, 1, 9, 5, 0, 0, 0},
                      {0, 9, 8, 0, 0, 0, 0, 6, 0},
                      {8, 0, 0, 0, 6, 0, 0, 0, 3},
                      {4, 0, 0, 8, 0, 3, 0, 0, 1},
                      {7, 0, 0, 0, 2, 0, 0, 0, 6},
                      {0, 6, 0, 0, 0, 0, 2, 8, 0},
                      {0, 0, 0, 4, 1, 9, 0, 0, 5},
                      {0, 0, 0, 0, 8, 0, 0, 7, 9}};
    if (!isProper(grid))
    {
        cout << "Recheck Grid";
        return 0;
    }
    if (sudokusolver(grid))
    {
        cout << "Solveable\n";
        printGrid(grid);
    }
    else
    {
        cout << "No Solution";
    }
    return 0;
}