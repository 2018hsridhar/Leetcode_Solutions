'''
URL = https://leetcode.com/problems/cells-in-a-range-on-an-excel-sheet/
2194. Cells in a Range on an Excel Sheet

Guaranteed within alphabets, and within 1-9, all upper case.
No input handling.
[A-Z] only.

14 mins
'''
class Solution:
    def cellsInRange(self, s: str) -> List[str]:
        myFinalCells = []
        delim = ':'
        myTokens = s.split(delim)
        colStart = myTokens[0][0]
        colEnd = myTokens[1][0]
        rowStart = int(myTokens[0][1])
        rowEnd = int(myTokens[1][1])
        # https://www.geeksforgeeks.org/ways-increment-character-python/
        while(ord(colStart) <= ord(colEnd)):
            # print(colStart) # Col start is correct
            # r = rowStart
            # while(r <= rowEnd):
            # for r in (rowStart,rowEnd): # INcorrect Python syntax
            for r in range(rowStart,rowEnd+1,1):
                nextCell = colStart + str(r) # Seems the '1' here is an extra offset!
                print(nextCell)
                myFinalCells.append(nextCell)
                # r += 1
            colStart = chr(ord(colStart) + 1) # A lot of casting here
        return myFinalCells
