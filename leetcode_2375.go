/*
URL := https://leetcode.com/problems/construct-smallest-number-from-di-string/
2375. Construct Smallest Number From DI String

Lex smallest -> be greedy
Each digit used @ most once
Solve I first -> then solve D

Last character check just in case
    for I
    for D

keep good notion of the counter too

IIIDII
1237456
1235467 ...

IIIDDI
1237645

IIDIDII
1243657

Preserving the counter too, right?
That's also an idea
and grow it from the ends too!

Number of another element preceding you -> go fill on the way back
    You did not fill that earlier too!

(A) IIIDII
(B) IIDIDII
(C) I
(D) D
(E) IIII
(F) DDDD
(G) DIDIDIDI
(H) IDIDIDID
(I) DDIIDDI
(J) IIDIIIDD


*/
func smallestNumber(pattern string) string {
    n := len(pattern)
    myDigits := []string{}
    for i := 0; i < n+1; i++ {
        myDigits = append(myDigits,"")
    }
    counter := 1
    numPrecD := 0
    for idx, _ := range pattern {
        if pattern[idx] == 'I' {
            myDigits[idx] = strconv.Itoa(counter)
            counter++
            tempIdx := idx-1
            for numPrecD > 0 {
                myDigits[tempIdx] = strconv.Itoa(counter)
                counter++
                tempIdx--
                numPrecD--
            }
        } else if pattern[idx] == 'D' {
            numPrecD++
        }
    }
    // hey we checked number preceding D here earlier
    if pattern[n-1] == 'I' {
        myDigits[n] = strconv.Itoa(counter)
        counter++ 
    }
    // handle final case
    // counter must be set correctly here too
    startIdx := n
    if pattern[n-1] == 'D' {
        for numPrecD >= 0 {
            myDigits[startIdx] = strconv.Itoa(counter)
            counter++
            startIdx--
            numPrecD--
        }
    }
    // Ability to print a value in a generalized form
    // fmt.Printf("%v\n", myDigits)
    // now for the decrement portion of the problem
    // unless you just reverse the slice in-place anyways
    smallestNum := strings.Join(myDigits[:], "")
    return smallestNum
}

func reverseInSlice(slice []int, i int, j int) {
    lPtr := i
    rPtr := j
    for lPtr < rPtr {
        swap(slice,lPtr,rPtr)
        lPtr++
        rPtr--
    }
}

func swap(slice []int, i int, j int) {
    temp := slice[i]
    slice[i] = slice[j]
    slice[j] = temp
}
