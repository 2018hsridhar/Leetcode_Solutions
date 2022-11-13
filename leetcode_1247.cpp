/*
URL = https://leetcode.com/problems/minimum-swaps-to-make-strings-equal/description/
1247. Minimum Swaps to Make Strings Equal

Complexity
Let N := len(S1) = len(s2)
TIME = O(N)
SPACE = O(1) ( EX & IMP ) 

45 mins to solution
*/
class Solution {
public:
    int minimumSwap(string s1, string s2) {
        int minSwapsNeeded = 0;
        int n = s1.size();
        int freqCaseOne = 0;
        int freqCaseTwo = 0;
        for(int i = 0; i < n; ++i){
            char firstEl = s1.at(i);
            char secondEl = s2.at(i);
            if(firstEl != secondEl){
                if(firstEl == 'x' && secondEl == 'y'){
                    freqCaseOne++;
                } else {
                    freqCaseTwo++;
                }
            }
        }
        if(freqCaseOne % 2 == 1 && freqCaseTwo % 2 == 1){
            minSwapsNeeded += 2;
            freqCaseOne--;
            freqCaseTwo--;
            minSwapsNeeded += (freqCaseOne / 2) + (freqCaseTwo / 2);
        } else if ( freqCaseOne % 2 == 0 && freqCaseTwo % 2 == 0){
            minSwapsNeeded = (freqCaseOne / 2) + (freqCaseTwo / 2);
        } else {
            minSwapsNeeded = -1;
        }
        
        return minSwapsNeeded;
    }
};
