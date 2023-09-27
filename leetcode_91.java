/*
URL := https://leetcode.com/problems/decode-ways/description/
91. Decode Ways

Seems recursive/DP to me honestly
Doesn't seem like it can be done better too!

May contain leading zeroes ( but never trailing zeroes at least ) 
If we run into a leading zero -> NOP ; only allow for trailing zeroes

0, 06, 066, 0666 -> none permissible
but ... 10, 110 -> permissible

*/
public class Solution {
    
    public int numDecodings(String s) {
        int n = s.length();
        int[] memo = new int[n];
        for(int i = 0; i < n; i++) {
            memo[i] = -1;
        }
        int numWays = 0;
        int startIndex = 0;
        if(s.isEmpty()) {
            numWays = 0;
        } else {
            numWays = topDownMemo(s, startIndex, memo); 
        }
        return numWays; 
    }
    
    public int topDownMemo(String s, int curIndex, int[] memo)
    {
        if(memo[curIndex] != -1) {
            return memo[curIndex];
        }
        int numWays = 0;
        if(curIndex == s.length() - 1){
            if(s.charAt(curIndex) != '0') {
                numWays = 1;
            } else {
                numWays = 0;
            }
        } else if ( curIndex < s.length() - 1) {
            for(int j = 1; j <= 2; j++)
            {
                String prefix = s.substring(curIndex,curIndex + j); 
                int i = Integer.parseInt(prefix);
                if(i <= 26) // prevent a "27" case, from being parsed ... only as (b,g)!
                {
                    if ( prefix.charAt(0) == '0') {
                        numWays += 0; // nothing doable here
                        continue;
                    }
                    int childIndex = curIndex + j;
                    if (childIndex < s.length()) 
                    {
                        numWays += topDownMemo(s, childIndex, memo); 
                    }
                    // only one way if we hit end of string
                    else if ( childIndex == s.length())
                    {
                        numWays += 1;
                    }
                }
            }
        }
        memo[curIndex] = numWays;
        return memo[curIndex]; // safety in return of memoized result
    }   
}
