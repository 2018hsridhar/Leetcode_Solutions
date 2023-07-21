/*
URL := https://leetcode.com/problems/rank-teams-by-votes/
1366. Rank Teams by Votes

Only so many characters here too
Consider a hashmap ( letter -> votesArray ) ?

*/
func rankTeams(votes []string) string {
    voteLen := len(votes[0])
    voteMap := make(map[rune][]int)
    for _, vote := range votes {
        for index, letter := range vote {
            // If not in the map
            if _, ok := voteMap[letter]; !ok {
                voteMap[letter] = []int{}
                for i := 0; i < voteLen; i++ {
                    voteMap[letter] = append(voteMap[letter],0)
                }
            }
            voteMap[letter][index]++
        }
    }
    ranksToSort := []*letInfo{}
    for letter, rank := range voteMap {
        letStruct := newLetInfo(letter,rank)
        ranksToSort = append(ranksToSort, letStruct)
    }
    sort.SliceStable(ranksToSort, func(i,j int) bool {
        infoOne := ranksToSort[i]
        infoTwo := ranksToSort[j]
        lsOne := infoOne.ranks
        lsTwo := infoTwo.ranks
        allTied := true
        for i := 0; i < len(lsOne); i++ {
            if lsOne[i] < lsTwo[i]{
                return false
            } else if lsOne[i] > lsTwo[i] {
                return true
            } 
        }
        if allTied {
            return infoOne.letter < infoTwo.letter
        }
        return false
    })
    var sb strings.Builder
    for _, el := range ranksToSort {
        sb.WriteRune(el.letter)
    }
    return sb.String()
}

type letInfo struct {
    letter rune
    ranks []int
}

// Ideally returns pointer : not value.
func newLetInfo(letter rune, ranks []int) *letInfo {
    p := letInfo{
        letter: letter,
        ranks: ranks,
    }
    return &p
}
