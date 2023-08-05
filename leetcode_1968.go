/*
URL := https://leetcode.com/problems/array-with-elements-not-equal-to-average-of-neighbors/
1968. Array With Elements Not Equal to Average of Neighbors

Cases
(A) [1,2,3,4] => PASS
(B) [0,5,2,6] => PASS
(C) [1,2,3,4,5,6,7,8] => PASS
(D)[0,5,2,6,7,3,1] => PASS
(E)

*/
func rearrangeArray(nums []int) []int {
    sort.Ints(nums)
    for i := 0; i < len(nums); i += 2 {
        if i + 1 < len(nums) {
            swap(&nums,i,i+1)
        } else {
            break
        }
    }
    return nums
}

func swap(nums *[]int, i int, j int) {
    temp := (*nums)[j]
    (*nums)[j] = (*nums)[i]
    (*nums)[i] = temp
}
