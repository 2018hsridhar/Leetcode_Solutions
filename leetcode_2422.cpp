/*
2422. Merge Operations to Turn Array Into a Palindrome
URL = https://leetcode.com/problems/merge-operations-to-turn-array-into-a-palindrome/description/

stime = 1:30
etime = 12:00
ttime = 10:30

Complexity
Let N := len(nums)
Time = O(N)
Space = O(1) ( EXP ) O(1) ( IMP ) 

Constraints : 
Arr len in memory
Ints not all unique, but all pos
Always a solution

Cases:
(A) [1]
(B) [1,1,1,1]
(C) [1,1,1,1,1]
(D) [1,2,2,1]
(E) [3,2,1,3,2,1]
(F) [3,2,1,9,3,2,1,2,7]
(G) 
(H)
(I)

*/
class Solution {
public:
    int minimumOperations(vector<int>& nums) {
        using ll = long long;
        int minOps = 0;
        int n = nums.size();
        int lPtr = 0;
        int rPtr = n-1;
        // Are these the correct seed values?
        ll runSumLeft = nums.at(lPtr);
        ll runSumRight = nums.at(rPtr);
        while(lPtr < rPtr){
            if(runSumLeft < runSumRight){
                lPtr++;
                runSumLeft += nums.at(lPtr);
                minOps++;
            } else if ( runSumLeft > runSumRight){
                rPtr--;
                runSumRight += nums.at(rPtr);
                minOps++;
            } else { // no merge when equal : greedy here
                // Reset running sums
                lPtr++;
                rPtr--;
                runSumLeft = nums.at(lPtr);
                runSumRight = nums.at(rPtr);
            }
        }
        return minOps;
    }
};
