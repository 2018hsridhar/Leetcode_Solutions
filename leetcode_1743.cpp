/*
1743. Restore the Array From Adjacent Pairs
URL = https://leetcode.com/problems/restore-the-array-from-adjacent-pairs/
Always given at least two elements too for visitations!
Your code works, but runs into a TLE
    -> need a more effiicent solution
    RIP
    40/46 test cases pass here
    
*/
class Solution {
public:
    vector<int> restoreArray(vector<vector<int>>& adjacentPairs) {
        int n = adjacentPairs.size() + 1;
        vector<int> restored;
        // restored.reserve(n); // we can call ahead of time I think?
        int lPtr = 0;
        int rPtr = 0;
        // [1] Create an adjacent list here : modularize to own method.
        map<int,vector<int>> adjList;
        for(const auto& x : adjacentPairs){
            int src = x.at(0);
            int dst = x.at(1);
            if(adjList.find(src) == adjList.end())
                adjList[src] = vector<int>();
            adjList[src].push_back(dst);
            if(adjList.find(dst) == adjList.end())
                adjList[dst] = vector<int>();
            adjList[dst].push_back(src);
        }
        // [2] DFS from any entry in the adj list ; any solution will work here
        // Only the first case has two children as well : all other cases as single child or no child cases!
        // Ensure to handle the `n = 2 ` case as well
        // Ensureit is a valid start node too
        int startNode = adjacentPairs.at(0).at(0);
        vector<int> adjStart = adjList[startNode];
        int curNode = startNode;
        vector<int> curAdj;
        vector<int> curAdjFirst;
        vector<int> curAdjSecond;
        bool hasChild = true;
        restored.push_back(startNode); // always commence here!
        set<int> visited;
        visited.insert(startNode);
        if(adjStart.size() == 1){
            // cout << "here" << endl;
            while(hasChild){
                 hasChild = false; // check again
                 // cout << "CurNode = " << curNode << endl;
                 curAdj = adjList[curNode];
                 for(int child : curAdj){
                     if(visited.find(child) == visited.end()){
                         hasChild = true;
                         restored.push_back(child);
                         visited.insert(curNode);
                         curNode = child;
                     }
                 }
            }
        } else if ( adjStart.size() == 2){ // as if you did it for case n = 1 twice TBH
            // May be another way to do this though
            int firstChild = adjStart.at(0);
            int secondChild = adjStart.at(1);
            restored.push_back(secondChild);
            restored.insert(restored.begin(), firstChild);
            visited.insert(firstChild);
            visited.insert(secondChild);
            vector<int> frontend;
            while(hasChild){
                 hasChild = false; // check again
                 curAdjFirst = adjList[firstChild];
                 curAdjSecond = adjList[secondChild];
                 for(int child : curAdjFirst){
                     if(visited.find(child) == visited.end()){
                         hasChild = true;
                         visited.insert(firstChild);
                         frontend.push_back(child);
                         firstChild = child;
                     }
                 }
                 for(int child : curAdjSecond){
                     if(visited.find(child) == visited.end()){
                         hasChild = true;
                         visited.insert(secondChild);
                         restored.push_back(child);
                         secondChild = child;
                     }
                 }
            }
            reverse(restored.begin(), restored.end());
            restored.insert(restored.end(), frontend.begin(), frontend.end());
        }
        return restored;
    }
};
