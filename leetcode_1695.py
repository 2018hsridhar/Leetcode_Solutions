'''
1695. Maximum Erasure Value
URL = https://leetcode.com/problems/maximum-erasure-value/description/

Complexity
Let N := len(nums)
Time = O(N)
Space = O(1) ( EXP ) O(1) ( IMP )

Test Cases :
(A) [1,2,3,4,5] => 15
(B) [1,1,1,1,1] => 1
(C) [1,2,3,3,2,1] => 6 
(D) [100] => 100
(E)
(F)

'''

class Solution:
    def maximumUniqueSubarray(self, nums: List[int]) -> int:
        myMaximalScore = 0
        lPtr = 0
        rPtr = 0
        n = len(nums)
        myRunSum = 0
        myVisitedNums = set()
        while(rPtr < n):
            el = nums[rPtr]
            if(el not in myVisitedNums):
                myRunSum += nums[rPtr]
                myVisitedNums.add(el)
                myMaximalScore = max(myMaximalScore, myRunSum)
            else:
                while(lPtr <= rPtr):
                    leftMostEl = nums[lPtr]
                    if(leftMostEl == el):
                        lPtr += 1
                        myMaximalScore = max(myMaximalScore, myRunSum)
                        break
                    else:
                        myVisitedNums.remove(leftMostEl)
                        myRunSum -= leftMostEl
                        lPtr += 1
            rPtr += 1
        return myMaximalScore

    # def maximumUniqueSubarray(self, nums: List[int]) -> int:
    #     print(maximumUniqueSubarrayInternal([1,2,3,4,5]))
    #     print(maximumUniqueSubarrayInternal([1,1,1,1,1]))
    #     print(maximumUniqueSubarrayInternal([1,2,3,3,2,1]))
    #     print(maximumUniqueSubarrayInternal([100]))
    #     return maximumUniqueSubarrayInternal(nums)
    
