/*
URL := https://leetcode.com/problems/minimum-insertions-to-balance-a-parentheses-string/
1541. Minimum Insertions to Balance a Parentheses String

Variation of popular stack-based problems
Case
)()
len(rcs) = 2
len(lcs) = 1
Careful here!!

Rune or helper struct instead?
    Nah just code up 1s and 2s ; just like Enums :-) 
    With the 1 case -> check if left exist : if so, evict -> else, preserve

Splices : no pop and push as much . woooh!

Complexity
N := len(s)
Time := O(N)
Space := O(1) ( EXP & IMP ) 

Can we just do counters ( no stack ops )?? Possible?
*/
func minInsertions(s string) int {
    minOps := 0
    leftCount := 0
    ptr := 0
    n := len(s)
    for ptr < n {
        ch := s[ptr]
        if ch == '(' {
            leftCount++
        } else if ch == ')' {
            // We enforce guarantee of always adding the '))' after the '(' at least
            // Treat as a unit : can do eviction here :-)
            if(ptr + 1 < n && s[ptr + 1] == ')') {
                if leftCount >= 1 {
                    leftCount--
                } else {
                    minOps += 1 // must add left here to match such case
                }
                ptr++
            } else { // case of a single ')' instead 
                if leftCount >= 1 {
                    leftCount--
                    minOps += 1
                } else {
                    minOps += 2 // must add left and right 'de facto'
                }
            }
        }
        ptr++
    }
    // The entries which failed to have a match, as left parantheticals
    minOps += leftCount * 2
    return minOps
}
