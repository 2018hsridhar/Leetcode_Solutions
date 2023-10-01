/*
2771. Longest Non-decreasing Subarray From Two Arrays
URL := https://leetcode.com/problems/longest-non-decreasing-subarray-from-two-arrays/


Complexity
Let N := len(nums1) = len(nums2)
Time := O(N) ( DP ) 
Space := O(N) ( IMP TD-memoed ) O(N) ( EXP ) 

Close YET near surprising accurate
wonder what's going on
*/
func maxNonDecreasingLength(nums1 []int, nums2 []int) int {
    maxLength := 1
    ptr := 0
    n := len(nums1)
    // [1] Create our adjacency lists ( seperated this time ) for the DAG :-) 
    adjListOne := make(map[int][]int)
    adjListTwo := make(map[int][]int)
    for ptr < n {
        next := ptr + 1
        if(next < n) {
            if nums1[ptr] <= nums1[next] {
                adjListOne[ptr] = append(adjListOne[ptr], 0)
            }
            if nums1[ptr] <= nums2[next] {
                adjListOne[ptr] = append(adjListOne[ptr], 1)
            }
            if nums2[ptr] <= nums1[next] {
                adjListTwo[ptr] = append(adjListTwo[ptr], 0)
            }
            if nums2[ptr] <= nums2[next] {
                adjListTwo[ptr] = append(adjListTwo[ptr], 1)
            }
        }
        ptr++
    }
    // [2] Solve longest length problem
    // Init to unsovled lengths at least
    memoOne := make([]int, n)
    memoTwo := make([]int, n)
    for idx, _ := range memoOne{
        memoOne[idx] = -1
        memoTwo[idx] = -1
    }
    maxLen := []int{-1}
    for i := n-1; i >= 0; i-- {
       helper(0,i,adjListOne, adjListTwo, memoOne,memoTwo, maxLen)
       helper(1,i,adjListOne, adjListTwo, memoOne,memoTwo, maxLen)
    }
    maxLength = maxLen[0]
    return maxLength
}

func helper(arr int, index int, adjListOne map[int][]int, adjListTwo map[int][]int, memoOne []int, memoTwo []int, maxLen []int) int{
    n := len(memoOne)
    if(index >= n) { // bounds checking just in case
        return 0
    }
    if(arr == 0 && memoOne[index] != -1) {
        return memoOne[index]
    }
    if(arr == 1 && memoTwo[index] != -1) {
        return memoTwo[index]
    }
    curLen := 1 // stand alone at least
    if(arr == 0 ) {
        for _, kid := range adjListOne[index] {
            if(kid == 0) {
                curLen = max(curLen, 1 + helper(0, index + 1, adjListOne, adjListTwo, memoOne,memoTwo, maxLen))
            } else if ( kid == 1) {
                curLen = max(curLen, 1 + helper(1, index + 1, adjListOne, adjListTwo, memoOne,memoTwo, maxLen))
            }
        }
    } else if(arr == 1 ) {
        for _, kid := range adjListTwo[index] {
            if(kid == 0) {
                curLen = max(curLen, 1 + helper(0, index + 1, adjListOne, adjListTwo, memoOne,memoTwo, maxLen))
            } else if ( kid == 1) {
                curLen = max(curLen, 1 + helper(1, index + 1, adjListOne, adjListTwo, memoOne,memoTwo, maxLen))
            }
        }
    }
    if(arr == 0) {
        memoOne[index] = curLen
        maxLen[0] = max(maxLen[0], curLen)
        return memoOne[index]
    } else {
        memoTwo[index] = curLen
        maxLen[0] = max(maxLen[0], curLen)
        return memoTwo[index]
    }
}

func max(a int, b int) int {
    if(a > b) {
        return a
    }
    return b
}
