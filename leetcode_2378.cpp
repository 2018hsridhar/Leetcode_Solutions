/*
2378. Choose Edges to Maximize Score in a Tree
URL = https://leetcode.com/problems/choose-edges-to-maximize-score-in-a-tree/

Let N := #-verts in the tree
TIME = O(N)
SPACE = O(N) ( EXP ) O(1) ( IMP ) 

40 minutes to solution :-)
Got it on the first try!
*/
class Solution {
using ll = long long; // Var is class-scope here.
public:
    long long maxScore(vector<vector<int>>& edges) {
        map<int,ll> maxPerNode;
        map<int, vector<pair<int,int>>> adjList; // references must be initialied : no empty decl
        generateAdjList(edges,adjList);
        internal(adjList, maxPerNode,0); // use value of func return : do not get -wunused
        ll globalMaxScore = maxPerNode.at(0);
        return globalMaxScore;
    }
    
private:
     
    void generateAdjList(vector<vector<int>>& edges, map<int, vector<pair<int,int>>>& mp){
         // Eschew 1st edge = [-1,-1] here.
         for(int i = 1; i < edges.size(); ++i){
             int child = i;
             int parent = edges.at(i).at(0);
             int weight = edges.at(i).at(1);
             if(mp.find(parent) == mp.end()){
                 mp[parent] = vector<pair<int,int>>();
             }
             if(mp.find(child) == mp.end()){
                 mp[child] = vector<pair<int,int>>();
             }
             mp[parent].push_back(make_pair(child,weight));
         }
    }
    
      // const variables which do not change in func calls.
        // not handling the leaf case right !
     ll internal(const map<int, vector<pair<int,int>>>& mp, map<int,ll>& maxPerNode, const int curNode){
         // [1] check memo
         if(maxPerNode.find(curNode) != maxPerNode.end()){
             return maxPerNode[curNode];
         }

         // [2] Exec algo
         ll childSum = 0;
         // cout << "Here curnode = " << curNode << endl;
         const vector<pair<int,int>>& myChildren = mp.at(curNode);
         // cout << "Here curnode = " << curNode << endl;
         for(const pair<int,int>& x : myChildren){
             childSum += internal(mp,maxPerNode,x.first);
         }
         
         ll curSubProblemSum = std::max(static_cast<ll>(0.0),childSum); // case of selecting no edges here!
         for(const pair<int,int>& x : myChildren){
             int myChildNode = x.first;
             int myChildEdge = x.second;
             const vector<pair<int,int>>& myGrandChildren = mp.at(myChildNode);
             ll grandChildSum = 0.0;
             ll otherChildSum = 0.0;
             for(const pair<int,int>& x : myGrandChildren){
                 grandChildSum += internal(mp,maxPerNode,x.first);
             }
             for(const pair<int,int>& x : myChildren){
                 if(x.first != myChildNode){
                     otherChildSum += internal(mp,maxPerNode,x.first);
                 }
             }
             ll oneEdgeSum = ( myChildEdge + grandChildSum ) + otherChildSum;
             curSubProblemSum = std::max(curSubProblemSum, oneEdgeSum);
         }
         
         // [3] assign memo.
         maxPerNode[curNode] = curSubProblemSum;
         // cout << "got val for curNode = " << curNode << endl;
         return maxPerNode[curNode];
     }    
};
