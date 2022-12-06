'''
2419. Longest Subarray With Maximum Bitwise AND
URL = https://leetcode.com/problems/longest-subarray-with-maximum-bitwise-and/description/
Bitwise AND : leverage property there
Conjure up test cases

cases
(A) [4] => 1
(B) [4,4,4] => 3
(C) [4,2,7,6,9,3] => 1
(D) [9,9,1,5,2,9,9,9,9,3,2,5,9,9,9,9,9,9] => 6
(E) [6,5,3,6,6,6,9,9,9,9,9] => 5
(F)

Let N := len(nums)
Time = O(N)
Space = O(1) ( EXP && IMP ) 
'''
class Solution:
    def longestSubarray(self, nums: List[int]) -> int:
        # https://www.geeksforgeeks.org/python-list-max-method/
        maxVal = max(nums)
        longestLen = 1 # default since every input has a max int
        i = 0
        n = len(nums)
        runLen = 1
        while(i < n -1):
            if(nums[i] == maxVal):
                if(nums[i] == nums[i+1]):
                    runLen += 1
                    longestLen = max(longestLen,runLen)
                else:
                    runLen = 1
            else:
                runLen = 1
            i += 1
        return longestLen
