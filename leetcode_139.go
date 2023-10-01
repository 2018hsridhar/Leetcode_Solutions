/*
139. Word Break
URL := https://leetcode.com/problems/word-break/

Idea : Leverage hashmaps and recursive checking
Can we memoize solutions yet again :-) . Seems like a thing too
How to avoid repeat work

Complexity
Let S := len(S)
Let D := #-words in dict, let W := length longest word in dict
Time := O(S^2) 
Space := O(D) (EXP) O(1) (IMP : BUDP )

45:58 = 13 minutes :-) 
*/

// Get candidates to break code modularly to hide implementation details
func wordBreak(s string, wordDict []string) bool {
    wordMap := make(map[string]bool)
    constructWordMap(wordDict,wordMap)
    return helper(s,wordMap)
}

// Set as own function -> toggle implementation details with ease
// Interviewers desire to see this.
func constructWordMap(wordDict []string, wordMap map[string]bool){
    for _, word := range wordDict {
        wordMap[word] = true
    }
}

func helper(s string, wordMap map[string]bool) bool {
    n := len(s)
    // https://www.includehelp.com/golang/what-is-the-default-value-of-a-bool-variable-in-go-language.aspx :: all false
    memo := make([]bool,n)
    // Gotta initialize local problem status each time too :-) 
    for i := n-1; i >= 0; i-- {
        localProblemStatus := false
        for j := i; j < n; j++ {
            subStr := s[i:j+1]
            if _, ok := wordMap[subStr]; ok {
                if(j+1 < n && memo[j+1] == true) {
                    localProblemStatus = true
                    break
                } else if ( j+1 == n) {
                    localProblemStatus = true
                    break
                }
            }
        }
        memo[i] = localProblemStatus
    }
    return memo[0]
}
