
'''
1387. Sort Integers by The Power Value

'''
import sys

class Solution:

    # User-defined functions ( versus lambda function ) 
    # Solution is right, but, running into TLE exceptions here ( 4.5 ms time )
    # How to speed up pythonic code?
    # Try this weird cache now
    # We need the cache technique : do NOT allocate a 1,1e9 sized array of continguous memory!
    def recurse(self, value: int, DP: list) -> None :
        res = 0
        if value not in DP:
            if value == 1:
                return 1
            else:
                halfVal = int(value/2)
                if value % 2 == 0 :
                    res = 1 + self.recurse(halfVal,DP)
                elif value % 2 == 1 :
                    res = 1 + self.recurse(value*3 + 1, DP)
            DP[value] = res
        elif value in DP:
            res = DP[value]
        return res
    
    # Your python solution is indeed correct
    def getKth(self, lo: int, hi: int, k: int) -> int:
        
        kthVal = -1
        highest = 3*hi + 1
        DP = {1:0} #[0 for i in range(1300000) ] # Allocation taking too much time!
        DP[0] = 0
        DP[1] = 0
        DP[2] = 1
        for value in range(1,highest + 1,1):
            self.recurse(value,DP)
        powValTuples = []
        for elem in range(lo,hi+1,1):
            pairing = (elem, self.recurse(elem,DP))
            powValTuples.append(pairing)
        ascVals = sorted(powValTuples, key = lambda x : (x[1], x[0]))
        kthTuple = ascVals[k-1]
        kthVal = kthTuple[0]
        return kthVal
