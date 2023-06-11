/*
URL := https://leetcode.com/problems/minimum-cost-to-move-chips-to-the-same-numChips/
1217. Minimum Cost to Move Chips to The Same numChips

*/
import (
    "math"
)

func minCostToMoveChips(numChips []int) int {
    oddSumCount := 0
    evenSumCount := 0
    // [-1] Calculate the number of chips for each given position too
    // numChips := []int{}
    // for x := 0; x < n; x++ {
    //     numChips = append(numChips, 0)
    // }

    // Your approach is right : just add if even or if odd here ( the test cases )
    // Utilize a hashmap instead of an array
    // Ok we can have a huge number of positions, but should not run into TLE here
    // [0] Store of counts for minChip costs
    leftChipCountMap := make(map[int]int)
    rightChipCountMap := make(map[int]int)
    // [1] March rightwards
    n := len(numChips)
    sort.Ints(numChips) // asc order too
    for i := 0; i < n; i++ {
        if numChips[i] % 2 == 1 { // odds
            oddSumCount += 1
            if _, ok := leftChipCountMap[numChips[i]]; ! ok {
                leftChipCountMap[numChips[i]] = evenSumCount
            }
        } else if numChips[i] % 2 == 0 { // hit an event index ( after an odd hit )
            evenSumCount += 1
            if _, ok := leftChipCountMap[numChips[i]]; ! ok {
                leftChipCountMap[numChips[i]] = oddSumCount
            }
        }
    }
    // [2] March leftwards
    oddSumCount = 0
    evenSumCount = 0
    for i := n-1; i >= 0; i-- {
        if numChips[i] % 2 == 0 { // evens
            evenSumCount += 1
            if _, ok := rightChipCountMap[numChips[i]]; ! ok {
                rightChipCountMap[numChips[i]] = oddSumCount
            }
        } else if numChips[i] % 2 == 1  {
            oddSumCount += 1
            if _, ok := rightChipCountMap[numChips[i]]; ! ok {
                rightChipCountMap[numChips[i]] = evenSumCount
            }
        }
    }
    // [3] Get global min
    myMinCost := math.MaxInt32
    // for i := 0; i < len(numChips); i++ {
    for k, _ := range leftChipCountMap {
        // Paran enclosures : ensure that statements terminate
        myMinCost = (int)(math.Min((float64)(myMinCost),(float64)(leftChipCountMap[k] + rightChipCountMap[k])))
    }
    return myMinCost
}
