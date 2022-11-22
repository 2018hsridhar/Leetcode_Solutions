/*
URL = https://leetcode.com/problems/node-with-highest-edge-score/
2374. Node With Highest Edge Score

Complexity
Let N/V := #-verts
Let E := #-edges
TIME = O(E)
SPACE = O(V) ( EXP ) O(1) ( IMP ) 

8:30 mins only
*/
class Solution {
public:
    int edgeScore(vector<int>& edges) {
        int n = edges.size(); // matches exact num verts
        using ll = long long;
        vector<ll> edgeScores(n,0); // 1-indexed!)
        for(int i = 0; i < n; ++i){
            int src = i;
            int dst = edges.at(i);
            edgeScores.at(dst) += src;
        }
        ll myMaxScore = 0; // node : not the score
        int myMaxNode = -1; // unsolved for.
        for(int i = 0; i < n; ++i){ // rets node with smallest index/label too
            if(edgeScores.at(i) > myMaxScore){ // strict inequality
                myMaxScore = edgeScores.at(i);
                myMaxNode = i;
            }
        }
        return myMaxNode;
    }
};
