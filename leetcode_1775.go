/*
URL := https://leetcode.com/problems/equal-sum-arrays-with-minimum-number-of-operations/
1775. Equal Sum Arrays With Minimum Number of Operations
The sums may be unequal initially -> iterate fully to test for equality
Even if the lengths differ too
Idea -> go greedy here

Combinatorial -> do not go thru all choices to exhaust here.

Test cases
(A) [6],[6]
(B) [1,2,3],[3,2,1]
(C) [1,2,3],[1,2,3]
(D) [5,2,3,6,4,1,2], [6,4,1,2,4,5,2]


GAAAH 1 hour
slightly harder than usual
*/
func minOperations(nums1 []int, nums2 []int) int {
    var minOps = -1
    sort.Ints(nums1)
    sort.Ints(nums2)
    sumOne := sum(nums1)
    sumTwo := sum(nums2)
    // Base case both the same : no operations -> but no elements had to be changed
    if sumOne == sumTwo {
        return 0
    }
    countOps := 0
    if sumOne <= sumTwo {
        countOps = countOpsHelper(nums1,nums2,sumOne,sumTwo)
    } else {
        countOps = countOpsHelper(nums2,nums1,sumTwo, sumOne)
    }
    if countOps == 0 {
        return -1
    }
    minOps = countOps
    return minOps
}

// aSum <= bSum property holds here
func countOpsHelper(a []int, b []int, sumOne int, sumTwo int) int {
    countOps := 0
    ptrOne := 0
    ptrTwo := len(b) - 1
    for sumOne != sumTwo && ptrOne < len(a) && ptrTwo >= 0 {
        leftEl := a[ptrOne]
        rightEl := b[ptrTwo]
        diffToSix := 6 - leftEl
        diffToOne := rightEl - 1
        if diffToSix > diffToOne {
            sumOne += diffToSix
            ptrOne++
            countOps++
            if sumOne >= sumTwo {
                break
            }
        } else if diffToOne > diffToSix  {
            sumTwo -= diffToOne
            ptrTwo--
            countOps++
            if sumTwo <= sumOne {
                break
            }
        } else if diffToOne == diffToSix {
            if diffToOne == 0 {
                // renders as NOP for both numbers here
                ptrOne++
                ptrTwo--
            } else if diffToOne != 0 {
                if sumTwo <= sumOne {
                    break
                }
                sumTwo -= diffToOne
                ptrOne++
                countOps++
                if sumTwo <= sumOne {
                    break
                }
            }
        }
    }
    for sumOne != sumTwo && ptrOne < len(a) {
        leftEl := a[ptrOne]
        diffToSix := 6 - leftEl
        if sumOne >= sumTwo {
            break
        }
        sumOne += diffToSix
        ptrOne++
        if diffToSix > 0 {
            countOps++
        }
        if sumOne >= sumTwo {
            break
        }
    }
    for sumOne != sumTwo && ptrTwo >= 0 {
        rightEl := b[ptrTwo]
        diffToOne := rightEl - 1  
        if sumTwo <= sumOne {
            break
        }
        sumTwo -= diffToOne
        ptrTwo--
        if diffToOne > 0 {
            countOps++
        }
        if sumTwo <= sumOne {
            break
        }
    }
    if sumTwo > sumOne {
        return 0
    }
    return countOps
}

func sum(nums []int) int {
    sum := 0
    for _, el := range nums {
        sum += el
    }
    return sum
}
