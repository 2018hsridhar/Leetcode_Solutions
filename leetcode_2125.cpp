/*
https://www.geeksforgeeks.org/std-count-cpp-stl/
2125. Number of Laser Beams in a Bank
URL = https://leetcode.com/problems/number-of-laser-beams-in-a-bank/
*/
class Solution {
public:
    int numberOfBeams(vector<string>& bank) {
        int numBeams = 0;
        int lCount = 0;
        int rCount = 0;
        bool hitRow = false;
        for(auto& entry : bank){
            int oneCount = std::count(entry.begin(), entry.end(), '1');
            if(oneCount > 0){
                if(!hitRow){
                    hitRow = true;
                    rCount = oneCount;
                } else {
                    lCount = rCount;
                    rCount = oneCount;
                    numBeams += (lCount * rCount);
                }
            }
        }
        return numBeams;
    }
};
