'''
URL = https://leetcode.com/problems/make-the-string-great/
1544. Make The String Great

Algos : Stack, Linear Scan
Time = O(N)
Space = O(N) ( EXP ) O(1) ( IMP ) 

Careful on handling 
Time = 10 minutes

Test Cases
(A)
(B)
(C)
(D)
(E)

'''
class Solution:
    def makeGood(self, s: str) -> str:
        goodStr = ""
        strStk = []
        letterCaseDelta = abs(ord('A') - ord('a')) # can't tell, so use abs difference :-)
        for curLetter in s:
            if(curLetter.isupper()):
                if(len(strStk) >= 1):
                    lastLetter = strStk[-1]
                    curLetterCaseDelta = abs(ord(lastLetter) - ord(curLetter))
                    if(lastLetter.islower() and abs(ord(curLetter) - ord(lastLetter)) == letterCaseDelta):
                        strStk.pop()
                    else:
                        strStk.append(curLetter)
                else:
                    strStk.append(curLetter)
            elif(curLetter.islower()):
                if(len(strStk) >= 1):
                    lastLetter = strStk[-1]
                    curLetterCaseDelta = abs(ord(lastLetter) - ord(curLetter))
                    if(lastLetter.isupper() and abs(ord(curLetter) - ord(lastLetter)) == letterCaseDelta):
                        strStk.pop()
                    else:
                        strStk.append(curLetter)
                else:
                    strStk.append(curLetter)
        goodStr = "".join(strStk)
        return goodStr
