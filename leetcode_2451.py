'''
URL = https://leetcode.com/problems/odd-string-difference/
2451. Odd String Difference

Complexity
Let W := # words in words
Let K := len(max_word)
Time = O(WK)
Space = O(1) ( IMP ) O() ( EXP ) 
'''
# TBH, we could make concatenate strings instead ( simplifies problem massively)
# well . . . do append a "-" to help out too!
def getStringifiedDifferenceArrayOfWord(word: str) -> str:
    differenceList = ""
    for idx in range(len(word) - 1):
        delta = str(ord(word[idx+1]) - ord(word[idx]))
        differenceList = differenceList + delta + '-'
    return differenceList

class Solution:
    def oddString(self, words: List[str]) -> str:
        myOddString = ""
        freqDifferenceArrays = {}
        for word in words:
            serializedWordDifferenceArray = getStringifiedDifferenceArrayOfWord(word)
            if(serializedWordDifferenceArray not in freqDifferenceArrays):
                freqDifferenceArrays[serializedWordDifferenceArray] = 0
            freqDifferenceArrays[serializedWordDifferenceArray] += 1
        for word in words:
            serializedWordDifferenceArray = getStringifiedDifferenceArrayOfWord(word)
            if(freqDifferenceArrays[serializedWordDifferenceArray] == 1):
                myOddString = word
                break
        return myOddString
