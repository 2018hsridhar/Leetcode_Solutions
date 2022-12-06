'''
2452. Words Within Two Edits of Dictionary
https://leetcode.com/problems/words-within-two-edits-of-dictionary/

Complexity
Let Q := len(queries) and D := len(dictionary)
Let N := len(queries[0])
Let \Sigma = 26 [a-z]
Time = O(QDN)
Space = O(1) ( EXP ) O(1 ) ( IMP ) 
'''
def dist(query:str, word:str):
    myDiff = 0
    for i in range(len(query)):
        if(query[i] != word[i]):
            myDiff += 1
    return myDiff

class Solution:
    def twoEditWords(self, queries: List[str], dictionary: List[str]) -> List[str]:
        queriesHit = []
        # https://www.askpython.com/python/list/iterate-through-list-in-python
        for idx, query in enumerate(queries,0): # Default of 0 for iterable : index of counting
            hitWord = False
            for word in dictionary:
                if(dist(query,word) <= 2):
                    hitWord = True
                    break
            if(hitWord):
                queriesHit.append(query)
        return queriesHit
