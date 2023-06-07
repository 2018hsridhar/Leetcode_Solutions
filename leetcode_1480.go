/*
URL = https://leetcode.com/problems/running-sum-of-1d-array/
1480. Running Sum of 1d Array
Array as input, Slice as output
*/
func runningSum(nums []int) []int {
    // Make : slice-map-channel fixed size :-) 
    runSumArr := make([]int, len(nums))
    myRumSum := 0
    for i:=0; i < len(nums); i++ {
        myRumSum += nums[i]
        runSumArr[i] = myRumSum
    }
    return runSumArr
}
