/*
URL = https://leetcode.com/problems/shortest-distance-to-target-color/submissions/
1182. Shortest Distance to Target Color

*/
class Solution {
public:
    vector<int> shortestDistanceColor(vector<int>& colors, vector<vector<int>>& queries) {
        // We have a boudn on max length too : can leverage INT_MAX for curDist!
        vector<int> shortestDists; // queries.size()); // OH extra space type of thing!
        vector<vector<int>> colorDists(3, vector<int>(colors.size()));
        for(int c = 1; c <= 3; ++c){
            int curDist = 0;
            bool hitC = false;
            // fill from left
            for(int i = 0; i < colors.size(); ++i){
                if(colors.at(i) != c){
                    if(!hitC){
                        colorDists.at(c-1).at(i) = INT_MAX;
                    } else {
                        curDist++;
                        colorDists.at(c-1).at(i) = curDist;
                    }
                } else {
                    hitC = true;
                    curDist = 0;
                    colorDists.at(c-1).at(i) = 0; // at color ; have a hit
                }
            }
            curDist = 0;
            hitC = false;
            // fill from right
            // Note : all values have been filled out from the left too!
            // So do we need our boolean state kept in the func?
            for(int i = colors.size() - 1; i >= 0; --i){
                if(colors.at(i) != c){
                    if(hitC){
                        curDist++;
                        colorDists.at(c-1).at(i) = min(colorDists.at(c-1).at(i),curDist);
                    }
                } else { 
                    hitC = true;
                    curDist = 0;
                    colorDists.at(c-1).at(i) = 0; // at color ; have a hit ( might be a NOP effectively )
                }
            }
        }
        for(const vector<int>& query : queries){
            // color -> then pos
            // auto queryForCol = colorDists.at(query.at(1),query.at(0)); 
            int myDist = colorDists[query[1]-1][query[0]]; 
            if(myDist == INT_MAX)
                myDist = -1;
            shortestDists.push_back(myDist);
        }
        return shortestDists;
    }
};
