# 6 mins
# 2441. Largest Positive Integer That Exists With Its Negative
# URL = https://leetcode.com/problems/largest-positive-integer-that-exists-with-its-negative/description/
class Solution:
    def findMaxK(self, nums: List[int]) -> int:
        myMaxK = -1
        # https://docs.python.org/3/tutorial/datastructures.html#sets
        mySet = set()
        for x in nums:
            mySet.add(x)
        for i,v in enumerate(mySet): # Sets can be iterated ( not ordered ) 
            negEl = v * -1
            if negEl in mySet:
                myMaxK = max(myMaxK, abs(negEl))
            # print(i,v)
        return myMaxK
