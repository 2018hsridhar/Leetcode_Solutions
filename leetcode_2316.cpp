/*
URL = https://leetcode.com/problems/count-unreachable-pairs-of-nodes-in-an-undirected-graph/
2316. Count Unreachable Pairs of Nodes in an Undirected Graph
23 mins
*/
class Solution {
// Is using ll allowed in this scope?
using ll = long long;
public:
    long long countPairs(int n, vector<vector<int>>& edges) {
        ll numPairs = 0;
        // T& identifier ( type & ) 
        // Initialize the adjList
        unordered_map<int,vector<int>> adjList;
        // better yet : just make it for each node
        for(int i = 0; i < n; ++i){
            adjList[i] = vector<int>();
        }
        for(const vector<int>& edge : edges){
            int src = edge.at(0);
            int dst = edge.at(1);
            auto it = adjList.find(src);
            // if(it == adjList.end()){
                // adjList[src] = vector<int>();
            // }
            adjList[src].push_back(dst);
            // it->second.push_back(dst);
            auto itTwo = adjList.find(dst);
            // if(itTwo == adjList.end()){
                // adjList[dst] = vector<int>();
            // }
            adjList[dst].push_back(src);
            // itTwo->second.push_back(src);
        }
        // Exec DFS per each node
        // avoid globals/pass mem addrs in func calls.
        // avoid introducing data to bad scopes
        std::unordered_set<int> visited;
        int curNumUnderCon = n;
        for(const auto& [node,hood] : adjList){
            if(visited.find(node) == visited.end()){ // node NOT visited!
                ll sizeConnComp = dfs(node,adjList,visited);
                ll sizeOtherComp = curNumUnderCon - sizeConnComp;
                // you forgot to avoid double counting here as well :-(. Go running for sizeOtherComp at least
                numPairs += sizeConnComp * sizeOtherComp;
                curNumUnderCon -= sizeConnComp;
            }
        }
        return numPairs;
    }

private:
    ll dfs(int parentNode, unordered_map<int,vector<int>>& adjList, unordered_set<int>& visited){
        ll curSize = 0;
        if(visited.find(parentNode) != visited.end()){ // node not visited
            return 0;
        } else {
            visited.insert(parentNode);
            curSize++;
            vector<int>& children = adjList.at(parentNode);
            for(int child : children){
                curSize += dfs(child,adjList,visited);
            }
        }
        return curSize;
    }

};
