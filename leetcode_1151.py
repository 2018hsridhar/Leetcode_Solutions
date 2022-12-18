'''
1151. Minimum Swaps to Group All 1's Together
URL = https://leetcode.com/problems/minimum-swaps-to-group-all-1s-together/

Complexity
Let N := len(data)
Time = O(N)
Space = O(1) ( EXP ) O(1) ( IMP ) 

Cases
(A) [0,0,0,0,0]
(B) [1,1,1,1,1]
(C) [1,1,1,0,0,1,1,0,0,0,1,0,0,0,1,0,1]

14.5 mins to solution

'''
class Solution:
    def minSwaps(self, data: List[int]) -> int:
        minSwapsNeeded = float('inf')
        numberOfZeroesInWindow = 0
        lPtr = 0
        rPtr = 0
        n = len(data)
        # [1] Get count of ones
        maximalOneCount = 0
        for el in data:
            if(el == 1):
                maximalOneCount += 1
        # [2] Get counts over the windows.
        curRunOneCount = 0
        while(rPtr < n):
            if(data[rPtr] == 1):
                curRunOneCount += 1
            if(rPtr - lPtr + 1 == maximalOneCount + 1):
                if(data[lPtr] == 1):
                    curRunOneCount -= 1
                lPtr += 1
            if(rPtr - lPtr + 1 == maximalOneCount):
                curRunZeroCount = maximalOneCount - curRunOneCount
                minSwapsNeeded = min(minSwapsNeeded, curRunZeroCount)
            rPtr += 1
        return minSwapsNeeded
