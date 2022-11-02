/*
Follows follow one another if p1.y < p2.x ( strictly )
Return the longest chain possible
Select any order
Seems a sort could work ( greediest idea ) 
Lexicographic sorting

URL = https://leetcode.com/problems/maximum-length-of-pair-chain/
646. Maximum Length of Pair Chain

Only 1K pairs max -> O(N^2) BUDP not atrocious
COMPLEXITY : 
TIME = O(N^2)
SPACE = O(N) ( EXP ) O(1) ( IMP ) 

Cases
(A) [[1,2],[2,3],[3,4],[1,10],[1,8]]
10 mins 30 sec to solution

*/
class Solution {
public:
    int findLongestChain(vector<vector<int>>& pairs) {
        int globalLongestChain = 1;
        int n = pairs.size();
        // bool cmp(const Type1 &a, const Type2 &b)
        std::sort(pairs.begin(), pairs.end(), [](const vector<int>& p1, const vector<int>& p2){
           if(p1.at(0) < p2.at(0))
               return true;
            else if ( p1.at(0) > p2.at(0))
                return false;
            else{
                if(p1.at(1) < p2.at(1))
                    return true;
                else
                    return false;
            }
        });
        std::vector<int>DP(n,1);
        for(int i = n-2; i >= 0; --i){
            int subProblemMaxLen = 1;
            for(int j = i+1; j < n; ++j){
                if(pairs.at(j).at(0) > pairs.at(i).at(1)){
                    subProblemMaxLen = std::max(subProblemMaxLen, 1 + DP[j]); 
                }
            }
            DP[i] = subProblemMaxLen;
            globalLongestChain = std::max(globalLongestChain,DP[i]);
        }
        return globalLongestChain;
    }
};
