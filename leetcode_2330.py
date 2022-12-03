# 2330. Valid Palindrome IV
# URL = https://leetcode.com/problems/valid-palindrome-iv/description/
# Let N := len(s)
# TIME = O(N) Space = O(1) ( EXP ) ( IMP ) 
# 7 mins - not a tough problem
class Solution:
    def makePalindrome(self, s: str) -> bool:
        status = True
        n = len(s)
        lPtr = 0
        rPtr = n-1
        numOps = 0
        while(lPtr < rPtr):
            if (s[lPtr] != s[rPtr]):
                numOps += 1
            if(numOps > 2):
                status = False
                break
            lPtr += 1
            rPtr -= 1
        return status;
