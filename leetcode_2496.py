'''
2496. Maximum Value of a String in an Array
URL = https://leetcode.com/problems/maximum-value-of-a-string-in-an-array/description/
'''
class Solution:
    def maximumValue(self, strs: List[str]) -> int:
        myMaxValue = 0
        for word in strs:
            isDigit = True
            for letter in word:
                if(letter.isdigit() == False):
                    isDigit = False
            myValue = 0
            if(isDigit):
                myValue = int(word) # Direct cast ( not ord -> int )
            else:
                myValue = len(word)
            myMaxValue = max(myMaxValue, myValue)
        return myMaxValue
