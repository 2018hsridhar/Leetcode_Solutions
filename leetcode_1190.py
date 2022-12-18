'''
URL = https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/
1190. Reverse Substrings Between Each Pair of Parentheses

Complexity : 
Let N := len(str)
Time = O(N)
Space = O(N) ( EXP ) O(1) ( IMP )

20 mins
'''
class Solution:
    def reverseParentheses(self, s: str) -> str:
        myReversedString = ""
        tokenStk = deque([]) # no inbuilt stack : use this instead
        rPtr = 0
        n = len(s)
        while(rPtr < n):
            if(s[rPtr] == '('):
                tokenStk.append("")
            elif(s[rPtr] == ')'): # balance guarantee : a stack record exists!
                topEl = tokenStk.pop()
                reversedEl = topEl[::-1]
                if(len(tokenStk) == 0):
                    myReversedString += reversedEl
                else:
                    curLen = len(tokenStk)
                    tokenStk[curLen-1] += reversedEl
            else: # default character case
                if(len(tokenStk) == 0):
                    myReversedString += s[rPtr]
                else:
                    curLen = len(tokenStk)
                    tokenStk[curLen-1] += s[rPtr]
            rPtr += 1
        return myReversedString
