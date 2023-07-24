/*
URL := https://leetcode.com/problems/sort-vowels-in-a-string/
2785. Sort Vowels in a String

*/
func sortVowels(s string) string {
    newStringBuilder := strings.Builder{}
    vowels := []rune{}
    for _, letter := range s {
        if isVowel(letter) {
            vowels = append(vowels, letter)
        } 
    }
    ptr := 0
    sort.Slice(vowels, func(i,j int) bool {
        return vowels[i] < vowels[j]
    })
    // fmt.Printf("%v\n", vowels)
    for _, letter := range s {
        if isVowel(letter) {
            newStringBuilder.WriteRune(vowels[ptr])
            ptr++        
        } else {
            newStringBuilder.WriteRune(letter)        
        }
    }
    newString := newStringBuilder.String()
    return newString
}

func isVowel(r rune) bool {
    lowerR := unicode.ToLower(r)
    vowelMap := map[rune]bool{
        'a':true,
        'e':true,
        'i':true,
        'o':true,
        'u':true,
    }
    if _, ok := vowelMap[lowerR]; ok {
        return true
    }
    return false
}
