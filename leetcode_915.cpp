/*
915. Partition Array into Disjoint Intervals
URL = https://leetcode.com/problems/partition-array-into-disjoint-intervals/
Seems easier than it looks: two ptrs meet in middle
    Hit the first El which breaks the constraints here
    Return from there for size of leftPart
Test cases are generated such that partitions exist
\forall x \in left, \forall y \in right, x <= y holds true

Better : we can store the running min, running max of both partition sides
Seems stack based too!

Edge Cases
(A) [1,1,1,9,0,6,12] 
    => 6
    ^ FIXIT
    ^ Go left -> right too
(B) [0,1,2,3,4]
    => 1
(C) [0,0,0,0]
    => 1
(D) [5,0,6,3,2,7,1,9]
    => 7
(E)
(F)
(G)

TLE, but right idea :-)


*/
class Solution {
public:
    int partitionDisjoint(vector<int>& nums) {
        int minSizePart = INT_MAX;
        int n = nums.size();
        std::vector<int> runLeftMax;
        std::vector<int> runRightMin;
        int leftMax = INT_MIN;
        int rightMin = INT_MAX;
        // use priority queue ( sifts within )?
        // HOw else to get O(N) though?
        for(int i = 0; i < nums.size(); ++i){
            leftMax = max(leftMax,nums.at(i));
            runLeftMax.push_back(leftMax);
        }
        for(int i = n-1; i >= 0; --i){
            rightMin = min(rightMin,nums.at(i));
            // Insertion operator got the better of you!
            // Reversals prefered!
            runRightMin.push_back(rightMin);
        }
        reverse(runRightMin.begin(), runRightMin.end());
        // Do not encompass the entire array here!
        for(int i = 0; i < nums.size()-1; ++i){
            if(runLeftMax.at(i) <= runRightMin.at(i+1))
            {
                minSizePart = min(minSizePart, i+1);
            }
        }
        return minSizePart;
    }
};
