/*
URL := https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/
1011. Capacity To Ship Packages Within D Days

Max weightage possible := 500 * 5 * 104 = 2 * 10-3 * 10-4 = 2 * 10-7
log2(20000000) := 25
nlgn time is reasonable for iteration over input n times

Wait days is a given too ( and we must get said number exactly :-( )
Do not change input

Maybe a false partition technique instead?
the max weight is the sum of a given consecutive sequence OR a single element by itself

DP func to return the max Weightage
    - avoid terrible recursive combinations
Dealing with data overflow makes these problems harder.

It works
as DP
but mem alloc issue gaaah
// how to minimize the mem alloc for top down dp

Looks like the others ran into TLE with the DP approach
Wow -> not even DP is alwasy efficiency
We really may have to go with binary searching too

*/
func shipWithinDays(weights []int, days int) int {
    startPos := 0
    // Forgot to put cache here for top down memo gaah
    // ND array technique
    // gaaah aray lengths need constness need slices
    // cache := [len(weights)][days + 1]int{}
    cache:= make([][]int, len(weights))       
    for i:=0;i<len(weights);i++ {
        cache[i] = make([]int, days + 1)
    }
    return numberDaysToShipWithWeight(weights,startPos,days,cache)
}

// Ensure to exhaust number of days too
// We'd return 0, but want least weight capacity
// Weight is never negative one though
// use a larger data type to work with the problem?
// {} := initialize list syntax only
// how to pass nd cache with ease
func numberDaysToShipWithWeight(weights []int, startPos int, days int, cache [][]int) int {
    myBestPartitionFromStartPos := math.MaxInt
    n := len(weights)
    if (startPos >= n) || (days > 1 && startPos >= n) {
        // It may work due to the math Min operation too?
        // partition sizes are bounded as well
        return math.MaxInt
    }
    if cache[startPos][days] > 0 {
        return cache[startPos][days]
    }
    if days == 1 {
        sum := 0
        for i := startPos; i < n; i++ {
            sum += weights[i]
        }
        return sum
    } else if days > 1 {
        myCurrentPartitionSize := 0
        for i := startPos; i < n; i++ {
            myCurrentPartitionSize += weights[i]
            childCase := numberDaysToShipWithWeight(weights,i + 1, days - 1, cache)
            myCurrentBestPartitionForChildCase := max(myCurrentPartitionSize, childCase)
            // fmt.Printf("current part size /= %d \t childCase = %d\n", myCurrentPartitionSize, childCase)
            myBestPartitionFromStartPos = min(myBestPartitionFromStartPos, myCurrentBestPartitionForChildCase)
        }
    }
    cache[startPos][days] = myBestPartitionFromStartPos
    return myBestPartitionFromStartPos
}

// gaah one-off funcs
func min(a, b int) int {
    if a < b {
        return a
    }
    return b
}

func max(a, b int) int {
    if a > b {
        return a
    }
    return b
}







