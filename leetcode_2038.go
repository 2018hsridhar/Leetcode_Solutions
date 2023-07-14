/*

2038. Remove Colored Pieces if Both Neighbors are the Same Color

Count of A's and B's for both of them.
Subtract min from both -> one of them will hit 0 naturally
Alice always goes first anyways


(A) aa -> false
(B) bb -> false
(C) aab -> false
(D) aaab -> true
(E)
(F)

11 mins :-)

*/
func winnerOfGame(colors string) bool {
    aliceMidCount := 0
    bobMidCount := 0
    n := len(colors)
    for idx, color := range colors {
        prevIdx := idx - 1
        nextIdx := idx + 1
        if isInBounds(n,prevIdx) && isInBounds(n,nextIdx) {
            prevColor := colors[prevIdx]
            nextColor := colors[nextIdx]
            if color == 'A' {
                if prevColor == 'A' && nextColor == 'A' {
                    aliceMidCount++
                }
            } else if color == 'B' {
                if prevColor == 'B' && nextColor == 'B' {
                    bobMidCount++
                }
            }
        }
    }
    minBoth := getMin(aliceMidCount, bobMidCount)
    fmt.Printf("aCount = %d \t bobCount = %d \t min = %d\n", aliceMidCount, bobMidCount, minBoth)
    aliceMidCount -= minBoth
    bobMidCount -= minBoth
    if aliceMidCount == 0 {
        return false
    }
    if aliceMidCount > 0 && bobMidCount == 0 {
        return true
    }
    return true
}

func getMin(a, b int) int {
    if ( a < b ) {
        return a
    }
    return b
}

func isInBounds(n int, index int) bool {
    return (0 <= index && index < n)

}
