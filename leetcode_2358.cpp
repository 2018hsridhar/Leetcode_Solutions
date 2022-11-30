/*
2358. Maximum Number of Groups Entering a Competition
URL = https://leetcode.com/problems/maximum-number-of-groups-entering-a-competition/description/
*/
class Solution {
public:
    // struct pred {
    //     // State can be tracked here : C++ 11
    //     bool operator() {const int& a, const int& b) const {
    //         return a < b;
    //     }
    // };

    int maximumGroups(vector<int>& grades) {
        // https://stackoverflow.com/questions/2758080/how-to-sort-an-stl-vector
        // Check syntax correctness.
        int numGroups = 0;
        // std::sort(grades.begin(), grades.end(), pred());
        // Elements are not unique = your gotcha
        int n = grades.size();
        int delta = 1;
        while(n > 0){
            if(n - delta >= 0){
                n -= delta;
                delta++;
                numGroups++;
            } else {
                break;
            }
        }
        return numGroups;
    }
};
