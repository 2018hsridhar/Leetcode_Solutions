/*
URL := https://leetcode.com/problems/divide-players-into-teams-of-equal-skill/
2491. Divide Players Into Teams of Equal Skill

Input len always even
-1 if no chemisry or division to all equality case

sum odd -> 3 groups -> can still work
but if a remainder : it ain't gonna work

Variation of the classic 2SUM problem
    -> be greedy : avoid overly combinatorial problem.

Does GoLang allow widening conversions too?
6 mins to solution WOOOOH :-) 

*/
func dividePlayers(skill []int) int64 {
    totalSum := 0
    // Range loops less buggy than non-range loops
    // GoLang : only lang where a range loop provisions both ( index, el ) : not just element
    // Avoids boilerplate code.
    for _, el := range skill {
        totalSum += el
    }
    numGroups := len(skill) / 2
    sumOfGroup := totalSum / numGroups
    remOfGroups := totalSum % numGroups
    if remOfGroups != 0 {
        return -1
    }
    chemSum := int64(0)
    // Readability : communicate to programmer that this is ASC order sort
    sort.Ints(skill)
    lPtr := 0
    rPtr := len(skill) - 1
    for lPtr < rPtr {
        leftEl := skill[lPtr]
        rightEl := skill[rPtr]
        if (leftEl + rightEl != sumOfGroup) {
            return -1
        }
        localChemSum := (int64)( leftEl * rightEl )
        chemSum += localChemSum     
        lPtr++
        rPtr--
    }
    return chemSum
}

