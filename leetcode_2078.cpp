/*
URL = https://leetcode.com/problems/two-furthest-houses-with-different-colors/
2078. Two Furthest Houses With Different Colors

Complexity
Let N := len(colors)
Time = O(N)
Space = O(N) ( EXP ) O(1) ( IMP ) 
*/
class Solution {
public:
    int maxDistance(vector<int>& colors) {
        int maxDist = std::numeric_limits<int>::min();
        int n = colors.size();
        map<int,vector<int>> positions;
        for(int i = 0; i < n; ++i){
            int color = colors.at(i);
            if(positions.find(color) == positions.end()){
                positions[color] = vector<int>();
            }
            positions[color].push_back(i);
        }
        for(const auto& [k1,v1] : positions){
            for(const auto& [k2,v2] : positions){
                if(k1 != k2){
                    int leftestOne = positions[k1].at(0);
                    int rightestTwo = positions[k2].at(positions[k2].size() - 1);
                    maxDist = std::max(maxDist, abs(rightestTwo - leftestOne));
                }
            }
        }

        return maxDist;        
    }
};
