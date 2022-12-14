'''
URL = https://leetcode.com/problems/longest-uncommon-subsequence-i/
521. Longest Uncommon Subsequence I
Seems like a greedy algorithm; hashmap for char frequency counting too?

Complexity
Let A := len(A) and B := len(B)
Time = O(A) + O(B)
Space = O(1) ( EXP ) O(1) ( IMP ) 
Check most uncomon : str1 and str2

'''
# https://stackoverflow.com/questions/10617045/how-to-create-a-fix-size-list-in-python

def getDeltaDiff(freqMapOne: List[int], freqMapTwo: List[int]) -> int:
    delta = 0
    for i in range(len(freqMapOne)):
        if(freqMapOne[i] >= freqMapTwo[i]):
            delta += (freqMapOne[i] - freqMapTwo[i])
    return delta

def getFreqMapOfCharacters(input: str) -> List[int]:
    freqMap = [0 for i in range(26)]
    for letter in input:
        letterIndex = ord(letter) - ord('a')
        freqMap[letterIndex] += 1
    return freqMap
        
class Solution:

    def findLUSlength(self, a: str, b: str) -> int:
        if(len(b) > len(a)):
            return len(b)
        elif(len(a) > len(b)):
            return len(a)
        if(a != b):
            return len(a)
            # WOW
        myLUSLen = -1
        lenA = len(a)
        lenB = len(b)
        # Not all contianers are mutable.
        aSorted = ''.join(sorted(a))
        bSorted = ''.join(sorted(b)) # Is the sort algo in place or not in-place ( avoid extra ops here)
        print(aSorted)
        print(bSorted)
        freqMapA = getFreqMapOfCharacters(a)
        freqMapB = getFreqMapOfCharacters(b)
        # Method must be declared before calling it.
        lenLUSFromA = getDeltaDiff(freqMapA,freqMapB)
        lenLUSFromB = getDeltaDiff(freqMapB,freqMapA)
        # If eithrer is 0, then no distinct characters in both of them!
        if(lenLUSFromA > 0 or lenLUSFromB > 0):
            myLUSLen = max(lenLUSFromA, lenLUSFromB)
        # Code of "-1" denotes result not found.
        return myLUSLen
