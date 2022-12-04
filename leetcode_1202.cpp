/*
Assume a case of all unique pairs here
URL = https://leetcode.com/problems/smallest-string-with-swaps/
1202. Smallest String With Swaps

Complexity
Let S := len(S)
Let E := len(pairs)
Time = O(E) + O(S)
Space = O(S+E) ( EXP ) O(S) ( IMP ) 
25 mins
passed 1st try

*/
class Solution {
public:
    string smallestStringWithSwaps(string s, vector<vector<int>>& pairs) {
        // [1] Create our adjList
        // Some entries may be empty -> isolated node cases.
        map<int,vector<int>> adjList;
        int n = s.size();
        for(int i = 0; i < n; ++i){
            adjList[i] = vector<int>();
        }
        for(vector<int>& pair: pairs){
            int src = pair.at(0);
            int dst = pair.at(1);
            adjList[src].push_back(dst);
            adjList[dst].push_back(src);
        }
        // [2] DFS here
        std::string result(s); // initialize as a direct copy
        set<int> visited;
        for(int i = 0; i < n; ++i){
            // Sort and DFS steps incumbent.
            if(visited.find(i) == visited.end()){
                vector<int> indices;
                vector<char> replChars;
                dfs(s, i,indices,replChars,visited, adjList);
                std::sort(indices.begin(), indices.end());
                std::sort(replChars.begin(), replChars.end());
                for(int a = 0; a < indices.size(); ++a){
                    int wIdx = indices.at(a);
                    int wChar = replChars.at(a);
                    result[wIdx] = wChar;
                }
            }
        }
        return result;
    }

private:
    void dfs(string& s, int parent, vector<int>& indices, vector<char>& replChars, set<int>& visited, map<int,vector<int>>& adjList){
        if(visited.find(parent) == visited.end()){
            // [1] Op on parent
            visited.insert(parent);
            indices.push_back(parent);
            replChars.push_back(s[parent]);
            // [2] Op on kids
            vector<int> children = adjList[parent];
            for(int child : children){
                dfs(s,child,indices,replChars,visited,adjList);
            }
        }
    }
};
