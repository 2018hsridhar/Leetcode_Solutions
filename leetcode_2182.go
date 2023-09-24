/*
URL := https://leetcode.com/problems/construct-string-with-repeat-limit/
2182. Construct String With Repeat Limit

Wow close
seems almost at correct answer
but weird test case posppsed up
*/
type Info struct {
    letter rune
    freq int
}

func repeatLimitedString(s string, repeatLimit int) string {
    lexLargest := ""
    elMap := make(map[rune]int)
    for _, el := range s {
        elMap[el]++
    }
    toSortSlice := make([]Info,0)
    for l, f := range elMap {
        info := Info {
            letter: l,
            freq: f,
        }
        toSortSlice = append(toSortSlice, info)
    }
    sort.Slice(toSortSlice, func(i, j int) bool {
        if(toSortSlice[i].letter == toSortSlice[j].letter) {
            return toSortSlice[i].freq < toSortSlice[j].freq
        }
        return toSortSlice[i].letter < toSortSlice[j].letter
    })
    for len(toSortSlice) > 0 {
        lastIdx := len(toSortSlice) - 1
        lastEl := toSortSlice[lastIdx]
        if(lastEl.freq >= 0) {
            delta := lastEl.freq - repeatLimit
            if(delta > 0) {
                toSortSlice[lastIdx].freq = delta
                lexLargest +=  strings.Repeat((string)(lastEl.letter), repeatLimit)
                if(len(toSortSlice) > 1) {
                    for {
                        secondLastIdx := lastIdx - 1
                        secondLastEl := toSortSlice[secondLastIdx]
                        deltaTwo := secondLastEl.freq - 1
                        if(deltaTwo < 0) { 
                            // just cuz this one did not work does not mean a future one wont
                            toSortSlice = append(toSortSlice[:secondLastIdx], toSortSlice[secondLastIdx+1:]...)
                        } else if ( deltaTwo == 0 ) {
                            lexLargest += (string)(secondLastEl.letter)
                            toSortSlice = append(toSortSlice[:secondLastIdx], toSortSlice[secondLastIdx+1:]...)
                            break
                        } else {
                            lexLargest += (string)(secondLastEl.letter)
                            toSortSlice[secondLastIdx].freq -= 1
                            break // don't exec further
                        }
                        if(len(toSortSlice) == 1) {
                            break
                        }
                    }
                } else {
                    break
                }
            } else if ( delta <= 0 ){
                // go remove last element now :-)
                // note : we are back to the last element, so clearly, we appended it all here :-) ( lest we go back elsewhere ) 
                // fmt.Printf("%v\n", lastEl.letter)
                lexLargest +=  strings.Repeat((string)(lastEl.letter), lastEl.freq)
                toSortSlice = toSortSlice[:len(toSortSlice) - 1] // reslice operation
            }
        }    
    }
    return lexLargest
}
