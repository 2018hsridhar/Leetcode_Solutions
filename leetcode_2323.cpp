/*
URL = https://leetcode.com/problems/find-minimum-time-to-finish-all-jobs-ii/
2323. Find Minimum Time to Finish All Jobs II

Complexity
Let n := len(workers)
TIME = O(NlgN)
SPACE = O(1) ( EXP & IMP ) 
5 mins
*/
class Solution {
public:
    int minimumTime(vector<int>& jobs, vector<int>& workers) {
        std::sort(jobs.begin(), jobs.end());
        std::sort(workers.begin(), workers.end());
        int n = jobs.size();
        int minTime = INT_MIN;
        for(int i = 0; i < n; ++i){
            // exex ceil operation
            int curTime = std::ceil(static_cast<double>(jobs.at(i)) / static_cast<double>(workers.at(i)));
            minTime = std::max(minTime,curTime);
        }
        return minTime;
    }
};
