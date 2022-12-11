'''
1668. Maximum Repeating Substring

'''
class Solution:
    def maxRepeating(self, sequence: str, word: str) -> int:
        if(len(word) > len(sequence)):
            return 0
        kValue = 0
        n = len(sequence)
        matchStartIndices = []
        for i in range(len(sequence)):
            seqPtr = i
            wordPtr = 0
            hasMatch = True
            while(wordPtr < len(word) and seqPtr < len(sequence)):
                if(word[wordPtr] == sequence[seqPtr]):
                    seqPtr += 1
                    wordPtr += 1
                else:
                    hasMatch = False
                    break
            if(wordPtr >= len(word) and seqPtr <= len(sequence)):
                hasMatch = True
            else:
                hasMatch = False
            if(hasMatch):
                matchStartIndices.append(i)
        matchStartIndices.sort() # prefer a set instead / find
        if(len(matchStartIndices) == 0):
            kValue = 0
        else:
            # Waiting on these TLE takes up time for solving.
            # 15 mins already oh jeez!
            for val in matchStartIndices:
                runLen = 1
                curPos = val
                while(True):
                    kValue = max(kValue, runLen)
                    curPos = curPos + len(word) # this was your bug ( gaaah was set to val ) 
                    if(curPos not in matchStartIndices):
                        break
                    runLen += 1
        return kValue
