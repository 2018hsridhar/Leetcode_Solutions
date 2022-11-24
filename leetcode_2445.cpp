/*
2445. Number of Nodes With Value One
URL = https://leetcode.com/problems/number-of-nodes-with-value-one/description/

Complexity
Let V := #-nodes in the bianry tree = N
Time = O(N)
Space = O(N) ( EXP ) O(N) ( IMP ) 
*/
class Solution {
public:
    int numberOfNodes(int n, vector<int>& queries) {
        set<int> inQuery;
        int i = 0;
        map<int,int> queryCount;
        for(int query : queries){
            queryCount[query]++;
        }
        for(auto [k,v] : queryCount){
            if(v % 2 == 1){
                inQuery.emplace(k); // does not account for frequency : take note of this!
            }
        }
        int rootNode = 1;
        char curState = 'B';
        int numNodes = dfs(inQuery,rootNode, n,curState);
        return numNodes;
    }

private:
    int dfs(set<int>& inQuery, int curNode, int n, char curState){
        int leftChild = ( curNode * 2 );
        int rightChild = ( curNode * 2 ) + 1;
        char childState = curState;
        int curCountRed = 0;
        if(1 <= curNode && curNode <= n){
            if(inQuery.find(curNode) != inQuery.end()){
                if(curState == 'B'){
                    childState = 'R';
                    curCountRed++; // executed flip to red
                } else if ( curState == 'R'){
                    childState = 'B'; // executed flip to blue
                }
            } else {
                if(curState == 'R'){
                    curCountRed++;
                }
            }
            curCountRed += dfs(inQuery,leftChild,n,childState);
            curCountRed += dfs(inQuery,rightChild,n,childState);
            return curCountRed;
        } else {
            return 0;
        }
    }
};
