/*
Cases
(A) [1,0,0,0,0]
(B) [0,0,0,0,1]
(C) [1,0,0,0,1]
(D) [1,0,1,0,1]
(E) [1,0,0,1,0,1,0,0,0,1]
(F) [0,0,0,0,0,0,0,0,1,0,0,1,0,1,0,0,0,1]
(G) [0,0,0,0,0,0,0,0,1,0,0,1,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0]


*/
class Solution {
public:
    int maxDistToClosest(vector<int>& seats) {
        int hitTwoSeats = 0;
        int maxDist = 1; // presume this
        int lPtr = 0;
        int rPtr = 0;
        int n = seats.size();
        while(rPtr < n){
            if(seats.at(rPtr) == 1){
                hitTwoSeats++;
                int curDist = rPtr - lPtr;
                if(hitTwoSeats >= 2){
                    maxDist = std::max(maxDist,(int)std::floor(curDist/2));
                } else {
                    maxDist = std::max(maxDist,rPtr - lPtr);
                }
                lPtr = rPtr;
                rPtr = lPtr + 1;
            } else {
                ++rPtr;
            }
        }
        maxDist = std::max(maxDist,(rPtr -1 - lPtr ));
        return maxDist;
    }
};
