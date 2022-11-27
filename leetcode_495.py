# https://leetcode.com/problems/teemo-attacking/description/
# 495. Teemo Attacking
class Solution:
    def findPoisonedDuration(self, timeSeries: List[int], duration: int) -> int:
        timePoisoned = 0
        for i in range(1,len(timeSeries),1):
            prevStartTime = timeSeries[i-1]
            prevEndTime = timeSeries[i-1] + duration - 1
            curStartTime = timeSeries[i]
            if(prevEndTime < curStartTime):
                timePoisoned += (prevEndTime - prevStartTime + 1)
            else:
                timePoisoned += (curStartTime - prevStartTime)
        # Handle last element case too!
        timePoisoned += duration # final case : restart here anyways
        return timePoisoned
