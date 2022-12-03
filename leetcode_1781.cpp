/*
1781. Sum of Beauty of All Substrings
URL = https://leetcode.com/problems/sum-of-beauty-of-all-substrings/

Complexity
Let S := len(S)
Sigma = size(lang) = 26 chars
Time = O(S^2)
Space = O(SIGMA) = O(1) ( EXP ) O(1) ( IMP ) 

Time = 45 min
You overoptimiezd for efficiency
The brute force solution is satisfactory enough TBH


*/
class Solution {
public:
    int beautySum(string s) {
        int myBeautySum = 0;
        int n = s.size();
        for(int lPtr = 0; lPtr < n; ++lPtr){
            myBeautySum += getSum(lPtr,n-1,s);
        }
        return myBeautySum;
    }

    int getSum(const int lPtr, const int rPtr, const string& s){
        int mySum = 0;
        vector<int> countChars(26,0);
        int maxFreq = std::numeric_limits<int>::min();
        char minElC = 'A';
        bool hitSecondEl = false;
        set<char> elsEnc;
        for(int i = lPtr; i <= rPtr; ++i){
            char curC = s[i];
            elsEnc.insert(curC);
            int idx = static_cast<int>(s[i] - 'a');
            countChars[idx]++;
            maxFreq = std::max(maxFreq, countChars[idx]); // after the update
            int minFreq = std::numeric_limits<int>::max();
            for(int x : countChars)
            {
                if(x > 0) {
                    minFreq = std::min(minFreq,x);
                }
            }
            mySum += (maxFreq - minFreq);
        }
        return mySum;
    }
};
