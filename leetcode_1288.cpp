'''
URL = https://leetcode.com/problems/remove-covered-intervals/
1288. Remove Covered Intervals

Complexity
Let N := len(intervals)
Time = O(nlgn)
Space = O(1) (EXP ) O(1) ( IMP ) 

Test Case
(A) [[1,2],[2,3],[3,4]] => 3
(B) [[1,2],[1,10],[1,5]] => 1
(C) [[1,2]] => 1
(D) [[1,2],[1,5],[1,10],[2,3],[2,4],[2,12],[4,5],[4,6],[4,15]] => 3
(E)

22 mins
'''
# Use predicate syntax for readability.
def isCoveredInterval(coveredInterval: List[int], coveringInterval: List[int]):
    isIntervalCovered = False
    if(coveredInterval[0] >= coveringInterval[0] and coveredInterval[1] <= coveringInterval[1]):
        isIntervalCovered = True
    return isIntervalCovered

# At leaast one interval in all solutions.
class Solution:
    # https://stackoverflow.com/questions/16537636/how-to-sort-in-decreasing-value-first-then-increasing-in-second-value
    def removeCoveredIntervals(self, intervals: List[List[int]]) -> int:
        numRemainingIntervals = 1
        sortedIntervals = sorted(intervals, key=lambda el : (el[0], -el[1])) # incr, then decr
        intervalExtendingRightmost = sortedIntervals[0]
        for index in range(1, len(intervals),1):
            myCurrentInterval = sortedIntervals[index] # Use the mutated state.
            if(isCoveredInterval(myCurrentInterval, intervalExtendingRightmost)):
                continue
            else:
                numRemainingIntervals += 1
                intervalExtendingRightmost = myCurrentInterval
        return numRemainingIntervals
