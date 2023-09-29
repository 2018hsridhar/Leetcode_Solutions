/*
URL := https://leetcode.com/problems/number-of-ways-to-select-buildings/
2222. Number of Ways to Select Buildings

12 minutes to solution

Complexity
Let N := len(s)
Time := O(N)
Space := O(N) ( EXP ) O(1) ( IMP ) 
 */
func numberOfWays(s string) int64 {
    numWays := int64(0)
    n := len(s)
    numZeroesLeft := make([]int64, n)
    numOnesLeft := make([]int64, n)
    numZeroesRight := make([]int64, n)
    numOnesRight := make([]int64, n)
    fillLeftCounts(s,numZeroesLeft,numOnesLeft)
    fillRightCounts(s,numZeroesRight,numOnesRight)
    for idx, let := range s {
      if(let == '0') {
        numWays += (numOnesLeft[idx] * numOnesRight[idx])
      } else if ( let == '1' ) {
        numWays += (numZeroesLeft[idx] * numZeroesRight[idx])
      }
    }
    return numWays
}

func fillRightCounts(s string, numZeroesRight []int64, numOnesRight []int64 ) {
  n := len(s)
  rPtr := n - 1
  countZero := int64(0)
  countOne := int64(0)
  for rPtr >= 0  {
    // [1] fill
    numZeroesRight[rPtr] = countZero
    numOnesRight[rPtr] = countOne
    // [2] check el and add
    if(s[rPtr] == '1') {
      countOne++
    } else if ( s[rPtr] == '0'){
      countZero++
    }
    // [3] incr/decr
    rPtr--
  }
}

func fillLeftCounts(s string, numZeroesLeft []int64, numOnesLeft []int64 ) {
  n := len(s)
  rPtr := 0
  countZero := int64(0)
  countOne := int64(0)
  for rPtr < n {
    // [1] fill
    numZeroesLeft[rPtr] = countZero
    numOnesLeft[rPtr] = countOne
    // [2] check el and add
    if(s[rPtr] == '1') {
      countOne++
    } else if ( s[rPtr] == '0'){
      countZero++
    }
    // [3] incr/decr
    rPtr++
  }
}

