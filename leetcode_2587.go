/*
URL := https://leetcode.com/problems/rearrange-array-to-maximize-prefix-score/
2587. Rearrange Array to Maximize Prefix Score

Compelxity
Let N := len(nums)
Time = O(N)
Space = O(1) ( EXP & IMP ) 
*/
func maxScore(nums []int) int {
    myMaxScore := 0
    sort.Ints(nums)
    // gaaah GoLang needs you to specify size of a slice in make built in
    nonPos := make([]int,0)
    pos := []int{}
    for _, num := range nums {
        if num <= 0 {
            nonPos = append(nonPos,num)
        } else {
            pos = append(pos,num)
        }
    }
    // gaaah attach the "methods of interface" to an []int to then reverse and then sort that gaaah
    sort.Sort(sort.Reverse(sort.IntSlice(nonPos)))
    sort.Ints(pos)
    myMaxScore += len(pos)
    sum := 0
    for _, el := range pos {
        sum += el
    }
    for _, el := range nonPos {
        sum += el
        if sum > 0 {
            myMaxScore++
        }
    }

    return myMaxScore
}
