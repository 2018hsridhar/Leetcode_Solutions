/*
2462. Total Cost to Hire K Workers
URL = https://leetcode.com/problems/total-cost-to-hire-k-workers/

Hunch : bias to use of priority queue

Complexity
TIME = O(...)
SPACE O(...) ( EXP ) O(...) ( IMP ) 

33 mins passed
How much more to give?

Edge Cases
(A) [17,12,19,20] 3 4 => PASS
(B) [1] 1 1 => PASS
(C) [5,4,6,8,9] 2 5 => PASS
(D) [5,4,6,8,9,7,5,2,7] 9 9 => PASS
(E)
(F)
(G)

WOW
35 minutes

*/
class Solution {
public:
    long long totalCost(vector<int>& costs, int k, int candidates) {
        using ll = long long;
        ll myTotalCost = 0;
        int lPtr = 0;
        int rPtr = costs.size() - 1;
        std::priority_queue<int,vector<int>,std::greater<int>> leftSide;
        std::priority_queue<int,vector<int>,std::greater<int>> rightSide;
        int n = costs.size();
        // [1] Fill up left-hand side
        for(int i = 0; i < candidates; ++i){
            if(lPtr < n){
                leftSide.emplace(costs.at(lPtr));
                ++lPtr;
            } else {
                break;
            }
        }
        --lPtr;
        // [2] Fill up right-hand side
        bool leftRightHit = false;
        // GOTCHA : handle case of lPtr = n
        if(lPtr == n){
            leftRightHit = true;
        }
        for(int i = 0; i < candidates; ++i){
            if(leftRightHit == true || (rPtr >= 0 && rPtr == lPtr)){
                leftRightHit = true; // diff case!
                break;
            }
            else if(rPtr >= 0){
                rightSide.emplace(costs.at(rPtr));
                --rPtr;
            } else {
                break;
            }
        }
        ++rPtr;
        // [3] Priority queues filled : execute main portion of algo
        // [4] Execute overlap case ( or minimal candidates case ) 
        for(int i = 0; i < k; ++i){
            if(leftRightHit == true){
                // Handle case : each side may not have data
                if(leftSide.size() == 0 && rightSide.size() == 0){
                    break;
                } else if ( rightSide.size() == 0 && leftSide.size() > 0){
                    myTotalCost += leftSide.top();
                    leftSide.pop();
                } else if ( leftSide.size() == 0 && rightSide.size() > 0){
                    myTotalCost += rightSide.top();
                    rightSide.pop();
                } else {
                    int topLeft = leftSide.top();
                    int topRight = rightSide.top();
                    // printf("[%d\t%d]\n", topLeft,topRight);
                    if(topLeft <= topRight){
                        myTotalCost += topLeft;
                        leftSide.pop();
                    } else {
                        myTotalCost += topRight;
                        rightSide.pop();
                    }
                }
            } else if ( leftRightHit == false ) {
                // Note : expected that both sides have data
                // printf("ptrs = [%d,%d]\n", lPtr,rPtr);
                int topLeft = leftSide.top();
                int topRight = rightSide.top();
                if(topLeft <= topRight){
                    myTotalCost += topLeft;
                    leftSide.pop();
                    ++lPtr;
                    if(lPtr < rPtr){
                        leftSide.emplace(costs.at(lPtr));
                    }
                } else {
                    myTotalCost += topRight;
                    rightSide.pop();
                    --rPtr;
                    if(lPtr < rPtr){
                        rightSide.emplace(costs.at(rPtr));
                    }
                }
                if(lPtr == rPtr){
                    leftRightHit = true;
                }
            }
        }
        return myTotalCost;
    }
};
