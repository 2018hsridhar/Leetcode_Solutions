/*
1312. Minimum Insertion Steps to Make a String Palindrome
URL = https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/description/

Let M := len(S)
TIME = O(M^2)
SPACE = O(M^2) ( EXP ) O(1) ( IMP ) 

Cases
(A) "m" => PASS
(B) "dm" => PASS
(C) "mdm" = >PASS
(D) "abc" => PASS
(E) "fabccbad" => PASS
(F) "noijkjkilmno" => PASS
(G)
(H)

10 mins 

*/
class Solution {
public:
    int minInsertions(string s) {
        int n = s.size();
        vector<vector<int>> budp(n+1,vector<int>(n+1,0));
        // I think ordering is where folks trip up easily
        for(int r = n-1; r >= 0; --r){
            for(int c = r; c < n; ++c){
                if (r == c){
                    budp.at(r).at(c) = 0;
                } else if ( r == c - 1){
                    if(s.at(r) == s.at(c)){
                        budp.at(r).at(c) = 0;
                    } else {
                        budp.at(r).at(c) = 1;
                    }
                } else {
                    if(s.at(r) == s.at(c)){
                        budp.at(r).at(c) = budp.at(r+1).at(c-1);
                    } else {
                        budp.at(r).at(c) = 1 + std::min(
                            budp.at(r+1).at(c),
                            budp.at(r).at(c-1)
                        );
                    }
                }
            }
        }
        int minInserts = budp.at(0).at(n-1);
        return minInserts;
    }
};
