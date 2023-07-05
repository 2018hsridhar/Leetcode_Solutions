/*
URL := https://leetcode.com/problems/count-sub-islands/description/
1905. Count Sub Islands

Make sure matrices/arrays are passed as shallow - not deep - copies
https://stackoverflow.com/questions/67682522/passing-array-to-a-function-in-go#:~:text=Array%20elements%20are%20passed%20by%20values%20in%20Go%2C,%28%26arr%29%20and%20receive%20it%20as%20array%20%2A%20int.



*/
func countSubIslands(grid1 [][]int, grid2 [][]int) int {
    subIslandCount := 0
    for i := 0; i < len(grid2); i++ {
        for j := 0; j < len(grid2[0]); j++ {
            if grid2[i][j] == 1 && isSubIsland(grid1,grid2,i,j) == true {
                subIslandCount++
            }
        }
    }
    return subIslandCount
}

/*
Slices are the idiomatic expression in GoLang
Slices desired over Arrays
4-dir vs 8-dir connectedness too
Set value to 2 := is visited!
    Do not change grid1 : you may reuse it in the future -> take note

*/
func isSubIsland(grid1 [][]int, grid2 [][]int, r int, c int) bool {
    islandStatus := true
    dirs := [][]int{
        {-1,0},
        {0,-1},
        {1,0},
        {0,1},
    }
    // Go is a lang where slices := underlying dstruct. Implement stacks and queues as arrays
    // But need to trust slices
    queue := make([][]int, 0)
    queue = append(queue, []int{r,c})
    for len(queue) > 0 {
        curEl := queue[0]
        queue = queue[1:]
        curR := curEl[0]
        curC := curEl[1]
        if isInBounds(grid2,curR,curC) {
            // apply only if island cell
            if grid2[curR][curC] == 1 {
                if grid1[curR][curC] == 1 {
                    grid2[curR][curC] = 2
                    for _, dir := range dirs {
                        nextR := curR + dir[0]
                        nextC := curC + dir[1]
                        if isInBounds(grid2,nextR,nextC) && grid2[nextR][nextC] == 1 {
                            queue = append(queue, []int{nextR,nextC})
                        }
                    }
                } else {
                    islandStatus = false
                }
            }
        }
    }
    return islandStatus
}

func isInBounds(grid [][]int, i int, j int) bool {
    return ((0 <= i && i < len(grid)) && (0 <= j && j < len(grid[0])))
}
