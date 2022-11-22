/*
886. Possible Bipartition
https://leetcode.com/problems/possible-bipartition/

It is a 2-coloring, DFS problem.
13 mins
*/
class Solution {
public:
    bool possibleBipartition(int n, vector<vector<int>>& dislikes) {
        vector<int> colors(n+1,-1); // -1 := not colored : 1-indexed
        // and make +1 in size : accounts for 1-indexing.
        // [1] Create adjacency list.
        map<int,vector<int>> adjList;
        for(int i = 1; i <= n; ++i){
            adjList[i] = vector<int>();
        }
        for(vector<int> edge : dislikes){
            int src = edge.at(0);
            int dst = edge.at(1);
            adjList[src].push_back(dst);
            adjList[dst].push_back(src);
        }
        // [2] Execute algorithm from any node ( use 1 in this case ) 
        int rootNode = 1;
        int initColor = 0;
        for(int i = 1; i <= n; ++i){
            if(colors.at(i) == -1){
                bool isBipartitionable = dfs(colors, adjList,i, initColor);
                if(!isBipartitionable){
                    return false;
                }
            }
        }
        return true;
    }

private:
    bool dfs(vector<int>& colors, const map<int,vector<int>>& adjList, const int rootNode, int color){
        bool stat = true; // assume bipartitionable, unless otherwise.
        if(colors.at(rootNode) != -1){ // already visited
            if(colors.at(rootNode) == color){
                return true;
            } else {
                return false;
            }
        } else { // not visited
            colors.at(rootNode) = color;
            int childColor = 1 - color; // flip : 1 and 0
            const vector<int>& children = adjList.at(rootNode);
            for(int child : children){
                bool childStat = dfs(colors,adjList, child, childColor);
                if(!childStat){
                    stat = false;
                    break;
                }
            }
        }
        return stat;
    }
};
