/*
TIME TAKEN = _______ MINS
URL = https://leetcode.com/problems/max-consecutive-ones-iii/
1004. Max Consecutive Ones III

Remeber : it is an ( @ most ) option
You need not flip them all too ( or even none too ) 
You flip only the 0s too!
BUDP style
Go solve with (k-1) to rest of the array here too
k = 0 : a base case
count from left, count from right too
How to get the counting mechanism right ( without introducing another loop too ) 
How to account if 0 or 1 too!

Complexity
Let N := len(nums)
TIME = O(N^2)
SPACE = O(N^2) ( EXP ) O(1) ( IMP ) 

Maybe O(N) Time solution possible here too

How to ignore the boundary conditions?
Is this sliding window in the hiding?
Be greedy -> select the most in terms of <k> too.
How to get the consecutive sequences too?

What if we start with all 1's too
We incr rPtr : should not matter s well

You have a bug too : take this case
[1,1,1,0,0,0,1,1,1,1,0], k = 3
    first 3 0's : forgetting the right portion
    or actually -> maybe we need to account that size portion too during calculations
    or change it @ the final pop as well!
    remember : you need not flip em all!

Cases
(A) [1,1,1,0,0,0,1,1,1,1,0], k = 3
    => PASS
(B) [1,1,1,0,0,0,1,1,1,1,0], k = 0
    => PASS
(C) [1,1,1,0,0,0,1,1,1,1,0], k = 6
    => PASS
(D) [1,1,1,1,1,1,1,1,1,1], k = 2
    => PASS
(E) [0,0,0,0,0,0], k = 0 or k = 5
    => PASS
(F)  [0,1,1,0,1,0,0,0,1,1,1,1,1,1,0,0,0,1,1,1,1,1,1,0,1,0,1] k = 7
    => PASS
(G) [1,1,1,1,0,0,0,1] k = 2 
    => PASS
(H) [1,0,0,0,0,1,1,1,1,0,0,0] k = 2
    => PAS
(I)
(J)

WORKS :-)

Time = 30 mins or so
*/
class Solution {
public:
    int longestOnes(vector<int>& nums, int k) {
        int maxNumOnes = 0;
        int n = nums.size();
        int lPtr = 0;
        int rPtr = 0;
        std::queue<int> positions;
        while(rPtr < n){
            if(nums.at(rPtr) == 0){
                if(k == 0){
                    lPtr = rPtr+1;
                } else {
                    if(positions.size() < k){
                        positions.emplace(rPtr);
                    } else if ( positions.size() == k) {
                        lPtr = positions.front() + 1;
                        positions.pop();
                        positions.emplace(rPtr);
                    }
                }
                maxNumOnes = std::max(maxNumOnes, rPtr-lPtr + 1);
                cout << "Max num ones = " << maxNumOnes << endl;
                ++rPtr;
            } else if ( nums.at(rPtr) == 1)  {
                maxNumOnes = std::max(maxNumOnes, rPtr-lPtr + 1);
                ++rPtr;
            }
        }
        return maxNumOnes;
    }
};
