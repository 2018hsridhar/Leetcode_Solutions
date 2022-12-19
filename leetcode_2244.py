'''
URL = https://leetcode.com/problems/minimum-rounds-to-complete-all-tasks/description/
2244. Minimum Rounds to Complete All Tasks

Complexity
Let N := len(tasks)
Time = O(nlgn)
Space = O(1) ( EXP && IMP ) 
Passes, but ran into a TLE
How to speed up python here?
'''

# Greedy approach ( use math here ) 
# of use DP instead ( solve optimal for earlier cases too ) 
# Seems better anyways!
# TD memo ( speed ) 
def minRoundsForCurrentNumberTasksGreedy(numTasks : int) -> int:
    rounds = 0
    if(numTasks < 2):
        return -1
    elif(numTasks == 2 or numTasks == 3):
        return 1
    elif(numTasks > 3):
        if(numTasks % 2 == 1):
            numTasks -= 3
            rounds += 1
        while(numTasks - 6 >= 0):
            numTasks -= 6
            rounds += 2
        rounds += int(numTasks/2)
    return rounds

def minRoundsForCurrentNumberTasks(numTasks : int, cache: dict) -> int:
    if(numTasks in cache):
        return cache[numTasks]
    if(numTasks < 2):
        return -1
    elif(numTasks == 2 or numTasks == 3):
        return 1
    elif(numTasks > 3):
        minRoundsNeeded = sys.maxsize
        minCaseThree = minRoundsForCurrentNumberTasks(numTasks - 3,cache)
        minCaseTwo = minRoundsForCurrentNumberTasks(numTasks - 2,cache)
        if(minCaseThree != -1):
            minRoundsNeeded = min(minRoundsNeeded,1 + minCaseThree)
        if(minCaseTwo != -1):
            minRoundsNeeded = min(minRoundsNeeded,1 + minCaseTwo)
        if(minRoundsNeeded == sys.maxsize):
            return -1
        return minRoundsNeeded
    return -1

class Solution:
    def minimumRounds(self, tasks: List[int]) -> int:
        minRoundsToFinishTasks = 0
        tasks.sort()
        rPtr = 0
        n = len(tasks)
        runLen = 1
        cache = dict()
        while(rPtr < n - 1):
            if(tasks[rPtr] == tasks[rPtr + 1]):
                runLen += 1
            else:
                # delta = minRoundsForCurrentNumberTasks(runLen,cache)
                delta = minRoundsForCurrentNumberTasksGreedy(runLen)
                if(delta == -1):
                    return -1
                minRoundsToFinishTasks += delta
                runLen = 1
            rPtr += 1
        # delta = minRoundsForCurrentNumberTasks(runLen,cache)
        delta = minRoundsForCurrentNumberTasksGreedy(runLen)
        if(delta == -1):
            return -1
        minRoundsToFinishTasks += delta
        return minRoundsToFinishTasks
