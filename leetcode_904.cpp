/*
URL = https://leetcode.com/problems/fruit-into-baskets/
904. Fruit Into Baskets

Complexity
Let N := len(fruits)
TIME = O(N)
SPACE = O(1) ( IMP & EXP ) ( 2 keys only ) 

Edge Cases
(A) [1,2,1,2,1] => 5
(B) [1,1,1,1,1] => 5
(C) [0,1,2,2] => 3
(D) [1,2,3,2,2] => 4
(E) [2,3,2,1,2,3,2,2]=> 4
(F) [1,1,1,1,2,3,3,3,4,2,2,6,5,4,6,18,9,9,1,2] => 
    ^ wrong : amend this
(G) [4,3,2,2,3,2,3,2,4,4,4,4,4] => 7
(H)
(I)

Kudos on catching your bug too :-)

*/
class Solution {
public:
    int totalFruit(vector<int>& fruits) {
        int maxFruits = 0;
        int n = fruits.size();
        int curLen = 0;
        int lPtr = n-1;
        int rPtr = n-1;
        map<int,int> elLeftMostIdx;
        while(lPtr >= 0){
            int fruit = fruits.at(lPtr);
            if(elLeftMostIdx.size() != 2){
                elLeftMostIdx[fruit] = lPtr; // createOrUpdateTheKey :-)
                curLen = rPtr - lPtr + 1;
                maxFruits = max(maxFruits, curLen);
            } else {
                if(elLeftMostIdx.find(fruit) == elLeftMostIdx.end()){
                    int leftEl = INT_MAX;
                    int leftElIdx = INT_MAX;
                    int rightEl = INT_MIN;
                    int rightElIdx = INT_MIN;
                    for(const auto& [k,v] : elLeftMostIdx){
                        if(v <= leftElIdx){
                            leftElIdx = v;
                            leftEl = k;
                        } 
                        if ( v >= rightElIdx){
                            rightElIdx = v;
                            rightEl = k;
                        }
                    }
                    int delta = rightElIdx - leftElIdx - 1; // how many steps till the end
                    // remove the rightEl from hashmap
                    // and execute replacement
                    elLeftMostIdx.erase(rightEl);
                    elLeftMostIdx[fruit] = lPtr; // createOrUpdateTheKey :-)
                    curLen = (leftElIdx+delta) - lPtr + 1;
                    maxFruits = max(maxFruits, curLen);
                    rPtr = leftElIdx + delta;
                } else {
                    curLen = rPtr - lPtr + 1;
                    elLeftMostIdx[fruit] = lPtr;
                    maxFruits = max(maxFruits, curLen);
                }
            }
            --lPtr;
        }
        // execute again?
        return maxFruits;
    }
};
