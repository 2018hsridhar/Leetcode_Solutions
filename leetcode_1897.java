/*

THOUGHT PROCESSES : 
URL = https://leetcode.com/problems/redistribute-characters-to-make-all-strings-equal/
1897. Redistribute Characters to Make All Strings Equal

Input data and datatype testing : 
Array is of length >= 1, and reasonable ( till 100 only ) 
Each word also reasonable length : 1-100
Each character is lowercase : range [a-z] entails a bound too from [0,25] = 26 letters

Use character-binning strategy
Use array in lieu of hashmap : when we can map the unique key set to some arithmetic progress of {0,..,n} or a range {x,...,m} in a n length array where 0 <= x <= m AND m < n;


Edge cases : 
(1) Singleton - ["a"] OR ["a","b"] OR double - ["ab","cd"]
- str len = 1 case guaranteed a fail ( if this be average ) : no null strings to begin with too!
["abc"] is TRUE
["ababab","ab"] = true
["abab","ab"] = false

Singleton arrays are always true, no matter their string inputs too


(2) All fail - ["a","b","c"]
(3) Passing case - ["abc","aabc","bc"]
(4) Failing case where counts do not work - ["ab","bc","ca"] 
- each occurs twice, but can not move any characters from one word to another to entail equality!

Ideas : 
Utilize hashmap data structures
Utilize counting mechanisms

Computatinoal Complexity Desired
Let N := number of words and K := length of longest word
Time = O(NK)
Space = O(1) [ even if counting char freqs, space is constant - 26 len max here ]



*/
class Solution
{    
    public boolean makeEqual(String[] words) 
    {
        boolean canMakeEqual = false;
        
        int[] charFreq = new int[26];
        int n = words.length;
        for(int i = 0; i < charFreq.length; ++i)
            charFreq[i] = 0; // Zero-initialization ; do not worry about zero case anyways!
        
        for(String s : words)
        {
            char[] c_arr = s.toCharArray();
            for(char c : c_arr)
            {
                int writeIdx = (int)(c - 'a');
                ++charFreq[writeIdx];
            }
        }
        
        for(int i = 0; i < charFreq.length; ++i)
        {
            int myFreq = charFreq[i];
            if(myFreq > 0 )
                if(myFreq % n != 0)
                    return false;
        }
        
        return true;
    }
}
