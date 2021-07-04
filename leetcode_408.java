/*

408. Valid Word Abbreviation
URL = https://leetcode.com/problems/valid-word-abbreviation/

Ideal Time-Space Complexity : [T,S] = [O(N), O(1)] via one-pass linear scan ( two-pass at worst ) 

THUOGHT PROCESS : 

Abbreviation lengths are reaosnable : either single-digits or low double-digits 
And are never zero too

Range of letters in {word,abbr} also reasonable - only [a-z] for word, only [a-z0-9] for digits
Need a look ahead condition with bounds check when iterating over the "abbr" array too

Test caes : 
Let us denote as (word,abbr) pairs here too

String s.get(i) may take O(N) time unless optimized underneath : hence allocate char array objects as a preprocessing stage
In most cases, seems that we store index variables as integers ( makes sense naturally ) - never as other data types
But this poses a limit to the size of an array here [ 0,2^32 - 1]

Test abbreviation - offsets should be in correct position ( e.g. "12ii34g3wef1wer12" )

*/
class Solution
{
    public boolean validWordAbbreviation(String word, String abbr) 
    {
        int ptr1 = 0;
        int ptr2 = 0;
        char[] word_arr = word.toCharArray();
        char[] abbr_arr = abbr.toCharArray(); 
        while(ptr1 < word.length() && ptr2 < abbr.length())
        {
            if('1' <= abbr_arr[ptr2] && abbr_arr[ptr2] <= '9')
            {
                int offset = (int)(abbr_arr[ptr2] - '0'); // proper offsetting from char->dec space
                ++ptr2;   
                if((ptr2) < abbr.length())
                {
                    if('0' <= abbr_arr[ptr2] && abbr_arr[ptr2] <= '9') // in this case a range of [0-9] is VALID!
                    {
                        offset *= 10;
                        offset += (int)(abbr_arr[ptr2] - '0');
                        ++ptr2;
                    }
                }
                for(int i = 0; i < offset; ++i)
                    ++ptr1;
            }
            else
            {
                if(word_arr[ptr1] != abbr_arr[ptr2])
                    return false;
                ++ptr1;
                ++ptr2;
            }
        }
        // [2] Case where pointers did not finish : improper lengths here
        // Exact length testing prefereable 
        if(ptr1 != word.length() || ptr2 != abbr.length())
            return false; 
        return true;
    }
}
