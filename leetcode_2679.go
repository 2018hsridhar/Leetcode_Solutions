/*
URL := https://leetcode.com/problems/sum-in-a-matrix/description/
2679. Sum in a Matrix

*/
func matrixSum(nums [][]int) int {
    myMatrixSum := 0
    n := len(nums[0])
    m := len(nums)
    for r := 0; r < m; r++ {
        sort.Ints(nums[r])
    }
    // Sorted in ASC order smallest -> largest, colwise
    // colwise, go largest -> smallest here
    for c := n-1; c >= 0; c-- {
        curColMax := -1
        for r := 0; r < m; r++ {
            // if-else cond beat the function call memory wise wow
            if(curColMax < nums[r][c]) {
                curColMax = nums[r][c]
            }
        }
        myMatrixSum += curColMax
    }
    return myMatrixSum
}
