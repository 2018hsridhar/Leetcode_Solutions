/*

1753. Maximum Score From Removing Stones
URL = https://leetcode.com/problems/maximum-score-from-removing-stones/description/

Cases
(A) (1,1,1)
(B) (1,2,3)
(C) 
(D)
(E)
(F)


*/
class Solution {
public:
    int maximumScore(int a, int b, int c) {
//   std::priority_queue<int> first;
//   std::priority_queue<int> second (myints,myints+4);
//   std::priority_queue<int, std::vector<int>, std::greater<int> >
                            // third (myints,myints+4);
        int maxScore = 0;
        int myVals[] = {a,b,c};
        std::priority_queue<int,std::vector<int>,std::less<int>> vals(myVals, myVals + 3);
        while(true){
            const int firstEl = vals.top(); // reference to a primitive : ignore
            vals.pop();
            const int secondEl = vals.top(); // prevent manipulation of data you read
            vals.pop();
            if(firstEl > 0 && secondEl > 0){
                maxScore++;
                const int nextFirstEl = firstEl - 1; // data you write now : explicitly deep-copied!
                const int nextSecondEl = secondEl - 1;
                vals.push(nextFirstEl);
                vals.push(nextSecondEl);
            } else {
                break;
            }
        }
        return maxScore;
    }
};
