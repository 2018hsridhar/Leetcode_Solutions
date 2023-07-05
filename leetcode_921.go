/*
921. Minimum Add to Make Parentheses Valid
URL := https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/description/

Test Cases :
(A) "("
(B) ")"
(C) "((()))"
(D) "((("
(E) ")))"
(F) ")))((()))((("
(G) ")))()(())(())))((()((()))((("

*/
func minAddToMakeValid(s string) int {
    minToAdd := 0
    // It is of tyep rune for range string in GoLang
    // Is a stack even needed ( versus just counters here ) ?
    // stack := []rune{}
    leftParenCount := 0
    extraRightCount := 0
    for _, ch := range s {
        if ch == '(' {
            leftParenCount++
        } else if ch == ')' {
            if leftParenCount > 0 {
                leftParenCount--
            } else {
                extraRightCount++
            }
        }
    }
    minToAdd = leftParenCount + extraRightCount
    return minToAdd
}
