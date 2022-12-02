# 2446. Determine if Two Events Have Conflict
# https://leetcode.com/problems/determine-if-two-events-have-conflict/description/
# 10 mins
def getTimeFromHHMM(time: str):
    times = time.split(":")
    minTime = int(times[1])
    hourTime = int(times[0])
    # print("HH:MM = (%s,%s)\n" % (hourTime,minTime))
    return minTime + (hourTime * 60)
    

class Solution:
    def haveConflict(self, event1: List[str], event2: List[str]) -> bool:
        status = True
        stOne = getTimeFromHHMM(event1[0])
        etOne = getTimeFromHHMM(event1[1])
        stTwo = getTimeFromHHMM(event2[0])
        etTwo = getTimeFromHHMM(event2[1])
        # https://stackoverflow.com/questions/15286401/print-multiple-arguments-in-python
        # print("Start,End of eOne = [%s,%s]\n" % (stOne,etOne))
        # print("Start,End of eTwo = [%s,%s]\n" % (stTwo,etTwo))
        status = ((stOne <= stTwo and stTwo <= etOne) or (stTwo <= stOne and stOne <= etTwo))
        return status
