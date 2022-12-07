'''
1539. Kth Missing Positive Number
URL = https://leetcode.com/problems/kth-missing-positive-number/description/
'''
class Solution:
    def findKthPositive(self, arr: List[int], k: int) -> int:
        seed = 1
        while(k > 0):
            if(seed in arr):
                seed += 1
            elif(seed not in arr):
                k -= 1
                if(k == 0):
                    break
                seed += 1
        kthMissing = seed
        return kthMissing
