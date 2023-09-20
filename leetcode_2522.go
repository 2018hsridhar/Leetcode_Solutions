/*
URL = https://leetcode.com/problems/partition-string-into-substrings-with-values-at-most-k/
2522. Partition String Into Substrings With Values at Most K

Seems like a dynamic programming problem in the hiding

Either DP or Greedy -> not really any other ways
Concerned if greedy -> which partitioning was best too
If we get a (-1), we have to propagate it back upwards

Complexity:
Solve it in O(N) time or so? Or O(N^2) time?
10^5^2 = 10^10 performance
Time := O(N^2)
Space := O(N) ( EXP ) O(1) ( IMP ) 

Default initialize our array to all (-1) values too

Wooooh for saving to local here :-) 

Cases
(A) "165462", 1
(B) "165462", 100
(C) "111111", 1
(D)
(E)
(F)

21 minutes
bug at the <j> here
Was bottom up DP quickly :-) 
*/
func minimumPartition(s string, k int) int {
    minPart := 0
    n := len(s)
    // oneSlice := []int{1, 1, 1, 1, 1}
    // can we avoid composite literal syntax
    // preferable to have initalization in the `make` built-in functionality instead.
    memo := make([]int,n)
    for idx, _ := range memo {
        memo[idx] = -1
    }
    // gaaah base case inductive case handling
    // traverse right -> left here
    for i := n - 1; i >= 0; i-- {
        for j := i + 1; j <= n; j++ {
            // range-based extraction for substring
            substr := s[i:j]
            intVal, _ := strconv.Atoi(substr)
            // fmt.Printf("At idx = %d \t val = %d\n", i, intVal)
            if ( intVal <= k ) { // guarantee that intVal in curent partition fits at least :-)
                if(j < n) {
                    childMinParts := memo[j]
                    if(childMinParts != -1) {
                        childMinPartNum := 1 + childMinParts
                        if(memo[i] == -1) {
                            memo[i] = childMinPartNum
                        } else {
                            memo[i] = min(memo[i], childMinPartNum)
                        }
                    }
                } else if ( j >= n ) {
                    if(memo[i] == -1) {
                        memo[i] = 1
                    } else if (memo[i] != -1) {
                        memo[i] = 1
                    }
                }
            } else {
                break // go to next iteration : break out of inner for loop
            }
        }
    }
    fmt.Printf("memo = %v\n", memo)
    minPart = memo[0]
    return minPart
}

// gaaah @ GoLang for lacking the built-in min functionality
func min(a int, b int) int {
    if(a < b) {
        return a
    }
    return b
}
