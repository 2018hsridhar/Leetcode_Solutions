/*
URL := https://leetcode.com/problems/maximum-number-of-jumps-to-reach-the-last-index/\
2770. Maximum Number of Jumps to Reach the Last Index

N up to 1K max
target and diffs reasonable : no data overflow :-) 

Def a DP problem -> TopDown or Bottom Up style?
Max vs Min to trip up[ readers here

Works but TLE with recursion
Need DP solving here too :-) 

Technically can jump to ANY index
Can't sort on input -> to destructive!

15 mins
*/
func maximumJumps(nums []int, target int) int {
    startPos := 0
    // Map vs array as cache 
    // Careful on languages default init for maps too
    cache := make(map[int]int)
    maxJumps := maximumJumpsHelper(startPos,nums,target,cache)
    return maxJumps
}

// Wait if unreachable -> return -1 too
// Conditional logic used for re-assignment of values?
func maximumJumpsHelper(startPos int, nums[] int, target int, cache map[int]int) int {
    maxJumps := -1
    n := len(nums)
    if startPos == n - 1 {
        cache[startPos] = 0
        return 0
    } else if startPos < n - 1 {
        // 4 mins sublte bug
        // Forgot to return -1 case here !!!
        if _, ok := cache[startPos]; ok {
            return cache[startPos]
        }
        for i := startPos + 1; i < n; i++ {
            // How many nested paranthenticals for explicit type conversions in GoLang!
            diff := (int)(math.Abs((float64)(nums[i] - nums[startPos])))
            if (-1 * target <= diff && diff <= target) {
                childCase := maximumJumpsHelper(i, nums, target,cache)
                if childCase != -1 {
                    curJumps := 1 + childCase
                    if curJumps > maxJumps {
                        maxJumps = curJumps
                    }
                }
            }
        }
    }
    cache[startPos] = maxJumps
    return maxJumps
}
