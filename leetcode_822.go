/*
1K max for the length of the cards
For each card - with a front and a back - check if it's a double pair
Once a double pair -> the number is invalid
If no double pair -> the number is valid
Sort each input ( or iter again ) and check if in double map. If not, that's our minimum card
If no good cards, ret 0
    bool status check too?
Vals >= 1 and vals <= 2K total

12 mins
*/
func flipgame(fronts []int, backs []int) int {
    hitCard := false
    doubleCards := map[int]bool{}
    flipVal := 0
    for index, _ := range fronts {
        frontVal := fronts[index]
        backVal := backs[index]
        flipVal = max(flipVal, frontVal)
        flipVal = max(flipVal, backVal)
        if(frontVal == backVal) {
            doubleCards[frontVal] = true
        }
    }
    for _, el := range fronts {
        if _, ok := doubleCards[el]; !ok {
            if hitCard == false {
                hitCard = true
            }
            flipVal = min(flipVal, el)
        }
    }
    for _, el := range backs {
        if _, ok := doubleCards[el]; !ok {
            if hitCard == false {
                hitCard = true
            }
            flipVal = min(flipVal, el)
        }
    }
    if hitCard == false {
        return 0
    }
    return flipVal
}

func min(a int, b int) int {
    if(a < b) {
        return a
    }
    return b
}

func max(a int, b int) int {
    if(a > b) {
        return a
    }
    return b
}
