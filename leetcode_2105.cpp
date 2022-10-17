/*
2105. Watering Plants II
URL = https://leetcode.com/problems/watering-plants-ii/submissions/
*/
class Solution {
public:
    int minimumRefill(vector<int>& plants, int capacityA, int capacityB) {
        int numRefills = 0;
        int n = plants.size();
        int lPtr = 0;
        int rPtr = n - 1; // classic bug of getting n here
        int capA = capacityA;
        int capB = capacityB;
        // Challenge : make sure to race the point at the right speed
        // Idea : race one individually until they water as much as they can?
        // They begin SIMULTANEOUSLY ( you do not race them from the start too ) 
        // They refill instantaneously : no distance computations here!
        while(lPtr <= rPtr){
            if(lPtr == rPtr){
                if(capA >= capB){ // in same case, Alice waters
                    if(plants[lPtr] > capA){
                        numRefills++;
                    }
                    break;
                } else {
                    if(plants[rPtr] > capB){
                        numRefills++;
                    }
                    break;
                }
            } else {
                // Simulate Alice
                if(plants[lPtr] <= capA ){
                    capA -= plants[lPtr];
                } else if ( plants[lPtr] > capA) {
                    capA = capacityA;
                    capA -= plants[lPtr];
                    numRefills++;
                }
                // Simulate Bob
                if(plants[rPtr] <= capB ){
                    capB -= plants[rPtr];
                } else if ( plants[rPtr] > capB) {
                    capB = capacityB;
                    capB -= plants[rPtr];
                    numRefills++;
                }
                ++lPtr;
                --rPtr;
            }
        }
        return numRefills;
    }
};
