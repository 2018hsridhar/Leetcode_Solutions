/*
URL :+ https://leetcode.com/problems/find-the-prefix-common-array-of-two-arrays/
2657. Find the Prefix Common Array of Two Arrays
Permutations engender uniqueness attribute :-) 
    from 1 to n ( and not other numbers too ) 
*/
func findThePrefixCommonArray(A []int, B []int) []int {
    pca := []int{}
    idx := 0
    n := len(A)
    twiceCount := 0
    // Gaaah why a make() built in for containers?
    // With slices ... {} initialization syntax???
    // countEls = make(map[int]int)
    countEls := map[int]int{}
    seenTwice := map[int]bool{}
    for idx < n {
        valOne := A[idx]
        valTwo := B[idx]
        countEls[valOne] += 1
        countEls[valTwo] += 1
        if countEls[valOne] == 2 && !seenTwice[valOne] {
            twiceCount += 1
            seenTwice[valOne] = true
        }
        if countEls[valTwo] == 2 && !seenTwice[valTwo] {
            twiceCount += 1
            seenTwice[valTwo] = true
        }
        // again with the built in : why not a concat operator '+'
        pca = append(pca, twiceCount)
        idx++
    }
    return pca
}
