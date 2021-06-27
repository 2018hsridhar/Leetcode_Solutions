/*

URL = https://leetcode.com/problems/split-a-string-in-balanced-strings/
1221. Split a String in Balanced Strings

THOUGHT PROCESS : 
Ideal [T,S] = [O(N), O(1)]

Greedy approach : Just maintain counts and split the substrings as we go
Note the immutability of Strings -> String Builders better here so that objects do not keep getting created in memory!
StringBuilder substring operations be efficient too!

During equality test -> test only after increment operation ( not before : no LHS=RHS when 0=0 type of thing )
Reset counters when needed too ( after equality passes ) 
String always guaranteed {L,R} and equality is done after only one of these variabels - lCount, rCount - increments upwards!

Test cases : 
Single length strings - {L},{R}
Complete repeating - {LLLLL} or {RRRR}
(n-1:1) partition - {LLLLLLLLR} or {LRRRRRRRRRRRRR} 

Random assortments - {LRLRLRLR}, {LLRRRLRRLL}, {LLLLRRRR}

Strings are guaranteed the balance property too! 


*/

class Solution 
{
    public int balancedStringSplit(String s) 
    {
        int numBalancedStrings = 0;
        int lCount = 0;
        int rCount = 0;
        for(int i = 0; i < s.length(); ++i)
        {
            char c = s.charAt(i);
            if(c == 'L')
                ++lCount;
            else if(c =='R')
                ++rCount;
            if(lCount == rCount)
            {
                ++numBalancedStrings;
                lCount = 0;
                rCount = 0;
            }
            
        }
        
        return numBalancedStrings;
    }
}
