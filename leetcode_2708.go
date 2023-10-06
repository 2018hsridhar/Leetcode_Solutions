/*
URL := https://leetcode.com/problems/maximum-strength-of-a-group/description/
2708. Maximum Strength of a Group

Can we avoid two seperate arrays

Complexity
Let N := len(nums)
Time := O(Nlgn)
Space := O(1) ( EXP & IMP ) 

Handle the zero number case too ( can arise ) 

Can have case of all numbers being negative too ( take note of this ) 
Try seperating out at least
strength is additive -> NOP on the zero still

Must be a non-empty group of students too

Cases
(A) [-1] => -1
(B) [-3,-2] => 6
(C) [-3,-2,-1] => 6
(D) [-1,0] => 0
(E) [0] => 0
(F) [1] => 1
(G) [0,0,0,0] => 0
(H) [-3,-2,0,0,1,2] => 6*1*2 = 12
(I)
(J) 
*/
func maxStrength(nums []int) int64 {
    // BASE CASE HANDLING
    if(len(nums) == 1) {
        return (int64)(nums[0])
    }
    maxStren := int64(1)
    n := len(nums)
    ptr := 0
    sort.Ints(nums) // ASC order
    hitTwoNeg := false
    hitPos := false
    for ptr < n {
        if nums[ptr] < 0 {
            next := ptr + 1
            if isInBounds(next,n) && nums[next] < 0 {
                hitTwoNeg = true
                maxStren *= (int64)(nums[ptr] * nums[next])
                ptr += 2
            } else {
                ptr++
            }
        } else if nums[ptr] > 0 {
            hitPos = true
            maxStren *= (int64)(nums[ptr])
            ptr++
        } else if nums[ptr] == 0 {
            ptr++ // NOP here
        }
    }
    // only hit zeroes in this case
    if !hitTwoNeg && !hitPos {
        maxStren = int64(0)
    }
    return maxStren
}

func isInBounds(a int, n int) bool {
    return (0 <= a && a < n)
}
