'''
URL = https://leetcode.com/problems/longest-nice-substring/
1763. Longest Nice Substring

Case Test
(A) "aa" => ""
(B) "AA" => ""
(C) "aA" => "aA"
(D) "AaaA" => "AaaA"


'''
def getLettersInString(s:str) -> set:
    letterSet = set()
    for letter in s:
        if letter not in letterSet:
            letterSet.add(letter)
    return letterSet

# Use custom generator :-)
# https://stackoverflow.com/questions/7001144/range-over-character-in-python
def char_range(c1,c2):
    for c in range(ord(c1),ord(c2) + 1):
        yield chr(c)

def isNice(letterSet:set) -> bool:
    delta = abs(ord('A') - ord('a'))
    # print(delta)
    for lowerCaseLetter in char_range('a','z'):
        if(lowerCaseLetter in letterSet):
            # Delta is NOT 26
            upperCaseLetter = chr(ord(lowerCaseLetter) - delta)
            if(upperCaseLetter not in letterSet):
                return False
    for upperCaseLetter in char_range('A','Z'):
        if(upperCaseLetter in letterSet):
            lowerCaseLetter = chr(ord(upperCaseLetter) + delta)
            if(lowerCaseLetter not in letterSet):
                return False
    return True

def longestNiceSubstringInternal(s:str) -> str:
    lns = ""
    for i in range(len(s)):
        for j in range(i+1,len(s)+1,1):
            spliced = s[i:j]
            letterSet = getLettersInString(spliced)
            if(isNice(letterSet) and len(spliced) > len(lns)):
                lns = spliced
    return lns

def testDriver() -> None:
    print(longestNiceSubstringInternal("aa"))
    print(longestNiceSubstringInternal("AA"))
    print(longestNiceSubstringInternal("aA"))
    print(longestNiceSubstringInternal("AaaA"))
    print(longestNiceSubstringInternal("AabcDeEd"))
    print(longestNiceSubstringInternal("abcdDCBEAe"))


class Solution:
    def longestNiceSubstring(self, s: str) -> str:
        # testDriver()
        return longestNiceSubstringInternal(s)

