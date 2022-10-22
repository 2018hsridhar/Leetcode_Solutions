/*
Cases
(A) [4,3,2,1,0] => 1
(B) [0,1,2,3,4] => 5
(C) [1,3,2,0,4] => 2
(D) [5,6,4,1,2,3,0,7] => 2
(E)
(F)

769. Max Chunks To Make Sorted
URL = https://leetcode.com/problems/max-chunks-to-make-sorted/

*/
class Solution {
public:
    int maxChunksToSorted(vector<int>& arr) {
        int maxChunks = 1;
        int n = arr.size();
        vector<vector<int>> DP(n,vector<int> (n));
        // Leftboud = i, rightBound = n-1
        set<int> hasEls;
        for(int i = n-1; i >= 0; --i){
            for(int j = i; j < n; ++j){
                // [1] Solve the parent itself
                int leftBound = i;
                int rightBound = j;
                // printf("bounds = [%d,%d]\n", leftBound, rightBound);
                for(int k = leftBound; k <= rightBound; ++k){
                    hasEls.insert(arr[k]);
                }
                bool isChunk = true;
                for(int k = leftBound; k <= rightBound; ++k){
                    if(hasEls.find(k) == hasEls.end()){
                        isChunk = false;
                        break;
                    }
                }
                if(isChunk){
                    DP[i][j] = 1;
                } else {
                    DP[i][j] = -1;
                }
                // [2] Get maxima from the children too
                for(int rBound = leftBound; rBound <= rightBound - 1; rBound++){
                    int subProblemMax = 0;
                    if(DP[leftBound][rBound] != -1 && DP[rBound+1][rightBound] != -1){
                        subProblemMax = DP[leftBound][rBound] +  DP[rBound+1][rightBound];
                    }
                    DP[i][j] = std::max(DP[i][j], subProblemMax);
                }
                // printf("DP[%d][%d] = %d\n", leftBound, rightBound, DP[i][j]);
                hasEls.clear();
            }
        }
        maxChunks = DP[0][n-1];
        return maxChunks;
    }
};
