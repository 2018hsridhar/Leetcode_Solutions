/*
2593. Find Score of an Array After Marking All Elements

URL := https://leetcode.com/problems/find-score-of-an-array-after-marking-all-elements/
Need a visited set
Don't go DFS-ing it
Copy the input deeply to another array
Store index of each value too adjList representation idea. Seems tenable
    -> iterate over indices as we go anyways
    -> iterate over sortedKeys and check if in a visited set of not
O(M) Space, O(N) time processing
https://aguidehub.com/blog/how-to-get-all-the-sortedKeys-from-map-in-golang/?expand_article=1

GAAAH range look for sortedKeys. Jeezus heck
20 mins
correct BUT ineff in time
- > need more efficient solution
*/
func findScore(nums []int) int64 {
    minScore := int64(0)
    adjList := make(map[int][]int)
    for idx, el := range nums {
        adjList[el] = append(adjList[el], idx)
    }
    visited := make(map[int]bool)

    sortedKeys := make([]int,len(adjList))
    ptr := 0
    for key := range adjList {
        sortedKeys[ptr] = key
        ptr++
    }
    sort.Ints(sortedKeys)
    n := len(nums)
    for _, key := range sortedKeys {
        for _, pos := range adjList[key] {
            midPos := pos
            if !visited[midPos] {
                visited[midPos] = true
                minScore += (int64)(key)
                lowPos := midPos - 1
                highPos := midPos + 1
                if 0 <= lowPos && lowPos < n {
                    visited[lowPos] = true
                }
                if 0 <= highPos && highPos < n {
                    visited[highPos] = true
                }
            }
        }
    }
    return minScore
}
