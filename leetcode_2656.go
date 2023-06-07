/*
2656. Maximum Sum With Exactly K Elements
URL = https://leetcode.com/problems/maximum-sum-with-exactly-k-elements/
Find maximum in an array
max
max + 1
max + (max + 1 ) + (max + 2)
(max+0) + (max + 1 ) + ... + (max + k - 1) [ k operations ] 
(max*k) + (0 + 1 + ... + k-1)
(k * max) + (k*(k+1) / 2)
PANIC on empty inputs
*/
func maximizeSum(nums []int, k int) int {
    var maxEl = getMaxEl(nums)
    var myMaxSum = (maxEl*k) + (k*(k-1) / 2)
    return myMaxSum
}

// WTF no built-in package for getMax/getMin on slices :-( )
// Woah no need for assignment here!
func getMaxEl(v []int) (maxEl int) {
    maxEl = 0
    for i := 0; i < len(v); i++ {
        if v[i] > maxEl {
            maxEl = v[i]
        }
    }
    return maxEl
}
