/*
2614. Prime In Diagonal
URL := https://leetcode.com/problems/prime-in-diagonal/
*/
func diagonalPrime(nums [][]int) int {
    myLargestPrime := 0
    for i := 0; i < len(nums); i++ {
        el := nums[i][i]
        if numberIsPrime(el) && el > myLargestPrime {
            myLargestPrime = el
        }
        elTwo := nums[i][len(nums) - i - 1]
        if numberIsPrime(elTwo) && elTwo > myLargestPrime {
            myLargestPrime = elTwo
        }
    }
    return myLargestPrime
}

func numberIsPrime(num int) bool {
    if num == 1 {
        return false
    }
    status := true
    sqrt := (int)(math.Floor(math.Sqrt((float64)(num))))
    for i := 2; i <= sqrt; i++ {
        if num % i == 0 {
            status = false
            break
        }
    }
    return status
}
