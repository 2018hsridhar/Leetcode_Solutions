# 2485. Find the Pivot Integer
# URL = https://leetcode.com/problems/find-the-pivot-integer/
# N goes up to 1K here
# Doing brute force, linear solution ( could try binary search ) 
# Leverage sum(natural numbers ) formula.
# Inclusive range
class Solution:
    def pivotInteger(self, n: int) -> int:
        result = 0
        if(n == 1):
            return 1
        totalSum = int(n*(n+1)/2)
        pivotInt = -1
        for x in range(1,n,1):
            sumLeft = int(x*(x+1)/2)
            sumRight = totalSum - sumLeft + x # Inclusive x back here
            if(sumLeft == sumRight):
                pivotInt = x
                break
        return pivotInt
