/*
Binary search problem in the hiding
URL := https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/

Let N := len(weights)
Let W := max sum of weights possible, across all weights ( in this case, 5e2 * 5e4 = 2.5e7) = 25,000,000 ( 25 mil ) a constant

Time := O(NlogW)
Space := O(1) ( EXP & IMP ) 

cwc = current weight capacity
lwc = least weight capacity

Capacity returns and tests if we are within the number of days bound too!!!!
We will always have an answer ( no case of bad ans -1 return ) 

17 minutes

*/
func shipWithinDays(weights []int, days int) int {
   lwc := math.MaxInt32
   low := 1
   high := 25000000 // no comma for value assignment :-) 
   for(low <= high) {
       mid := low + (high-low) / 2
       cwc := mid
       numDaysToShip := solveNumDaysWithCapacity(weights,cwc)
       if(numDaysToShip == -1) { // to low
           low = mid + 1
       } else if(numDaysToShip > days) {
           low = mid + 1
       } else if ( numDaysToShip <= days) {
            if(cwc < lwc) {
                lwc = cwc
                high = mid - 1
            } else if ( cwc >= lwc) {
                low = mid + 1
            }
       }
   }
   return lwc
}

// always have at least one day for capacity
// note : what if case where capacity is way way to low too!
func solveNumDaysWithCapacity(weights []int, cwc int) int {
    numDays := 1
    ptr := 0
    n := len(weights)
    runSum := 0
    for(ptr < n) {
        delta := weights[ptr]
        if(delta > cwc) {
            return -1
        }
        if(runSum + delta > cwc) {
            numDays += 1
            runSum = 0
            runSum += delta // reset here ( can not add )
        } else {
            runSum += delta
        }
        ptr++
    }
    return numDays
}
