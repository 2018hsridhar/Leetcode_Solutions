'''
URL = https://leetcode.com/problems/decode-the-message/description/
2325. Decode the Message

Complexity
Tiem + O(M+K)
Space = O(SIGMA)
SIGMA = 26, K = len(key) and M = len(message)

Chp 2 : Uncle Bob's Naming Code Practice
15 mins
'''
class Solution:
    def decodeMessage(self, key: str, message: str) -> str:
        mapKeyCharactersToCiper = dict()
        visitedKeyCharacterInMessage = set()
        mappedCipherLetter = 'a'
        SPACE_CHAR = ' '
        for keyLetter in key:
            if(keyLetter not in visitedKeyCharacterInMessage and keyLetter != SPACE_CHAR):
                mapKeyCharactersToCiper[keyLetter] = mappedCipherLetter
                visitedKeyCharacterInMessage.add(keyLetter)
                mappedCipherLetter = chr(ord(mappedCipherLetter) + 1)
        decodedMessage = ""
        for encodedMessageLetter in message:
            if(encodedMessageLetter == SPACE_CHAR):
                decodedMessage += SPACE_CHAR
            else:
                decodedMessage += mapKeyCharactersToCiper[encodedMessageLetter]
        return decodedMessage
