/*
1664. Ways to Make a Fair Array
URL = https://leetcode.com/problems/ways-to-make-a-fair-array/

Quick idea : precompute all the sums we will use here.

Complexity
Let N := len(nums)
TIME = O(N)
SPACE = O(N) ( EXP ) O(1) ( IMP ) 

Edge Cases
(A) [1,2,1,2,1,2,1,2] => 1
(B) [1] => 1
(C) [1,1] or [2,3] => 0
(D) [1,1,1,1,1,1,1,1] => 0
(E) [1,1,1,1,1,1,1,1,1] => 19
 Vals > 0 too

*/
class Solution {
public:
    int waysToMakeFair(vector<int>& nums) {
        // check len constraint too
        if(nums.size() == 1) return 1;
        if(nums.size() == 2){ return 0; } // one index only!
        int waysFair = 0;
        int n = nums.size();
        vector<int> sumToLeft(n,0);// trick syntax here with ordering
        vector<int> sumToRight(n,0);
        // [1] fill in sumToLeft
        for(int i = 0; i < n; ++i){
            sumToLeft.at(i) += nums.at(i);
            if(i - 2 >= 0){
                sumToLeft.at(i) += sumToLeft.at(i-2);
            }
        }
        // [2] fill in sumToRight 
        for(int i = n-1; i >= 0; --i){
            sumToRight.at(i) += nums.at(i);
            if(i + 2 < n){
                sumToRight.at(i) += sumToRight.at(i+2);
            }
        }
        // [3] Execute computations
        // sum(oddIdx) = sum(evenIdx)
        // took one el away : solve for different sums
        // ensure to check your boundary cases too!
        for(int i = 0; i < n; ++i){
            int evenSum = 0;
            int oddSum = 0;
            if(i == 0){
                if(sumToRight.at(1) == sumToRight.at(2)){
                    waysFair++;
                }
            } else if ( i == n-1 ) {
                if(sumToLeft.at(i-2) == sumToLeft.at(i-1)){
                    waysFair++;
                }
            } else {
                // perform an index check here too!
                if(i % 2 == 0){ // even
                    oddSum += sumToLeft.at(i-1);
                    if(i + 2 < n)
                        oddSum += sumToRight.at(i+2);
                    if(i - 2 >= 0)
                        evenSum += sumToLeft.at(i-2); // by itself, called a `call operator` : ohh
                    if(i+1 < n)
                        evenSum += sumToRight.at(i+1);
                    
                } else if ( i % 2 == 1) { // odd
                    evenSum += sumToLeft.at(i-1);
                    if(i + 2 < n)
                        evenSum += sumToRight.at(i+2);
                    if(i - 2 >= 0)
                        oddSum += sumToLeft.at(i-2);
                    if(i+1 < n)
                        oddSum += sumToRight.at(i+1);
                }
                if(oddSum == evenSum){
                    waysFair++;
                }
            }
        }
        return waysFair;
        
    }
};
