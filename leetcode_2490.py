'''
2490. Circular Sentence
URL = https://leetcode.com/problems/circular-sentence/

Complexity
Let N := len(sentence) := #-tokens in sentence
Time = O(N)
Space = O(1) (EXP & IMP ) 
'''
class Solution:
    def isCircularSentence(self, sentence: str) -> bool:
        isMySentenceCircular = True
        # delimeter = "\\s+"
        delimeter = ' '
        sentenceTokens = sentence.split(delimeter)
        # sentenceTokens = sentence.split() # Space is the default delimeter in most languages
        n = len(sentenceTokens)
        for idx in range(0,n,1): # 3-arg range loop
            lastCharacterOfCurrentToken = sentenceTokens[idx][-1]
            firstCharacterOfNextToken = sentenceTokens[int((idx+1)%n)][0] # reverse find in python3 :-)
            if(lastCharacterOfCurrentToken != firstCharacterOfNextToken):
                isMySentenceCircular = False
                break
        return isMySentenceCircular
