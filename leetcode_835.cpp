/*
URL = https://leetcode.com/problems/image-overlap/
835. Image Overlap

DP/Recursion - avoid recomputing shifts which already happened

Complexity
Let M,N := dims(img1)
TIME = O(MN)
SPACE = O(MN) ( EXP ) O(MN) ( recursive -> memoized ) 

TBH -> you might just be able to do direct comparisons instead
This seems more akin to a simulation problem too

30^4 = 810,000 <= 1 MIL cmp ops
14 mins

*/
class Solution {
public:
    // You choose : img1 or img2
    int largestOverlap(vector<vector<int>>& img1, vector<vector<int>>& img2) {
        int myLargest = 0;
        int m = img1.size();
        int n = img1[0].size();
        vector<vector<int>> DP(m,vector<int>(n,0));
        // its is a comparison shift here
        for(int sR = 0; sR < m; ++sR){
            for(int sC = 0; sC < n; ++sC){
                // int curOneCountOne = 0;
                // int curOneCountTwo = 0;
                // int curOneCountThree = 0;
                // int curOneCountFour = 0;
                vector<int> curCounts = vector<int>(4,0);
                for(int i = 0; i < m; ++i){
                    for(int j = 0; j < n; ++j){
                        // SLIDE TO BOTTOM RIGHT
                        if(i-sR >= 0 && j-sC >= 0){
                            if(img2.at(i).at(j) == 1 && img2.at(i).at(j) == img1.at(i-sR).at(j-sC)){
                                curCounts.at(0)++;
                            }
                        }
                        // SLIDE TO UPPER LEFT
                        if(i+sR < m && j+sC < n){
                            if(img2.at(i).at(j) == 1 && img2.at(i).at(j) == img1.at(i+sR).at(j+sC)){
                                curCounts.at(1)++;
                            }
                        }
                        // SLIDE TO BOTTOM LEFT
                         if(i-sR >= 0 && j+sC < n){
                            if(img2.at(i).at(j) == 1 && img2.at(i).at(j) == img1.at(i-sR).at(j+sC)){
                                curCounts.at(2)++;
                            }
                        }
                        // SLIDE TO UPPER RIGHT
                        if(i+sR < m && j-sC >= 0){
                            if(img2.at(i).at(j) == 1 && img2.at(i).at(j) == img1.at(i+sR).at(j-sC)){
                                curCounts.at(3)++;
                            }
                        }
                    }
                }
                myLargest = max(*std::max_element(curCounts.begin(), curCounts.end()),myLargest);
            }
        }
        return myLargest;
    }
};
