/*
1042. Flower Planting With No Adjacent
URL = https://leetcode.com/problems/flower-planting-with-no-adjacent/

Complexity
Time = O(V+E) = O(4V) = O(V)
Space = O(V+E) ( EXP ) O(V) ( IMP ) 

29 min to solution
Accepted on first submission too!

*/
class Solution {
public:
    vector<int> gardenNoAdj(int n, vector<vector<int>>& paths) {
        vector<int> assignment(n,-1);
        // [1] construct the adj list
        map<int,vector<int>> adjList;
        for(int i = 1; i <= n; ++i){
            adjList[i] = vector<int>();
        }
        for(vector<int> edge : paths){
            int src = edge.at(0);
            int dst = edge.at(1);
            adjList[src].push_back(dst);
            adjList[dst].push_back(src);
        }
        // [2] exec dfs and incorporate visited set
        set<int> visited;
        for(int i = 1; i <= n; ++i) {
            if(visited.find(i) == visited.end()){
                dfs(i,adjList,assignment,visited);
            }
        }
        return assignment;
    }

private:
    void dfs(int rootNode, map<int,vector<int>>& adjList, vector<int>& assignment, set<int>& visited){
        set<int> valSpace = {1,2,3,4};
        if(visited.find(rootNode) != visited.end()){
            return;
        } else {
            visited.emplace(rootNode);
            vector<int> children = adjList[rootNode];
            // [1] check visited children : assign val not colliding with visited
            for(int child : children){
                if(visited.find(child) != visited.end()){
                    int valAssigned = assignment.at(child-1);
                    valSpace.erase(valAssigned);
                } 
            }
            assignment.at(rootNode-1) = static_cast<int>(*(valSpace.begin()));          
            // [2] go through the unvisited children and dfs from them
            for(int child : children){
                if(visited.find(child) == visited.end()){
                    dfs(child,adjList,assignment,visited);
                } 
            }
        }
    }
};
