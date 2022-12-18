'''
URL = https://leetcode.com/problems/number-of-enclaves/
1020. Number of Enclaves

Let M := len(grid) and N := len(grid[0])
Time = O(MN)
Space = O(MN) ( EXP ) O(1) ( IMP ) 

Explore board first -> then, explore all non-border cells
Incr from the non-bordered cells only

Time taken = 15 minutes ( problem too well known now ) in Py3
Minimal bugs.

'''

def isInBounds(m: int, n: int, i : int, j : int) -> int:
    return ((0 <= i and i < m) and (0 <= j and j < n))

def explore(grid: List[List[int]], startRow : int, startCol : int) -> int:
    sizeOfEnclave = 0
    m = len(grid)
    n = len(grid[0])
    myFrontier = deque()
    myFrontier.append([startRow,startCol])
    cardinalSteps = [
        [-1,0], # North
        [0,1],  # East
        [1,0],  # South
        [0,-1] # West
    ]
    while(len(myFrontier) > 0):
        parentCell = myFrontier.pop()
        parentX = parentCell[0]-
        parentY = parentCell[1]
        if(isInBounds(m,n,parentX,parentY) and grid[parentX][parentY] == 1):
            grid[parentX][parentY] = 2
            sizeOfEnclave += 1
            for cardinalStep in cardinalSteps:
                childCell = [parentX,parentY]
                childCell[0] += cardinalStep[0]
                childCell[1] += cardinalStep[1]
                myFrontier.append(childCell)
    return sizeOfEnclave

class Solution:
    # Check if params passed by val or by ref too!
    def numEnclaves(self, grid: List[List[int]]) -> int:
        myNumberEnclaves = 0
        m = len(grid)
        n = len(grid[0])
        # [1] leftCol and rightCol
        for i in range(m):
            if(grid[i][0] == 1):
                explore(grid,i,0)
            if(grid[i][n-1] == 1):
                explore(grid,i,n-1)
        # [2] topRow and bottomRow
        for j in range(n):
            if(grid[0][j] == 1):
                explore(grid,0,j)
            if(grid[m-1][j] == 1):
                explore(grid,m-1,j)
        # [3] All other cells
        for i in range(1,m-1,1):
            for j in range(1,n-1,1):
                if(grid[i][j] == 1):
                    countOfCurrentEnclave = explore(grid,i,j)
                    myNumberEnclaves += countOfCurrentEnclave
        return myNumberEnclaves
