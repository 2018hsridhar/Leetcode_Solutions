/*
URL = https://leetcode.com/problems/champagne-tower/
799. Champagne Tower

Edge Cases
(A) 100,33 => 0
(B) 400,33 => 1

Complexity
Let G := max-#-glass ( 100 ) 
Let R := max-#-rows ( 100 ) 
TIME = O(RG) = O(1)
SPACE = O(RG) ( EXP ) O(1) ( IMP ) 
*/
class Solution {
public:
    double champagneTower(int poured, int query_row, int query_glass) {
        double result = 0.0;
        int m = 100;
        int n = 100;
        vector<vector<double>> memo(m+1,vector<double>(n+1,0.0)); // pad extra 0 dim.
        memo.at(0).at(0) = static_cast<double>(poured); // init state here
        // Special case at the top though!
        // Seems easier to pour over to kids, than to have kids take from parents
        for(int i = 0; i < m; ++i){
            for(int j = 0; j <= i; ++j){
                pair<int,int> lc = make_pair(i+1,j);
                pair<int,int> rc = make_pair(i+1,j+1);
                if(memo.at(i).at(j) > 1){
                    memo.at(i).at(j) -= 1; // take away '1'
                    memo.at(lc.first).at(lc.second) += static_cast<double>(memo.at(i).at(j) / 2.0);
                    memo.at(rc.first).at(rc.second) += static_cast<double>(memo.at(i).at(j) / 2.0);
                    memo.at(i).at(j) = 1; // cap to one here
                }
            }
        }
        // clear bottom row
        for(int j = 0; j <= n; ++j){
            memo.at(query_row).at(j) = std::min(memo.at(query_row).at(j), 1.0);
        }
        result = memo.at(query_row).at(query_glass);
        return result;
    }
};
