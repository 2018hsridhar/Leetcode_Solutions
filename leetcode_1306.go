/*
1306. Jump Game III
URL := https://leetcode.com/problems/jump-game-iii/
Reach any index with value = 0 ( this can be duplicated ) 
Input is all non negative values.
Think DFS/BFS -> hey, some indices already visited ( do not go visit ) ; otherwise, go visit
    If you hit a 0 : return true : we done here
    if everybody visited -> return a falsehood
    Hey only two children -> may have overlap -> avoid via caching strategy.
    Initial parent always within bounds anyways.
Wow 8 mins coding time here!
*/
func canReach(arr []int, start int) bool {
    visited := make(map[int]bool)
    canReachStatus := canReachHelper(arr,start,visited)
    return canReachStatus
}

// At least GoLang introduces good re-assignment syntax
func canReachHelper(arr []int, parentPos int, visited map[int]bool) bool {
    canReachStatus := false
    if visited[parentPos] {
        return false
    } else {
        if arr[parentPos] == 0 {
            return true
        } else {
            visited[parentPos] = true
            leftChildPos := parentPos - arr[parentPos]
            rightChildPos := parentPos + arr[parentPos]
            if isInBounds(arr, leftChildPos) { 
                canReachStatus = canReachHelper(arr,leftChildPos,visited)
                if canReachStatus {
                    return true
                }
            }
            // I like GoLangs `have` and `want` syntax here for func call arg verification
            if isInBounds(arr, rightChildPos) {
                canReachStatus = canReachHelper(arr,rightChildPos,visited)
                if canReachStatus {
                    return true
                }
            }
        }
    }
    return canReachStatus
}

func isInBounds(arr []int, pos int) bool {
    return (0 <= pos && pos < len(arr))
}
