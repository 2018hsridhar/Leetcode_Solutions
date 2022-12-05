'''
URL = https://leetcode.com/problems/largest-local-values-in-a-matrix/
A non-sliding window Solution
2373. Largest Local Values in a Matrix

Complexity
Let N := len(grid) in both dims
Time = O(N^2)
Space = O(N^2) ( EXP ) O(1) ( IMP ) 

Not ethta n will be >= 3 : a valid entry exists

18 mins Python3 coding.
3 mins ++ debug var scope collisions!
'''
def inBounds(grid: List[List[int]], i: int, j: int):
    m = len(grid)
    n = len(grid[0])
    inBound = False
    if((0 <= i and i < m) and (0 <= j and j < n)):
        inBound = True
    return inBound

def getMaxVal(grid: List[List[int]], i: int, j: int):
    maxVal = float("-inf")
    # Initialize-list syntax
    deltaArr = [[-1,-1],[-1,0],[-1,1],[0,-1],[0,0],[0,1],[1,-1],[1,0],[1,1]]
    for k in range(0,len(deltaArr), 1): # Scoped loop var collides with passed in var here!
        step = deltaArr[k]
        childR = i + step[0]
        childC = j + step[1]
        # print("child R,C = %d,%d\n" % (childR,childC))
        if(inBounds(grid,childR,childC)):
            maxVal = max(maxVal, grid[childR][childC])
    return maxVal

class Solution:
    def largestLocal(self, grid: List[List[int]]) -> List[List[int]]:
        # https://stackoverflow.com/questions/2397141/how-to-initialize-a-two-dimensional-array-in-python
        n = len(grid)
        #List[List[int]] 
        resultArr = [[0 for i in range(n-2)] for j in range(n-2)]
        # print(type(resultArr)) # Verify via type(<identifier>) method
        # Make sure we employ correct offseting here
        for i in range(0,len(resultArr),1): # range applies only on iterable types
            myRow = resultArr[i]
            for j in range(0,len(myRow),1):
                childR = i + 1
                childC = j + 1
                # print("Eval R,C = %d,%d\n" % (childR,childC))
                myMaxVal = getMaxVal(grid,childR,childC)
                resultArr[i][j] = myMaxVal
                # print(resultArr[i][j])
            # print('\n')
        return resultArr

