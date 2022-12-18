'''
1792. Maximum Average Pass Ratio
URL = https://leetcode.com/problems/maximum-average-pass-ratio/

Complexity
Let N := len(list)
Time = O(nlgn)
Space = O(N) ( EXP ) O(1) ( IMP ) 

TLE, but al test cases pass?
Wait ... what!

23 mins on this problems
Wow PY you slow!

'''
class Solution:
    def maxAverageRatio(self, classes: List[List[int]], extraStudents: int) -> float:
        myMaxAverageRatio = 0.0
        # https://docs.python.org/3/library/heapq.html
        # https://stackoverflow.com/questions/8875706/heapq-with-custom-compare-predicate
        myRecordDeltaMaxHeap = []
        heapq.heapify(myRecordDeltaMaxHeap)
        for myClass in classes:
            num = float(myClass[0])
            denom = float(myClass[1])
            delta = float(num+1)/float(denom+1) - float( num / denom )
            myRecord = [-delta, num, denom]
            heapq.heappush(myRecordDeltaMaxHeap, myRecord)
        # If the delta is the same, it probably does not matter.
        # Even if num and den the same, the delta will differ!
        for student in range(extraStudents):
            myCurBestDelta = heapq.heappop(myRecordDeltaMaxHeap)
            myDelta = abs(myCurBestDelta[0])
            replNum = myCurBestDelta[1] + 1
            replDenom = myCurBestDelta[2] + 1
            replDelta = float(replNum+1)/float(replDenom+1) - float( replNum / replDenom )
            replRecord = [-replDelta, replNum, replDenom]
            heapq.heappush(myRecordDeltaMaxHeap, replRecord)
        # Processed all extra students : collect the average from the heap now
        numClasses = float(len(classes))
        sumPassRatio = 0.0
        while(len(myRecordDeltaMaxHeap) > 0):
            curRecord = heapq.heappop(myRecordDeltaMaxHeap)
            myCurPassRatio = curRecord[1] / curRecord[2]
            sumPassRatio += myCurPassRatio
        myMaxAverageRatio = sumPassRatio / numClasses
        return myMaxAverageRatio
