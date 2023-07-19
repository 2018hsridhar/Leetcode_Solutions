/*
URL := https://leetcode.com/problems/matrix-block-sum/
1314. Matrix Block Sum

*/
func matrixBlockSum(mat [][]int, k int) [][]int {
    mbs := [][]int{}
    for i := 0; i < len(mat); i++ {
        mbsRow := []int{}
        for j := 0; j < len(mat[0]); j++ {
            mbsRow = append(mbsRow, solveMBS(i,j,k,mat))
        }
        mbs = append(mbs, mbsRow)
        mbsRow = nil
    }
    return mbs
}

/*
https://stackoverflow.com/questions/19979178/what-is-the-idiomatic-go-equivalent-of-cs-ternary-operator
GoLang lacks ternary operator
Emulate via inline function wow
-> lang designers saw to much confusion there
*/
func solveMBS(r, c, k int, mat [][]int) int {
    mbs := 0
    m := len(mat)
    n := len(mat[0])
    for i := r - k; i <= r + k; i++ {
        for j := c - k; j <= c + k; j++ {
            val := func() int { if ((0 <= i && i < m) && (0 <= j && j < n)) { return mat[i][j] } else { return 0 } }()
            mbs += val
        }
    }
    return mbs
}
