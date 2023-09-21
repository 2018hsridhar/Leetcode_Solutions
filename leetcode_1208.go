*
Basic two pointer approach to this problem : 
URL := https://leetcode.com/problems/get-equal-substrings-within-budget/
1208. Get Equal Substrings Within Budget

Strs of equal length

*/
func equalSubstring(s string, t string, maxCost int) int {
    maxEqualSubstring := 0
    lPtr := 0
    rPtr := 0
    n := len(s)
    runCost := 0
    for rPtr < n {
        rightCost := absCall((int)(s[rPtr]) - (int)(t[rPtr]))
        runCost += rightCost
        if(runCost <= maxCost){
            currDiff := (rPtr - lPtr + 1)
            maxEqualSubstring = max(maxEqualSubstring, currDiff)
        } else {
            // can we scope break conditions to for loop expr ( versus within ) ?
            // efficiency? possible?
            for runCost > maxCost && lPtr <= rPtr {
                leftCost := absCall((int)(s[lPtr]) - (int)(t[lPtr]))
                runCost -= leftCost
                lPtr++
            }
        }
        rPtr++
    }
    return maxEqualSubstring
}
