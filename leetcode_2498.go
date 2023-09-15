/*
URL := https://leetcode.com/problems/frog-jump-ii/
2498. Frog Jump II

Complexity
Let N := len(stones)
Time = O(N)
Space = O(1) ( EXP & IMP ) 

Aproach : two pointers, greedy, array
At lesat two stones

Test Cases
(A)
(B)
(C)
(D)
(E)
(F)

9:30 = time ( outside of other day :-) ) 

*/
func maxJump(stones []int) int {
    n := len(stones)
    lastEl := stones[n-1]
    minCost := 0
    if(len(stones) <= 2) {
        return stones[1]
    }
    seqOneJump := stones[1]
    seqTwoJump := stones[2]
    minCost = max(minCost, seqOneJump)
    minCost = max(minCost, seqTwoJump)
    ptr := 3
    for ptr < n {
        curEl := stones[ptr]
        deltaOne := curEl - seqOneJump
        deltaTwo := curEl - seqTwoJump
        if deltaOne > deltaTwo {
            seqOneJump = curEl
            minCost = max(minCost, deltaOne)
        } else {
            seqTwoJump = curEl
            minCost = max(minCost, deltaTwo)
        }
        ptr++
    }
    if (seqOneJump == lastEl) {
        minCost = max(minCost, lastEl - seqTwoJump)
    } else if (seqTwoJump == lastEl) {
        minCost = max(minCost, lastEl - seqOneJump)
    }
    return minCost
}

func max(a int, b int) int{
    if (a > b ) {
        return a
    }
    return b
}
