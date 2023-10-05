/*
712. Minimum ASCII Delete Sum for Two Strings
URL := https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/

Top-Down Memoization here strikes again
Need the 2d array to memoize it though :-) 

Complexity
Let M, N := len(s1) and len(s2)
Time := O(MN)
Space := O(MN) ( memo ) O(MN) ( recursive call stack ) 

Time spent = 20 minutes
*/
func minimumDeleteSum(s1 string, s2 string) int {
    m := len(s1)
    n := len(s2)
    memo := make([][]int, m+1)
    for i := range memo {
        memo[i] = make([]int, n+1)
    }
    for _, arr := range memo {
        for idx, _ := range arr {
            arr[idx] = -1
        }
    }
    // fmt.Printf("m = %d\n", len(memo))
    // fmt.Printf("n = %d\n", len(memo[0]))
    idxOne := 0
    idxTwo := 0
    minSum := helper(s1,s2,idxOne,idxTwo,memo)
    return minSum
    // return 0
}

func min(a,b int) int {
    if(a < b) {
        return a
    }
    return b
}

/*
Handle base cases properly here as well
ASCII sum -> must convert rune to int as well !
Gaaah empty string handling as well here
Hey go to sleep now
*/
func helper(s1 string,s2 string, idxOne int, idxTwo int, memo [][]int) int {
    localAns := 0
    m := len(s1)
    n := len(s2)
    if idxOne < m && idxTwo < n && memo[idxOne][idxTwo] != -1 {
        return memo[idxOne][idxTwo]
    }
    if(idxOne == m && idxTwo == n) { // final indices both strings cases
        return 0 // empty here
    } else if ( idxOne >= m) { //  single character cases to handle as well
        localAns = (int)(s2[idxTwo]) + helper(s1,s2,idxOne,idxTwo+1,memo)
    } else if ( idxTwo >= n) {
        localAns = (int)(s1[idxOne]) + helper(s1,s2,idxOne+1,idxTwo,memo)
    } else {
        letOne := s1[idxOne]
        letTwo := s2[idxTwo]
        if(letOne == letTwo) {
            localAns = helper(s1,s2,idxOne + 1, idxTwo + 1, memo)
        } else if ( letOne != letTwo) {
            localAnsOne := (int)(s1[idxOne]) + helper(s1,s2,idxOne + 1, idxTwo, memo)
            localAnsTwo := (int)(s2[idxTwo]) + helper(s1,s2,idxOne,idxTwo + 1, memo)
            localAnsThree := (int)(s1[idxOne]) + (int)(s2[idxTwo]) + helper(s1,s2,idxOne + 1, idxTwo + 1, memo)
            localAns = min(min(localAnsOne,localAnsTwo), localAnsThree)
        }
    }
    memo[idxOne][idxTwo] = localAns
    return memo[idxOne][idxTwo]
}




