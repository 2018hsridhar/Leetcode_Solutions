/*
URL := https://leetcode.com/problems/max-sum-of-a-pair-with-equal-sum-of-digits/
2342. Max Sum of a Pair With Equal Sum of Digits

Nums need not be unique -> but indices are

5 mins
*/
func maximumSum(nums []int) int {
    maxSum := -1
    digSums := make(map[int][]int)
    for _, el := range nums {
        digSum := getDigitSum(el)
        digSums[digSum] = append(digSums[digSum],el)
    }
    // Fascinating that range over a complex 1d+ dstruct is just 1d only - keys - in Golang
    for key := range digSums {
        digList := digSums[key]
        if len(digList) > 1 {
            sort.Ints(digList)
            n := len(digList)
            maxEl := digList[n-1]
            secondMaxEl := digList[n-2]
            curSum := maxEl + secondMaxEl
            if curSum > maxSum {
                maxSum = curSum
            }
        }
    }
    return maxSum
}

func getDigitSum(x int) int {
    digSum := 0
    for x >= 10 {
        rem := x % 10
        x /= 10
        digSum += rem
    }
    digSum += x
    return digSum
}
