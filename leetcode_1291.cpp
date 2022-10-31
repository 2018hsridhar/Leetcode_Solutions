/*
URL = https://leetcode.com/problems/sequential-digits/
1291. Sequential Digits

Once you hit to high of a number => immediately evict out of it
No need for some weird tricks : brute-forcing should suffice here as appositive.

10 mins to solution
COMPELXITY

*/
class Solution {
public:
    vector<int> sequentialDigits(int low, int high) {
        vector<int> seqDigs;
        int lowLen = radix(low);
        int highLen = radix(high);
        // Let us generate to the right !
        for(int i = lowLen; i <= highLen; ++i){
            for(int j = 1; j <= 9; ++j){
                int nextSeqEl = genNum(j,i);
                if(nextSeqEl >= low && nextSeqEl <= high){
                    seqDigs.push_back(nextSeqEl);
                } else if ( nextSeqEl < low ){
                    continue;
                } else if ( nextSeqEl > high){
                    break;
                }
            }
        }
        return seqDigs;
    }
    
private:
    
    // That length boudns check is needed too!
    int genNum(int j, int i){
        if(j + i - 1 > 9)
            return 0; // render as a NOP
        int res = 0;
        while(i >= 1){
            res += j * pow(10,i-1);
            j++;
            --i;
        }
        return res;
    }
    
    int radix(int num){
        return std::ceil(std::log10(num));
    }
    
};
