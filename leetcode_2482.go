/*
URL := https://leetcode.com/problems/difference-between-ones-and-zeros-in-row-and-column/
2482. Difference Between Ones and Zeros in Row and Column

8 mins to solution

*/
func onesMinusZeros(grid [][]int) [][]int {
    m := len(grid)
    n := len(grid[0])
    diffMatrix := make([][]int,m)
    // slices are always 1d -> composition gets us to nd land
    // range syntax assignation based WTF?
    for i := range diffMatrix {
        diffMatrix[i] = make([]int, n) 
    }
    // Complement technique for count of zero implicitly too
    onesRow := make([]int, m)
    onesCol := make([]int, n)

    // Set up rows count
    for i := 0; i < m; i++ {
        rowOnesCount := 0
        for j := 0; j < n; j++ {
            if grid[i][j] == 1 {
                rowOnesCount += 1
            }
        }
        onesRow[i] = rowOnesCount
    }

    // Set up cols count
    for j := 0; j < n; j++ {
        colOnesCount := 0
        for i := 0; i < m; i++ {
            if grid[i][j] == 1 {
                colOnesCount += 1
            }
        }
        onesCol[j] = colOnesCount
    }

    for i := 0; i < m; i++ {
        for j := 0; j < n; j++ {
            diffMatrix[i][j] = onesRow[i] + onesCol[j] - (m - onesRow[i]) - (n - onesCol[j])
        }
    }
    return diffMatrix
}
