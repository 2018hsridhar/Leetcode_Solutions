/*
410. Split Array Largest Sum
URL = https://leetcode.com/problems/split-array-largest-sum/description/

50 mins GAAAH
But hey a LC hard :-)
Stupid subtle bugs hither-and-tither

Cases
(A) [7,2,5,10,8], 4 => CHECK
(B) [7,2,5,10,8,0,0,0,1,3,5], 6 => OK
(C) [7,2,5,10,8,435,214,65,87,5,65,54,34,2,12,54,8,90,900,2121,32,323232], 3 => OK
(D)
(E)

Complexity
Let N := len(nums)
TIME = O(NK)
SPACE = O(NK) ( EXP ) O(1) ( IMP ) 

Inputs are nonnegative, but can zero out.
*/
class Solution {
public:
    int splitArray(vector<int>& nums, int k) {
        int n = nums.size();
        vector<vector<int>> DP(k+1,vector<int>(n+1,-2)); // -2 denotes NOT filled entry
        // solve subproblems for the base case ( 1st row ) : right->left
        int myRunSum = 0;
        for(int j = n-1; j >= 0; --j){
            myRunSum += nums.at(j);
            DP[1][j] = myRunSum;
        }
        // solve for the non-trivial cases : left->right here
        // for(int i = 2; i <= k; ++i){
        //     for(int j = 0; j < n; ++j){
        //         int curRunSum = 0;
        //         for(int m = j; m < n; ++m){
        //             curRunSum += nums.at(j);
        //             if(j+1 < n){
        //                 if(DP[k-1][j+1] == -1){
        //                     DP[k][j] = -1;
        //                 } else {
        //                     DP[k][j] = std::max(curRunSum,DP[k-1][j+1]);
        //                 }
        //             } else {
        //                 DP[k][j] = -1;   // invalid case : we should have split before hand too!
        //             }
        //         }
        //     }
        // }
        // for(int j = 0; j < n; ++j){
            // if(DP[k][j] != -1){
                // minimizedLargestSum = std::min(minimizedLargestSum,DP[k][j]);
            // }
        // }
        int minimizedLargestSum= internal(nums,DP,k,0);
        return minimizedLargestSum;
    }

    
    int internal(vector<int>& nums, vector<vector<int>>& memo,int k, int idx){
        int n = nums.size();
        int result = INT_MAX; // invalid case of split
        if(idx == n){
            return -1; // invalid entry
        } else if ( k == 1){
            return memo[1][idx];
        }else if(memo[k][idx] != -2){
            return memo[k][idx];
        } else {
            int runSum = 0;
            for(int i = idx; i < n; ++i){
                runSum += nums.at(i);
                int childCase = internal(nums,memo,k-1,i+1);
                if(childCase != -1){
                    result = std::min(result,max(runSum,childCase));
                }
            }
        }
        memo[k][idx] = result;
        // cout << "k,idx = " << k << "," << idx << "\tmemo = " << memo[k][idx] << endl;
        return memo[k][idx];
    }
};
