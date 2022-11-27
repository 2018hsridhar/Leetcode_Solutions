# URL = https://leetcode.com/problems/apply-operations-to-an-array/description/
# 2460. Apply Operations to an Array

class Solution:
    def applyOperations(self, nums: List[int]) -> List[int]:
        results = []
        n = len(nums)
        countZero = 0
        for i in range(0,n-1,1):
            if( nums[i] == nums[i+1] ):
                nums[i] *= 2
                nums[i+1] = 0
        for i in range(0,n,1):
            if(nums[i] != 0):
                results.append(nums[i])
            else:
                countZero += 1 # no prefix/postfix ++ notation
        while(countZero > 0):
            results.append(0)
            countZero -= 1
        return results

