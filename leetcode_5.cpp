/*
5. Longest Palindromic Substring
URL = https://leetcode.com/problems/longest-palindromic-substring/

TBH -> it is a brute force problem
1000*1000 ~= 1,000,000 ops only
Could be DP possibly??? 
    Probably : otherwise, you run into that pesky TLE issue

Edge cases
(A) "bbbbbbbbbbbbbbbbbbbbbb" => PASS
(B) "abcdefghijkl" => PASS
(C) "abcddcbaghijkl" => PASS
(D) "lmnopabcddcbaghijkl" => PASS
(E) "lmnopabcddcba" => PASS
(F) "lmnopaapwerioncbcddcbwefsabccbaiormgpoeqgp" -> PASS

30 mins to implmeentation

*/
class Solution {
public:
    string longestPalindrome(string s) {
        int n = s.size();
        vector<vector<int>> DP(n,vector<int>(n,-1)); // not solved case
        return helper(s,DP);
    }

private:

    // (0->n) = solve((1->n-1),(0->n-1),(1->n)) and
    // solve(2->n-3) = solve((2->n-4),(3->n-3),(3,n-3))
    // BUDP guarantees that the subproblems have been solved : leverage that!
    // wait -> this is getting the longest! Get max as we go too!
    std::string helper(const std::string& s, vector<vector<int>>& DP){
        int maxLen = INT_MIN;
        int lIdx = -1;
        int rIdx = -1;
        int n = s.size();
        for(int i = n-1; i >= 0; --i){
            for(int j = i; j < n; ++j){
                if(i == j){ // one len case : the default
                    DP[i][j] = 1;
                } else if ( i == j - 1) { // 2 len case
                    if(s.at(i) == s.at(j)){
                        DP[i][j] = 2;
                    } 
                } else { // >= len 3
                    int includeBoth = DP[i+1][j-1];
                    int maxLenIncludeBoth = (includeBoth == -1 || s.at(i) != s.at(j)) ? -1 : 2 + includeBoth;
                    DP[i][j] = maxLenIncludeBoth;
                }
                if(DP[i][j] != -1 && j-i+1 >= maxLen){
                    maxLen = j - i + 1;
                    lIdx = i;
                    rIdx = j;
                }
            }
        }
        return s.substr(lIdx,rIdx-lIdx+1);
    }
};
