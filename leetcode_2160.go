/*
https://leetcode.com/problems/minimum-sum-of-four-digit-number-after-splitting-digits/description/
2160. Minimum Sum of Four Digit Number After Splitting Digits

*/
import (
    "strconv"
    "fmt"
    // "math"
    "sort"
    "strings"
)

// https://stackoverflow.com/questions/22688651/golang-how-to-sort-string-or-byte
// Need supporting function for sorting a string in GoLang GAAAAH
func sortMyString(w string) string {
    s := strings.Split(w, "") // string -> splice of strings
    sort.Strings(s)
    return strings.Join(s, "") // rejoin from splice of strings, to string
}

// https://golang.cafe/blog/golang-int-to-string-conversion-example.html
// https://stackoverflow.com/questions/15018545/how-to-index-characters-in-a-golang-string
func minimumSum(num int) int {
    myMinSum := 0
    initStrConv := strconv.Itoa(num)
    strConv := sortMyString(initStrConv)
    arrLen := len(strConv)
    for i := 0; i < arrLen / 2; i++ {
        // fmt.Printf("%d\t%d\n", i, arrLen - 1 - i)
        // numToAddStr := fmt.Sprintf("%s%s", strConv[i], strConv[arrLen - 1 - i])
        numToAddStr := (string)(strConv[i]) + (string)(strConv[arrLen - 1 - i])
        fmt.Printf("%s\n", numToAddStr)
        numToAdd, _ := strconv.Atoi(numToAddStr)
        myMinSum += numToAdd
    }
    // fmt.Printf("%v\n", strConv)
    /*
    for i := 1; i <= 3; i++ {
        // No direct casting :-( from string -> int
        // ATOI : Ascii TO Integer
        sliceOne, _ := strconv.Atoi(strConv[0:i])
        sliceTwo, _ := strconv.Atoi(strConv[i:])
        fmt.Println(sliceOne,sliceTwo)
        curSum := sliceOne + sliceTwo
        fmt.Println(curSum)
        if curSum < myMinSum {
            myMinSum = curSum
        }
    }
    */

    return myMinSum
}
