/*
2285. Maximum Total Importance of Roads
URl = https://leetcode.com/problems/maximum-total-importance-of-roads/description/

*/
class Solution {
public:
    long long maximumImportance(int n, vector<vector<int>>& roads) {
        using ll = long long;
        ll maxImp = 0.0;
        // n = #-vertices : already affixed here
        // no need to worry about assignment of vals : just compute degrees of each node and sort that list
        // can we set up ordering by vals : greatest -> least. Ordering is done via keys here
        unordered_map<int,int> degree;
        for(const vector<int>& road : roads){
            int source = road.at(0);
            int dst = road.at(1);
            degree[source]++;
            degree[dst]++;
        }
        vector<pair<int,int>> outDegs; // deep copy needed for sort ops here
        for(const pair<int,int>& entry : degree){
            outDegs.push_back(entry);
        }
        // lambda syntax is reverse : type is @ the end, not the beginning
        std::sort(outDegs.begin(), outDegs.end(), [](pair<int,int>& a, pair<int,int>& b) -> bool {
            return a.second > b.second;
        });
        for(const auto& [node,deg] : outDegs){
            // Can we avoid multiple static_cast operations? They seem . . . bleh
            maxImp += static_cast<ll>(n)*static_cast<ll>(deg);
            --n;
        }
        return maxImp;
    }
};
