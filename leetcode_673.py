'''
URL = https://leetcode.com/problems/number-of-longest-increasing-subsequence/
673. Number of Longest Increasing Subsequence

Integer array of nums
#-longest increasing subsequences
Seq must be STRICTLY increasing here

COMPLEXITY of DP
Let N := len(nums)
TIME = O(N^2) or O(N)
SPACE = O(N) or O(N^2)

TEST CASES :
(A) [1,2,3,4,5]
(B) [2,2,2,2,2]
(C) [5,4,3,2,1]
(D) [1,2,3,3,3]
    => 3
(E)

Typical choice presentation problem here
Default min count = 1

Store len of longest incr subseq as we traverse
Note the counter resets here too

No index traversal for the backtrack/path reconstruction luckily
maxLen need not be STORED at each index -> can just propogate as we traverse 
Note -> it is a number here : exert caution as well!

PSEUDOCODE : 

    numOfLIS = 0
    maxLen = 1  # default value anyways
    maxCount = 1   # default as well
    n = len(A)
    lenLongest[n] # initialize to all zeroes
    for each index i in range(n-2,-1,-1):
        ithMaxLen = 1   # elem stand-alone case
        ithCount = 1
        for each index j in range(i+1,n,1):
            if A[i] < A[j]:
                ithMaxLen = 1 + lenLongest[j]
                if curMaxLen > ithMaxLen :
                    ithMaxLen = curMaxLen
                    ithCount = 1
                elif curMaxLen == ithMaxLen :
                    ithCount += 1
        if(ithMaxLen > maxLen):
            maxCount = ithCount
            maxLen = ithLen
        lenLongest[i] = ithMaxLen
            
    ret numOfLis

[0 for i in range(3)] 
Why does python take a whiel to compile too?
You may also need to store those frequencies too. it's not just the comparisons here!
'''


class Solution:
    def findNumberOfLIS(self, nums: List[int]) -> int:
        if nums == None or len(nums) <= 1:
            return len(nums)
        maxLen = 1  # default value anyways
        numOfLIS = 1   # default as well
        n = len(nums)
        lenLongest = [0 for i in range(n)] # initialize to all zeroes
        freqLongest = [0 for i in range(n)]
        freqLongest[n-1] = 1
        lenLongest[n-1] = 1
        for i in range(n-2,-1,-1):
            ithMaxLen = 1   # elem stand-alone case
            ithCount = 1
            for j in range(i+1,n,1):
                if nums[i] < nums[j]:
                    curMaxLen = 1 + lenLongest[j]
                    if curMaxLen > ithMaxLen :
                        ithMaxLen = curMaxLen
                        ithCount = freqLongest[j]
                    elif curMaxLen == ithMaxLen :
                        ithCount += freqLongest[j]
            # print("[Len = %d \t count = %d]\n" % (ithMaxLen, ithCount))
            if ithMaxLen > maxLen :
                numOfLIS = ithCount
                maxLen = ithMaxLen
            elif ithMaxLen == maxLen :  # ensure not buggy as well too!
                numOfLIS += ithCount
            lenLongest[i] = ithMaxLen
            freqLongest[i] = ithCount
        return numOfLIS
