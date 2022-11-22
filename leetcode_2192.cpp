/*
2192. All Ancestors of a Node in a Directed Acyclic Graph
URL = https://leetcode.com/problems/all-ancestors-of-a-node-in-a-directed-acyclic-graph/

Typical topological sort algorithm.
27 mins

*/
class Solution {
public:
    // num verts an unknown oto.
    vector<vector<int>> getAncestors(int n, vector<vector<int>>& edges) {
        vector<vector<int>> myAncestors(n,vector<int>()); // n vectors of empty size.
        vector<set<int>> myAncestorsSet(n,set<int>()); // n vectors of empty size.
        int e = edges.size();
        // adjList and map of inDegrees of each vertex.
        map<int,vector<int>> adjList;
        map<int,int> inDegVerts;
        for(const vector<int> edge : edges){
            int src = edge.at(0);
            int dst = edge.at(1);
            if(adjList.find(src) == adjList.end()){
                adjList[src] = vector<int>();
            }
            adjList[src].push_back(dst);
            if(inDegVerts.find(dst) == inDegVerts.end()){
                inDegVerts[dst] = 0;
            }
            if(inDegVerts.find(src) == inDegVerts.end()){
                inDegVerts[src] = 0;
            }
            inDegVerts[dst]++;
        }
        // [2] Exec the top sort algo ( queue-based ) 
        queue<int> topSort;
        for(const pair<int,int> entry : inDegVerts){
            // printf("%d\t%d\n", entry.first,entry.second);
            if(entry.second == 0){
                topSort.push(entry.first);
            }
        }
        set<int> visited;
        while(topSort.size() > 0){
            int parentNode = topSort.front();
            topSort.pop();
            visited.emplace(parentNode);
            vector<int>& children = adjList[parentNode];
            for(int child : children){
                // printf("Child = %d\n", child);
                inDegVerts[child]--;
                if(inDegVerts[child] == 0 && visited.find(child) == visited.end()){
                    topSort.push(child);
                }
                // for the child, set its ancestors to the following
                // [1] the ancestors of parent node
                // [2] the parent node itself
                // reference gotcha here ( no deep copy ) 
                // vector<int>& ancestorsChild = myAncestors.at(child);
                // ahh : no duplicate shere ( use set ) ( or other technique ) ?
                set<int>& ancestorsParent = myAncestorsSet.at(parentNode);
                for(int anc : ancestorsParent){
                    myAncestorsSet.at(child).insert(anc);
                }
                myAncestorsSet.at(child).insert(parentNode);
            }
        }
        // [3] sort each part of myAncestors : sorts tend to efficiency.
        // annoying copy op
        for(int i = 0; i < myAncestorsSet.size(); ++i){
            for(int x : myAncestorsSet.at(i)){
                myAncestors.at(i).push_back(x);
            }
        }
        for(vector<int> anc : myAncestors){
            std::sort(anc.begin(), anc.end(), std::less<int>());
        }
        return myAncestors;
    }
};
