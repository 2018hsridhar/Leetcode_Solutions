/*
URL := https://leetcode.com/problems/find-kth-bit-in-nth-binary-string/
1545. Find Kth Bit in Nth Binary String

Binary search ideas
Brings us to logarithmic time performance too :-) 

kth is 1-indexed :-).
_______ := 7 digits long
_______0_______ := 15 digits long

*/
func findKthBit(n int, k int) byte {
    // Base case
    if n == 1 {
        return '0'
    }
    kth := 0
    state := 0
    // 0 := s as 1 := rev(inv(s))
    nF := (float64)(n)
    // Inductive case
    for nF >= 2.0 {
        nextF := nF - 1.0
        sValNext := (int)(math.Pow(2.0,nextF)) - 1
        if k <= sValNext {
            state = 0
        } else if k == sValNext + 1 {
            if state == 0 {
                return '1'
            } else {
                return '0'
            }
        } else {
            k -= sValNext
            k -= 1
            state = 1
        }
        nF -= 1.0
    }
    if state == 0 {
        kth = 0
    } else {
        kth = 1
    }
    if kth == 0 {
        return '0'
    }
    return '1'
}
