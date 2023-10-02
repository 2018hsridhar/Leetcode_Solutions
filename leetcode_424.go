/*
Sliding window with a queue
But also need to keep track of the preceding element that was not equal to the current character

424. Longest Repeating Character Replacement
URL = https://leetcode.com/problems/longest-repeating-character-replacement/

20 mins to solution
*/
func characterReplacement(s string, k int) int {
    longestStr := 0
    for letter := 'A'; letter <= 'Z'; letter++ {
        longestStr = max(longestStr, fillToLeft(letter,s,k))
        longestStr = max(longestStr, fillToRight(letter,s,k))
    }
    return longestStr
}

func fillToLeft(letter rune, s string, k int) int {
    longestStr := 0
    queue := []int{}
    lastNonLetterIndex := -1
    ptr := 0
    n := len(s)
    for ptr < n{
        curLet := (rune)(s[ptr])
        if(curLet != letter) {
            if(len(queue) < k) {
                queue = append(queue, ptr)
            } else if ( len(queue) == k) {
                queue = append(queue, ptr)
                firstEl := queue[0]
                queue = queue[1:]
                lastNonLetterIndex = firstEl 
            }
        } else if ( curLet == letter ) {
            if(lastNonLetterIndex == -1) {
                window := (ptr - 0 + 1)
                longestStr = max(longestStr, window)
            } else {
                window := (ptr - lastNonLetterIndex)
                longestStr = max(longestStr, window)
            }
        }
        ptr++
    }
    if(lastNonLetterIndex == -1) {
        window := (ptr - 0)
        longestStr = max(longestStr, window)
    } else {
        window := (ptr - lastNonLetterIndex - 1)
        longestStr = max(longestStr, window)
    }
    return longestStr
}

// Check reverse direction too
func fillToRight(letter rune, s string, k int) int {
    longestStr := 0
    queue := []int{}
    lastNonLetterIndex := -1
    n := len(s)
    ptr := n - 1
    for ptr >= 0 {
        curLet := (rune)(s[ptr])
        if(curLet != letter) {
            if(len(queue) < k) {
                queue = append(queue, ptr)
            } else if ( len(queue) == k) {
                queue = append(queue, ptr)
                firstEl := queue[0]
                queue = queue[1:]
                lastNonLetterIndex = firstEl 
            }
        } else if ( curLet == letter ) {
            if(lastNonLetterIndex == -1) {
                window := n - ptr
                longestStr = max(longestStr, window)
            } else {
                window := lastNonLetterIndex - ptr
                longestStr = max(longestStr, window)
            }
        }
        ptr--
    }
    return longestStr
}

func max(a int, b int) int {
    if(a > b) {
        return a
    }
    return b
}
