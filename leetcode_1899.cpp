/*
1899. Merge Triplets to Form Target Triplet
URL = https://leetcode.com/problems/merge-triplets-to-form-target-triplet/

Complexity
Let N := len(triplets)
TIME = O(N)
SPACE = O(1) ( EXP ) O(1) ( IMP ) 
*/
class Solution {
public:
    bool mergeTriplets(vector<vector<int>>& triplets, vector<int>& target) {
        bool status = true;
        bool canMergeFirst = checkMerge(triplets,target,0,1,2);
        bool canMergeSecond = checkMerge(triplets,target,1,0,2);
        bool canMergeThird = checkMerge(triplets,target,2,0,1);
        status = (canMergeFirst && canMergeSecond && canMergeThird);
        return status;
    }
    
private:
    bool checkMerge(const vector<vector<int>>& triplets, const vector<int>& target, int targetIdx,
                   int lIdx, int rIdx){
        bool status = false;
        bool found = false;
        int n = triplets.size();
        for(int i = 0; i < n; ++i){
            const vector<int>& myTriplet = triplets.at(i);
            // Can you references as if not ptrs ( but are ptrs ) -> WOAH
            if(myTriplet.at(targetIdx) == target.at(targetIdx)){
                if(myTriplet.at(lIdx) <= target.at(lIdx) && myTriplet.at(rIdx) <= target.at(rIdx)){
                    status = true;
                    found = true;
                    break;
                }
            }
        }
        return ( status && found );
    }
    
};
