/*
851. Loud and Rich
URL =  https://leetcode.com/problems/loud-and-rich/

Complexity
Let V = N = #-nodes
Let E = #-edges
TIME = O(V+E)
SPACE = O(V) ( IMP ) O(V + E ) ( EXP ) [ adjList size ] 
*/
class Solution {
public:
    vector<int> loudAndRich(vector<vector<int>>& richer, vector<int>& quiet) {
        int n = quiet.size();
        vector<pair<int,int>> results(n,make_pair(-1,-1)); // all initialized to state of unsolved here
        unordered_map<int,vector<int>> adjList;
        for(int i = 0; i < n; ++i){
            adjList[i] = vector<int>();
        }
        for(const vector<int>& edge : richer){
            int parent = edge.at(0);
            int child = edge.at(1);
            adjList[child].push_back(parent);
        }
        // Logically-consistent directed graph : eschew visited set
        for(int i = 0; i < n; ++i){
            pair<int,int> answer = dfs(i,adjList,results, quiet);
            // printf("For node = %d \t ans = %d\n", i, answer);
            results.at(i).first = answer.first;
            results.at(i).second = answer.second;
        }
        vector<int> trueAnswers(n,0);
        for(int i = 0; i < n; ++i){
            trueAnswers.at(i) = results.at(i).first;
        }
        return trueAnswers;
    }

private:
    // Mistake : quitest node, not quietest value. Must get the node with min val here!
    // first = node, second == nodeQuitenessValue
    pair<int,int> dfs(int childNode, unordered_map<int,vector<int>>& adjList, vector<pair<int,int>>& answers, vector<int>& quiet){
        int quitestNode = childNode; // default value : own node
        int quitestVal = quiet.at(childNode);
        if(answers.at(childNode).first != -1){
            quitestNode = answers.at(childNode).first;
            quitestVal = answers.at(childNode).second;
        } else {
            // printf("Here node = %d\n", childNode);
            vector<int> richParents = adjList.at(childNode);
            for(int richParent : richParents){
                pair<int,int> dfsPair = dfs(richParent,adjList,answers,quiet);
                if(dfsPair.second <= quitestVal){
                    quitestVal = dfsPair.second; // no .at() members for pairs GAAAH vector preference
                    quitestNode = dfsPair.first;
                }
            }
        }
        answers.at(childNode).first = quitestNode;
        answers.at(childNode).second = quitestVal;
        return answers.at(childNode);
    }
};
