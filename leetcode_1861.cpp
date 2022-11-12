/*
URL = https://leetcode.com/problems/rotating-the-box/
1861. Rotating the Box

COMPLEXITY
Let M := #-rows, N := #-cols
TIME = O(MN)
SPACE = O(MN) ( EXP ) O(1) ( IMP ) 

2 ptrs : restart your read index and write index in approach.
15 mins to a working solution
No cases -> passed :-)
*/
class Solution {
public:
    vector<vector<char>> rotateTheBox(vector<vector<char>>& box) {
        int m = box.size();
        int n = box[0].size();
        vector<vector<char>> rotated(n,vector<char>(m,'.')); // default to empty cell `.` here.
        for(int i = 0; i < m; ++i){ // each row
            int readPtr = n-1;
            int writePtr = n-1;
            // obstacles do not move : copy them if need be!
            // nested loops or conditions needed here!
            while(readPtr >= 0 && writePtr >= 0){
                if(box.at(i).at(readPtr) == '.'){
                    readPtr--;
                } else if ( box.at(i).at(readPtr) == '*'){ // obstacle stuck in pos after rotation.
                    writePtr = readPtr; // reset read-write op
                    rotated.at(writePtr).at(m-1-i) = '*';
                    readPtr--;
                    writePtr--;
                } else if ( box.at(i).at(readPtr) == '#'){
                    rotated.at(writePtr).at(m-1-i) = '#';
                    --readPtr;
                    --writePtr;
                }
            }
        }
        return rotated;
    }
};
