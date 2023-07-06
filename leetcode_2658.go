/*
URL := https://leetcode.com/problems/maximum-number-of-fish-in-a-grid/
2658. Maximum Number of Fish in a Grid

Make sure matrices/arrays are passed as shallow - not deep - copies
https://stackoverflow.com/questions/67682522/passing-array-to-a-function-in-go#:~:text=Array%20elements%20are%20passed%20by%20values%20in%20Go%2C,%28%26arr%29%20and%20receive%20it%20as%20array%20%2A%20int.



*/
// 0 if no water cell
// return max number : not the starting position
// denote explored : grid value to negative one here ( modif in place ) 
func findMaxFish(grid [][]int) int {
    maxFishCaught := 0
    for r := 0; r < len(grid); r++ {
        for c := 0; c < len(grid[0]); c++ {
            if grid[r][c] > 0 {
                myLocalFishCaught := getFishCountFromPosition(grid,r,c)
                if myLocalFishCaught > maxFishCaught {
                    maxFishCaught = myLocalFishCaught
                }
            }
        }
    }
    return maxFishCaught
}

/*
Slices are the idiomatic expression in GoLang
Slices desired over Arrays
4-dir vs 8-dir connectedness too
Set value to 2 := is visited!
    Do not change grid1 : you may reuse it in the future -> take note

*/
func getFishCountFromPosition(grid [][]int, r int, c int) int {
    localFishCaught := 0
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
        if isInBounds(grid,curR,curC) {
            // apply only if water cell : cell value not equal to zero
            if grid[curR][curC] > 0 {
                localFishCaught += grid[curR][curC]
                grid[curR][curC] = -1
                for _, dir := range dirs {
                    nextR := curR + dir[0]
                    nextC := curC + dir[1]
                    // Optimize here for efficiency too :-) 
                    if isInBounds(grid,nextR,nextC) && grid[nextR][nextC] > 0 {
                        queue = append(queue, []int{nextR,nextC})
                    }
                }
            }
        }
    }
    return localFishCaught
}

func isInBounds(grid [][]int, i int, j int) bool {
    return ((0 <= i && i < len(grid)) && (0 <= j && j < len(grid[0])))
}/*
URL := https://leetcode.com/problems/maximum-number-of-fish-in-a-grid/
2658. Maximum Number of Fish in a Grid

Make sure matrices/arrays are passed as shallow - not deep - copies
https://stackoverflow.com/questions/67682522/passing-array-to-a-function-in-go#:~:text=Array%20elements%20are%20passed%20by%20values%20in%20Go%2C,%28%26arr%29%20and%20receive%20it%20as%20array%20%2A%20int.



*/
// 0 if no water cell
// return max number : not the starting position
// denote explored : grid value to negative one here ( modif in place ) 
func findMaxFish(grid [][]int) int {
    maxFishCaught := 0
    for r := 0; r < len(grid); r++ {
        for c := 0; c < len(grid[0]); c++ {
            if grid[r][c] > 0 {
                myLocalFishCaught := getFishCountFromPosition(grid,r,c)
                if myLocalFishCaught > maxFishCaught {
                    maxFishCaught = myLocalFishCaught
                }
            }
        }
    }
    return maxFishCaught
}

/*
Slices are the idiomatic expression in GoLang
Slices desired over Arrays
4-dir vs 8-dir connectedness too
Set value to 2 := is visited!
    Do not change grid1 : you may reuse it in the future -> take note

*/
func getFishCountFromPosition(grid [][]int, r int, c int) int {
    localFishCaught := 0
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
        if isInBounds(grid,curR,curC) {
            // apply only if water cell : cell value not equal to zero
            if grid[curR][curC] > 0 {
                localFishCaught += grid[curR][curC]
                grid[curR][curC] = -1
                for _, dir := range dirs {
                    nextR := curR + dir[0]
                    nextC := curC + dir[1]
                    // Optimize here for efficiency too :-) 
     
