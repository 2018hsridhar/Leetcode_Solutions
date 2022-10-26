/*
URL = https://leetcode.com/problems/minimum-time-to-make-rope-colorful/
1578. Minimum Time to Make Rope Colorful

Let N := len(colors)
TIME = O(N)
SPACE = O(N) ( EXP ) O(1) ( IMP ) 
*/
class Solution {
public:
    int minCost(string colors, vector<int>& neededTime) {
        int n = neededTime.size();
        std::vector<int> dp = std::vector<int>(n,INT_MAX);
        dp[n-1] = 0;
        int lPtr = n-2;
        int rBallPtr = n-1; // to the right!
        char rBall = colors.at(n-1);
        std::vector<int> prefixSum = std::vector<int>(n,0);
        for(int i = 0; i < n; ++i){
            prefixSum.at(i) += neededTime.at(i);
            if(i > 0)
                prefixSum.at(i) += prefixSum.at(i-1);
        }
        while(lPtr >= 0){
            char curBall = colors.at(lPtr);
            int curBallCost = neededTime.at(lPtr);
            int curCost = 0;
            if(curBall != rBall ){ // unequal : no need for Bob to cut ballons
                curCost = dp[lPtr+1];
                rBallPtr = lPtr; // the new right boundary
                rBall = curBall;
                dp[lPtr] = curCost;
            } else { // do not change the rPtr here yet
                rBall = curBall;
                int costPreserveBall = 0;
                if(rBallPtr+1 < n)
                    costPreserveBall = dp[rBallPtr+1]; // probably location of buffer overflow here
                costPreserveBall += (prefixSum.at(rBallPtr) - prefixSum.at(lPtr));
                int costCutBall = curBallCost + dp[lPtr+1];
                dp[lPtr] = std::min(costPreserveBall, costCutBall);
            }
            --lPtr;
        }
        int myMinCost = dp[0]; // start from the beginning!
        return myMinCost;
    }
};
