/*
1245. Tree Diameter
URL = https://leetcode.com/problems/tree-diameter/

Time taken = - 23 mins - 1 min = 22 mins
Passed on first submission :-)

*/
class Solution {
public:
    int treeDiameter(vector<vector<int>>& edges) {
        map<int,vector<int>> adjList;
        // n nodes ( we do not know what n here is : obtain as we go )
        // [1] Execute standard steps for adjaceny list creation.
        set<int> myNodes;
        for(vector<int> edge : edges){
            int src = edge.at(0);
            int dst = edge.at(1);
            if(adjList.find(src) == adjList.end()){
                adjList[src] = vector<int>();
            }
            if(adjList.find(dst) == adjList.end()){
                adjList[dst] = vector<int>();
            }
            adjList[src].push_back(dst);
            adjList[dst].push_back(src);
            myNodes.insert(src);
            myNodes.insert(dst);
        }
        int n = myNodes.size();
        for(int i = 0; i < n; ++i){
            if(adjList.find(i) == adjList.end()){
                adjList[i] = vector<int>();
            }
        }
        // do we have a way to ID the root from an edgeList?
        // we lack a specific way to make this into a tree.
        // but in graphs, any node can be a root node.
        vector<int> maxPathLen(1,0);
        set<int> visited;
        dfs(0,adjList,maxPathLen,visited);
        return maxPathLen.at(0);
    }

private:
    int dfs(int rootNode, map<int,vector<int>>& adjList, vector<int>& maxPathLen, set<int>& visited){
        visited.insert(rootNode); // at at each func call.
        vector<int> maxPathLenKids;
        int curMaxPathLen = 0;
        vector<int> children = adjList[rootNode];
        for(int x : children){
            if(visited.find(x) == visited.end()){ // if not found in the visited set
                int childPathLen = 1 + dfs(x, adjList,maxPathLen, visited);
                maxPathLenKids.push_back(childPathLen);
            }
        }
        std::sort(maxPathLenKids.begin(), maxPathLenKids.end(), greater<int>()); // desc order desired!
        // need handling for case when size 0,1,or 2 too
        if(maxPathLenKids.size() == 0){
            curMaxPathLen = 0;
            maxPathLen.at(0) = std::max(maxPathLen.at(0), 0);
        } else if ( maxPathLenKids.size() == 1){
            curMaxPathLen = maxPathLenKids.at(0); // we do +1 already
            maxPathLen.at(0) = std::max(maxPathLen.at(0), curMaxPathLen);
        } else {
            curMaxPathLen = std::max((maxPathLenKids.at(0)),(maxPathLenKids.at(1))); // only one path returnable here!
            maxPathLen.at(0) = std::max(maxPathLen.at(0), 
                (maxPathLenKids.at(0)) + (maxPathLenKids.at(1))
            );
        }
        return curMaxPathLen;
    }
};
