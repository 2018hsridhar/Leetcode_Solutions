/*
2361. Minimum Costs Using the Train Line
URL = https://leetcode.com/problems/minimum-costs-using-the-train-line/description/

Complexity
Let N := len(regular)
TIME = O(N)
SPACE = O(N) ( EXP ) O(1) ( IMP )
*/
class Solution {
public:
    vector<long long> minimumCosts(vector<int>& regular, vector<int>& express, int expressCost) {
        using ll = long long;
        int numStops = regular.size();
        vector<ll> minCosts(numStops,0.0);
        vector<ll> minCostToRegularStops(numStops+1,0.0);
        vector<ll> minCostsToExpressStops(numStops+1,0.0);
        // Establish base cases
        // may need min cost to station 0 as well!
        minCostToRegularStops.at(0) = 0;
        minCostsToExpressStops.at(0) = expressCost;
        for(int i = 1; i <= numStops; ++i){
            // Solve regular before solving express
            ll minCostToCurStopRegular = std::min(
                static_cast<ll>(regular.at(i-1) + minCostToRegularStops.at(i-1)),
                static_cast<ll>(regular.at(i-1) + minCostsToExpressStops.at(i-1))
            );
            minCostToRegularStops.at(i) = minCostToCurStopRegular;

            ll minCostToCurStopExpress = std::min(
                static_cast<ll>(express.at(i-1) + minCostsToExpressStops.at(i-1)),
                static_cast<ll>(expressCost + minCostToRegularStops.at(i))
            );
            // printf("Min costss to regular/expr = %llu,%llu\n", minCostToCurStopRegular, minCostToCurStopExpress);
            ll subProblemCost = std::min(minCostToCurStopExpress, minCostToCurStopRegular);
            minCosts.at(i-1) = subProblemCost;
            // execute write back operation too!
            minCostsToExpressStops.at(i) = minCostToCurStopExpress;
        }
        return minCosts;
    }
};
