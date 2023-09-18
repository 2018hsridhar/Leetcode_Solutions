/*
URL := https://leetcode.com/problems/find-the-substring-with-maximum-cost/
2606. Find the Substring With Maximum Cost

Rederive Kadane's Algorithm

Let N := len(s)
Time = O(N)
Space = O(1) ( EXP + IMP ) 

*/
func maximumCostSubstring(s string, chars string, vals []int) int {
    maxCost := 0
    runSum := 0
    // Build hashmap from vals string
    charVal := make(map[rune]int)
    for idx, c := range chars {
        charVal[c] = vals[idx]
    }
    for _, curChar := range s {
        curVal := (int)(curChar - 'a') + 1
        if mapVal, ok := charVal[(rune)(curChar)]; ok {
            curVal = mapVal
        }
        if (runSum <= 0) {
            // Reset to the current value
            runSum = curVal
        } else {
            // or add the new value possible
            runSum += curVal
        }
        maxCost = max(maxCost, runSum)
    }
    maxCost = max(maxCost, runSum)
    return maxCost
}

func max(a int, b int) int {
    if(a >= b) {
        return a
    }
    return b
}
