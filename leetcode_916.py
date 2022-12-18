'''
URL = https://leetcode.com/problems/word-subsets/
916. Word Subsets

Complexity
Let m := len(words1) and n := len(words2)
Time = O(mn)
Space = O(1) EXP O(1) ( IMP ) 

Tries probably do not help.
Problem still seems to be frequency/counting based.

Pass but TLE here
It is hashtable and string based

29 mins ( with 7.5 min video on PY ) :-)
Brute force -> Efficient sol
'''

def getFrequencyMap(word: str) -> List[int]:
    letterFreqMap = [0 for i in range(26)]
    for letter in word:
        charIndex = ord(letter) - ord('a')
        letterFreqMap[charIndex] += 1
    return letterFreqMap

def computeMaximalFreqMapsInWords2(words2: List[str]) -> List[int]:
    words2MaximalFreqMap = [0 for i in range(26)]
    for word in words2:
        myCurrWordFreqMap = getFrequencyMap(word)
        for index in range(len(words2MaximalFreqMap)):
            words2MaximalFreqMap[index] = max(words2MaximalFreqMap[index], myCurrWordFreqMap[index])
    return words2MaximalFreqMap

def secondWordIsSubsetFirstWord(freqMapOne: List[int], freqMapTwo: List[int]) -> bool:
    passSubsetTest = True
    for index in range(len(freqMapOne)):
        if(freqMapTwo[index] > freqMapOne[index]):
            passSubsetTest = False
            break
    return passSubsetTest

class Solution:
    def wordSubsets(self, words1: List[str], words2: List[str]) -> List[str]:
        myUniversalWords = []
        words2MaximalFreqMap = computeMaximalFreqMapsInWords2(words2)
        # print(words2MaximalFreqMap)
        for word in words1:
            isWordUniversal = True
            firstWordMap = getFrequencyMap(word)
            if(secondWordIsSubsetFirstWord(firstWordMap,words2MaximalFreqMap) == False):
                isWordUniversal = False
            if(isWordUniversal):
                myUniversalWords.append(word)
        return myUniversalWords
