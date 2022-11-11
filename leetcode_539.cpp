/*
539. Minimum Time Difference
URL = https://leetcode.com/problems/minimum-time-difference/description/

Cases
(A) ["00:00","23:59","14:05","01:09","02:22"] => PASS

*/
class Solution {
public:
    int findMinDifference(vector<string>& timePoints) {
        int myMinDiff = INT_MAX;
        vector<int> myTimes;
        for(const string& point : timePoints){
            int posColon = point.find(":");
            int hourTime = stoi(point.substr(0,posColon)); // 0, sz 2
            int minTime = stoi(point.substr(posColon+1,point.size() - posColon-1)); // 3, sz 2
            printf("Hour time = %d \t min time = %d\n", hourTime, minTime);
            int integralTime = ((hourTime * 60) + minTime);
            myTimes.push_back(integralTime);
        }
        // minutes difference desired : there's a loop-de-loop thing going on too
        // must account that way as well ( reverse )
        // you get minute diff between ANY two points
        std::sort(myTimes.begin(), myTimes.end());
        int n = myTimes.size();
        for(int i = 0; i < myTimes.size(); ++i){
            int curDay = myTimes.at(i);
            int nextDay = myTimes.at((i+1)%n);
            if(nextDay < curDay){ // wrap around case
                nextDay += (60*24); // offset
            }
            int myCurDiff = nextDay - curDay;
            myMinDiff = std::min(myMinDiff, myCurDiff);
        }
        return myMinDiff;        
    }
};
