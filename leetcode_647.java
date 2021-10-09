/*

String s is guaranteed at least [1,1000] characters and is composed only of [a-z] lowercase English letters
Find the length of the longest palindromic subsequence in string s

647. Palindromic Substrings
URL = https://leetcode.com/problems/palindromic-substrings/

Recursive Complexity : 
Let N := len(string s)
Time = 
Space = 

DP Complexity : 
Time = O(N^2)
Space = O(N^2) 

Edge Case testing : 
(A) s="b"
    => 1
(B) s="abc"
    =>1
(C) s="bb"
    =>2
(D) s="abba"
    =>4
(E) s="abcdefghijkl"
    => 0
(F) s="abbacdef"
    => 4
(G) s="cdefcabba"
    => 4
(H) s="abbacdeedc"
    => 6
(I) s="abcdeffedcba"
    => 12

A subsequence order is fixed => it does NOT change here
You are allowed element deletion here @ ANy element -> not just one element boyo! DAMM!

*/

class Solution
{
    public int countSubstrings(String s) 
    {
        int subStrCount = 0;
        subStrCount = BUDP(s);
        return subStrCount;
    }
    
    // Suppose string len = 5 here : {1,2,3,4} for the gaps between (i,j)
    public int BUDP(String s)
    {
        int subStrCount = 0;
        
        // 1. Handle base cases
        if(s.length() == 1) return 1;
        if(s.length() <= 2)
        {
            if(s.charAt(0) == s.charAt(1)) return 3;
            else return 2;
        }
        
        // 2. Then the palindromic cases
        int longestPalin = 0;
        int n = s.length();
        int[][] DP = new int[n][n]; // no need to account for the empty string here
        
        // Zero initialization
        for(int i = 0; i < n; ++i)
            for(int j = 0; j < n; ++j)
                DP[i][j] = 0;
        
        for(int i = 0; i < n; ++i)
            DP[i][i] = 1; 
        // Truly must fill in a diagonal traversal manner
        for(int i = 0; i < n-1; ++i) // we have to grow the gap of (i,j) here as well too!
        {
            if(s.charAt(i) == s.charAt(i+1))
                DP[i][i+1] = 2;
            else
                DP[i][i+1] = 1;
        }
        

            
        for(int j = 2; j < n; ++j) // difference based on j though
        {
            for(int i = 0; i < (n-j); ++i) // we have to grow the gap of (i,j) here as well too!
            {
                // System.out.printf("(%d,%d),", i, j+i);
                // Check 3 boundaries - left, bottom-left, bottom
                // But should always be valid anyways
                int x = i;
                int y = j+i;
                int firstLet = s.charAt(x);
                int lastLet = s.charAt(y);
                if(firstLet == lastLet)
                {
                    int subLen = (int)Math.abs(y-x)-1;
                    // System.out.printf("@ (%d,%d), sub len = %d\n", x, y, subLen);
                    if(DP[x+1][y-1] == subLen )
                    {
                        DP[x][y] = Math.max(2 + DP[x+1][y-1], 
                                         Math.max(DP[x][y-1],DP[x+1][y]));
                    }
                    else
                    {
                        DP[x][y] = Math.max(DP[x][y-1],DP[x+1][y]);
                    }
                }
                else
                {
                    DP[x][y] = Math.max(DP[x][y-1],DP[x+1][y]);
                }
            }
        }
        
        
        for(int i = 0; i < n; ++i)
        {
            for(int j = 0; j < n; ++j)
            {
                int subLen = (int)Math.abs(i-j)+1;
                if(DP[i][j] == subLen)
                    subStrCount++;
            }
        }
        
        return subStrCount;
    }
    
    
    
    
}
