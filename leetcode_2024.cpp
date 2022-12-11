/*
URL = https://leetcode.com/problems/maximize-the-confusion-of-an-exam/
2024. Maximize the Confusion of an Exam

Let N := len(answerKey)
Time = O(N)
Space = O(N) ( EXP ) O(1) ( IMP ) 

Edge Cases:
(A) "FFTTF" k = 2 -> 4
(B) "FFFFFT" k = 1 -> 5
(C) "TFTTFFTTTFFFTTTTT" k = 3 

25 mins ( with your unit testing at least ) !

*/
class Solution {
public:
    int maxConsecutiveAnswers(string answerKey, int k) {
        int maxConsec = 0;
        maxConsec = max(internalHelper(answerKey,k,'T'), internalHelper(answerKey,k,'F'));      
        return maxConsec;
    }

private:
    // You may not even perform operations too!
    // Reuse var names, in diff scope ( not the best idea ) 
    int internalHelper(const string& answerKey, int k, const char answerKeyCase) {
        int maxConsec = 0;
        int n = answerKey.size();
        int lPtr = 0;
        int rPtr = 0;
        std::queue<int> potentialLeftPositions;
        while(rPtr < n){
            if(answerKey.at(rPtr) == answerKeyCase){ // accrue to your len
                ++rPtr;
                maxConsec = std::max(maxConsec, rPtr - lPtr); // 0,1,2 ( +1 offset ) 
            } else {
                potentialLeftPositions.push(rPtr);
                ++rPtr;
                if(k > 0){
                    k--;
                } else if(k == 0){
                    lPtr = potentialLeftPositions.front() + 1;
                    potentialLeftPositions.pop();
                }
                maxConsec = std::max(maxConsec, rPtr - lPtr); // 0,1,2 ( +1 offset ) 
            }
        }
        return maxConsec;
    }
};
