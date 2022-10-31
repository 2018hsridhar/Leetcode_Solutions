/*
10 mins to solution
You still remember the spiral matrix pattern :-)

URL = https://leetcode.com/problems/spiral-matrix-ii/
59. Spiral Matrix II

*/
class Solution {
public:
    // You have the (nxn) matrix guarantee too!
    vector<vector<int>> generateMatrix(int n) {
        vector<vector<int>> spiral(n,vector<int>(n,0));
        int el = 1;
        int tR = 0;
        int bR = n-1;
        int lC = 0;
        int rC = n-1;
        // This weird while loop expr triped you up
        while(tR <= bR && lC <= rC){
            // printf("%d,%d,%d,%d\n", lC, rC, tR, bR);
            // [1] Go right
            for(int j = lC; j <= rC; ++j){
                spiral[tR][j] = el;
                ++el;
            }
            tR++;
            
            // [2] Go down
            for(int i = tR; i <= bR; ++i){
                spiral[i][rC] = el;
                ++el;
            }
            rC--;

            // [3] Go left
            for(int j = rC; j >= lC; --j){
                spiral[bR][j] = el;
                ++el;
            }
            bR--;

            // [4] Go up
            for(int i = bR; i >= tR; --i){
                spiral[i][lC] = el;
                ++el;
            }
            lC++;
            
        }
        return spiral;
    }
};
