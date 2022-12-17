'''
URL = https://leetcode.com/problems/find-all-groups-of-farmland/
1992. Find All Groups of Farmland

Complexity :
Let M,N := dims(land)
Time = O(MN)
Space = O(MN) ( EXP ) O(1) ( IMP ) 

'''
# THis does copy : may be slow ( pass dims instead )
# Check undertand of passing of variables in Python3 : are they by-val or by-ref!!


def isInBounds(maxRows : int, maxCols : int, myRow : int, myCol : int) -> bool:
    return ((0 <= myRow and myRow < maxRows) and (0 <= myCol and myCol < maxCols))

def exploreFarmlandInternal(land: List[List[int]], topLeftRow: int, topLeftCol : int) -> List[int]:
    m = len(land)
    n = len(land[0])
    bottomRightRow = topLeftRow
    bottomRightCol = topLeftCol
    # Explore only two dirs, from each cellValue
    # Check visited state of a cell too ( set to 2, to denote visited )
    queue = deque() # preferred from the collections module.
    queue.append([topLeftRow, topLeftCol]) # {} makes a set 
    deltaSteps = [
        [0,1], # bottom
        [1,0]  # right
    ]
    while(len(queue) > 0):
        parentCell = queue.popleft()
        parentX = parentCell[0]
        parentY = parentCell[1]
        if(isInBounds(m,n,parentX, parentY)):
            if(land[parentCell[0]][parentCell[1]] == 1): # denote unprocessed
                land[parentCell[0]][parentCell[1]] = 2 # denote hasBeenVisited
                bottomRightRow = max(bottomRightRow, parentX)
                bottomRightCol = max(bottomRightCol, parentY)
                for step in deltaSteps: # Something igoing wrong here
                    childCell = [0,0]   # bug : childCell = parentCell, was not a deep copy operation? huh?
                    childCell[0] = parentCell[0] + step[0]
                    childCell[1] += parentCell[1] + step[1]
                    queue.append(childCell) # append to right
    myFarmGroup = [topLeftRow,topLeftCol,bottomRightRow, bottomRightCol]
    return myFarmGroup

class Solution:
    def findFarmland(self, land: List[List[int]]) -> List[List[int]]:
        myFarmlandGroups = []
        m = len(land)
        n = len(land[0])
        for i in range(m):
            for j in range(n):
                cellValue = land[i][j]
                if(cellValue == 1): # hit farmland
                    print("HIT")
                    nextFarmGroup = exploreFarmlandInternal(land, i, j)
                    myFarmlandGroups.append(nextFarmGroup)
                # Avoid extraneous else-continue clauses
        return myFarmlandGroups
