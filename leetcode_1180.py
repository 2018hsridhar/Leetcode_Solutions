'''
URL = https://leetcode.com/problems/count-substrings-with-only-one-distinct-letter/
1180. Count Substrings with Only One Distinct Letter
Complexity
Let N := len(s)
Time = O(N)
Space = O(1) ( EXP & IMP ) 
Float introduced somehow?
'''
class Solution:
    def countLetters(self, s: str) -> int:
        countDist = 0
        i = 0
        n = len(s)
        curLen = 0
        while(i < n):
            if(s[i] == s[i-1]):
                curLen += 1
            else:
                # countDist += ((curLen)*(curLen + 1)/2) # 0-case handled here
                countDist += int((curLen)*(curLen + 1)/2) # 0-case handled here
                curLen = 1
            i += 1
        # Account curLen @ end again
        countDist += int((curLen)*(curLen + 1)/2)
        # return int(countDist)
        return countDist
Console
