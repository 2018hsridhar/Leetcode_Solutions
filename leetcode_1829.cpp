/*
URL = https://leetcode.com/problems/maximum-xor-for-each-query/
1829. Maximum XOR for Each Query
Better : eschew bitsets

Complexity
Let N := len(nums)
TIME = O(N)
SPACE = O(1) ( EXP && IMP ) 

Cases
(A) [1,2,4,8,16,32,64,128,256] 15
    => PASS
(B) [0,0,0,0] 1
    => PASS
(C) [0,1,32,365,879,4332,5654,7687] 20
    => PASS
(D)
(E)


*/
class Solution {
public:
    vector<int> getMaximumXor(vector<int>& nums, int maximumBit) {
        vector<int> maxXors;
        int i = 0;
        int n = nums.size();
        int runXor = 0;
        while(i < n){   
            int curMaxXor = 0;
            runXor = runXor ^ nums.at(i);
            // printf("Run xor = %d\n", runXor);
            int numBits = maximumBit;
            int radix = 0;
            int runXorCopy = runXor;
            while(runXorCopy > 0 && numBits > 0){
                int rem = runXorCopy % 2;
                if(rem == 0){
                    curMaxXor += pow(2,radix);
                }
                runXorCopy /= 2;
                --numBits;
                ++radix;
            }
            // still have numBits available!
            while(numBits > 0){
                --numBits;
                curMaxXor += pow(2,radix);
                ++radix;
            }
            // printf("My max Xor = %d\n", curMaxXor);
            maxXors.push_back(curMaxXor);
            ++i;
        }
        reverse(maxXors.begin(), maxXors.end());
        return maxXors;
    }
};
