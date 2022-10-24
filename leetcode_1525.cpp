/*
URL = https://leetcode.com/problems/number-of-good-ways-to-split-a-string/
1525. Number of Good Ways to Split a String

*/
class Solution {
public:
    int numSplits(string s) {
        int numWays = 0;
        map<char,int> leftWind;
        map<char,int> rightWind;
        for(const char c : s){
            if(rightWind.find(c) == rightWind.end())
                rightWind[c] = 0;
            rightWind[c]++;
        }
        int n = s.size();
        int lPtr = 0;

        while(lPtr < n - 1){
            char letOne = s.at(lPtr);
            if(leftWind.find(letOne) == leftWind.end())
                leftWind[letOne] = 0;
            leftWind[letOne]++;
            
            rightWind[letOne]--;
            if(rightWind[letOne] == 0)
                rightWind.erase(letOne);
            
            int numDistLeft = leftWind.size();
            int numDistRight = rightWind.size();
            if(numDistLeft == numDistRight)
            {
                numWays++;
            }
            // printf("numL = [%d] \t numR = [%d]\n", numDistLeft, numDistRight);
            lPtr++;
        }
        return numWays;
    }
};
