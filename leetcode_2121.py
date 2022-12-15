'''
2121. Intervals Between Identical Elements
URL = https://leetcode.com/problems/intervals-between-identical-elements/

Complexity
Let N := len(arr)
Time = O(N)

22 mins
You got the complex iteratoin too!
Space = O(N) ( EXP ) O(1) ( IMP ) 
'''
def executeAlgorithm(arr: List[int], dir: str) -> List[int]:
    myDistances = [0 for i in range(len(arr))]
    step = 1
    start = 0
    stop = len(arr)
    if(dir == 'L'): # read right -> left
        step = - 1
        start = len(arr) - 1
        stop = -1
    valueFreq = dict()
    valueIndexSum = dict()
    # Can include own index too ( it will 0 out ) 
    # TBH, iteration direction has no effect here with valueIndexSum map
    for index in range(start, stop, step):
        # [1] Update hashmaps
        element = arr[index]
        if(element not in valueIndexSum):
            valueIndexSum[element] = 0
        valueIndexSum[element] += index
        if(element not in valueFreq):
            valueFreq[element] = 0
        valueFreq[element] += 1
        # [2] Solve for the deltas
        delta = 0
        if(dir == 'L'):
            delta += valueIndexSum[element]
            delta -= index * valueFreq[element]
        elif(dir == 'R'):
            delta -= valueIndexSum[element]
            delta += index * valueFreq[element]
        myDistances[index] = delta
    return myDistances

class Solution:
    def getDistances(self, arr: List[int]) -> List[int]:
        myDistances = [0 for i in range(len(arr))]
        myLeftDistances = executeAlgorithm(arr, 'L')
        myRightDistances = executeAlgorithm(arr, 'R')
        for index in range(len(arr)):
            myDistances[index] = myRightDistances[index] + myLeftDistances[index]
        return myDistances
