/*
URL = https://leetcode.com/problems/difference-between-element-sum-and-digit-sum-of-an-array/
2535. Difference Between Element Sum and Digit Sum of an Array
<range> keyword yay :-)
GoLang seems to enforce more package usage too
*/
import (
    "fmt"
    // "strconv" // commented out, yet passing? Implicit import done here in Leetcode or GoLang?
)
// Package names seem lowercase

func differenceOfSum(nums []int) int {
    var diffOfSums = 0
    elemSum := getElementSum(nums)
    digSum := getDigitSum(nums)
    // fmt.Printf("ElSum = %d \t digSum = %d\n", elemSum, digSum)
    diffOfSums = elemSum - digSum
    return diffOfSums
}

func getElementSum(nums []int) (ans int) {
    elemSum := 0
    for _, val := range nums {
        elemSum += val
    }
    return elemSum
}


func getDigitSum(nums []int) (ans int) {
    overallDigSum := 0
    for _, val := range nums {
        localDigSum := 0
        // strVal := (string)(val) // stupid unexpected val at end of statement GAAAH
        strVal := strconv.Itoa(val) // more correct conversion done here
        for _, ch := range strVal {
            // GAAH rune->int conversion
            // https://stackoverflow.com/questions/21322173/convert-rune-to-int
            //  digit, _ := strconv.Atoi(ch)
            // fmt.Printf("%c\n", ch) // %c specifier not working as expected huh?
            digit := int(ch - '0') // func returns forced better syntax I presume
            // fmt.Printf("Type digit = %T\n", digit) // exported funcs upper case
            // type != expression ?
            // localDigSum += digit
            localDigSum = localDigSum + digit
        }
        // fmt.Printf("local sum = %d\n", localDigSum)
        overallDigSum += localDigSum
    }
    return overallDigSum
}
