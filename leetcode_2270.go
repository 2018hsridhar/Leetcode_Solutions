/*
URL := https://leetcode.com/problems/number-of-ways-to-split-array/description/
2270. Number of Ways to Split Array

*/
func waysToSplitArray(nums []int) int {
    numWays := 0
    sum := getSum(nums)
    leftSum := 0
    rightSum := sum
    n := len(nums)
    for lPtr := 0; lPtr < n - 1; lPtr++ {
        leftSum += nums[lPtr]
        rightSum -= nums[lPtr]
        if leftSum >= rightSum {
            numWays++
        }
    }
    return numWays
}

func getSum(nums []int) int {
    sum := 0
    for _, el := range nums {
        sum += el
    }
    return sum
}
