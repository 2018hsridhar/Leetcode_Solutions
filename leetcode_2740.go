/*
2740. Find the Value of the Partition
URL = https://leetcode.com/problems/find-the-value-of-the-partition/
Both arrays are nonempty
Seems like a sort and greey problem in the hiding
We have the all elements property : can not avoid this with ease either
O(N) or O(NlgN) N large number inputs
Handle numeric overflows too
    {a b c max(d) | min(e) f g }
    Order property of numbers too!

    3 and 8 ( dist 5 )
    all els < 3 :: P2
    all els > 8 : P1
    and min(P2) = 8 and max(P1) = 3

*/
func findValueOfPartition(nums []int) int {
    partVal := math.MaxFloat64
    sort.Ints(nums)
    i := 0
    // Inf loop case ran into a TLE
    for i < len(nums) - 1 {
        firstEl := (float64)(nums[i])
        secondEl := (float64)(nums[i+1])
        diff := math.Abs(secondEl - firstEl)
        partVal = math.Min(partVal, diff)
        i++
    }
    return (int)(partVal)
}
