/*
583. Delete Operation for Two Strings
URL := https://leetcode.com/problems/delete-operation-for-two-strings/description/

Highly akin to the minimum ASCII deletion problem

Complexity
Let M := len(word1)
Let N := len(word2)

Time := O(MN)
Space := O(MN) ( EXP ) O(1) ( IMP ) ( bottom-up DP ) 

BUDP harder for string problems too :-) 

14 minutes

*/
func minDistance(word1 string, word2 string) int {
    m := len(word1)
    n := len(word2)
    memo := make([][]int, m+1)
    for rowIdx := range memo {
        memo[rowIdx] = make([]int,n+1)
    }
    for _, rowArr := range memo {
        for idx, _ := range rowArr {
            rowArr[idx] = 0 // is the zero-ing out correct??? check again
        }
    }
    // So the boundary should all be at least positive
    // Everything else zeroed out then?
    // [1] Fill case : m, and still indices in word2
    for j := n-1; j >= 0; j-- {
        memo[m][j] = 1 + memo[m][j+1]
    }
    // [2] Fill case : n, and still indices in word1
    for i := m-1; i >= 0; i-- {
        memo[i][n] = 1 + memo[i+1][n]
    }
    // [3] Case : i,j < m,n respectively
    for i := m-1; i >= 0; i-- {
        for j := n-1; j >= 0; j-- {
            letOne := word1[i]
            letTwo := word2[j]
            childOne := 1 + memo[i+1][j]
            childTwo := 1 + memo[i][j+1]
            childThreeNotEqual := 2 + memo[i+1][j+1]
            childThreeEqual := memo[i+1][j+1]
            if letOne != letTwo {
                memo[i][j] = min(min(childOne,childTwo),childThreeNotEqual)
            } else if letOne == letTwo {
                memo[i][j] = min(min(childOne,childTwo),childThreeEqual)
            }
        }
    }
    minSteps := memo[0][0]
    return minSteps
}

func min(a, b int) int {
    if(a < b) {
        return a
    }
    return b
}

func max(a, b int) int {
    if(a > b ) {
        return a
    }
    return b
}
