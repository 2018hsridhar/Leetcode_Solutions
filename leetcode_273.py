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
(J)
(K)

Leverage power of 10 trick too!

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
                res = placeInEnglish + " " + placeholders[placeIdx] + " " + res
            placeIdx += 1
            endIdx -= 3
        return res
        
    # So length can be three, but careful with trailing zeroes
    # Why not just handle ahead of time anyways ( pointer variable thing )?
    # "12" or "012" : "1" or "001"!
    # "102" : One Hundred Two ( skip the zero here ) 
    def convertPlaceToEnglish(place: str ) -> str:
        englishTrans = ""
        singleDigits = {
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
            '10' : 'Ten',
            '11' : 'Eleven',
            '12' : 'Twelve',
            '13' : 'Thirteen',
            '14' : 'Fourteen',
            '15' : 'Fifeteen',
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
        if(len(place) == 3):
            if(place[0] != '0'):
                englishTrans = singleDigits[place] + " " + "Hundred"
            
            
        if(len(place) >= 2 and place[1]):    # More comprehensive too!
            
        if(len(place) >= 1): # never matters if the leading two are never zero
            englishTrans = singleDigits[place]
        return englishTrans
    
        
        
        
        
