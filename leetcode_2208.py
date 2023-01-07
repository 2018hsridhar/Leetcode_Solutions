'''
URL = https://leetcode.com/problems/minimum-operations-to-halve-array-sum/
2208. Minimum Operations to Halve Array Sum

Approach : Priority Queue, Greedy, Sort

Case Testing :
(A)
(B)
(C)
(D)
(E)

Complexity
Let N := len(nums)
Time = O(N*logN) + O(N*log(N)) = O(NlgN)
Space = O(N) ( EXP ) O(1) ( IMP ) 

21 mins ( py heaps annoying )
'''
class Solution:
    # Return type = int.
    def halveArray(self, nums: List[int]) -> int:
        minNumOpsToHalf = 0
        myInitialSum = 0
        for element in nums:
            myInitialSum += element
        # [1] Init heap
        #https://stackoverflow.com/questions/2501457/what-do-i-use-for-a-max-heap-implementation-in-python
        maxHeap = []
        heapq.heapify(maxHeap)
        # [2] Insert els into heap
        numsNeg = [float(-i) for i in nums]
        for el in numsNeg:
            heapq.heappush(maxHeap, el)
        curSum = float(myInitialSum)
        halfSum = float(curSum / 2) # precision caution
        # https://www.geeksforgeeks.org/max-heap-in-python/
        while(curSum > halfSum):
            maxEl = -1 * heapq.heappop(maxHeap)
            replMaxEl = float(maxEl / 2)
            curSum = curSum - replMaxEl
            # print("Maxel = %d \t replMaxEl = %d\n" % (maxEl, replMaxEl))
            heapq.heappush(maxHeap,-1 * replMaxEl)
            minNumOpsToHalf += 1
        return minNumOpsToHalf

