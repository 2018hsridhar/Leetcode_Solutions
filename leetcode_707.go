/*
707. Extra Characters in a String
This is a DP problem in the hiding
Can we immediately code up the bottom-up solution? Hmmmm?
URL := https://leetcode.com/problems/extra-characters-in-a-string/

Complexity
Let N := len(s)
Let D := len(D)

Time := O(N^2)
Space := O(D) + O(N) ( EXP ) O(1) ( IMP ) 


leetcode
---^
Time = 15-ish minutes
*/
func minExtraChar(s string, dictionary []string) int {
    dictHash := make(map[string]bool)
    for _, word := range dictionary {
        dictHash[word] = true
    }
    n := len(s)
    memo := make([]int,n)
    for idx, _ := range memo {
        memo[idx] = n
    }
    // this for loop with just the boundary condition is weird to code up gaaah
    i := n-1
    for i >= 0 {
        localMinChars := len(s)
        j := i
        for j < n { // boundary cond testing in BUDP
            substr := s[i:j+1]
            childChars := 0
            if _, ok := dictHash[substr]; !ok {
                childChars = len(substr)
            }
            if(j+1 < n) {
                childChars += memo[j+1]
            }
            localMinChars = min(localMinChars, childChars)
            j++
        }
        memo[i] = localMinChars
        i--
    }
    fmt.Printf("%v\n", memo)
    return memo[0]
}

func min(a int, b int) int {
    if(a < b) {
        return a
    }
    return b
}


