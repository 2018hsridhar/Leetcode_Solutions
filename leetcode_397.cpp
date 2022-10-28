/*
URL = https://leetcode.com/problems/integer-replacement/submissions/
397. Integer Replacement

*/
class Solution {
public:
    int integerReplacement(int n) {
        // vector<int> DP = vector<int>(INT_MAX,0); // offset by 1 here too
        // whoops mem limit exceeded : take note of this
        // can we start from n then? can we use a map instead to serve as a cache?
        map<long long,int> stepsForInt;
        stepsForInt[1] = 0; // fill in default too!
        int numOps = helper(stepsForInt,n);
        return numOps;
    }
    
    int helper(map<long long,int>& stepsForInt, long long n){
        if(stepsForInt.find(n) != stepsForInt.end())
            return stepsForInt[n];
        int minOps = 0;
        if(n % 2 == 0){
            minOps = 1 + helper(stepsForInt, n / 2);
        } else {
            minOps = min(
                1 + helper(stepsForInt, n - 1),
                1 + helper(stepsForInt, n + 1));
        }
        stepsForInt[n] = minOps;
        return minOps;
    }
    
};
