/*
URL = https://leetcode.com/problems/queries-on-a-permutation-with-key/
1409. Queries on a Permutation With Key

*/
func searchForElementInSlice(mySlice []int, el int) int {
    var idxOfEl = -1
    for idx, val := range mySlice {
        if val == el {
            idxOfEl = idx
            break // allowed only in loops or switch-case statements
        }
    }
    return idxOfEl
}

func processQueries(queries []int, m int) []int {
    queryPos := []int{}
    // No sorted property
    // If data underneath is a list -> removal is fast and prepend operation is fast too
    // Golang slcie deletion consumes O(len(slice)) time
    // Can we just create two new slices instead -> seems faster too?
    perms := []int{}
    for i := 1; i <= m; i++ {
        perms = append(perms,i)
    }
    for _, queryEl := range queries {
        // fmt.Printf("Query el = %d\n", queryEl)
        elIdx := searchForElementInSlice(perms, queryEl)
        // fmt.Printf("el idx = %d\n", elIdx)
        // Slice expressions ( slice of a slice ) 
        queryPos = append(queryPos, elIdx)
        var sliceOne = perms[0:elIdx]
        var sliceTwo = perms[elIdx + 1:len(perms)]
        // Gaaah slice expension for sliceTwo here
        // first arg slice only in append(...) method
        perms = []int{}
        perms = append(perms, queryEl)
        perms = append(perms,sliceOne...)
        perms = append(perms, sliceTwo...)
        // fmt.Printf("%v", perms)
    }
    return queryPos
}
