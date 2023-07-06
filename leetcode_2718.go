/*
URL = https://leetcode.com/problems/maximum-split-of-positive-even-integers/description/
2178. Maximum Split of Positive Even Integers

*/
func maximumEvenSplit(finalSum int64) []int64 {
    evenPartition := make([]int64,0)
    if finalSum % 2 != 0 {
        return []int64{}
    }
    // Mismatched type -> invalid ops errors
    evenStep := int64(2)
    for finalSum > 0 {
        nextVal := finalSum - evenStep
        if nextVal > evenStep {
            finalSum = nextVal
            evenPartition = append(evenPartition, evenStep)
            evenStep += 2
        } else if nextVal <= evenStep {
            evenPartition = append(evenPartition, finalSum)
            break
        }
    }
    // https://stackoverflow.com/questions/38607733/sorting-a-uint64-slice-in-go
    // gaaah no wrapper use slice function instead
    // gaaah comma expectations here
    sort.Slice(evenPartition, func(i, j int) bool {
        return evenPartition[i] < evenPartition[j]
    })
    return evenPartition
}
