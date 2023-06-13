/*
URL := https://leetcode.com/problems/check-array-formation-through-concatenation/
1640. Check Array Formation Through Concatenation

Intution : Leverage hashmap : start of each piece belongs to a specific index in pieces
Two pointers idea
*/
func canFormArray(arr []int, pieces [][]int) bool {
    status := true
    hm := make(map[int]int)
    for index, piece := range pieces { 
        key := piece[0]
        val := index
        if _, ok := hm[key]; !ok {
            hm[key] = val
        }
    }
    arrPtr := 0
    n := len(arr)
    for arrPtr < n {
        el := arr[arrPtr]
        // No start of the piece for the given element
        if pieceIndex, ok := hm[el]; !ok {
            status = false
            break
        } else if ok { // Have the piece, but race ptrs and ensure piece matche too
            piece := pieces[pieceIndex]
            piecePtr := 0
            pieceLen := len(piece)
            for arrPtr < n && piecePtr < pieceLen {
                if arr[arrPtr] != piece[piecePtr] {
                    status = false
                    break
                }
                arrPtr++
                piecePtr++
            }
            if piecePtr < pieceLen {
                status = false
                break
            }
        }
    }
    return status
}
