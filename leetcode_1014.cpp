/*
Is this more  of a greedy type of problem than a DP type of problem?
We want a O(N) Time solution
Remember that (v(i) + v(j)) always add up here
Can we decr the second operand?

1014. Best Sightseeing Pair
URL = https://leetcode.com/problems/best-sightseeing-pair/

Complexity
Let N := len(values)
TIME = O(N)
SPACE = O(1) ( EXP & IMP ) 
Have @ least 2 els in arr

19 mins to solution :-)
*/
class Solution {
public:
    int maxScoreSightseeingPair(vector<int>& values) {
        int maxScore = 0;
        int n = values.size();
        // get the first diff
        maxScore = values[n-1] + values[n-2] - 1;
        int curEl = values[n-1] - 1;
        for(int i = n-3; i >= 0; --i){
            // check next el
            int nextEl = values.at(i+1)-1;
            // check cur Candid ( could include next el in some cases )
            curEl = curEl - 1;
            if(nextEl > curEl){
                curEl = nextEl;
            }
            maxScore = std::max(maxScore,values.at(i) + curEl);
        }
        return maxScore;
    }
};
