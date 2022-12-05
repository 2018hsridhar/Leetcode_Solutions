/*
URL = https://leetcode.com/problems/number-of-times-binary-string-is-prefix-aligned/
1375. Number of Times Binary String Is Prefix-Aligned

17 mins
Complexity
Let N := len(flips)
Time = O(N)
Space = O(N) ( EXP ) O(1) ( IMP ) 
*/
class Solution {
public:
    int numTimesAllBlue(vector<int>& flips) {
        int timesBlue = 0;
        // Obtain str len here
        int n = 0;
        for(int x : flips){
            n = max(n,x);
        }
        int curCountOneToI = 0;
        vector<int> curState(n,0);
        // cout << curState.size() << endl;
        // Flips is a permutation : all ints unique -> all to flip Ints unique too!
        for(int i = 1; i <= n; ++i){
            int idx = flips.at(i-1) - 1;
            if ( curState.at(i-1) == 1){
                curCountOneToI += 1;
            }
            if(curState.at(idx) == 0){ // An earlier, uncounted for index
                if(idx < i){
                    curCountOneToI++;
                }
                curState.at(idx) = 1;
            }
            if(curCountOneToI == i){
                timesBlue++;
            }
        }
        return timesBlue;      
    }
};
