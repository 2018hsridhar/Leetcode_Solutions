/*

1221. Split a String in Balanced Strings
URL = https://leetcode.com/problems/split-a-string-in-balanced-strings/

THOUGHT PROCESS : 
Ideal [T,S] = [O(N), O(1)]

Greedy approach : Just maintain counts and split the substrings as we go
Note the immutability of Strings -> String Builders better here so that objects do not keep getting created in memory!
StringBuilder substring operations be efficient too!



*/

class Solution 
{
    public int balancedStringSplit(String s) 
    {
        int numBalancedStrings = 0;
        int lCount = 0;
        int rCount = 0;
        StringBuilder sb = new StringBuilder(s);
        return numBalancedStrings;
    }
}
