# https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/description/
# 1456. Maximum Number of Vowels in a Substring of Given Length
# Lambda syntax in python
# 18 mins
def isVowel(c: str) -> bool:
    if(c == 'a' or c == 'e' or c == 'i' or c == 'o' or c == 'u'):
        return True
    return False

# Why doesn't class Solution take this function?
class Solution:
    # HM style in function signature.
    # Of length = k only
    def maxVowels(self, s: str, k: int) -> int:
        lenLongestStr = 0
        lPtr = 0
        rPtr = 0
        n = len(s)
        vowelCount = 0
        maxNumVowelLet = 0
        while(rPtr < n):
            # Incr the right
            c = s[rPtr]
            # Compiler checks if names are defined.
            if(isVowel(c)):
                vowelCount += 1
            # Decr the left
            if(rPtr - lPtr + 1 > k):
                if(isVowel(s[lPtr])):
                    vowelCount -= 1
                lPtr += 1
            maxNumVowelLet = max(maxNumVowelLet, vowelCount)
            rPtr += 1
        return maxNumVowelLet
