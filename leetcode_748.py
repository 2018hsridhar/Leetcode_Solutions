'''
748. Shortest Completing Word
URL = https://leetcode.com/problems/shortest-completing-word/
# https://www.tutorialspoint.com/How-can-we-return-a-dictionary-from-a-Python-function
# Not sure if lambda syntax works for python objects ( maybe type(object) here ) ?

25 mins ( 10:20 - 10:45 )
Time spent learning pythoning syntax
Big hurdle : make sure to execute function invocations
'''
def getFreqMap(word: str) -> dict:
    # https://www.geeksforgeeks.org/python-check-whether-given-key-already-exists-in-a-dictionary/#:~:text=Using%20has_key%20%28%29%20method%20returns%20true%20if%20a,method%20has%20been%20removed%20from%20the%20Python3%20version.
    freqMap = {}
    for letter in word:
        if(letter.isalpha()):
            lowerLetter = letter.lower()
            # has_keys() not in PY3
            # if(letter not in freqMap.keys()):
            if(freqMap.get(lowerLetter) == None):
                freqMap[lowerLetter] = 0
            freqMap[lowerLetter] += 1
    return freqMap


class Solution:
    # does word contain all the letters in license plate ( not the other way around ) 
    # focus on lower case only ( strip away case insensitive too ) 
    def shortestCompletingWord(self, licensePlate: str, words: List[str]) -> str:
        licensePlateFreqMap = getFreqMap(licensePlate)
        # print(licensePlateFreqMap)
        # print(type(licensePlateFreqMap))
        shortestWord = "" # idx prefered in other langs
        shortestWordLen = float("inf")
        for word in words: 
            wordFreqMap = getFreqMap(word)
            # print(wordFreqMap)
            # print("-----------------------\n")
            isWordSuperSetOfLicensePlate = True
            for licensePlateKeyLetter in licensePlateFreqMap.keys():
                #if(licensePlateFreqMap.get(keyLetter) != None 
                #   and wordFreqMap[keyLetter] <= licensePlateFreqMap[keyLetter]):
                if(wordFreqMap.get(licensePlateKeyLetter) != None 
                   and licensePlateFreqMap[licensePlateKeyLetter] <= wordFreqMap[licensePlateKeyLetter]):
                    continue
                else:
                    isWordSuperSetOfLicensePlate = False
                    break
            if(isWordSuperSetOfLicensePlate):
                if(len(word) < shortestWordLen):
                    shortestWordLen = len(word)
                    shortestWord = word
        return shortestWord


