/*
URL := https://leetcode.com/problems/minimum-additions-to-make-valid-string/description/
2645. Minimum Additions to Make Valid String

NOT as simple as counting each letter
Can we avoid in-place modifications of the input too
Need before and after checks here
Need bound checking too
Can we do directionality testing ( or not )?

a <- b -> c
a -> b
c -> b

type of thing?

(A) "bbb"
(B) "ccc"
(C) "aaa"
(D) "a"
(E) "b"
(F) "c"
(G) "abc"
(H) "abcabc"
(I) "aaabbccaab"
(J) "cccabcabcbbb"


'a' at begin or 'c' at end if fine
-> all else : go remedy that

Better idea -> go through each, but independently ( versus combined )/
Can enforce ordering too?

Len <= 50 : go create new strings too ( via String Builders ) 
give n characters in the input : length of output known ( or not ) too?
Or given max of a given character as well? e.g. aaabbcc

12 mins here.

Going forwards and going recerse seems beta solution here with string builders


a -> b -> c
c -> b -> a

(idx,idx+1) cmp with exception of end idx ( OOB ) or case of startIdx preceding too
aaaaa
__
 __
  __ 
   __

20 mins to solution woooh :-) 
Pure mathematical -> no mem :-) O(N) time on input 
*/
func addMinimum(word string) int {
    minAdd := 0
    n := len(word)
    // Actually -> seperate out our pieces for processing
    startLetter := word[0]
    endLetter := word[n-1]
    // [0] START
    if startLetter == 'b' {
        minAdd += 1
    } else if startLetter == 'c' {
        minAdd += 2
    }
    // [1] END
    if endLetter == 'a' {
        minAdd += 2
    } else if endLetter == 'b' {
        minAdd += 1
    }
    // [2] MIDDLE
    for ptr := 0; ptr < n - 1; ptr++ {
        curLetter := word[ptr]
        nextLetter := word[ptr+1]
        if curLetter == 'a' {
            if nextLetter == 'c' {
                minAdd += 1
            } else if nextLetter == 'a' {
                minAdd += 2
            }
        } else if curLetter == 'b' {
            if nextLetter == 'a' {
                minAdd += 1
            } else if nextLetter == 'b' {
                minAdd += 2
            }
        } else if curLetter == 'c' {
            if nextLetter == 'b' {
                minAdd += 1
            } else if nextLetter == 'c' {
                minAdd += 2
            }
        }
    }
    return minAdd
}
