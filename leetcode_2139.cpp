/*
2139. Minimum Moves to Reach Target Score

Cases
(A) 999034023 => PASS
(B)
(C)
(D)
(E)

*/
class Solution {
public:
    int minMoves(int target, int maxDoubles) {
        int myMoves = 0;
        while(target > 1){
            if(target % 2 == 0 && maxDoubles > 0){
                    target /= 2;
                    maxDoubles--;
                    myMoves++;
            } else {
                if(maxDoubles == 0){
                    myMoves += target - 1;
                    break;
                } else {
                    myMoves++;
                    target -= 1;
                }
            }
        }
        return myMoves;
    }
};
