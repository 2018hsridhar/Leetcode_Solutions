/*
URL = https://leetcode.com/problems/how-many-numbers-are-smaller-than-the-current-number/
1365. How Many Numbers Are Smaller Than the Current Number

*/

// Go hates unused imports
import (
    "fmt"
    // "strconv"
    // "math"
    // "reflect"
)

// Default return `nil` unless we actuall have a value to return 
func smallerNumbersThanCurrent(nums []int) []int {
    // var myArr = [len(nums)]{}
    // Arrays : Fixed AS Slices : Dynamic
    // myArr := []int{}
    myArr := make([]int, len(nums), len(nums))
    for idx,_ := range myArr {
        myArr[idx] = countNumSmallerThan(nums[idx], idx, nums)
    }
    // fmt.Printf("Slice capacity = %d\n", cap(myArr)) // Printf is variadic : println is not
    return myArr
}

func countNumSmallerThan(target int, targetIdx int, nums[] int) int {
    var mySmallerCount = 0
    for idx, _ := range nums {
        if nums[idx] < target && idx != targetIdx {
            mySmallerCount++
        }
    }
    return mySmallerCount
}
