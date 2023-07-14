/*
Seems like a reverse binary search problem
Ranges are known ahead of time too

URL := https://leetcode.com/problems/maximum-distance-between-a-pair-of-values/
1855. Maximum Distance Between a Pair of Values

How right -> of a greater value -> can I go here?

M := len(nums1)
N := len(nums2)
Time := O(Mlgn)
Space := O(1) ( EXP & IMP ) 

15 minutes
*/
func maxDistance(nums1 []int, nums2 []int) int {
    myMaxDist := 0
    // don't forgot range keyword in loops
    for idx, el := range nums1 {
        maximalIndexInNums2 := getMaximalIndex(idx, el, nums2)
        // we have a value here that can work
        if maximalIndexInNums2 != -1 {
            myCurDiff := maximalIndexInNums2 - idx
            if myCurDiff > myMaxDist {
                myMaxDist = myCurDiff
            }
        }
    }
    return myMaxDist
}

func getMaximalIndex(idx int, el int, nums2[] int) int {
    myMaxIdx := -1
    lower := idx
    upper := len(nums2) - 1
    for lower <= upper {
        mid := (int)((lower + upper)/2)
        val := nums2[mid]
        if val < el {
            upper = mid - 1
        } else if val >= el {
            myMaxIdx = mid
            lower = mid + 1
        }
    }
    return myMaxIdx
}
