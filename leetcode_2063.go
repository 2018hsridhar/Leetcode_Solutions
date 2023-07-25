/*
URL := https://leetcode.com/problems/vowels-of-all-substrings/
2063. Vowels of All Substrings

"noosabasboosa"


*/
func countVowels(word string) int64 {
    myVowelCount := int64(0)
    vowelRunLen := int64(0)
    curLen := int64(0)
    for i := len(word) - 1; i >= 0; i-- {
        if isVowel((rune)(word[i])) {
            curLen = (int64)(len(word) - 1 - i + 1)
            vowelRunLen += curLen
        } 
        myVowelCount += vowelRunLen 
    }
    return myVowelCount
}

func isVowel(letter rune) bool {
    vowelMap := make(map[rune]bool)
    vowelMap['a'] = true
    vowelMap['e'] = true
    vowelMap['i'] = true
    vowelMap['o'] = true
    vowelMap['u'] = true
    if _, ok := vowelMap[letter]; ok {
        return true
    }
    return false
}
