'''
1337. The K Weakest Rows in a Matrix
URL = https://leetcode.com/problems/the-k-weakest-rows-in-a-matrix/description/
'''
def getSoldierCountOfRow(row: List[int]) -> int:
    soldierCount = 0
    for soldier in row:
        if(soldier == 1):
            soldierCount += 1
    return soldierCount

class Solution:
    def kWeakestRows(self, mat: List[List[int]], k: int) -> List[int]:
        myKWeakestRows = []
        numRows = len(mat)
        numCols = len(mat[0])
        rowRecords = []
        for row in range(0,numRows,1):
            myCountOfSodliersForRow = getSoldierCountOfRow(mat[row])
            rowRecord = [row, myCountOfSodliersForRow]
            rowRecords.append(rowRecord)
        # https://www.delftstack.com/howto/python/lambda-functions-in-sort/
        # Get valid syntax here
        sortedRowRecords = sorted(rowRecords, key=lambda x: (x[1], x[0]))
        # Presuming that the rowRecords have been ordered
        for a in range(k):
            myKWeakestRows.append(sortedRowRecords[a][0])
        return myKWeakestRows
