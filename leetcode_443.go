/*
URL := https://leetcode.com/problems/string-compression/
443. String Compression

O(1) Space
-> We want compression algos to run in low-memory settings :-) 
-> Inplace modif of parameters conserves on stack space and registers taken up too.

*/
func compress(chars []byte) int {
    charsWritePtr := 0
    newArrLen := 0
    lPtr := 0
    rPtr := 0
    curLetter := chars[lPtr]
    for rPtr < len(chars) {
        if chars[rPtr] == curLetter {
            rPtr++
        } else if chars[rPtr] != curLetter {
            // Update the curletter : it has changed now
            // capture the window here
            windowSize := (rPtr - lPtr)
            if windowSize == 1 {
                chars[charsWritePtr] = curLetter
                charsWritePtr++
                newArrLen++
            } else if windowSize > 1 {
                windowSizeStr := strconv.Itoa(windowSize)
                chars[charsWritePtr] = curLetter
                charsWritePtr++
                for _, digit := range windowSizeStr {
                    chars[charsWritePtr] = (byte)(digit)
                    charsWritePtr++
                }
                newArrLen += (1 + len(windowSizeStr))
            }
            lPtr = rPtr
            curLetter = chars[rPtr]
            rPtr++
        }
    }
    // Why do I code up a sliding window algo like this always gaah!
    // Check window size @ the end too
    // Last window anyways too
    // Cur letter was reset as well
    // this was the last window too
    lastWindowSize := rPtr - lPtr
    // fmt.Printf("last window Size = %d\n", lastWindowSize)
    if lastWindowSize == 1 {
        chars[charsWritePtr] = curLetter
        charsWritePtr++
        newArrLen++
    } else if lastWindowSize > 1 {
        lastWindowSizeStr := strconv.Itoa(lastWindowSize)
        chars[charsWritePtr] = curLetter
        charsWritePtr++
        for _, digit := range lastWindowSizeStr {
            chars[charsWritePtr] = (byte)(digit)
            charsWritePtr++
        }
        newArrLen += (1 + len(lastWindowSizeStr))
    }
    return newArrLen
}
