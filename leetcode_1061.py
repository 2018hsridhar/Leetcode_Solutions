'''
URL = https://leetcode.com/problems/lexicographically-smallest-equivalent-string/
Lexicographically Smallest Equivalent String
1061. Lexicographically Smallest Equivalent String

Complexity
Let N := #-chars in the string
Let S := size of the language ( here, 26 chars ) 

Time = O(N) + O(S^2)
Space = O(S^2)) ( EXP ) O(S) ( IMP ) 

Emphasis on breaking into seperate methods for effective unit testing.
Time spent = 25 minutes.
'''
class Solution:

    def createAdjacencyListFromStrings(self, s1:str, s2:str, letterAdjList:dict) -> None:
        for index in range(len(s1)):
            letOne = s1[index]
            letTwo = s2[index]
            if(letOne not in letterAdjList):
                letterAdjList[letOne] = set()
            if(letTwo not in letterAdjList):
                letterAdjList[letTwo] = set()
            letterAdjList[letOne].add(letTwo)
            letterAdjList[letTwo].add(letOne)

    def dfs(self,letter:str,visitedLetters:set,adjList:dict,charsConnComp:List[str]) -> None:
        hood = adjList[letter]
        charsConnComp.append(letter)
        visitedLetters.add(letter)
        for childLetter in hood:
            if(childLetter not in visitedLetters):
                Solution.dfs(self,childLetter,visitedLetters,adjList,charsConnComp)

    def getSmallestLetter(self,charsConnComp:List[str]) -> str:
        smallestLetter = charsConnComp[0]
        for letter in charsConnComp:
            if(letter < smallestLetter):
                smallestLetter = letter
        return smallestLetter

    def executeEquivalencyConversion(self,letterToSmallestLetterMap:dict,baseStr:str) -> str:
        result = ""
        for letter in baseStr:
            if(letter not in letterToSmallestLetterMap):
                equivLetter = letter
                result += equivLetter
            else:
                equivLetter = letterToSmallestLetterMap[letter]
                result += equivLetter
        return result

    def smallestEquivalentString(self, s1: str, s2: str, baseStr: str) -> str:
        letterToSmallestLetterMap = dict()
        letterAdjList = dict()
        Solution.createAdjacencyListFromStrings(self,s1,s2,letterAdjList)
        visitedLetters = set()
        for letter in letterAdjList:
            if(letter not in visitedLetters):
                charsConnComp = []
                Solution.dfs(self,letter,visitedLetters,letterAdjList,charsConnComp)
                smallestLetter = Solution.getSmallestLetter(self,charsConnComp)
                for letter in charsConnComp:
                    letterToSmallestLetterMap[letter] = smallestLetter
        return Solution.executeEquivalencyConversion(self,letterToSmallestLetterMap,baseStr)
