'''
URL = https://leetcode.com/problems/check-if-all-as-appears-before-all-bs/
2124. Check if All A's Appears Before All B's
While loop over for loop optimization here
'''

import numpy
import sys

class Solution:
    def checkString(self, s: str) -> bool:
        i = 0
        n = len(s)
        hitB = False
        while i < n:
            if s[i] == 'b' :
                hitB = True
            elif s[i] == 'a' and hitB :
                return False
            i += 1
        return True
            
        
