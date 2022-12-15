'''
Your sectors are labeled 1->n in the CCW ordering.
Delta diff of round_i vs. round_{i-1} thing.
Most visitied sectors, sorted in ASC order.
Visit in modular arithmetic form.
Count o each visited sector.

1560. Most Visited Sector in a Circular Track
URL = https://leetcode.com/problems/most-visited-sector-in-a-circular-track/

'''
class Solution:
    def mostVisited(self, n: int, rounds: List[int]) -> List[int]:
        maxTimesSingleSectorVisited = 0
        sectorVisitFreq = {}
        for sector in range(1,n+1,1):
            sectorVisitFreq[sector] = 0
        for i in range(len(rounds) - 1):
            curSector = rounds[i]
            nextSector = rounds[i+1]
            curModulo = curSector
            while(curModulo != nextSector): # TLE err
                sectorVisitFreq[curModulo] += 1
                if(sectorVisitFreq[curModulo] > maxTimesSingleSectorVisited):
                    maxTimesSingleSectorVisited = sectorVisitFreq[curModulo]
                curModulo += 1
                if(curModulo > n):
                    curModulo -= n
        # final case handling for the last sector though!
        finalSector = rounds[-1]
        if(finalSector not in sectorVisitFreq):
            sectorVisitFreq[finalSector] = 0
        sectorVisitFreq[finalSector] += 1
        if(sectorVisitFreq[finalSector] > maxTimesSingleSectorVisited):
            maxTimesSingleSectorVisited = sectorVisitFreq[finalSector]
        mostVisitedSectors = []
        for key,val in sectorVisitFreq.items():
            if(val == maxTimesSingleSectorVisited):
                mostVisitedSectors.append(key)
        mostVisitedSectors.sort()
        return mostVisitedSectors
