/*
1198. Find Smallest Common Element in All Rows
URL = https://leetcode.com/problems/find-smallest-common-element-in-all-rows/description/

Complexity
Let M := #-rows, N := #-cols
TIME = O(NLGM)
SPACE = O(1) ( EXP && IMP ) 
*/
class Solution {
public:
    int smallestCommonElement(vector<vector<int>>& mat) {
        bool foundAMinEl = false;
        int curMinEl = INT_MAX;
        int m = mat.size();
        int n = mat[0].size();
        for(int j = n-1; j >= 0; --j){ // can avoid std::min() due to rev order here.
            int elToSearch = mat.at(0).at(j);
            bool foundElInAllRows = true;
            for(int i = 1; i < m; ++i){
                // el must be found in all rows too!
                if(!foundElInBinarySearch(elToSearch, mat.at(i))){
                    foundElInAllRows = false;
                }
            }
            if(foundElInAllRows){
                foundAMinEl = true;
                curMinEl = std::min(curMinEl, elToSearch);
            }
        }
        if(foundAMinEl == false){
            return -1;
        }
        return curMinEl;
    }

private:
    bool foundElInBinarySearch(int el, vector<int>& myRow){
        int low = 0;
        int high = myRow.size() - 1;
        while(low <= high){
            int mid = low + (high - low)/2;
            if(myRow.at(mid) == el){
                return true;
            } else if ( myRow.at(mid) < el){
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return false;
    }

};
