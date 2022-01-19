'''

273. Integer to English Words
URL = https://leetcode.com/problems/integer-to-english-words/

-> note a gotcha with a single "zero" as well !
But assume no leading zeroes anyways

-> Akin to IPv4 : testing if you handle leading zeroes properly!

TEST CASES
(0) 0
(A) 1,9
(B) 10
(C) 11
(D) 200
(E) 1,200
(F) 1,100,100
(G) 1,645,329,254
(H) INT_MAX = 2,147,483,647 ( a billion max anyways! ) 
(I) 1,231,001
(J) 81
(K) 107
(L) 345
(M) 2147000647, 2000000647
(N) 1009234000
(O) 1029234000, 1029200000
Leverage power of 10 trick too!
(P) 1000453000

Seems constant time and space too. Fully iterative solution!
TIME = O()
SPACE = O()

'''
class Solution:
    def numberToWords(self, num: int) -> str:
        placeIdx = 0
        placeholders = ["", "Thousand","Million","Billion"] # Notice the Hundred placeholder lacking here as well!
        if num == 0:
            return "Zero"
        res = ""
        rep = str(num)
        n = len(rep)
        endIdx = n - 1
        while endIdx >= 0:
            startIdx = max(0,endIdx - 2)
            # print("(%s,%s)" % (startIdx,endIdx))
            place = rep[startIdx:endIdx + 1]
            placeInEnglish = Solution.convertPlaceToEnglish(place)
            if(placeIdx == 0):
                res = placeInEnglish + res
            else:
                if len(placeInEnglish) != 0 :
                    frontPrefix = placeInEnglish + " " + placeholders[placeIdx]
                    if res != "":
                        frontPrefix = frontPrefix + " "
                    res = frontPrefix + res # Do not put in an empty placeholder either ( chess res len ) 
            placeIdx += 1
            endIdx -= 3
        return res
        
    # So length can be three, but careful with trailing zeroes
    # Why not just handle ahead of time anyways ( pointer variable thing )?
    # "12" or "012" : "1" or "001"!
    # "102" : One Hundred Two ( skip the zero here ) 
    def convertPlaceToEnglish(place: str ) -> str:
        # Why not incorporate the zero empty key trick here as well!
        englishTrans = ""
        singleDigits = {
            '0' : '',
            '1' : 'One',
            '2' : 'Two',
            '3' : 'Three',
            '4' : 'Four',
            '5' : 'Five',
            '6' : 'Six',
            '7' : 'Seven',
            '8' : 'Eight',
            '9' : 'Nine',
        }
        doubleDigits = {
            '0' : '',
            '10' : 'Ten',
            '11' : 'Eleven',
            '12' : 'Twelve',
            '13' : 'Thirteen',
            '14' : 'Fourteen',
            '15' : 'Fifteen',
            '16' : 'Sixteen',
            '17' : 'Seventeen',
            '18' : 'Eighteen',
            '19' : 'Nineteen',
            '2' : 'Twenty',
            '3' : 'Thirty',
            '4' : 'Forty',
            '5' : 'Fifty',
            '6' : 'Sixty',
            '7' : 'Seventy',
            '8' : 'Eighty',
            '9' : 'Ninety'
        }
        # TRIPLE DIGIT ( why not a bit of splicing as we go too -> consider for later refactoring! )
        # Turns out not as trivial to parse as expected earlier!
        if len(place) == 3:
            if place[0] == '0' and place[1] == '0' and place[2] != '0':
                englishTrans = singleDigits[place[2]]
                return englishTrans
            if place[1] != '0':
                if place[1] == '1':
                    englishTrans = englishTrans + doubleDigits[place[1:]] # splice and remainder!
                elif place[1] != '1':
                    englishTrans = englishTrans + doubleDigits[place[1]]
                    if place[2] != '0':
                        englishTrans = englishTrans + " " + singleDigits[place[2]] # Final here anyways!
            elif place[1] == '0':
                if place[2] != '0':
                    englishTrans = singleDigits[place[2]] # Final here anyways!
            if place[0] != '0':
                frontPrefix = singleDigits[place[0]] + " " + "Hundred"
                if len(englishTrans) >= 1:
                    frontPrefix = frontPrefix + " "
                englishTrans = frontPrefix + englishTrans
        # DOUBLE DIGIT                
        elif len(place) == 2:
            if place[0] != '0':
                if place[0] == '1':
                    englishTrans = englishTrans + doubleDigits[place[0:]] # splice and remainder!
                elif place[0] != '1':
                    englishTrans = englishTrans + doubleDigits[place[0]]
                    if place[1] != '0':
                        englishTrans = englishTrans + " " + singleDigits[place[1]] # Final here anyways!
        # SINGLE DIGIT                
        elif len(place) == 1: # never matters if the leading two are never zero
            englishTrans = singleDigits[place]
        return englishTrans
        
        
        
        
