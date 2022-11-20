/*
URL = https://leetcode.com/problems/reachable-nodes-with-restrictions/
2368. Reachable Nodes With Restrictions

Complexity
Let V = N = #-nodes
Let E = #-edges
Time = O(V+E)
Space = O(1) ( IMP ) O(V+E) ( EXP ) 

Your algo is right but runs into a TLE
Can we hasten our algorithm
Probably in the find for restricted : this may hinder us

18-5 = 13 mins
*/
class Solution {
public:
    int reachableNodes(int n, vector<vector<int>>& edges, vector<int>& restricted) {
        int numReach = 0;
        map<int,vector<int>> adjList;
        for(int i = 0; i < n; ++i){
            adjList[i] = vector<int>();
        }
        for(const vector<int> edge : edges){
            int src = edge.at(0);
            int dst = edge.at(1);
            adjList[src].push_back(dst);
            adjList[dst].push_back(src);
        }
        std::stack<int> stk;
        stk.push(0);
        std::set<int> visited;
        std::sort(restricted.begin(), restricted.end());
        // avoid set via binary search on a sorted vector :-)
        while(!stk.empty()){ // ideally no TLE here.
            int parentNode = stk.top();
            stk.pop();
            visited.emplace(parentNode);
            numReach++; // assumption : stack has only valid nodes
            vector<int> children = adjList[parentNode];
            for(int child : children){
                if(std::binary_search(restricted.begin(), restricted.end(), child) == false && visited.find(child) == visited.end()) {
                // if(std::find(restricted.begin(), restricted.end(), child) == restricted.end() && visited.find(child) == visited.end()) {
                // if(found.find(child) == found.end() && visited.find(child) == visited.end()) {
                    stk.push(child);
                }
            }
        }
        return numReach;
    }
};
