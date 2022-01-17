'''
URL = https://leetcode.com/problems/count-unique-characters-of-all-substrings-of-a-given-string/
828. Count Unique Characters of All Substrings of a Given String

Strategies : Sliding Window, Character Frequency Counting, Hashes, Sets

Complexity
Let N := #-characters in string
TIME = O(N*26) = O(N)
SPACE = O(1) ( CALL STK ) O(26) = O(1) ( EXP ) 

Include repeated substring count
E.g. "ABAB" : "AB" is repeated twice : (0,1) and (2,3) range-inclusive
Upper Case English letters only [ A-Z ]: leverage trick here?
Any rapid math techniques? Immediate cancel explorations ( with backtracking ) ?
Any DP/repeat computations too?

Test cases : 
(A) "ABC" => 10
(B) "ABCC" => 14
(C) "LEETCODE" => 92

'''
# ASCII modules
import string

class Solution:
    def uniqueLetterString(self, s: str) -> int:
        numStrs = 0
        if s == "":
            return 0
        HM = dict() # Could be array, but this works for now :-)
        uppercaseLets = string.ascii_uppercase
        for character in uppercaseLets:
            HM[character] =[-1,-1]  # Key-value syntax
            # print(HM[character])
        for i in range(len(s)): # Implicit range :-)
            # [1] Udpate hashmap first
            letter = s[i]    # subscript indexing into strings
            if(HM[letter][1] == -1):
                HM[letter][1] = i
            elif(HM[letter][1] != -1):
                HM[letter][0] = HM[letter][1]
                HM[letter][1] = i
            
            # [2] Iterate over (key,vals) in HM
            for let,pair in HM.items():
                numStrs += (pair[1] - pair[0])
            
        return numStrs
        
