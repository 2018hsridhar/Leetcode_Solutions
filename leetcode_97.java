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


*/
class Solution
{
    public boolean isInterleave(String s1, String s2, String s3) 
    {
        
    }
}
