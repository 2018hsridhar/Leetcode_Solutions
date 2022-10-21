/*
URL = https://leetcode.com/problems/minimum-numbers-of-function-calls-to-make-target-array/
1558. Minimum Numbers of Function Calls to Make Target Array

Edge Cases
(A) [98] => 9
(B) [98,127,24,25] => 21
(C) [1,2,4,8,16,1024] => 16
(D) [1,2,4,7,8,15,1023,1024] => 32
(E) [1,324,4423,654,78,909090,111,100000,123,5656,890934] => 83
(F)
(G)

Let X := max(input)
TIME = O(NlgX)
Space = O(1) ( EXP & IMP ) 

*/
class Solution {
public:
    int minOperations(vector<int>& nums) {
        int maxEven = 0;
        int numOdd = 0;
        int numNonZero = 0;
        int minOps = 0;
        for(int i : nums){
            if(i >= 1){
                numNonZero++;
                pair<int,int> evenOdd = getEvenOddJumps(i);
                maxEven = max(maxEven, evenOdd.first);
                numOdd += evenOdd.second;
            }
        }
        minOps = maxEven + numOdd + numNonZero;
        return minOps;
    }
    
private:
    // first is even
    // second is odd
    pair<int,int> getEvenOddJumps(int x){
        pair<int,int> evenOdd = make_pair(0,0);
        while(x > 1){
            if(x % 2 == 0){
                evenOdd.first++;
                x /= 2;
            } else {
                evenOdd.second++;
                x = x - 1;
            }
        }
        return evenOdd;
    }
    
};*
