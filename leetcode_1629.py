# https://leetcode.com/problems/slowest-key/description/
# 1629. Slowest Key
class Solution:
    def slowestKey(self, releaseTimes: List[int], keysPressed: str) -> str:
        longestRelTime = 0
        longestKey = 'a'
        for i in range(0,len(releaseTimes),1):
            keyPressed = keysPressed[i]
            relTime = releaseTimes[i] if (i == 0) else releaseTimes[i] - releaseTimes[i-1]
            if (relTime >= longestRelTime) :
                if(relTime > longestRelTime):
                    longestKey = keyPressed
                elif(relTime == longestRelTime and ord(keyPressed) >= ord(longestKey)):
                    longestKey = keyPressed
                longestRelTime = relTime
        return longestKey

