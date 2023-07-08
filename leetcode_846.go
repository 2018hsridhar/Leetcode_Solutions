/*
URL := https://leetcode.com/problems/hand-of-straights/
846. Hand of Straights

Leverage GoLang TreeMaps here
N = 10000 possible
At least one card in the hands too

7 mins wooohy
*/
func isNStraightHand(hand []int, groupSize int) bool {
    status := true
    countHandMap := make(map[int]int)
    for _, el := range hand {
        countHandMap[el]++
    }
    sort.Ints(hand)
    for _, el := range hand {
        if countHandMap[el] > 0 {
            if !canMakeGroupWithElement(el,groupSize,countHandMap){
                status = false
                break
            }
        }
    }
    return status
}

// Consecutive : assumed to just be +1 here for each element
func canMakeGroupWithElement(x int, groupSize int, handMap map[int]int) bool {
    status := true
    for el := x; el < x + groupSize; el++ {
        if handMap[el] == 0 {
            status = false
            break
        } else {
            handMap[el]--
        }
    }
    return status
}
