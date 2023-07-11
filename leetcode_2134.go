/*
URL := https://leetcode.com/problems/minimum-swaps-to-group-all-1s-together-ii/
2134. Minimum Swaps to Group All 1's Together II

Sliding window
Count number of zeros in a given range ( for the number of ones needed ) 

My Cases
(A) [1]
(B) [0]
(C) [0,0,0]
(D) [0,0,0,1,1,1,0]
(E) [1,1,1]
(F) [1,1,1,0,1,1,0,0]
(G) [1,0,1,0,1,0]
(H)

10 mins to solution
easier LC problem GAAAH
*/
func minSwaps(nums []int) int {
    countOnes := 0
    for _, el := range nums {
        if el == 1 {
            countOnes++
        }
    }
    // worst case : swap all zeroes to all ones
    // Gaaah initializing a sliding window seems hard
    minSwapsNeeded := countOnes
    lPtr := 0
    rPtr := 0
    n := len(nums)
    curNumberZerosInWind := 0
    for rPtr < countOnes {
        if nums[rPtr] == 0 {
            curNumberZerosInWind++
        }
        rPtr++
    }
    rPtr-- 
    // First window seen
    minSwapsNeeded = min(minSwapsNeeded, curNumberZerosInWind)
    // only rPtr will modulo back to itself
    // restore back here
    for lPtr < n {
        minSwapsNeeded = min(minSwapsNeeded, curNumberZerosInWind)
        rPtr = (rPtr + 1) % n
        nextEl := nums[rPtr]
        if nextEl == 0 {
            curNumberZerosInWind++
        }
        remEl := nums[lPtr]
        if remEl == 0 {
            curNumberZerosInWind--
        }
        lPtr++
    }
    return minSwapsNeeded
}

func min(a int, b int) int {
    if a < b {
        return a
    }
    return b
}
