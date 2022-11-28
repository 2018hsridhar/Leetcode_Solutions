# Pure simulation
# https://leetcode.com/problems/incremental-memory-leak/description/
# 1860. Incremental Memory Leak
# 14 mins
class Solution:
    def memLeak(self, memory1: int, memory2: int) -> List[int]:
        timeTaken = 1
        delta = 1
        while(memory1 >= 0 and memory2 >= 0):
            if(memory1 >= memory2 and memory1 - delta >= 0) :
                memory1 -= delta
                delta += 1
                timeTaken += 1
            elif ( memory2 - delta >= 0 ):
                memory2 -= delta
                delta += 1
                timeTaken += 1
            else:
                break
        while(memory1 >= 0):
            if(memory1 - delta >= 0):
                memory1 -= delta
                delta += 1
                timeTaken += 1
            else:
                break
        while(memory2 >= 0):
            if(memory2 - delta >= 0):
                memory2 -= delta
                delta += 1
                timeTaken += 1
            else:
                break
        results = [timeTaken, memory1, memory2]
        return results
