'''
URL = https://leetcode.com/problems/minimum-recolors-to-get-k-consecutive-black-blocks/
2379. Minimum Recolors to Get K Consecutive Black Blocks

Complexity
Let N := len(blocks)
Time = O(N)
Space = O(1) ( EXP ) ( IMP ) 

Type : Sliding window, 2 ptrs, linear scan.
'''
class Solution:
    def minimumRecolors(self, blocks: str, k: int) -> int:
        minNumOps = sys.maxsize # supports int casting
        rPtr = 0
        lPtr = 0
        n = len(blocks)
        runningNumberBlackBlocks = 0
        while(rPtr < n):
            if(blocks[rPtr] == "B"):
                runningNumberBlackBlocks += 1
            curWindowLen = (rPtr - lPtr + 1)
            if(curWindowLen == k + 1):
                if(blocks[lPtr] == "B"):
                    runningNumberBlackBlocks -= 1
                lPtr += 1
                curWindowLen -= 1
            if(curWindowLen == k):
                runningNumberWhiteBlocks = k - runningNumberBlackBlocks
                minNumOps = min(minNumOps, runningNumberWhiteBlocks)
            rPtr += 1
        return minNumOps
