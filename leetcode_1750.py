'''
URL = https://leetcode.com/problems/minimum-length-of-string-after-deleting-similar-ends/
1750. Minimum Length of String After Deleting Similar Ends

Complexity
Let n := len(s)
Time = O(N)
Space = O(1) ( EXP ) O(1) ( IMP ) 

Algo : Two Pointers, String, Linear Scanning.

Test Cases :
(A) "aaaaa"
(B) "abcba"
(C) "abcabcabcabc"
(D) "aabbbccccbaaa"
(E) "b"
(F) "cab"

Can we avoid destructing the string too!
14.5 mins to solution

'''
class Solution:
    def minimumLength(self, s: str) -> int:
        myMinLength = len(s) # initially this
        lPtr = 0
        rPtr = len(s) - 1
        while(lPtr < rPtr):
            canRemovePrefixAndSuffix = False
            while(lPtr < rPtr and s[lPtr] == s[rPtr]):
                canRemovePrefixAndSuffix = True
                changedPointer = False
                if(lPtr + 1 < rPtr and s[lPtr] == s[lPtr+1]):
                    lPtr = lPtr + 1
                    changedPointer = True
                if(rPtr - 1 > lPtr and s[rPtr] == s[rPtr - 1]):
                    rPtr = rPtr - 1
                    changedPointer = True
                if(changedPointer == False):
                    break
            # left, right at their maxes now!
            # interior only
            if(canRemovePrefixAndSuffix == True):
                myMinLength = min(myMinLength, (rPtr - lPtr - 1))
                lPtr += 1
                rPtr -= 1
            else:
                break
        return myMinLength
