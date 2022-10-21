/*
2110. Number of Smooth Descent Periods of a Stock
URL = https://leetcode.com/problems/number-of-smooth-descent-periods-of-a-stock/submissions/
*/
class Solution {
public:
    long long getDescentPeriods(vector<int>& prices) {
        long long numPeriods = 0;
        long long curLen = 1;
        for(int i = 1; i < prices.size(); ++i){
            if(prices.at(i) == prices.at(i-1) - 1){
                curLen++;
            } else {
                numPeriods += ((curLen + 1 ) * ( curLen ) / 2 );
                curLen = 1;
            }
        }
        numPeriods += ((curLen + 1 ) * ( curLen ) / 2 );
        return numPeriods;
    }
};
