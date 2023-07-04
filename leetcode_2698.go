/*
URL := https://leetcode.com/problems/find-the-punishment-number-of-an-integer/description/
2698. Find the Punishment Number of an Integer
*/
func punishmentNumber(n int) int {
    punNum := 0
    i := 1
    for i <= n {
        if isPunNumber(i) {
            // HOw did I get 37 but not 36. GAAAH
            // fmt.Println(i)
            punNum += i*i
        }
        i++
    }
    return punNum
}

// Would naive recursion really slow me down that much???
// Why not just go naive recursion?
// false : NOT False here
func isPunNumber(i int) bool {
    status := false
    nonSquareVal := i
    squareI := i * i
    squareIString := strconv.Itoa(squareI)
    curSum := 0
    curIdx := 0
    if recurse(squareIString, nonSquareVal, curSum, curIdx) {
        status = true
    }
    return status
}

// first index passed is 0
// get slice of first character until last character ... test each combination
// Low inclusive high exclusive pattern
func recurse(squareStr string, nonSquareVal int, curSum int, curIdx int) bool {
    statusAcrossKids := false
    rIndex := curIdx + 1
    // curIdx > : return false
    if curIdx >= len(squareStr) {
        return false
    }
    for rIndex <= len(squareStr) {
        sliceOne := squareStr[curIdx:rIndex]
        numOne, _ := strconv.Atoi(sliceOne)
        nextCurSum := curSum + numOne // slice first part
        // 1 + 36 case : need to be at end of string here
        if nextCurSum == nonSquareVal && rIndex == len(squareStr) {
            // fmt.Println("ncs = %d \t sv = %d\n", nextCurSum, nonSquareVal)
            return true
        } else {
            childStatus := recurse(squareStr, nonSquareVal, nextCurSum, rIndex)
            // Do not execute the other DFS call paths available.
            if childStatus {
                statusAcrossKids = true
                break     
            }
        }
        rIndex++
    }
    return statusAcrossKids
}
