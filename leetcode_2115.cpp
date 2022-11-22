/*
14 mins to solution
2115. Find All Possible Recipes from Given Supplies
URL = https://leetcode.com/problems/find-all-possible-recipes-from-given-supplies/description/

*/
class Solution {
public:
    vector<string> findAllRecipes(vector<string>& recipes, vector<vector<string>>& ingredients, vector<string>& supplies) {
        vector<string> finalRecipes;
        set<string> finalRecipesSet;
        // start off with supplies : this is the initial set of nodes
        map<string,vector<string>> adjList;
        map<string,int> inDeg;
        for(string supply : supplies){
            adjList[supply] = vector<string>(); // may end up empty anways.
        }
        for(int i = 0; i < recipes.size(); ++i){
            string recipe = recipes.at(i);
            vector<string> ings = ingredients.at(i);
            for(string ing : ings){
                if(adjList.find(ing) == adjList.end()){
                    adjList[ing] = vector<string>();
                }
                adjList[ing].push_back(recipe);
                if(inDeg.find(recipe) == inDeg.end()){
                    inDeg[recipe] = 0;
                }
                inDeg[recipe]++;
            }
        }
        queue<string> topSort;
        for(const string supply : supplies){
            topSort.push(supply);
        }
        while(topSort.size() > 0){
            string parent = topSort.front();
            topSort.pop();
            vector<string>& children = adjList[parent];
            for(string child : children){
                inDeg[child]--;
                if(inDeg[child] == 0){
                    topSort.push(child);
                    finalRecipesSet.insert(child);
                }
            }
        }
        for(string x : finalRecipesSet){
            finalRecipes.push_back(x);
        }
        return finalRecipes;
    }
};
