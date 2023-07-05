/*
URL := https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/description/
1249. Minimum Remove to Make Valid Parentheses
Most compilers use a stack underneath for handling of parsing.
VPS := Valid Parantheses String
Omnipresent in many compiler-based settings in computer science.
Easy idea : akin to earlier problem -> leverage leftParenCount and rightParenCounts.
But ... take note of the indices we can not exec a deletion from, and store them
Exec sort on these indices later and start consecutive handling ( save for the last index : to end of string length )

*/
func minRemoveToMakeValid(s string) string {
    leftParan := '('
    rightParan := ')'
    leftIndices := getExtraLeftIndices(s,leftParan)
    rightIndices := getExtraRightIndices(s,rightParan)
    myIndices := []int{}
    myIndices = append(myIndices, leftIndices ...)
    myIndices = append(myIndices, rightIndices ...)
    sort.Ints(myIndices)
    if len(myIndices) == 0 {
        return s
    }
    // fmt.Printf("%v\n", myIndices)
    builder := strings.Builder{}
    validString := ""
    // s[a:b] : a inclusive b exclusive pattern
    leftIndex := 0
    for idx := 0; idx < len(myIndices); idx++ {
        rightIndex := myIndices[idx]
        subStr := s[leftIndex:rightIndex]
        builder.WriteString(subStr)
        leftIndex = rightIndex + 1
    }
    // now at the final index here
    lastIdx := myIndices[len(myIndices) - 1]
    lastSubStr := s[lastIdx+1:]
    builder.WriteString(lastSubStr)
    // gaaah not toString()
    validString = builder.String()
    return validString
}

// Modularize for different delimeters
// but dir differs gaaaah
func getExtraLeftIndices(s string, c rune) []int {
    indices := []int{}
    i := len(s) - 1
    rightCount := 0
    for i >= 0 {
        if s[i] == ')' {
            rightCount++
        } else if s[i] == '(' {
            if rightCount > 0 {
                rightCount--
            } else {
                indices = append(indices, i)
            }
        }
        i--
    }
    return indices
}   

func getExtraRightIndices(s string, c rune) []int {
    indices := []int{}
    i := 0
    leftCount := 0
    for i < len(s) {
        if s[i] == '(' {
            leftCount++
        } else if s[i] == ')' {
            if leftCount > 0 {
                leftCount--
            } else {
                indices = append(indices, i)
            }
        }
        i++
    }
    return indices
}   
