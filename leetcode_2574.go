/*
2574. Left and Right Sum Differences
https://leetcode.com/problems/left-and-right-sum-differences/
*/

import (
    "fmt"
    "strings"
    "math"
    // "reflect" // Package helps determine types in a type system
)

func leftRightDifference(nums []int) []int {
    // inputLen := len(nums)
    // fmt.Printf("%d\n", inputLen)
    // answer := [inputLen]int{}
    // var answer [10]int
    // fmt.Println(reflect.ValueOf(answer).Kind())
    // return answer
    // https://stackoverflow.com/questions/57441647/dynamically-sized-array-in-golang
    answer := make([]int, 0)
    for i := 0; i < len(nums); i++ {
        leftSum := 0
        rightSum := 0
        for j := 0; j < i; j++ {
            leftSum += nums[j]
        }
        for j := i + 1; j < len(nums); j++ {
            rightSum += nums[j]
        }
        curSum := (int)(math.Abs((float64)(leftSum - rightSum)))
        answer = append(answer, curSum)
    }
    return answer
}
