/*
URL := https://leetcode.com/problems/the-k-th-lexicographical-string-of-all-happy-strings-of-length-n/
1415. The k-th Lexicographical String of All Happy Strings of Length n

Set {'a','b','c'} guarantee at least.
Incorporate binary 01 thinking here.

Cases
(A) n = 1 k = 1
(B) n = 5 k = 17
(C) n = 5 k = 31
(D) n = 5 k = 4
(E) n = 8 k = 54
(F) n = 10 k = 99

*/
func getHappyString(n int, k int) string {
    // Literals for shorthand syntax initialization :-)
    // Literals reduce code boilerplate and #-insts to execute
    lowerRuneMap := map[rune]rune{
        'a':'b',
        'b':'a',
        'c':'a',
    }
    // gaaah unexpected newline errors
    upperRuneMap := map[rune]rune{
        'a':'c',
        'b':'c',
        'c':'b',
    }
    // %v specifier : prints ASCII code based
    // fmt.Printf("%v\n", lowerRuneMap)
    // fmt.Printf("%v\n", upperRuneMap)
    myCharSet := []rune{'a','b','c'}
    myCharSetPtr := 0
    numberCombosPerLetter := (int)(math.Pow((float64)(2),(float64)(n-1)))
    // fmt.Printf("Number of combos = %d\n", numberCombosPerLetter)
    // k itself is NEVER a zero value ( take note of this ) 
    for k > 0 {
         nextK := k - numberCombosPerLetter
         if nextK > 0 {
             k = nextK
             myCharSetPtr++
         } else {
             break
         }
    }
    // For offsetting
    k -= 1 
    if myCharSetPtr >= len(myCharSet){
        // fmt.Println("HERE")
        return ""
    }
    // string builder perf critical path
    var prefix strings.Builder
    firstLetter := myCharSet[myCharSetPtr]
    curLetter := firstLetter
    prefix.WriteRune(firstLetter)
    // Need to get to next letter ( not just first letter ) 
    // fmt.Printf("offsetted(k) = %d\n", k)
    // two string builders idea here?? hmmm?
    // Reverse the directionality?
    var suffix strings.Builder
    var bString strings.Builder
    for k > 0 {
        rem := k % 2
        if rem == 0 {
            bString.WriteRune('0')
        } else if rem == 1 {
            bString.WriteRune('1')
        }
        k /= 2
    }
    // GAAAh golang lacking in maturity : no good built-ins for reversing strings or string builderes either
    // bString.Reverse()
    bStr := bString.String()
    // gaaah string builder is bytes, but strings ranged are runs
    zeroPrefixDelta := ( n - 1 - bString.Len())
    // Ensure to reset
    curLetter = firstLetter
    for zeroPrefixDelta > 0 {
        nextLetter := (lowerRuneMap[curLetter])
        prefix.WriteRune(nextLetter)
        curLetter = nextLetter
        zeroPrefixDelta--
    }
    // whatever cur letter here is
    for idx := len(bStr) - 1; idx >= 0; idx-- {
        el := bStr[idx]
        nextLetter := (lowerRuneMap[curLetter])
        if el == '1' {
            nextLetter = (upperRuneMap[curLetter])
        }
        suffix.WriteRune(nextLetter)
        curLetter = nextLetter
    }
    // PREFIX first -> then suffix?
    // fmt.Printf("Suffix = %s\n", suffix.String())
    // gaaah no good way of concat of string builders
    prefix.WriteString(suffix.String())
    happyString := prefix.String()
    return happyString
}

// gaaaah go inefficient here anyways
// slice expressions be weird bruh
// func genBinaryString(n int, k int) string {
// }
