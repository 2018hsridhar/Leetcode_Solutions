'''

1218. Longest Arithmetic Subsequence of Given Difference
URL = https://leetcode.com/problems/longest-arithmetic-subsequence-of-given-difference/

DP Complexity desired : O(N) Time in one pass, with O(N) Space potentially
Idea : Store a hashmap as we iterate, instead of an array. Check if there is an element with the different as we traverse too
SUbsequence is NOT contiguous -> can derive from the array by deleting some OR no elements as well

TEST CASES : 
(A) [1],10
(B) [1,2,4,5,6] difference = 2


'''
class Solution:
    def longestSubsequence(self, arr: List[int], difference: int) -> int:
        longestLen = 0
        n = len(arr)
        i = n-1
        lenLongestWithStart = dict()
        while i >= 0:
            val = arr[i]
            curLongestLen = 1
            nextVal = val + difference
            if nextVal in lenLongestWithStart:
                curLongestLen += lenLongestWithStart[nextVal]
            if val in lenLongestWithStart:
                lenLongestWithStart[val] = max(lenLongestWithStart[val], curLongestLen)
            else:
                lenLongestWithStart[val] = curLongestLen
            longestLen = max(longestLen, curLongestLen) # Encompasses the subproblems too as well
            i -= 1
        return longestLen
        
        
