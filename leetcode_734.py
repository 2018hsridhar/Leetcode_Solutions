'''
URL = https://leetcode.com/problems/sentence-similarity/description/
734. Sentence Similarity

Complexity
Let M := len(sentence1) = N = len(sentence2)
Time = O(N)
Space = O(N) ( EXP ) O(1) ( IMP ) 

Algo : 2 ptrs, hashmap, string
'''
class Solution:
    def areSentencesSimilar(self, sentence1: List[str], sentence2: List[str], similarPairs: List[List[str]]) -> bool:
        sentenceSimilarStatus = True
        lenOne = len(sentence1)
        lenTwo = len(sentence2)
        if(lenOne != lenTwo):
            return False
        # [1] Go through the similarPairs and set up our 1-1 mappings
        # Ok, as you thoguht, these are sets indeed
        mySimilarWord = {}
        for pair in similarPairs:
            srcWord = pair[0]
            dstWord = pair[1]
            if(srcWord not in mySimilarWord):
                mySimilarWord[srcWord] = set()
            if(dstWord not in mySimilarWord):
                mySimilarWord[dstWord] = set()
            mySimilarWord[srcWord].add(dstWord)
            mySimilarWord[dstWord].add(srcWord)
        # [2] Go through each sentence pairings
        idx = 0
        while(idx < lenOne):
            token1 = sentence1[idx]
            token2 = sentence2[idx]
            if(token1 != token2): # words are different
                if(token1 not in mySimilarWord):
                    sentenceSimilarStatus = False
                    break
                if(token2 not in mySimilarWord[token1]): # unsimilar words
                    sentenceSimilarStatus = False
                    break     
            idx += 1
        return sentenceSimilarStatus
