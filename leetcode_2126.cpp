/*
2126. Destroying Asteroids
URL = https://leetcode.com/problems/destroying-asteroids/
*/
class Solution {
public:
    bool asteroidsDestroyed(int mass, vector<int>& asteroids) {
        bool canDestroy = true;
        long runningMass = mass;
        std::sort(asteroids.begin(), asteroids.end());
        for(const auto& x : asteroids ){
            if(runningMass >= x){
                runningMass += x;
            } else {
                canDestroy = false;
                break;
            }
        }
        return canDestroy;
    }
};
