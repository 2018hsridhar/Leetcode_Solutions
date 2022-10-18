/*
2391. Minimum Amount of Time to Collect Garbage
URL = https://leetcode.com/problems/minimum-amount-of-time-to-collect-garbage/
Complexity : Quadratic = O(NK) = T, O(1) = S
*/
class Solution {
public:
    int garbageCollection(vector<string>& garbage, vector<int>& travel) {
        int minTime = 0;
        vector<char> gTypes = vector<char>{'M','P','G'}; // no constructor-() syntax : initializer list.
        size_t n = garbage.size();
        for(int k = 0; k < gTypes.size(); ++k){ // For each garbage type
            int runTravelTime = 0;
            int myTruckTime = 0;
            char gType = gTypes.at(k);
            for(int i = 0; i < n; ++i){ // Compilers can optimize for loops easily over while loops
                // Candidate function template viability : #-args provided for reoslution
                int numGType = std::count(garbage.at(i).begin(), garbage.at(i).end(), gType);
                myTruckTime += numGType;
                if(numGType >= 1){
                    myTruckTime += runTravelTime;
                    runTravelTime = 0;
                }
                if(i < n - 1) {
                    runTravelTime += travel.at(i);
                }
            }
            minTime += myTruckTime;
        }
        return minTime;
    }
};
