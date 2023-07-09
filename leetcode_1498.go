/*
URL := https://leetcode.com/problems/number-of-subsequences-that-satisfy-the-given-sum-condition/
1498. Number of Subsequences That Satisfy the Given Sum Condition
Being greedy in this algorithm too

*/
func numSubseq(nums []int, target int) int {
    sort.Ints(nums)
    numMeetsCond := 0
    modulo := (int)(math.Pow(10,9)) + 7
    lPtr := 0
    rPtr := len(nums) - 1
    minEl := 0
    maxEl := 0
    for lPtr <= rPtr {
        minEl = nums[lPtr]
        maxEl = nums[rPtr]
        if minEl + maxEl > target {
            rPtr--
        } else {
            diffLength := (rPtr - lPtr)
            numMeetsCond += numSequencesFromLength(diffLength)
            // Avoid casting : hey still in same data type with the modulo :-) 
            numMeetsCond = numMeetsCond % modulo
            // proceed to next el in this case
            // Modulo op not defined on ints
            lPtr++
        }
    }
    return numMeetsCond
}

// It could be here that modulo bug gets introduced
// need to account for this too
// It is passing
// Gotta do a modulo trick too on compute of powers of 2 GAAAH why
func numSequencesFromLength(diffLength int) int {
    numSeqsFromLength := 1
    modulo := (int)(math.Pow(10,9)) + 7
    if diffLength == 0 {
        numSeqsFromLength = 1
    } else if diffLength > 0 {
        // Ok how this is hacky
        // WOW factor of 10 made a difference too
        for (diffLength - 10) > 0 {
            numSeqsFromLength *= 1024
            numSeqsFromLength = numSeqsFromLength % modulo
            diffLength -= 10
        }
        numSeqsFromLength *= (int)(math.Pow((float64)(2.0),(float64)(diffLength)))
        numSeqsFromLength = numSeqsFromLength % modulo

        // numSeqsFromLength = (int)(math.Pow((float64)(2.0),(float64)(diffLength)))
    }
    return numSeqsFromLength % modulo
}
