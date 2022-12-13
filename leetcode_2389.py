'''
Greedy Solution
2389. Longest Subsequence With Limited Sum
URL = https://leetcode.com/problems/longest-subsequence-with-limited-sum/description/

Complexity
Let N := len(nums) and Q := len(queries)
Time = O(NlgN) + O(QN)
Space = O(Q) ( EXP ) O(1) ( IMP ) 
'''
class Solution:
    def answerQueries(self, nums: List[int], queries: List[int]) -> List[int]:
        answersToQueries = []
        nums.sort()
        for query in queries:
            myRunningSum = 0
            myRunningSumLen = 0
            for ptr in range(len(nums)):
                if(myRunningSum + nums[ptr] <= query):
                    myRunningSum = myRunningSum + nums[ptr]
                    myRunningSumLen = myRunningSumLen + 1
                else:
                    break
            answersToQueries.append(myRunningSumLen)
        return answersToQueries
