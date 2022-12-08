'''
URL = https://leetcode.com/problems/best-poker-hand/
2347. Best Poker Hand

What is the best poker hand you can make : think {1,2,3,"Flush"} -- oh

'''
class Solution:
    def bestHand(self, ranks: List[int], suits: List[str]) -> str:
        # https://pythongeeks.org/switch-in-python/
        bestHand = -1
        switch = {
            1 : 'High Card',
            2 : 'Pair',
            3 : "Three of a Kind",
            5 : "Flush"
        }
        myFutureKey = -1
        # Check the suit for Flush
        # https://www.delftstack.com/howto/python/deep-copy-list-in-python/#use-the-copy.deepcopy-function-to-deep-copy-a-list-in-python
        suitCopy = [x for x in suits]
        suitCopy.sort() # Ensure it is func-call syntax
        numSameSuitMax = 1
        numSameSuit = 1
        numSameRank = 1
        numSameRankMax = 1
        for i in range(len(suitCopy) - 1):
            if(suitCopy[i] == suitCopy[i+1]):
                numSameSuit += 1
            else:
                numSameSuit = 1
            numSameSuitMax = max(numSameSuitMax, numSameSuit)
        if(numSameSuitMax == 5):
            myFutureKey = 5
        else:
            # Check for rank now, not suit
            rankCopy = [x for x in ranks]
            rankCopy.sort()
            for i in range(len(rankCopy)-1):
                if(rankCopy[i] == rankCopy[i+1]):
                    numSameRank += 1
                else:
                    numSameRank = 1
                numSameRankMax = max(numSameRankMax, numSameRank)
            numSameRankMax = min(numSameRankMax,3)
        myFutureKey = max(myFutureKey,numSameRankMax)
        bestHandStr= switch.get(myFutureKey, "High Card") # default "High Card" for all other hands here ( the default  hand TBH in Texas Hold'Em )
        return bestHandStr
