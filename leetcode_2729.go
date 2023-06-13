/*
2729. Check if The Number is Fascinating
URL := https://leetcode.com/problems/check-if-the-number-is-fascinating/
*/
import (
    "fmt"
)

func isFascinating(n int) bool {
    status := true
    twiceN := 2 * n
    threeN := 3 * n
    digMap := make(map[int]int)
    concatVal,_ := strconv.Atoi(fmt.Sprintf("%s%s%s", strconv.Itoa(n), strconv.Itoa(twiceN), strconv.Itoa(threeN)))
    for concatVal >= 10 {
        var rem = concatVal % 10
        concatVal /= 10
        if rem == 0 {
            status = false
            break
        }
        if _, ok := digMap[rem]; !ok {
            digMap[rem] = 0
        } else {
            status = false
            break
        }
    }
    if concatVal == 0 {
        status = false
    }
    if _, ok := digMap[concatVal]; ok {
        status = false
    }
    return status
}
