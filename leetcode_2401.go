/*
2401. Longest Nice Subarray

URL := https://leetcode.com/problems/longest-nice-subarray/description/
Sliding array technique 
2 ptrs linear time
it's a count of one's filling in a window of 32 bits honestly

max val = 1,000,000,000 ( 1 BIL )
Counting problem in the hiding. 

Cases
(A) [1] 
(B) [5,2,76,98,43]
(C) [1,67,98,943034,32,45,87,12,1,1,4,45,76] => PASS
(D) [1,67,98,943034] => expected 1
(E) [84139415,693324769,614626365,497710833,615598711,264,65552,50331652,1,1048576,16384,544,270532608,151813349,221976871,678178917,845710321,751376227,331656525,739558112,267703680] => PASS
(F) oooh 64/65 cases : one edge case
(G) [1,4,8,5,2]


(H)

You are close but off somewhere
50/65 cases passing
Look into an edge case

WOAH I/O output limit exceeded
*/
func longestNiceSubarray(nums []int) int {
    lPtr := 0
    rPtr := 0
    n := len(nums)
    // min size of at leaset one
    longest := 1
    // Don't we have predeclaration of size, val for slice exprs GAAAH
    binCounts := []int{}
    for x := 0; x < 32; x++ {
        binCounts = append(binCounts, 0)
    }
    for rPtr < n {
        binRep := strconv.FormatInt(int64(nums[rPtr]), 2)
        exp := 0
        hitDouble := false
        // Go thru the new bitstring
        for i := len(binRep) - 1; i >= 0; i-- {
            el, _ := strconv.Atoi((string)(binRep[i]))
            binCounts[exp] += el
            if binCounts[exp] > 1 {
                hitDouble = true
            }
            exp++
        }
        // fmt.Printf("At rPtr = %v \t binCounts = %v \t hitDouble = %v\n", rPtr, binCounts, hitDouble)
        if !hitDouble {
            delta := rPtr - lPtr + 1
            if delta > longest {
                longest = delta
                // fmt.Printf("Longest = %d\n", longest)
            }
        } else if hitDouble {
            for lPtr < rPtr {
                binRepLeftEl := strconv.FormatInt(int64(nums[lPtr]), 2)
                // Go thru the new bitstring
                noDouble := true
                exp := 0
                for i := len(binRepLeftEl) - 1; i >= 0; i-- {
                    el, _ := strconv.Atoi((string)(binRepLeftEl[i]))
                    binCounts[exp] -= el
                    exp++
                }
                exp = 0
                for i := len(binCounts) - 1; i >= 0; i-- {
                    if binCounts[exp] > 1 {
                        noDouble = false
                    }
                    exp++
                }
                // fmt.Printf("After modif binCounts = %v \t noDouble = %v\n", binCounts, noDouble)
                lPtr++
                if noDouble {
                    break 
                    // keep leftPtr where it is?
                    // clearly the most recent element helped : so current element should stay as sequence starter
                }
            }
        }
        rPtr++
    }
    return longest
}
