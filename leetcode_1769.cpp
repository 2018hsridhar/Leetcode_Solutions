/*
1769. Minimum Number of Operations to Move All Balls to Each Box
URL = https://leetcode.com/problems/minimum-number-of-operations-to-move-all-balls-to-each-box/

Complexity
Let N := len(boxes)
TIME = O(N)
SPACE = O(N) ( EXP ) O(1) ( IMP )

30 mins to solution

Cases
(A)
(B)
(C)
(D)
(E)

*/
class Solution {
public:
    vector<int> minOperations(string boxes) {
        int n = boxes.size();
        vector<int> minOps(n,0);
        vector<int> numFromTheRight(n,0);
        vector<int> sumFromTheRight(n,0);
        for(int i = n-2; i >= 0; --i){
            // [1] Solve the original problem : as if we transfered from r->l
            numFromTheRight.at(i) += numFromTheRight.at(i+1);
            sumFromTheRight.at(i) += sumFromTheRight.at(i+1);
            sumFromTheRight.at(i) += numFromTheRight.at(i+1);
            // [2] Execute the update step now
            if(boxes.at(i+1) == '1'){
                numFromTheRight.at(i) += 1;
                sumFromTheRight.at(i) += 1;
            }
        }
        vector<int> numFromTheLeft(n,0);
        vector<int> sumFromTheLeft(n,0);
        for(int i = 1; i < n; ++i){
            // [1] Solve the original problem : as if we transfered from r->l
            numFromTheLeft.at(i) += numFromTheLeft.at(i-1);
            sumFromTheLeft.at(i) += sumFromTheLeft.at(i-1);
            sumFromTheLeft.at(i) += numFromTheLeft.at(i-1);
            // [2] Execute the update step now
            if(boxes.at(i-1) == '1'){
                numFromTheLeft.at(i) += 1;
                sumFromTheLeft.at(i) += 1;
            }
        }
        for(int i = 0; i < n; ++i){
            minOps.at(i) = sumFromTheLeft.at(i) + sumFromTheRight.at(i);
        }
        return minOps;
    }
};
