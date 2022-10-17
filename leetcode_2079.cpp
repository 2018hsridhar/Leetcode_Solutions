/*
URL = https://leetcode.com/problems/watering-plants/
2079. Watering Plants

*/
class Solution {
public:
    int wateringPlants(vector<int>& plants, int capacity) {
        int numSteps = 0;
        int initCap = capacity; // preferred not to change the original function param :+)
        // while loop preferred : finer-grain control in conditoinal logic exprs such as below
        int i = 0;
        /*
        // for(int i = 0; i < plants.size(); ++i){
        while(i < plants.size()){
            if(plants[i] <= capacity){
                capacity -= plants[i];
                numSteps++;
                ++i;
            } else { // you do the check ahead of time here!
                numSteps += i;          // back to river
                numSteps += (i);      // back to the plant befor the unwatered plant
                capacity = initCap; // replenish : go again to if COND in next part of loop.
            }
        }
        */
        for(int i = 0; i < plants.size(); ++i){
            if(plants[i] <= capacity){
                capacity -= plants[i];
                numSteps++;
            } else { // you do the check ahead of time here!
                numSteps += (2*i) + 1;
                capacity = initCap - plants[i]; // replenish : go again to if COND in next part of loop.
            }
        }
        return numSteps;
    }
};
