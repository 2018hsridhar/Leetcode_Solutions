/*
URL := https://leetcode.com/problems/minimum-index-of-a-valid-split/
2780. Minimum Index of a Valid Split

Strikes as hashmap, 2 pointers in its approach.
N := len(nums)
Time = O(N)
Space = O(N)

Values are all positive here
Keep to linear time
No data overflow issues.

Runs but TLE
Check right slice expansion issues here
Probably slow there
*/
func minimumIndex(nums []int) int {
    minIndex := -1
    leftDoms := []int{}
    rightDoms := []int{}
    freqMapLeft := make(map[int]int)
    freqMapRight := make(map[int]int)
    runLen := 0
    mostFreqEl := -1
    mostFreq := 0
    for _, val := range nums {
        freqMapLeft[val]++
        runLen++
        if freqMapLeft[val] > mostFreq {
            mostFreq = freqMapLeft[val]
            mostFreqEl = val
        }
        if mostFreq*2 > runLen {
            leftDoms = append(leftDoms, mostFreqEl)
        } else {
            leftDoms = append(leftDoms,-1)
        }
    }
    runLen = 0
    mostFreqEl = -1
    mostFreq = 0
    for index, _ := range nums {
        revIndex := len(nums) - 1 - index
        val := nums[revIndex]
        freqMapRight[val]++
        runLen++
        if freqMapRight[val] > mostFreq {
            mostFreq = freqMapRight[val]
            mostFreqEl = val
        }
        if mostFreq*2 > runLen {
            // rightDoms = append([]int{mostFreqEl}, rightDoms ...)
            rightDoms = append(rightDoms, mostFreqEl)
        } else {
            // rightDoms = append([]int{-1}, rightDoms ...)
            rightDoms = append(rightDoms, -1)
        }
    }
    // NO reverse built in : have to go with indexing gaaaah
    for i := 0; i < len(nums) - 1; i++ {
        leftDom := leftDoms[i]
        rightDom := rightDoms[len(nums) - 1 - i - 1]
        if leftDom == rightDom && leftDom != -1 {
            minIndex = i
            break
        }
    }
    return minIndex
}
