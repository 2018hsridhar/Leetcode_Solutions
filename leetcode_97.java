/*

URL = https://leetcode.com/problems/interleaving-string/
97. Interleaving String

Goal of problem is to break it down into overlapping subproblems
And to store the computation here and see if it returns true as well

Let us come up with a recursive approach here


Potential DP solution will either be a DP array OR a DP matrix too
We either take a full substring or a partial substring
Can we liken this to the edit distance problem as well?
We know a terminating case with the empty string as either input as well too
Exploit consecutive characters properties of the substrings as well too

If we get the combination at the end, or from backtracking, then we should be good
But recursive solution definitely proves exponential here as well
Seems going down the same substrings just leads to the same place anyways ( choosing "cd" is the same
if I had just chosen "c" and then on that path chosen "d")

We know we have to equal the third "target" string too!

interweave("a","b")
Possible combos are "ab" and "ba"
interweave("a","b")
    a+interweave("","b")
        ab
    b + interweave("a","")
        ba

interweave("ab","cd")
    a+interweave("b","cd")
        ab+interweave("","cd")
        ac+interweave("b","d")
        acd+interweave("b","")
            acdb
    ab+interweave("","cd")
        abc+interweave("","d")
            abcd
        abcd+interweave("","")
            abcd
    c+interweave("ab","d")
    cd+interweave("ab","")

Computational Complexity :
Space = O(H) [ call stack depth]
Time =  O(2^n)
Definitely O(2^n) time for recursion -> we make two choices only @ each stage
    Take character from string_1
    Take character from string_2

Optimization -> compare current choice to current character of the target string : tells us which choice to make
E.g. we know we need "aa" for "aadbbc" and not "ddbc" here!

*/

// Try global state here now

class Solution
{
    boolean haveInterleave = false;
    int[][] memo;
    public boolean isInterleave(String s1, String s2, String s3) 
    {
        haveInterleave = false;
        if(s1 == null || s2 == null)
        {
            if(s3 == null) return true;
            return false;
        }
        if(s1.equals("") && s2.equals(s3)) return true;
        if(s2.equals("") && s1.equals(s3)) return true;
        if(s1.equals("") && s2.equals("") && s3.equals("")) return true;
        
        // [1] Initialize out MEMO matrix here
        int m = s1.length();
        int n = s2.length();
        memo = new int[m+1][n+1];
        for(int i = 0; i < m+1; ++i)
            for(int j = 0; j < n + 1; ++j)
                memo[i][j] = 0;
        
        StringBuilder sb = new StringBuilder("");
        // recurse(s1,s2,s3,sb);
        // recurseTwo(s1,s2,s3);
        return memoized(s1,s2,s3);
        // dp(s1,s2,s3);

    }
    /*
        Memo[][] status
        0 => unsolved
        1 => solved : can interleave
        2 => solved : can NOT interleave
        Sadly with memoization, we need an extra layer of caller function scope here as well!
    */

    public boolean memoized(String s1, String s2, String s3)
    {
        // System.out.printf("s1 = %s \t s2 = %s \t s3 = %s\n", s1,s2,s3);
        int m = s1.length();
        int n = s2.length();
        // Hey we are already there with all these cases anyways
        // In this case, we have remainder characters : NOT a perfect interleaving!
        if(s3.equals(""))
        {
            memo[m][n] = 2;
            return false;
        }
        
        if(s1.equals(""))
        {
            String curStr = s2;
            if(curStr.equals(s3))
            {
                memo[0][s2.length()] = 1;
                return true;
            }
            else
            {
                memo[0][s2.length()] = 2;
                return false;                
            }
        }
        if(s2.equals(""))
        {
            String curStr = s1;
            if(curStr.equals(s3))
            {
                 memo[s1.length()][0] = 1;
                 return true;
            }
            else
            {
                memo[s1.length()][0] = 2;
                return false;
            }
        }
        
        char char_one = s1.charAt(0);
        char char_two = s2.charAt(0);
        char char_three = s3.charAt(0);
        boolean s1_path = false;
        boolean s2_path = false;
        if(char_one == char_three)
        {
            // ideally, memo is filled by this time ( if not filled here ) 
            if(memo[m-1][n] == 1)
                s1_path = true;
            if ( memo[m-1][n] == 2)
                s1_path = false;
            if ( memo[m-1][n] == 0)
                s1_path = memoized(s1.substring(1), s2, s3.substring(1));
        }
        if(char_two == char_three)
        {
            // ideally, memo is filled by this time ( if not filled here ) 
            if(memo[m][n-1] == 1)
                s2_path = true;
            if ( memo[m][n-1] == 2)
                s2_path = false;
            if ( memo[m][n-1] == 0)
                s2_path = memoized(s1, s2.substring(1), s3.substring(1));  
        }
        
        // Now store current computation, based on the other paths
        if(s1_path || s2_path)
            memo[m][n] = 1;
        else
        {
            memo[m][n] = 2;
            return false;
        }
        return true;
    }
    
        
    public void recurseTwo(String s1, String s2, String s3)
    {
        // System.out.printf("s1 = %s\t s2 = %s\n", s1.toString(), s2.toString());
        if(s1.equals(""))
        {
            String curStr = s2;
            if(curStr.equals(s3))
                haveInterleave = true;
            return;
        }
        if(s2.equals(""))
        {
            String curStr = s1;
            if(curStr.equals(s3))
                haveInterleave = true;
            return;
        }
        
        char char_one = s1.charAt(0);
        char char_two = s2.charAt(0);
        char char_three = s3.charAt(0);
        if(char_one == char_three)
            recurseTwo(s1.substring(1), s2, s3.substring(1));
        if(char_two == char_three)
            recurseTwo(s1, s2.substring(1), s3.substring(1));
    }
    
    
    public void recurse(String s1, String s2, String s3, StringBuilder sb)
    {
        // System.out.printf("s1 = %s\t s2 = %s\n", s1.toString(), s2.toString());
        if(s1.equals(""))
        {
            String curStr = sb.toString() + s2;
            if(curStr.equals(s3))
                haveInterleave = true;
            return;
        }
        if(s2.equals(""))
        {
            String curStr = sb.toString() + s1;
            if(curStr.equals(s3))
                haveInterleave = true;
            return;
        }
        String curStr = sb.toString();
        if(curStr.equals(s3))
        {
            haveInterleave = true;
            return;
        }
        char char_one = s1.charAt(0);
        char char_two = s2.charAt(0);
        StringBuilder sb_one = new StringBuilder(sb.toString() + char_one);
        StringBuilder sb_two = new StringBuilder(sb.toString() + char_two);
        recurse(s1.substring(1), s2, s3, sb_one);
        recurse(s1, s2.substring(1), s3, sb_two);
    }
    
    
    
}
