/*

2274. Maximum Consecutive Floors Without Special Floors
URL = https://leetcode.com/problems/maximum-consecutive-floors-without-special-floors/description/

Cases
(A) 2,9,[2,3,4,5,6,7,8,9]   => PASS
(B) 2,9,[2,9]               => PASS
(C) 2,9,[3,8]               => PASS
(D) 1,13,[1,4]              => PASS
(E) 1,1,[1]                 => PASS
(F) 1,13,[11,12,13]            => PASS
(G) 1,13,[1,2,3]            => PASS
(H) 1,13,[1,2,3,6,7,11,12]      => PASS


*/
class Solution {
public:
    int maxConsecutive(int bottom, int top, vector<int>& special) {
        int maxCon = 0;
        std::sort(special.begin(), special.end());
        int lPtr = bottom;
        int i = 0; // pos at special ( guaranteed in range too )
        while(lPtr <= top){
            if(i < special.size()){
                if(special.at(i) == lPtr){
                    ++lPtr;
                    ++i;
                } else {
                    int myNextRight = special.at(i) - 1;
                    int curIntSize = (myNextRight - lPtr + 1);
                    maxCon = std::max(maxCon,curIntSize);
                    lPtr = special.at(i);
                }
            } else {
                break;
            }
        }
        if(lPtr <= top){
            int curIntSize = top - lPtr + 1;
            maxCon = std::max(maxCon,curIntSize);
        }
        return maxCon;
    }
};
