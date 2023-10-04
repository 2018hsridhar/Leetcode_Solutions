/*
URL := https://leetcode.com/problems/minimum-suffix-flips/
1529. Minimum Suffix Flips

Complexity
Let N := len(target)
Time := O(N)
Space := O(1) ( EXP & IMP ) 

Rune comparison for range syntax in GoLang wooooh

*/
func minFlips(target string) int {
    minFlipCount := 0
    flipLetter := (rune)('0')
    for _, letter := range target {
        if(letter != flipLetter) {
            minFlipCount++
            if(flipLetter == '0') {
                flipLetter = '1'
            } else if ( flipLetter == '1') {
                flipLetter = '0'
            }
        }
    }
    return minFlipCount
}
