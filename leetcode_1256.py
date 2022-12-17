'''
1256. Encode Number
URL = https://leetcode.com/problems/encode-number/description/

Test Cases
(A) 0 => ""
(B) 1 => "0" FIX
(C) 99, 100000000, 77 -> PASS
(D) 39058234 -> PASS
(E)
'''
def getBinaryStringRepresentation(num:int, lenBinaryEquiv:int) -> str:
    binaryStringRep = ""
    while(num > 0 ):
        rem = num % 2
        binaryStringRep += str(rem)
        if(num == 0):
            break
        num = (int)(num/2)
    # https://www.w3schools.com/python/python_howto_reverse_string.asp
    # Need to concatenate up to the length too!
    while(len(binaryStringRep) < lenBinaryEquiv):
        binaryStringRep += "0"
    return binaryStringRep [::-1]

class Solution:
    def encode(self, num: int) -> str:
        encodingString = ""
        if(num == 0):
            return ""
        num -= 1 # for the first number ( may not even be needed ) 
        delta = 2
        lenBinaryEquiv = 1
        while(num >= 0):
            if(num - delta >= 0):
                num -= delta
                delta *= 2
                lenBinaryEquiv += 1
            else:
                break
        encodingString = getBinaryStringRepresentation(num, lenBinaryEquiv)
        # Have final num to manipuate
        return encodingString
