'''
524. Longest Word in Dictionary through Deleting
URL = https://leetcode.com/problems/longest-word-in-dictionary-through-deleting/description/

Complexity
Let D := len(dictionary)
Let S := len(string)
Let W := len(max(S,D[i])) for i in dictionary
Time = O(DW)
Space = O(W) ( EXP ) O(1) ( IMP ) 
'''

def stringHasWordAsSubsequence(s:str, word:str) -> bool:
    wordIsASubsequence = True
    wordPtr = 0
    sPtr = 0
    while(sPtr < len(s) and wordPtr < len(word)):
        if(word[wordPtr] == s[sPtr]):
            sPtr += 1
            wordPtr += 1
        else:
            sPtr += 1
    wordIsASubsequence = False if (wordPtr < len(word)) else True # Ternary
    return wordIsASubsequence

class Solution:
    def findLongestWord(self, s: str, dictionary: List[str]) -> str:
        myLongestWord = ""
        myLongestWordLen = 0
        for word in dictionary:
            if(stringHasWordAsSubsequence(s,word)):
                if(len(word) > myLongestWordLen):
                    myLongestWordLen = len(word) # len for iterable type
                    myLongestWord = word
                elif(len(word) == myLongestWordLen):
                    # Use ASCII/Unicode ordering.
                    if(myLongestWord > word): # is this string comparison in PY?
                        myLongestWord = word
        return myLongestWord
