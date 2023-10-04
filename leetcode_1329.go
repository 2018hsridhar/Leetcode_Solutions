/*
1329. Sort the Matrix Diagonally
URL := https://leetcode.com/problems/sort-the-matrix-diagonally/

Can we do merge sort on the array
Solve for the diagonal quickly too

Complexity
Let M, N := dims(mat)
Time := O(MNlgMN*(M+N))
Space := O(1) ( EXP ) O(MN) ( IMP ) 

Code like an API to simplify the merge sort process ( like, wow ... ) 
Way to much `in-place` optimizations
34 mins spent
Forgetting that we need top copy

Woah a near in place merge sort. This is amazing
And written in an API-esque format too :-)
WTG - Way to go !!

*/
func diagonalSort(mat [][]int) [][]int {
    m := len(mat)
    n := len(mat[0])
    for r := 0; r < m; r++ {
        brd := findBottomRightDiagonal(r,0,m,n)
        mergeSort(mat,r,0,brd[0], brd[1])
    }
    for c := 0; c < n; c++ {
        brd := findBottomRightDiagonal(0,c,m,n)
        mergeSort(mat,0,c,brd[0], brd[1])
    }
    return mat
}

func mergeSort(mat [][]int, lowR,lowC,highR,highC int) {
    if(lowR > highR || lowC > highC ) {
        return // NOP cond
    } else if ( lowR == highR && lowC == highC) {
        return // single element case anyways
    }
    midR := ( lowR + highR ) / 2
    midC := ( lowC + highC ) / 2
    mergeSort(mat,lowR,lowC,midR,midC)
    mergeSort(mat,midR+1,midC+1,highR,highC)
    merge(mat,lowR,lowC,midR+1,midC+1,midR+1,midC+1,highR+1,highC+1)
}

// now easy indexing for pointers TBH
// not easy to tell where to start write operaton
// start at min of the lowR,lowC
// you are writing in place here. Careful. These are not arrays! Take note of that!
func merge(mat [][]int, lowR,lowC,highR,highC, lowRTwo,lowCTwo,highRTwo,highCTwo int) {
    pointerOneEnd := []int{highR,highC}
    pointerTwoEnd := []int{highRTwo,highCTwo}
    writePointer := []int{min(lowR,lowRTwo), min(lowC,lowCTwo)}
    pointerOne := []int{lowR,lowC}
    pointerTwo := []int{lowRTwo,lowCTwo}
    toMerge := []int{}
    for !equalIndex(pointerOne,pointerOneEnd) && !equalIndex(pointerTwo,pointerTwoEnd) {
        // writePointerVal(writePointer)
        valOne :=  getVal(mat,pointerOne)
        valTwo := getVal(mat,pointerTwo)
        if(valOne <= valTwo) {
            toMerge = append(toMerge, valOne)
            stepDiag(pointerOne)
        } else {
            toMerge = append(toMerge, valTwo)
            stepDiag(pointerTwo)
        }
    }
    for !equalIndex(pointerOne,pointerOneEnd) {
        valOne :=  getVal(mat,pointerOne)
        toMerge = append(toMerge, valOne)
        stepDiag(pointerOne)
    }
    for !equalIndex(pointerTwo,pointerTwoEnd) {
        valTwo :=  getVal(mat,pointerTwo)
        toMerge = append(toMerge, valTwo)
        stepDiag(pointerTwo)
    }
    for _, val := range toMerge {
        writeVal(mat, writePointer,val)
        stepDiag(writePointer)
    }
}

// func writePointerVal(pointer []int){
//     fmt.Printf("Pointer = (%v,%v)\n", pointer[0], pointer[1])
// }

func getVal(mat [][]int, pointer []int) int {
    return mat[pointer[0]][pointer[1]]
}

func writeVal(mat [][]int, pointer []int, val int) {
    mat[pointer[0]][pointer[1]] = val
}

func stepDiag(ptr []int) {
    ptr[0]++
    ptr[1]++
}

func equalIndex(a,b []int) bool {
    return (a[0] == b[0] && a[1] == b[1])
}

func findBottomRightDiagonal(r,c,m,n int) []int {
    bR := m - 1
    rC := n - 1
    bottomRight := []int{-1,-1}
    deltaR := bR - r
    deltaC := rC - c
    delta := min(deltaR, deltaC)
    bottomRight[0] = r + delta
    bottomRight[1] = c + delta
    // fmt.Printf("For (r,c) = (%v,%v), diagonalEnd = (%v,%v)\n", r,c,bottomRight[0], bottomRight[1])
    return bottomRight 
}

func min(a, b int) int {
    if(a < b) {
        return a
    }
    return b
}
