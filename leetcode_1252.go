Intuition
Leverage the property of addition of integers with even-odd parity :
even + odd = odd
odd + even = odd
even + even = even
odd + odd = odd

Approach
Count number of rows with odd values and even values after applying row-wise increment operations, and store into a map
Execute step 1, but with columns instead, and store into a map
Use both the row map and the col map to find the final number of cells with odd values in them.
Complexity
Time complexity:
O(m+n+indices.length)O(m + n + indices.length )O(m+n+indices.length)

Space complexity:
O(m+n)O(m+n)O(m+n)

Code
/*
URL := https://leetcode.com/problems/cells-with-odd-values-in-a-matrix/
1252. Cells with Odd Values in a Matrix

*/
func oddCells(m int, n int, indices [][]int) int {
    colCountMap := make(map[int]int)
    rowCountMap := make(map[int]int)
    numberOddCells := 0
    // [1] O(indices.length), O(n+m) space alloc
    for _, index := range indices {
        row := index[0]
        col := index[1]
        if _, ok := rowCountMap[row]; !ok {
            rowCountMap[row] = 0
        }
        rowCountMap[row]++
        // Shorthand map check ok status desired
        if _, ok := colCountMap[col]; !ok {
            colCountMap[col] = 0
        }
        colCountMap[col]++
    }
    // [2] O(m) time for each row
    numberEvenRowCounts := 0
    numberOddRowCounts := 0
    for r := 0; r < m; r++ {
        if rowCountMap[r] % 2 == 0 {
            numberEvenRowCounts++
        } else {
            numberOddRowCounts++
        }
    }
    // [3] O(n) time for each col
    for c := 0; c < n; c++ {
        // Even + Odd case
        if colCountMap[c] % 2 == 0 {
            numberOddCells += numberOddRowCounts
        } else {
        // Odd + Even case
            numberOddCells += numberEvenRowCounts
        }
    }
    return numberOddCells
}
