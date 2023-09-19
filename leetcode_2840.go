/*
URL := https://leetcode.com/problems/check-if-strings-can-be-made-equal-with-operations-ii/
2840. Check if Strings Can be Made Equal With Operations II

It's cycles and mathematics in the hiding honestly
Check that the character sets match : for odd indices and even indices

Let N := max(len(s1), len(s2))
Time := O(N)
Space := O(1) ( EXP ) ( sigma = size of language type of reasoning ) 
*/
func checkStrings(s1 string, s2 string) bool {
    sOneOddMap := make(map[rune]int)
    sOneEvenMap := make(map[rune]int)
    sTwoOddMap := make(map[rune]int)
    sTwoEvenMap := make(map[rune]int)
    fillMaps(sOneOddMap,sOneEvenMap,s1)
    fillMaps(sTwoOddMap,sTwoEvenMap,s2)

    status := equalMaps(sOneOddMap,sTwoOddMap) && equalMaps(sOneEvenMap, sTwoEvenMap)
    return status
    // fmt.Printf("Map = %v\n", sOneOddMap)
    // fmt.Printf("Map = %v\n", sOneEvenMap)
}

// maps passed in as reference type in GoLang
// no need to make this into a pointer type :-) 
func equalMaps(mapOne map[rune]int, mapTwo map[rune]int) bool {
    status := true
    for k, v := range mapOne {
        if val, ok := mapTwo[k]; !ok || val != v {
            return false
        }
    }
    for k, v := range mapTwo {
        if val, ok := mapOne[k]; !ok || val != v {
            return false
        }
    }
    return status
}

func fillMaps(mapOne map[rune]int, mapTwo map[rune]int, s string){
    for idx, c := range s {
        if idx % 2 == 0 {
            // can not index into map error
            if _, ok := mapOne[c]; !ok {
                mapOne[c] = 0
            }
            mapOne[c] += 1
        } else {
            if _, ok := mapTwo[c]; !ok {
                mapTwo[c] = 0
            }
            mapTwo[c] += 1
        }
    }
}
