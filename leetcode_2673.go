/*
URL := https://leetcode.com/problems/make-costs-of-paths-equal-in-a-binary-tree/
2673. Make Costs of Paths Equal in a Binary Tree
    Perfect property enables always having 2 kids ( can still set up bound checks though ) 

Perfect Binary Tree -> can we exploit property to avoid weird Divide-and-Conquer steps?
Get the max cost to begin with.

This is def on the spectrum of a slightly harder medium

WOW TLE but passing
Says it is DP here
    -> interesting

Wow get rid of double recursive calling here!!

*/
func minIncrements(n int, cost []int) int {
    minIncrs := 0
    maxes := make([]int, n) // 0 init
    startNode := 1 
    // 2* node val no work on a zero index case gaaah
    computeMaxes(n, startNode, cost, maxes)
    myMaxCost := maxes[0]
    minIncrs = computeMinIncrs(n, startNode, cost, maxes, myMaxCost)
    return minIncrs
}

// Minimize parameter passing here?
func computeMinIncrs(n int, curPos int, costs []int, maxes []int, myMaxCost int) int {
    minIncrs := 0
    leftChild := (curPos * 2)
    rightChild := (curPos * 2) + 1
    nextMaxCost := myMaxCost - costs[curPos-1]
    if maxes[curPos-1] < myMaxCost {
        minIncrs += (myMaxCost - maxes[curPos-1])
        nextMaxCost -= (myMaxCost - maxes[curPos-1])
    }
    if isInBounds(leftChild, n) {
        minIncrs += computeMinIncrs(n, leftChild, costs, maxes, nextMaxCost)
    }
    if isInBounds(rightChild, n) {
        minIncrs += computeMinIncrs(n, rightChild, costs, maxes, nextMaxCost)
    }
    return minIncrs
}

// MOdularization enables quick testing of methods at least
// Fill out the maxes array here
func computeMaxes(n int, curPos int, costs []int, maxes []int) int {
    myMaxSum := costs[curPos-1]
    leftChild := (curPos * 2)
    rightChild := (curPos * 2) + 1 
    if isInBounds(leftChild, n) {
        // Oh gosh insufficient args to call errors
        leftCost := costs[curPos-1] + computeMaxes(n, leftChild,costs,maxes)
        if (leftCost) > myMaxSum {
            myMaxSum = leftCost
        }
    } 
    // Was this a space error? Woah!!
    if isInBounds(rightChild, n ) {
        rightCost := costs[curPos-1] + computeMaxes(n, rightChild,costs,maxes)
        if (rightCost) > myMaxSum {
            myMaxSum = rightCost
        }
    }
    // fmt.Printf("Costs @ curPo= %d is %d\n", curPos, myMaxSum)
    maxes[curPos-1] = myMaxSum
    return myMaxSum
}

func isInBounds(pos int, n int) bool {
    // fmt.Printf("pos = %d \t n = %d\n", pos, n)
    return ( 1 <= pos && pos <= n )
}
