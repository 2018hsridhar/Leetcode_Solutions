'''
URL = https://leetcode.com/problems/confusing-number/description/
1056. Confusing Number

Cases
(A) 80000 
'''
class Solution:
    def confusingNumber(self, n: int) -> bool:
        numInit = n
        if(n == 0):
            return False
        myConfusingNumber = ""
        while(n > 0):
            rem = n % 10
            delta = 0
            # https://stackoverflow.com/questions/32675712/how-to-use-switch-in-python ( no tswitch-case in python! ) 
            if(rem == 0 or rem == 1 or rem == 8):
                myConfusingNumber += str(rem)
            elif(rem == 6):
                myConfusingNumber += "9"
            elif(rem == 9):
                myConfusingNumber += "6"
            else:
                return False # no meet critera ( invalidiated )
            n = (int)(n/10)
        numReversed = int(myConfusingNumber) # why not reversed?
        # Confusing : when the number becomes different
        # print(numReversed)
        # print(numInit)
        return (numReversed != numInit)
        
