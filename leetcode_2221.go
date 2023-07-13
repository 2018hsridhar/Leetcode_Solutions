/*
URL := https://leetcode.com/problems/find-triangular-sum-of-an-array/description/
2221. Find Triangular Sum of an Array

T := O(N^2)
S := O(1)
Destructive of original input
Let N := len(nums)

4 mins to solution
*/
func triangularSum(nums []int) int {
    for j := len(nums) - 1; j >= 0; j-- {
        for i := 0; i <= j-1; i++ {
            nums[i] = (nums[i] + nums[i+1]) % 10
        }
    }
    tSum := nums[0]
    return tSum
}
