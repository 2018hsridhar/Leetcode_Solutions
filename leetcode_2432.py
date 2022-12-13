'''
URL = https://leetcode.com/problems/the-employee-that-worked-on-the-longest-task/
2432. The Employee That Worked on the Longest Task
'''
class Solution:
    def hardestWorker(self, n: int, logs: List[List[int]]) -> int:
        theHardestWorker = -1
        n = len(logs)
        taskBeginTime = 0
        mostTimeWorkerSpentOnATask = 0
        for logEntry in logs:
            workerId = logEntry[0]
            taskEndTime= logEntry[1]
            timeTakenForTask = taskEndTime - taskBeginTime
            if(timeTakenForTask > mostTimeWorkerSpentOnATask):
                mostTimeWorkerSpentOnATask = timeTakenForTask
                theHardestWorker = workerId
            elif(timeTakenForTask == mostTimeWorkerSpentOnATask):
                if(workerId < theHardestWorker):
                    theHardestWorker = workerId
            taskBeginTime = taskEndTime
        return theHardestWorker
