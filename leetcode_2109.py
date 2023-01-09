'''
URL = https://leetcode.com/problems/adding-spaces-to-a-string/
2109. Adding Spaces to a String
Current/Next node sequencing.

Consecutive sequence + pythonic string splicing :-)
Time = O(N)
Space = O(1) ( EXP & IMP ) 
N := len(s)

Test Cases
(A) s = "test" spaces = [0,1,2,3] => " t e s t"
(B) s = "abcdef" spaces = [0,1,2,3,4,5] => " a b c d e f"
(C) s = "a" spaces = [0] = " a"


'''
class Solution:
    def addSpaces(self, s: str, spaces: List[int]) -> str:
        spaceAddedString = ""
        indexOne = 0
        indexTwo = 0
        for spacePosition in spaces:
            indexTwo = spacePosition
            stringSplice = s[indexOne:indexTwo]
            spaceAddedString += stringSplice
            spaceAddedString += " "
            indexOne = indexTwo
        spaceAddedString += s[indexOne:len(s)] # final string
        return spaceAddedString
