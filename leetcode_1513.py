'''
URL = https://leetcode.com/problems/number-of-substrings-with-only-1s/description/
1513. Number of Substrings With Only 1s

Complexity
Let N := len(s)
Time = O(N)
Space = O(1) ( EXP && IMP ) 

Sliding window method.

5 mins
'''
class Solution:
    def numSub(self, s: str) -> int:
        numberOfStringsAllOnes = 0
        n = len(s)
        lPtr = 0
        rPtr = 0
        inOnesWindow = False
        while(rPtr < n):
            if(s[rPtr] == '0'):
                if(inOnesWindow): # Close out the window now
                    windowSize = (rPtr - lPtr + 1)
                    delta = windowSize * (windowSize - 1 ) / 2
                    numberOfStringsAllOnes += delta
                inOnesWindow = False
            elif(s[rPtr] == '1'):
                if(inOnesWindow == False):
                    lPtr= rPtr
                    inOnesWindow = True
            rPtr += 1
        if(inOnesWindow): # Check if we are still in a window ( to the end ) 
            windowSize = (rPtr - lPtr + 1)
            delta = windowSize * (windowSize - 1 ) / 2
            numberOfStringsAllOnes += delta
        moduland = pow(10,9) + 7
        numberOfStringsAllOnes %= moduland
        return int(numberOfStringsAllOnes)
