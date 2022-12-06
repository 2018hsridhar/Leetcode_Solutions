'''
2044. Count Number of Maximum Bitwise-OR Subsets
URL = https://leetcode.com/problems/count-number-of-maximum-bitwise-or-subsets/

Harken back to thinking from DP problems.
Complexity
Let N := len(list)
Time = O(2^n)
Space = O(n) ( IMP ) O(n*2^n) ( IMP)
    - #-subsets * len(subset) for space
    - explicitly alloc set of size n each call invocation.

stime = 5 mins
etime = 25 mins
20 mins
Your algo was right, but conditional logic you forgot.
Also double couting in the recursion
'''
# Py3 allows type specifier @ func end :-)
def internal(curSet: List[int], nums: List[int], idx : int, runMaxOr : int, maxOr : int)  -> int :
    #  Your math seems correct : check indexing or something else here?
    countMatchSet = 0
    if(idx > len(nums)):
        countMatchSet = 0
    elif(idx == len(nums)):
        if(runMaxOr == maxOr):
            countMatchSet += 1
    else:
        # Math is right, but eval might not be accounting for the 0 index case in selection!
        childNotChoseIdx = curSet
        childChoseIdx = curSet + [nums[idx]] # Concat a list and an list[single int]
        runMaxOrNotChoseIdx = runMaxOr
        runMaxOrChoseIdx = ( runMaxOr | nums[idx] )
        countMatchSet += internal(childChoseIdx, nums, idx + 1, runMaxOrChoseIdx, maxOr)
        countMatchSet += internal(childNotChoseIdx, nums, idx + 1, runMaxOrNotChoseIdx, maxOr)
    return countMatchSet

class Solution:
    def countMaxOrSubsets(self, nums: List[int]) -> int:
        maxBitWiseOr = 0
        # https://blog.finxter.com/python-bitwise-or-operator/#:~:text=Python%E2%80%99s%20bitwise%20OR%20operator%20x%20%7C%20y%20performs,input%20bits%20at%20the%20same%20position%20are%201. ( logical or should work ) 
        for x in nums :
            maxBitWiseOr = ( maxBitWiseOr | x ) # Bitwise or ( not logical or )
        rootSet = []
        initRunMaxOr = 0
        initIdx = 0
        # print("My Init runmaxOr = %d\n" % initRunMaxOr) # This is incorrect : should be 3 ( 11 ) not 2 ( 10 )
        # print("My Init maxOr = %d\n" % maxBitWiseOr) # This is incorrect : should be 3 ( 11 ) not 2 ( 10 )
        numMatchSubsets = internal(rootSet, nums, initIdx, initRunMaxOr, maxBitWiseOr)
        return numMatchSubsets
