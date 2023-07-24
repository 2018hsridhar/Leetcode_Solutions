/*
URL := https://leetcode.com/problems/minimum-consecutive-cards-to-pick-up/
2260. Minimum Consecutive Cards to Pick Up
Be greedy too

*/
func minimumCardPickup(cards []int) int {
    minDist := math.MaxInt32
    hitMatch := false
    hitIndexMap := map[int]int{}
    for idx, val := range cards {
        if _, ok := hitIndexMap[val]; !ok {
            hitIndexMap[val] = idx
        } else {
            curDist := idx - hitIndexMap[val] + 1
            if curDist < minDist {
                minDist = curDist
            }
            hitIndexMap[val] = idx
            hitMatch = true
        }
    }
    if !hitMatch {
        return -1
    }
    return minDist
}
