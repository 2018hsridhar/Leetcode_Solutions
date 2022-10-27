/*
URL = https://leetcode.com/problems/rings-and-rods/
2103. Rings and Rods
*/
class Solution {
public:
    int countPoints(string rings) {
        int myCount = 0;
        const char* c_arr = rings.c_str(); // JAVA syntax differs : c++ wants [] after identifier!
        int n = rings.size();
        set<char> blueRings;
        set<char> redRings;
        set<char> greenRings;
        for(int i = 0; i < n; i += 2){
            char myColor = *(c_arr+i);
            char ringIdx = *(c_arr+i+1);
            switch(myColor){
                case 'B':
                    blueRings.insert(ringIdx);
                    break;
                case 'R':
                    redRings.insert(ringIdx);
                    break;
                case 'G':
                    greenRings.insert(ringIdx);
                    break;
                default: // TBH, switch-case does make exception throwing somewhat easier too!
                    printf("Should throw an exception to caller\n");
                    break;
            }
        }
        for(char c : blueRings){
            if(redRings.find(c) != redRings.end() && greenRings.find(c) != greenRings.end()){
                myCount++;
            }
        }
        return myCount;
    }
};
