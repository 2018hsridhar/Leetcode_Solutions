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
        
        StringBuilder sb = new StringBuilder("");
        recurse(s1,s2,s3,sb);
        return haveInterleave;
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
