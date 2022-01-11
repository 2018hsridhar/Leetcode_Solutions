'''
URL = https://leetcode.com/problems/check-if-string-is-a-prefix-of-array/
1961. Check If String Is a Prefix of Array

Problem def be easier than you think here
Concatenate first "k" strings in words array AND k no larger than words.length


TEST CASES 
(A)
(B)
(C)
(D)
(E)

'''
class Solution:
    def isPrefixString(self, s: str, words: List[str]) -> bool:
        result = False
        cur = ""
        for el in words: # Element container iteration heree
            cur = cur + el
            if cur == s:
                result = True
                break
            elif cur != s and len(cur) > len(s):
                result = False
                break
        return result
        
