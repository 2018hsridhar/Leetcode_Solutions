/*

Given two strings - text1, text2 - return the length of their longest common subsequence
If no common subsequence -> return 0
Guaranteed text lengths >= 1 here at least, and that both are only [a-z] lowercase English characters as well
Does it map to a similar problem here such as edit distance DP?
Can be extended to the case of numerical sequences as well too
Longest order statistics := consider evaluating a maximum over recursive subproblems
OSP := 



Recursive Complexity : 
Let M := len(text1), N := len(text2)
Let H := M + N - 1, I = H + 1 = M + N
Time = 2^{H+1}-1 # of node ~= O(2^{H+1})
    -> probably no dropping if we have a power ... but we would defnote it differently? O(2^I)
Space = O(H)

BUDP Complexity 
Time = O(MN)
Space = O(MN)


Test the beginning, end, and miffle of our lists under consideration here
Edge Case Teting
(A) "a","abcd"
    => 1
(B) "abcd","c"
    => 1
(C) "", "abcd"
    => 0
(D) "abcd",""
    =>0
(E) "abcdef", "bdf"
    => 3
(F) "abc","def"
    => 0
(G) "abcdef","wxyzf"
    => 1 
(H) "abcdef","cwxyz"
    => 1

During the decomposition stages, do we pass in substrings, OR, indices denoting these substrings
and then executing a s.substring(start_idx,end_idx) operation in lieu of this?

String	substring(int beginIndex)
Returns a new string that is a substring of this string.

*/
class Solution 
{
    int[][] memo;
    public int longestCommonSubsequence(String text1, String text2) 
    {
        int M = text1.length();
        int N = text2.length();
        memo = new int[M+1][N+1];
        // [0] Prelim step -> fill memo matrix with default values to indicate a lack of computation as well
        // for(int i = 0; i < M+1; ++i)
        //     for(int j = 0; j < N+1; ++j)
        //         memo[i][j] = -1;
        // TDM(text1,text2);
        // return memo[M][N];
        return BUDP(text1,text2);
    }
    
    private int recurse(String text1, String text2)
    {
        int lcs_len = 0;
        if(text1.equals("") || text2.equals(""))
            return 0;
        int first_one = text1.charAt(0);
        int second_one = text2.charAt(0);
        if(first_one == second_one)
        {
            lcs_len = 1 + recurse(text1.substring(1), text2.substring(1));
        }
        else
        {
            lcs_len = Math.max(recurse(text1.substring(1), text2), recurse(text1, text2.substring(1)));
        }
        return lcs_len;
    }
    
    // Benefit of having the "memo" DP matrix in outside scope -> no need to pass in an address/reference in parameters of the function
    private int TDM(String text1, String text2)
    {
        int lcs_len = 0;
        
        // [1] Perform memo cache hit
        int M = text1.length();
        int N = text2.length();
        if(memo[M][N] != -1)
            return memo[M][N];
        
        // [2] Perform recursive portion
        if(text1.equals("") || text2.equals(""))
        {
            // they should not forgot to memoiezd this case as well
            memo[M][N] = 0;
            return 0;
        }
        else
        {
            int first_one = text1.charAt(0);
            int second_one = text2.charAt(0);
            if(first_one == second_one)
            {
                lcs_len = 1 + TDM(text1.substring(1), text2.substring(1));
            }
            else
            {
                lcs_len = Math.max(TDM(text1.substring(1), text2), TDM(text1, text2.substring(1)));
            }
            memo[M][N] = lcs_len;   
        }
        return memo[M][N];
    }
    
    private int BUDP(String text1, String text2)
    {
        // [1] Create the original DP matrix here
        int M = text1.length();
        int N = text2.length();
        int[][] DP = new int[M+1][N+1];
        for(int i = 0; i < M+1; ++i)
            for(int j = 0; j < N+1; ++j)
                DP[i][j] = -1;
        
        // [2] Write out the base case values here ( think empty strings ) 
        for(int j = 0; j < N+1; ++j)
            DP[0][j] = 0;
        
        for(int i = 0; i < M+1; ++i)
            DP[i][0] = 0;
            
        // [3] Perform rest of the DP array calculations
        for(int i = 1; i < M+1; ++i)
        {
            for(int j = 1; j < N+1; ++j)
            {
                char first_one = text1.charAt(i-1);
                char second_one = text2.charAt(j-1);
                if(first_one == second_one)
                    DP[i][j] = 1 + DP[i-1][j-1];
                else
                    DP[i][j] = Math.max(DP[i-1][j],DP[i][j-1]);
            }
        }
        return DP[M][N];
    }
}
